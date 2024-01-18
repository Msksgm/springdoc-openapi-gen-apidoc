package com.example.springdocopenapigenapidoc.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull

/**
 * 新規記事作成のリクエストモデル
 *
 * @property article
 */
data class NewArticleRequest(
    @field:Valid
    @field:NotNull
    @Schema(required = true, description = "記事を作成する時のリクエスト")
    @field:JsonProperty("article", required = true) val article: NewArticle? = null
)
