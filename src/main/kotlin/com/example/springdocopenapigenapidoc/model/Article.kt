package com.example.springdocopenapigenapidoc.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size

/**
 * Article
 *
 * 記事のモデル
 *
 * @property slug スラッグ
 * @property title タイトル
 * @property description 説明
 * @property body 本文
 */
data class Article(
    @get:Size(max = 32)
    @Schema(example = "article-slug", required = true, description = "")
    @field:JsonProperty("slug", required = true) val slug: String,

    @get:Size(max = 64)
    @Schema(example = "article-title", required = true, description = "")
    @field:JsonProperty("title", required = true) val title: String,

    @get:Size(max = 1024)
    @Schema(example = "article-description", required = true, description = "")
    @field:JsonProperty("description", required = true) val description: String,

    @get:Size(max = 4096)
    @Schema(example = "article-body", required = true, description = "")
    @field:JsonProperty("body", required = true) val body: String
)
