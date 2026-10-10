package com.ecommerce.automation.dataproviders;

public record LoginTestData(
        String testCaseId,
        String email,
        String password,
        String expectedResult
) {
}
