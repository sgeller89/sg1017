package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.PriceInfo;
import com.sgeller.tool.rental.domain.builders.PriceInfoBuilder;

import java.math.BigDecimal;
import java.util.Optional;

public class DefaultPriceService implements PriceService {

    @Override
    public PriceInfo getPriceInfo(BigDecimal dailyCharge, Integer chargeDays, Integer discountPercent) {
        if (dailyCharge == null) {
            throw new IllegalArgumentException("dailyCharge must be present.");
        }

        if (chargeDays == null) {
            throw new IllegalArgumentException("chargeDays must be present.");
        }

        if (discountPercent != null && (discountPercent < 0 || discountPercent > 100)) {
            throw new IllegalArgumentException("invalid discountPercent: " + discountPercent);
        }

        BigDecimal preDiscountCharge = dailyCharge.multiply(new BigDecimal(chargeDays));
        BigDecimal discountAmount = Optional.ofNullable(discountPercent)
                .map(discount -> this.calculateDiscountAmount(preDiscountCharge, discountPercent))
                .orElse(null);

        return new PriceInfoBuilder()
                .withPreDiscountCharge(preDiscountCharge)
                .withDiscountAmount(discountAmount)
                .build();
    }

    private BigDecimal calculateDiscountAmount(BigDecimal preDiscountCharge, Integer discountPercent) {
        BigDecimal discountDecimal = this.convertDiscountPercentToDecimal(discountPercent);

        return preDiscountCharge.multiply(discountDecimal);
    }

    private BigDecimal convertDiscountPercentToDecimal(Integer discountPercent) {
        return new BigDecimal(discountPercent).divide(new BigDecimal(100));
    }
}
