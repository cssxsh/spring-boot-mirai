package xyz.cssxsh.mirai.spring

import net.mamoe.mirai.console.*
import net.mamoe.mirai.console.extensions.*
import net.mamoe.mirai.console.plugin.*
import net.mamoe.mirai.console.plugin.jvm.*
import org.slf4j.*
import org.springframework.boot.autoconfigure.*
import org.springframework.util.*
import java.net.*
import java.security.*
import java.util.*

public object SpringBootMiraiStartupExtension :
    SecureClassLoader("spring-boot-mirai", MiraiConsole::class.java.classLoader), PostStartupExtension {

    public val version: String get() = "1.0.0"

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PublishedApi
    internal val classLoaders: MutableMap<String, ClassLoader> = hashMapOf(
        "org.springframework" to SpringBootApplication::class.java.classLoader,
        "org.springdoc" to SpringBootApplication::class.java.classLoader,
        "xyz.cssxsh.mirai.spring" to SpringBootMiraiStartupExtension::class.java.classLoader
    )

    private lateinit var thread: Thread

    override fun invoke() {
        for (plugin in PluginManager.plugins) {
            if (plugin !is JvmPlugin) continue
            val classLoader = plugin::class.java.classLoader

            val imports = classLoader.getResource("META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports")
                ?: continue
            if (imports.readText().lines().isEmpty()) continue

            val dependencies = classLoader.getResource("META-INF/mirai-console-plugin/dependencies-private.txt")
                ?: continue
            if (dependencies.readText().lines().any { it.startsWith("com.ali")  }) continue

            classLoaders[plugin::class.java.packageName] = classLoader
        }

        thread = Thread(SpringBootMiraiApplication, "spring-boot-mirai")
        thread.contextClassLoader = this
        thread.uncaughtExceptionHandler = Thread.UncaughtExceptionHandler { _, cause -> logger.error("uncaught exception", cause) }
        thread.start()
    }

    init {
        @Suppress("INVISIBLE_MEMBER")
        @OptIn(ConsoleFrontEndImplementation::class)
        MiraiConsoleImplementation.getBridge()
            .globalComponentStorage.contributeConsole(PostStartupExtension, this)
        logger.info("PostStartupExtension contributed!")
    }

    override fun loadClass(name: String): Class<*> {
        if (name.startsWith("com.ali")) throw IllegalArgumentException("ali! $name")
        for ((prefix, classLoader) in classLoaders) {
            if (name.startsWith(prefix).not()) continue
            val oc = ClassUtils.overrideThreadContextClassLoader(classLoader)
            return try {
                classLoader.loadClass(name)
            } catch (_: ClassNotFoundException) {
                continue
            } finally {
                ClassUtils.overrideThreadContextClassLoader(oc)
            }
        }
        return super.loadClass(name)
    }

    override fun getResource(name: String): URL? {
        if (name.startsWith("com/ali")) throw IllegalArgumentException("ali! $name")
        val used = hashSetOf<String>()
        for ((_, classLoader) in classLoaders) {
            if (used.add(classLoader.name).not()) continue
            val oc = ClassUtils.overrideThreadContextClassLoader(classLoader)
            return try {
                classLoader.getResource(name) ?: continue
            } finally {
                ClassUtils.overrideThreadContextClassLoader(oc)
            }
        }
        return super.getResource(name)
    }

    override fun getResources(name: String): Enumeration<URL> {
        if (name.startsWith("com/ali")) throw IllegalArgumentException("ali! $name")
        return object : Enumeration<URL> {
            val used = hashSetOf<String>()
            val iterator = iterator<URL> {
                for ((_, classLoader) in classLoaders) {
                    if (used.add(classLoader.name).not()) continue
                    val oc = ClassUtils.overrideThreadContextClassLoader(classLoader)
                    val enumeration = try {
                        classLoader.getResources(name) ?: continue
                    } finally {
                        ClassUtils.overrideThreadContextClassLoader(oc)
                    }
                    yieldAll(enumeration.asIterator())
                }
            }

            override fun nextElement(): URL = iterator.next()

            override fun hasMoreElements(): Boolean = iterator.hasNext()

            override fun asIterator(): MutableIterator<URL> = iterator as MutableIterator<URL>
        }
    }
}