package com.example.springdocopenapigenapidoc.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 * GenericErrorModelErrors
 *
 * エラーレスポンスの詳細を記述するモデル
 *
 * @property body エラーの詳細
 */
data class GenericErrorModelErrors(
    @Schema(required = true, description = "")
    @field:JsonProperty("body", required = true) val body: List<String>
)
