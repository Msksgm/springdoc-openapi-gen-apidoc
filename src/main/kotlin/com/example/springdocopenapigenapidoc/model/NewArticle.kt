package com.example.springdocopenapigenapidoc.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

/**
 * NewArticle
 *
 * 新規記事のモデル
 *
 * @property title タイトル
 * @property description 説明
 * @property body 本文
 */
data class NewArticle(
    @field:Size(max = 32)
    @field:NotBlank
    @Schema(example = "new-article-title", required = true, description = "新規記事のタイトル")
    @field:JsonProperty("title", required = true) val title: String? = null,

    @field:Size(max = 1024)
    @field:NotBlank
    @Schema(example = "new-article-description", required = true, description = "新規記事の説明")
    @field:JsonProperty("description", required = true) val description: String? = null,

    @field:Size(max = 2048)
    @field:NotBlank
    @Schema(example = "new-article-body", required = true, description = "新規記事の説明")
    @field:JsonProperty("body", required = true) val body: String? = null,
)
