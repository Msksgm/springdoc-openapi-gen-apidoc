package com.example.springdocopenapigenapidoc.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

/**
 * GenericErrorModel
 *
 * エラーレスポンスを返す時に利用するモデル
 *
 * @property errors
 */
data class GenericErrorModel(
    @field:Valid
    @Schema(required = true, description = "")
    @field:JsonProperty("errors", required = true) val errors: GenericErrorModelErrors
)
