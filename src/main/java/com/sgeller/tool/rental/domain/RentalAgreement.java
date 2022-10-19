package com.sgeller.tool.rental.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class RentalAgreement {

    private final String toolCode;
    private final String toolType;
    private final String brand;
    private final Integer rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final BigDecimal dailyCharge;
    private final Integer chargeDays;
    private final BigDecimal preDiscountCharge;
    private final Integer discountPercent;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;

    public RentalAgreement(String toolCode, String toolType, String brand, Integer rentalDays, LocalDate checkoutDate,
            LocalDate dueDate, BigDecimal dailyCharge, Integer chargeDays, BigDecimal preDiscountCharge,
            Integer discountPercent, BigDecimal discountAmount, BigDecimal finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyCharge = Optional.ofNullable(dailyCharge)
                .map(amount -> amount.setScale(2, RoundingMode.HALF_UP))
                .orElse(null);
        this.chargeDays = chargeDays;
        this.preDiscountCharge = Optional.ofNullable(preDiscountCharge)
                .map(amount -> amount.setScale(2, RoundingMode.HALF_UP))
                .orElse(null);
        this.discountPercent = discountPercent;
        this.discountAmount = Optional.ofNullable(discountAmount)
                .map(amount -> amount.setScale(2, RoundingMode.HALF_UP))
                .orElse(null);
        this.finalCharge = Optional.ofNullable(finalCharge)
                .map(amount -> amount.setScale(2, RoundingMode.HALF_UP))
                .orElse(null);
    }

    public String getToolCode() {
        return this.toolCode;
    }

    public String getToolType() {
        return this.toolType;
    }

    public String getBrand() {
        return this.brand;
    }

    public Integer getRentalDays() {
        return this.rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return this.checkoutDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public BigDecimal getDailyCharge() {
        return this.dailyCharge;
    }

    public Integer getChargeDays() {
        return this.chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return this.preDiscountCharge;
    }

    public Integer getDiscountPercent() {
        return this.discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return this.discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return this.finalCharge;
    }

    public void printAgreement() {
        System.out.println("Tool code: " + this.getToolCode());
        System.out.println("Tool type: " + this.getToolType());
        System.out.println("Tool brand: " + this.getBrand());
        System.out.println("Rental days: " + this.getRentalDays());

        Optional.ofNullable(this.getCheckoutDate())
                .map(date -> date.format(DateTimeFormatter.ofPattern("MM/dd/yy")))
                .ifPresent(date -> System.out.println("Checkout date: " + date));

        Optional.ofNullable(this.getDueDate())
                .map(date -> date.format(DateTimeFormatter.ofPattern("MM/dd/yy")))
                .ifPresent(date -> System.out.println("Due date: " + date));

        Optional.ofNullable(this.getDailyCharge())
                .map(amount -> NumberFormat.getCurrencyInstance().format(amount))
                .ifPresent(amount -> System.out.println("Daily rental charge: " + amount));

        System.out.println("Charge days: " + this.getChargeDays());

        Optional.ofNullable(this.getPreDiscountCharge())
                .map(amount -> NumberFormat.getCurrencyInstance().format(amount))
                .ifPresent(amount -> System.out.println("Pre-discount charge: " + amount));

        System.out.println("Discount percent: " + this.getDiscountPercent() + "%");

        Optional.ofNullable(this.getDiscountAmount())
                .map(amount -> NumberFormat.getCurrencyInstance().format(amount))
                .ifPresent(amount -> System.out.println("Discount amount: " + amount));

        Optional.ofNullable(this.getFinalCharge())
                .map(amount -> NumberFormat.getCurrencyInstance().format(amount))
                .ifPresent(amount -> System.out.println("Final charge: " + amount));
    }
}
