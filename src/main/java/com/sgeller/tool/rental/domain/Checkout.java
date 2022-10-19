package com.sgeller.tool.rental.domain;

import java.time.LocalDate;

public class Checkout {

    private final String toolCode;
    private final Integer rentalDayCount;
    private final Integer discountPercent;
    private final LocalDate checkoutDate;

    public Checkout(String toolCode, Integer rentalDayCount, Integer discountPercent, LocalDate checkoutDate) {
        this.toolCode = toolCode;
        this.rentalDayCount = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
    }

    public String getToolCode() {
        return this.toolCode;
    }

    public Integer getRentalDayCount() {
        return this.rentalDayCount;
    }

    public Integer getDiscountPercent() {
        return this.discountPercent;
    }

    public LocalDate getCheckoutDate() {
        return this.checkoutDate;
    }
}
