package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.Checkout;
import com.sgeller.tool.rental.domain.PriceInfo;
import com.sgeller.tool.rental.domain.RentalAgreement;
import com.sgeller.tool.rental.domain.RentalDaysInfo;
import com.sgeller.tool.rental.domain.Tool;
import com.sgeller.tool.rental.domain.builders.RentalAgreementBuilder;
import com.sgeller.tool.rental.domain.factories.ToolFactory;

import java.util.Optional;

public class DefaultCheckoutService implements CheckoutService {

    private final RentalDaysService rentalDaysService;
    private final PriceService priceService;

    public DefaultCheckoutService(RentalDaysService rentalDaysService, PriceService priceService) {
        this.rentalDaysService = rentalDaysService;
        this.priceService = priceService;
    }

    @Override
    public RentalAgreement createRentalAgreement(Checkout checkout) {
        if (checkout == null) {
            throw new IllegalArgumentException("checkout must be present.");
        }

        Tool tool = ToolFactory.createTool(checkout.getToolCode());

        RentalDaysInfo rentalDaysInfo = this.rentalDaysService.calculateChargeDays(checkout, tool);

        PriceInfo priceInfo =
                this.priceService.getPriceInfo(
                        Optional.ofNullable(tool)
                                .map(Tool::getDailyCharge)
                                .orElse(null),
                        Optional.ofNullable(rentalDaysInfo)
                                .map(RentalDaysInfo::getChargeDays)
                                .orElse(null),
                        checkout.getDiscountPercent());

        return new RentalAgreementBuilder()
                .withToolCode(checkout.getToolCode())
                .withToolType(Optional.ofNullable(tool)
                        .map(Tool::getToolType)
                        .orElse(null))
                .withBrand(Optional.ofNullable(tool)
                        .map(Tool::getBrand)
                        .orElse(null))
                .withRentalDays(checkout.getRentalDayCount())
                .withCheckoutDate(checkout.getCheckoutDate())
                .withDueDate(Optional.ofNullable(rentalDaysInfo)
                        .map(RentalDaysInfo::getDueDate)
                        .orElse(null))
                .withDailyCharge(Optional.ofNullable(tool)
                        .map(Tool::getDailyCharge)
                        .orElse(null))
                .withChargeDays(Optional.ofNullable(rentalDaysInfo)
                        .map(RentalDaysInfo::getChargeDays)
                        .orElse(null))
                .withPreDiscountCharge(Optional.ofNullable(priceInfo)
                        .map(PriceInfo::getPreDiscountCharge)
                        .orElse(null))
                .withDiscountPercent(checkout.getDiscountPercent())
                .withDiscountAmount(Optional.ofNullable(priceInfo)
                        .map(PriceInfo::getDiscountAmount)
                        .orElse(null))
                .withFinalCharge(Optional.ofNullable(priceInfo)
                        .map(PriceInfo::getFinalCharge)
                        .orElse(null))
                .build();
    }
}
