package com.example.springdocopenapigenapidoc

import com.example.springdocopenapigenapidoc.model.GenericErrorModel
import com.example.springdocopenapigenapidoc.model.GenericErrorModelErrors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * ErrorHandlerController
 *
 * グローバルなエラーハンドリングを実装するコントローラクラス
 *
 * 予期しない例外が発生した際に、以下を実施する。
 * - 秘密情報をレスポンスに含めないためのエラーハンドリング
 * - 原因調査のためのログ出力
 *
 */
@RestControllerAdvice
class ExceptionHandleController {
    /**
     * methodArgumentNotValidExceptionHandler
     *
     * リクエストのバリデーションエラーが発生した場合に発生させるエラーレスポンスを作成する関数
     *
     * @param e
     * @return 400 エラーのレスポンス
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<GenericErrorModel> {
        // エラーメッセージ本文の作成。FieldError の場合は、フィールド名を含める
        val messages = e.bindingResult.allErrors.map {
            when (it) {
                is FieldError -> "${it.field}は${it.defaultMessage}"
                else -> it.defaultMessage.toString()
            }
        }
        return ResponseEntity<GenericErrorModel>(
            GenericErrorModel(
                errors = GenericErrorModelErrors(
                    body = messages
                ),
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    /**
     * httpMessageNotReadableExceptionHandler
     *
     * API スキーマが想定していないリクエストだった場合に発生させるエラーレスポンスを作成する関数
     *
     * @param e
     * @return 400 エラーのレスポンス
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableExceptionHandler(e: HttpMessageNotReadableException): ResponseEntity<GenericErrorModel> {
        // 本番環境ではここで、エラーログをだす
        println(e.message)
        return ResponseEntity<GenericErrorModel>(
            GenericErrorModel(
                errors = GenericErrorModelErrors(
                    body = listOf("API が想定していない形式または型のリクエストが送られました。リクエストのパラメータを再度確認してください")
                ),
            ),
            HttpStatus.BAD_REQUEST
        )
    }
}
