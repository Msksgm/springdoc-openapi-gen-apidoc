package com.example.springdocopenapigenapidoc.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

/**
 * 単一記事のレスポンスモデル
 *
 * @property article
 */
data class SingleArticleResponse(
    @field:Valid
    @Schema(required = true, description = "新規記事")
    @field:JsonProperty("article", required = true) val article: Article
)
