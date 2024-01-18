package com.example.springdocopenapigenapidoc

import com.example.springdocopenapigenapidoc.model.Article
import com.example.springdocopenapigenapidoc.model.GenericErrorModel
import com.example.springdocopenapigenapidoc.model.GenericErrorModelErrors
import com.example.springdocopenapigenapidoc.model.NewArticleRequest
import com.example.springdocopenapigenapidoc.model.SingleArticleResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * SpringDoc のサンプルアプリ用 ArticleController
 */
@RestController
@Tag(name = "articles", description = "記事に関するエンドポイント")
@Validated
class ArticleController {
    /**
     * 記事作成エンドポイント
     *
     * @param newArticleRequest
     * @return
     */
    @Operation(
        summary = "記事作成",
        operationId = "createArticle",
        description = "記事を作成します。",
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "OK",
                content = [
                    Content(
                        schema = Schema(implementation = SingleArticleResponse::class),
                        examples = [
                            ExampleObject(
                                name = "OK",
                                value = """
                                {
                                    "article": {
                                        "slug": "new-slug",
                                        "title": "new-title",
                                        "body": "new-body",
                                        "description": "new-description"
                                    }
                                }
                            """
                            )
                        ]
                    )
                ]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Validation Error",
                content = [
                    Content(
                        schema = Schema(implementation = GenericErrorModel::class),
                        examples = [
                            ExampleObject(
                                name = "ValidationError",
                                value = """
                                    {
                                        "errors": {
                                            "body": [
                                                "article.titleは0文字以上32文字以下です",
                                                "article.bodyは必須です"
                                            ]
                                        }
                                    }
                                """
                            )
                        ]
                    )
                ]
            ),
            ApiResponse(
                responseCode = "409",
                description = "Already Exists",
                content = [
                    Content(
                        schema = Schema(implementation = GenericErrorModel::class),
                        examples = [
                            ExampleObject(
                                name = "AlreadyExists",
                                value = """
                                    {
                                        "errors": {
                                            "body": [
                                                "記事はすでに存在しています"
                                            ]
                                        }
                                    }
                                """
                            )
                        ]
                    )
                ]
            )
        ]
    )
    @PostMapping(
        value = ["/articles"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun createArticle(
        @Parameter(
            description = "新規記事作成",
            required = true
        )
        // Sample プロジェクトのため、Suppress を利用。本番環境では削除する
        @Valid @RequestBody @Suppress("UnusedParameter") newArticleRequest: NewArticleRequest,
    ): ResponseEntity<SingleArticleResponse> {
        return ResponseEntity(
            SingleArticleResponse(
                article = Article(
                    slug = "new-slug",
                    title = "new-title",
                    body = "new-body",
                    description = "new-description"
                )
            ),
            HttpStatus.OK
        )
    }

    /**
     * 記事作成エンドポイントに発生した例外を表現するクラス
     *
     * @property error エラー
     */
    data class CreateArticleErrorException(val error: Error) : Throwable()

    /**
     * 記事作成エンドポイントの例外をハンドリングする関数
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CreateArticleErrorException::class)
    fun onCreateArticleErrorException(e: CreateArticleErrorException): ResponseEntity<GenericErrorModel> {
        // Sample プロジェクトのため、AlreadyExists 以外は BadRequest としてエラーレスポンスを返している
        if (e.error.toString() == "AlreadyExists") {
            return ResponseEntity(
                GenericErrorModel(
                    errors = GenericErrorModelErrors(
                        body = listOf(e.error.toString())
                    )
                ),
                HttpStatus.CONFLICT
            )
        }
        return ResponseEntity(
            GenericErrorModel(
                errors = GenericErrorModelErrors(
                    body = listOf(e.error.toString())
                )
            ),
            HttpStatus.BAD_REQUEST
        )
    }
}
