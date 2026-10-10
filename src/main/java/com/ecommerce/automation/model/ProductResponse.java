package com.ecommerce.automation.model;

import java.math.BigDecimal;

public record ProductResponse(
        String id,
        String name,
        BigDecimal price
) {
}
