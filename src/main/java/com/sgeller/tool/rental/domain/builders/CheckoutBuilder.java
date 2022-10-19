package com.sgeller.tool.rental.domain.builders;

import com.sgeller.tool.rental.domain.Checkout;

import java.time.LocalDate;

public class CheckoutBuilder implements Builder<Checkout, CheckoutBuilder> {

    private String toolCode;
    private Integer rentalDayCount;
    private Integer discountPercent;
    private LocalDate checkoutDate;

    public CheckoutBuilder withToolCode(String toolCode) {
        this.toolCode = toolCode;
        return this;
    }

    public CheckoutBuilder withRentalDayCount(Integer rentalDayCount) {
        this.rentalDayCount = rentalDayCount;
        return this;
    }

    public CheckoutBuilder withDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
        return this;
    }

    public CheckoutBuilder withCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
        return this;
    }

    @Override
    public Checkout build() {
        return new Checkout(this.toolCode, this.rentalDayCount, this.discountPercent, this.checkoutDate);
    }
}
