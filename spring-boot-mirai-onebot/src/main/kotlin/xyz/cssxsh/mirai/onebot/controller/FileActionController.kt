package xyz.cssxsh.mirai.onebot.controller

import kotlinx.serialization.json.*
import org.springframework.web.bind.annotation.*

/**
 * [file actions](https://12.onebot.dev/interface/file/actions/)
 */
@RestController
public class FileActionController {
    @RequestMapping("/upload_file")
    public fun uploadFile(): JsonObject = JsonObject(emptyMap())

    @RequestMapping("/upload_file_fragmented")
    public fun uploadFileFragmented(): JsonObject = JsonObject(emptyMap())

    @RequestMapping("/get_file")
    public fun getFile(fileId: String, type: String): JsonObject = JsonObject(emptyMap())

    @RequestMapping("/get_file_fragmented")
    public fun getFileFragmented(): JsonObject = JsonObject(emptyMap())
}