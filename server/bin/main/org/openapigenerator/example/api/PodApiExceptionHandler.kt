package org.openapigenerator.example.api

import org.openapigenerator.example.models.AutomationError
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
@ResponseBody
class PodApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleNotFound(ex: IllegalArgumentException): AutomationError {
        val msgs: List<String> = listOf(ex.message ?: "Item not found")
        return AutomationError(msgs = msgs, errorCode = HttpStatus.NOT_FOUND.value())
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleBadRequest(ex: MethodArgumentNotValidException): AutomationError {
        val errors: List<String> = ex.bindingResult.fieldErrors.map {
            "Field '${it.field}': '${it.rejectedValue}' (${it.defaultMessage})"
        }.distinct()
        return AutomationError(msgs = errors, errorCode = HttpStatus.BAD_REQUEST.value())
    }
}
