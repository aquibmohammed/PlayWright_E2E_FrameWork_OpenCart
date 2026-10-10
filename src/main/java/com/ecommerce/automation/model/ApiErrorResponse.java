package com.ecommerce.automation.model;

public record ApiErrorResponse(
        String code,
        String message
) {
}
