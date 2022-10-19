package com.sgeller.tool.rental.domain.builders;

import com.sgeller.tool.rental.domain.RentalAgreement;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreementBuilder implements Builder<RentalAgreement, RentalAgreementBuilder> {

    private String toolCode;
    private String toolType;
    private String brand;
    private Integer rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BigDecimal dailyCharge;
    private Integer chargeDays;
    private BigDecimal preDiscountCharge;
    private Integer discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public RentalAgreementBuilder withToolCode(String toolCode) {
        this.toolCode = toolCode;
        return this;
    }

    public RentalAgreementBuilder withToolType(String toolType) {
        this.toolType = toolType;
        return this;
    }

    public RentalAgreementBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public RentalAgreementBuilder withRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
        return this;
    }

    public RentalAgreementBuilder withCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
        return this;
    }

    public RentalAgreementBuilder withDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public RentalAgreementBuilder withDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
        return this;
    }

    public RentalAgreementBuilder withChargeDays(Integer chargeDays) {
        this.chargeDays = chargeDays;
        return this;
    }

    public RentalAgreementBuilder withPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
        return this;
    }

    public RentalAgreementBuilder withDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
        return this;
    }

    public RentalAgreementBuilder withDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public RentalAgreementBuilder withFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
        return this;
    }

    @Override
    public RentalAgreement build() {
        return new RentalAgreement(this.toolCode, this.toolType, this.brand, this.rentalDays, this.checkoutDate,
                this.dueDate, this.dailyCharge, this.chargeDays, this.preDiscountCharge, this.discountPercent,
                this.discountAmount, this.finalCharge);
    }
}
