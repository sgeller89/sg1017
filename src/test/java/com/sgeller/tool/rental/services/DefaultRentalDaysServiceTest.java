package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.Checkout;
import com.sgeller.tool.rental.domain.DayType;
import com.sgeller.tool.rental.domain.RentalDaysInfo;
import com.sgeller.tool.rental.domain.Tool;
import com.sgeller.tool.rental.domain.builders.CheckoutBuilder;
import com.sgeller.tool.rental.domain.factories.ToolFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class DefaultRentalDaysServiceTest {

    private RentalDaysService rentalDaysService;
    private DayTypeService dayTypeService;

    @BeforeEach
    public void setup() {
        this.dayTypeService = Mockito.mock(DayTypeService.class);
        Mockito.doReturn(DayType.WEEKEND)
                .when(this.dayTypeService)
                .getDayType(LocalDate.of(2022, 7, 3));
        Mockito.doReturn(DayType.HOLIDAY)
                .when(this.dayTypeService)
                .getDayType(LocalDate.of(2022, 7, 4));
        Mockito.doReturn(DayType.WEEKDAY)
                .when(this.dayTypeService)
                .getDayType(LocalDate.of(2022, 7, 5));
        Mockito.doReturn(DayType.WEEKDAY)
                .when(this.dayTypeService)
                .getDayType(LocalDate.of(2022, 7, 6));
        Mockito.doReturn(DayType.WEEKDAY)
                .when(this.dayTypeService)
                .getDayType(LocalDate.of(2022, 7, 7));
        this.rentalDaysService = new DefaultRentalDaysService(this.dayTypeService);
    }

    @Test
    public void calculateChargeDaysChainsaw() {
        Checkout checkout = new CheckoutBuilder()
                .withToolCode("CHNS")
                .withRentalDayCount(5)
                .withCheckoutDate(LocalDate.of(2022, 7, 2))
                .withDiscountPercent(0)
                .build();

        Tool tool = ToolFactory.createTool(checkout.getToolCode());

        RentalDaysInfo rentalDaysInfo = this.rentalDaysService.calculateChargeDays(checkout, tool);
        Assertions.assertEquals(4, rentalDaysInfo.getChargeDays());
    }

    @Test
    public void calculateChargeDaysLadder() {
        Checkout checkout = new CheckoutBuilder()
                .withToolCode("LADW")
                .withRentalDayCount(5)
                .withCheckoutDate(LocalDate.of(2022, 7, 2))
                .withDiscountPercent(0)
                .build();

        Tool tool = ToolFactory.createTool(checkout.getToolCode());

        RentalDaysInfo rentalDaysInfo = this.rentalDaysService.calculateChargeDays(checkout, tool);
        Assertions.assertEquals(4, rentalDaysInfo.getChargeDays());
    }

    @Test
    public void calculateChargeDaysJackhammer() {
        Checkout checkout = new CheckoutBuilder()
                .withToolCode("JAKR")
                .withRentalDayCount(5)
                .withCheckoutDate(LocalDate.of(2022, 7, 2))
                .withDiscountPercent(0)
                .build();

        Tool tool = ToolFactory.createTool(checkout.getToolCode());

        RentalDaysInfo rentalDaysInfo = this.rentalDaysService.calculateChargeDays(checkout, tool);
        Assertions.assertEquals(3, rentalDaysInfo.getChargeDays());
    }

    @Test
    public void calculateChargeDaysNullCheckoutDate() {
        Tool tool = ToolFactory.createTool("JAKR");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.rentalDaysService.calculateChargeDays(null, tool));

        Assertions.assertEquals("checkoutDate must be present.", exception.getMessage());
    }

    @Test
    public void calculateChargeDaysNullRentalDays() {
        Checkout checkout = new CheckoutBuilder()
                .withToolCode("JAKR")
                .withCheckoutDate(LocalDate.of(2022, 7, 2))
                .build();

        Tool tool = ToolFactory.createTool(checkout.getToolCode());

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.rentalDaysService.calculateChargeDays(checkout, tool));

        Assertions.assertEquals("rentalDayCount must be 1 or greater.", exception.getMessage());
    }

    @Test
    public void calculateChargeDaysZeroRentalDays() {
        Checkout checkout = new CheckoutBuilder()
                .withToolCode("JAKR")
                .withRentalDayCount(0)
                .withCheckoutDate(LocalDate.of(2022, 7, 2))
                .build();

        Tool tool = ToolFactory.createTool(checkout.getToolCode());

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.rentalDaysService.calculateChargeDays(checkout, tool));

        Assertions.assertEquals("rentalDayCount must be 1 or greater.", exception.getMessage());
    }
}
