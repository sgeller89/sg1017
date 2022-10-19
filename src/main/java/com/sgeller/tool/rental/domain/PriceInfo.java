package com.sgeller.tool.rental.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class PriceInfo {

    private final BigDecimal preDiscountCharge;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;

    public PriceInfo(BigDecimal preDiscountCharge, BigDecimal discountAmount) {
        this.preDiscountCharge = Optional.ofNullable(preDiscountCharge)
                .map(amount -> amount.setScale(2, RoundingMode.HALF_UP))
                .orElse(BigDecimal.ZERO);
        this.discountAmount = Optional.ofNullable(discountAmount)
                .map(amount -> amount.setScale(2, RoundingMode.HALF_UP))
                .orElse(BigDecimal.ZERO);
        this.finalCharge = this.preDiscountCharge.subtract(this.discountAmount)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getPreDiscountCharge() {
        return this.preDiscountCharge;
    }

    public BigDecimal getDiscountAmount() {
        return this.discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return this.finalCharge;
    }
}
