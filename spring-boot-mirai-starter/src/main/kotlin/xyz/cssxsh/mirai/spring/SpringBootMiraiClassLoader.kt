package xyz.cssxsh.mirai.spring

import net.mamoe.mirai.console.plugin.*
import net.mamoe.mirai.console.plugin.jvm.*
import org.springframework.util.*
import java.io.*
import java.net.*
import java.security.*
import java.util.*

public class SpringBootMiraiClassLoader(starter: JvmPlugin) :
    SecureClassLoader("spring-boot-mirai", starter::class.java.classLoader) {
    private val classLoaders: Map<String, ClassLoader> = buildMap {
        put(starter::class.java.packageName, starter::class.java.classLoader)
        for (plugin in PluginManager.plugins) {
            if (plugin !is JvmPlugin) continue
            if (plugin.description.dependencies.none { it.id == starter.id }) continue
            val classLoader = plugin::class.java.classLoader
            val dependencies = classLoader.getResource("META-INF/mirai-console-plugin/dependencies-private.txt")
                ?: throw FileNotFoundException("dependencies-private.txt")
            if (dependencies.readText().lines().any { it.startsWith("com.ali")  }) {
                throw IllegalArgumentException("ali! $dependencies")
            }
            put(plugin::class.java.packageName, classLoader)
        }
    }

    override fun loadClass(name: String): Class<*> {
        if (name.startsWith("com.ali")) return super.loadClass(name)
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
        if (name.startsWith("com/ali")) return super.getResource(name)
        for ((_, classLoader) in classLoaders) {
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
        if (name.startsWith("com/ali")) return super.getResources(name)
        return object : Enumeration<URL> {
            val iterator = iterator<URL> {
                for ((_, classLoader) in classLoaders) {
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