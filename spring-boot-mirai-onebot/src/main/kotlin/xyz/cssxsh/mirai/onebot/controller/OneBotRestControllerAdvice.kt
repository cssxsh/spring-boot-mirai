package xyz.cssxsh.mirai.onebot.controller

import kotlinx.serialization.json.*
import org.springframework.core.*
import org.springframework.http.*
import org.springframework.http.converter.*
import org.springframework.http.server.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.*
import org.springframework.web.servlet.mvc.method.annotation.*
import xyz.cssxsh.mirai.onebot.model.action.*
import java.net.SocketException
import java.sql.SQLException
import java.util.concurrent.CancellationException

@RestControllerAdvice
public class OneBotRestControllerAdvice : ResponseBodyAdvice<Any> {

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): OneBotActionResponse<*> {
        return when (body) {
            !is OneBotActionResponse<*> -> OneBotActionResponse(
                status = OneBotActionStatus.OK,
                retcode = 0,
                message = "",
                data = body ?: JsonNull
            )
            else -> body
        }
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RestClientResponseException::class)
    public fun handleResponseException(exception: RestClientResponseException): OneBotActionResponse<JsonElement> {
        val retcode = when (exception.statusCode) {
            HttpStatus.BAD_REQUEST -> 1_0001L
            HttpStatus.FORBIDDEN -> 1_0001L
            HttpStatus.NOT_FOUND -> 1_0002L
            HttpStatus.METHOD_NOT_ALLOWED -> 1_0002L
            HttpStatus.UNAUTHORIZED -> 1_0003L
            HttpStatus.NOT_ACCEPTABLE -> 1_0004L
            else -> 1_0000L
        }
        return OneBotActionResponse(
            status = OneBotActionStatus.FAILED,
            retcode = retcode,
            message = exception.message ?: exception.statusText,
            data = JsonNull
        )
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoSuchMethodException::class)
    public fun handleResponseException(exception: NoSuchMethodException): OneBotActionResponse<JsonElement> {
        return OneBotActionResponse(
            status = OneBotActionStatus.FAILED,
            retcode = 1_0002,
            message = exception.message ?: "NoSuchMethodException",
            data = JsonNull
        )
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler
    public fun handleRequestError(exception: Exception): OneBotActionResponse<JsonElement> {
        return OneBotActionResponse(
            status = OneBotActionStatus.FAILED,
            retcode = 2_0002,
            message = exception.message ?: "Internal Handler Error",
            data = JsonNull
        )
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SQLException::class)
    public fun handleExecutionError(exception: SQLException): OneBotActionResponse<JsonElement> {
        return OneBotActionResponse(
            status = OneBotActionStatus.FAILED,
            retcode = 3_1000,
            message = exception.message ?: exception.sqlState ?: "SQLException",
            data = JsonNull
        )
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(FileSystemException::class)
    public fun handleExecutionError(exception: FileSystemException): OneBotActionResponse<JsonElement> {
        return OneBotActionResponse(
            status = OneBotActionStatus.FAILED,
            retcode = 3_2000,
            message = exception.message ?: exception.reason ?: exception.file.path,
            data = JsonNull
        )
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SocketException::class)
    public fun handleExecutionError(exception: SocketException): OneBotActionResponse<JsonElement> {
        return OneBotActionResponse(
            status = OneBotActionStatus.FAILED,
            retcode = 3_3000,
            message = exception.message ?: "SocketException",
            data = JsonNull
        )
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CancellationException::class)
    public fun handleExecutionError(exception: CancellationException): OneBotActionResponse<JsonElement> {
        return OneBotActionResponse(
            status = OneBotActionStatus.FAILED,
            retcode = 3_6000,
            message = exception.message ?: "CancellationException",
            data = JsonNull
        )
    }
}