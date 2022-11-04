package xyz.cssxsh.mirai.onebot.controller

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
                status = "ok",
                retcode = 0,
                message = "",
                data = body
            )
            else -> body
        }
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RestClientResponseException::class)
    public fun handleResponseException(exception: RestClientResponseException): OneBotActionResponse<*> {
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
            status = "failed",
            retcode = retcode,
            message = exception.message ?: exception.statusText,
            data = null
        )
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler
    public fun handleRequestError(exception: Exception): OneBotActionResponse<*> {
        exception.printStackTrace()
        return OneBotActionResponse(
            status = "failed",
            retcode = 2_0002,
            message = exception.message ?: "Internal Handler Error",
            data = null
        )
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SQLException::class)
    public fun handleExecutionError(exception: SQLException): OneBotActionResponse<*> {
        return OneBotActionResponse(
            status = "failed",
            retcode = 3_1000,
            message = exception.message ?: exception.sqlState ?: "SQLException",
            data = null
        )
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(FileSystemException::class)
    public fun handleExecutionError(exception: FileSystemException): OneBotActionResponse<*> {
        return OneBotActionResponse(
            status = "failed",
            retcode = 3_2000,
            message = exception.message ?: exception.reason ?: exception.file.path,
            data = null
        )
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SocketException::class)
    public fun handleExecutionError(exception: SocketException): OneBotActionResponse<*> {
        return OneBotActionResponse(
            status = "failed",
            retcode = 3_3000,
            message = exception.message ?: "SocketException",
            data = null
        )
    }

    /**
     * [style](https://12.onebot.dev/connect/data-protocol/action-response/#_1)
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CancellationException::class)
    public fun handleExecutionError(exception: CancellationException): OneBotActionResponse<*> {
        return OneBotActionResponse(
            status = "failed",
            retcode = 3_6000,
            message = exception.message ?: "CancellationException",
            data = null
        )
    }
}