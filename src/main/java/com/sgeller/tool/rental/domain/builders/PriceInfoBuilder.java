package com.sgeller.tool.rental.domain.builders;

import com.sgeller.tool.rental.domain.PriceInfo;

import java.math.BigDecimal;

public class PriceInfoBuilder implements Builder<PriceInfo, PriceInfoBuilder> {

    private BigDecimal preDiscountCharge;
    private BigDecimal discountAmount;

    public PriceInfoBuilder withPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
        return this;
    }

    public PriceInfoBuilder withDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    @Override
    public PriceInfo build() {
        return new PriceInfo(this.preDiscountCharge, this.discountAmount);
    }
}
