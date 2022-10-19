package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.Checkout;
import com.sgeller.tool.rental.domain.RentalAgreement;
import com.sgeller.tool.rental.domain.ToolCode;
import com.sgeller.tool.rental.domain.builders.CheckoutBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DefaultCheckoutServiceTest {

    private CheckoutService checkoutService;
    private RentalDaysService rentalDaysService;
    private PriceService priceService;

    @BeforeEach
    public void setup() {
        HolidayService holidayService = new DefaultHolidayService();
        DateTypeService dateTypeService = new DefaultDateTypeService(holidayService);

        this.rentalDaysService = new DefaultRentalDaysService(dateTypeService);
        this.priceService = new DefaultPriceService();

        this.checkoutService = new DefaultCheckoutService(this.rentalDaysService, this.priceService);
    }

    @Test
    public void test1() {
        Checkout checkout = new CheckoutBuilder()
                .withToolCode(ToolCode.JAKR.name())
                .withCheckoutDate(LocalDate.of(2015, 9, 3))
                .withRentalDayCount(5)
                .withDiscountPercent(101)
                .build();

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.checkoutService.createRentalAgreement(checkout));

        Assertions.assertEquals("invalid discountPercent: 101", exception.getMessage());
    }

    @Test
    public void test2() {
        Checkout checkout = new CheckoutBuilder()
                .withToolCode(ToolCode.LADW.name())
                .withCheckoutDate(LocalDate.of(2020, 7, 2))
                .withRentalDayCount(3)
                .withDiscountPercent(10)
                .build();

        RentalAgreement rentalAgreement = this.checkoutService.createRentalAgreement(checkout);
        rentalAgreement.printAgreement();

        Assertions.assertEquals(ToolCode.LADW.name(), rentalAgreement.getToolCode());
        Assertions.assertEquals(ToolCode.LADW.getToolType(), rentalAgreement.getToolType());
        Assertions.assertEquals(ToolCode.LADW.getBrand(), rentalAgreement.getBrand());
        Assertions.assertEquals(3, rentalAgreement.getRentalDays());
        Assertions.assertEquals(LocalDate.of(2020, 7, 2), rentalAgreement.getCheckoutDate());
        Assertions.assertEquals(LocalDate.of(2020, 7, 5), rentalAgreement.getDueDate());
        Assertions.assertEquals(new BigDecimal("1.99"), rentalAgreement.getDailyCharge());
        Assertions.assertEquals(1, rentalAgreement.getChargeDays());
        Assertions.assertEquals(new BigDecimal("1.99"), rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals(10, rentalAgreement.getDiscountPercent());
        Assertions.assertEquals(new BigDecimal("0.20"), rentalAgreement.getDiscountAmount());
        Assertions.assertEquals(new BigDecimal("1.79"), rentalAgreement.getFinalCharge());
    }

    @Test
    public void test3() {
        Checkout checkout = new CheckoutBuilder()
                .withToolCode(ToolCode.CHNS.name())
                .withCheckoutDate(LocalDate.of(2015, 7, 2))
                .withRentalDayCount(5)
                .withDiscountPercent(25)
                .build();

        RentalAgreement rentalAgreement = this.checkoutService.createRentalAgreement(checkout);
        rentalAgreement.printAgreement();

        Assertions.assertEquals(ToolCode.CHNS.name(), rentalAgreement.getToolCode());
        Assertions.assertEquals(ToolCode.CHNS.getToolType(), rentalAgreement.getToolType());
        Assertions.assertEquals(ToolCode.CHNS.getBrand(), rentalAgreement.getBrand());
        Assertions.assertEquals(5, rentalAgreement.getRentalDays());
        Assertions.assertEquals(LocalDate.of(2015, 7, 2), rentalAgreement.getCheckoutDate());
        Assertions.assertEquals(LocalDate.of(2015, 7, 7), rentalAgreement.getDueDate());
        Assertions.assertEquals(new BigDecimal("1.49"), rentalAgreement.getDailyCharge());
        Assertions.assertEquals(4, rentalAgreement.getChargeDays());
        Assertions.assertEquals(new BigDecimal("5.96"), rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals(25, rentalAgreement.getDiscountPercent());
        Assertions.assertEquals(new BigDecimal("1.49"), rentalAgreement.getDiscountAmount());
        Assertions.assertEquals(new BigDecimal("4.47"), rentalAgreement.getFinalCharge());
    }

    @Test
    public void test4() {
        Checkout checkout = new CheckoutBuilder()
                .withToolCode(ToolCode.JAKD.name())
                .withCheckoutDate(LocalDate.of(2015, 9, 3))
                .withRentalDayCount(6)
                .withDiscountPercent(0)
                .build();

        RentalAgreement rentalAgreement = this.checkoutService.createRentalAgreement(checkout);
        rentalAgreement.printAgreement();

        Assertions.assertEquals(ToolCode.JAKD.name(), rentalAgreement.getToolCode());
        Assertions.assertEquals(ToolCode.JAKD.getToolType(), rentalAgreement.getToolType());
        Assertions.assertEquals(ToolCode.JAKD.getBrand(), rentalAgreement.getBrand());
        Assertions.assertEquals(6, rentalAgreement.getRentalDays());
        Assertions.assertEquals(LocalDate.of(2015, 9, 3), rentalAgreement.getCheckoutDate());
        Assertions.assertEquals(LocalDate.of(2015, 9, 9), rentalAgreement.getDueDate());
        Assertions.assertEquals(new BigDecimal("2.99"), rentalAgreement.getDailyCharge());
        Assertions.assertEquals(3, rentalAgreement.getChargeDays());
        Assertions.assertEquals(new BigDecimal("8.97"), rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals(0, rentalAgreement.getDiscountPercent());
        Assertions.assertEquals(new BigDecimal("0.00"), rentalAgreement.getDiscountAmount());
        Assertions.assertEquals(new BigDecimal("8.97"), rentalAgreement.getFinalCharge());
    }

    @Test
    public void test5() {
        Checkout checkout = new CheckoutBuilder()
                .withToolCode(ToolCode.JAKR.name())
                .withCheckoutDate(LocalDate.of(2015, 7, 2))
                .withRentalDayCount(9)
                .withDiscountPercent(0)
                .build();

        RentalAgreement rentalAgreement = this.checkoutService.createRentalAgreement(checkout);
        rentalAgreement.printAgreement();

        Assertions.assertEquals(ToolCode.JAKR.name(), rentalAgreement.getToolCode());
        Assertions.assertEquals(ToolCode.JAKR.getToolType(), rentalAgreement.getToolType());
        Assertions.assertEquals(ToolCode.JAKR.getBrand(), rentalAgreement.getBrand());
        Assertions.assertEquals(9, rentalAgreement.getRentalDays());
        Assertions.assertEquals(LocalDate.of(2015, 7, 2), rentalAgreement.getCheckoutDate());
        Assertions.assertEquals(LocalDate.of(2015, 7, 11), rentalAgreement.getDueDate());
        Assertions.assertEquals(new BigDecimal("2.99"), rentalAgreement.getDailyCharge());
        Assertions.assertEquals(5, rentalAgreement.getChargeDays());
        Assertions.assertEquals(new BigDecimal("14.95"), rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals(0, rentalAgreement.getDiscountPercent());
        Assertions.assertEquals(new BigDecimal("0.00"), rentalAgreement.getDiscountAmount());
        Assertions.assertEquals(new BigDecimal("14.95"), rentalAgreement.getFinalCharge());
    }

    @Test
    public void test6() {
        Checkout checkout = new CheckoutBuilder()
                .withToolCode(ToolCode.JAKR.name())
                .withCheckoutDate(LocalDate.of(2020, 7, 2))
                .withRentalDayCount(4)
                .withDiscountPercent(50)
                .build();

        RentalAgreement rentalAgreement = this.checkoutService.createRentalAgreement(checkout);
        rentalAgreement.printAgreement();

        Assertions.assertEquals(ToolCode.JAKR.name(), rentalAgreement.getToolCode());
        Assertions.assertEquals(ToolCode.JAKR.getToolType(), rentalAgreement.getToolType());
        Assertions.assertEquals(ToolCode.JAKR.getBrand(), rentalAgreement.getBrand());
        Assertions.assertEquals(4, rentalAgreement.getRentalDays());
        Assertions.assertEquals(LocalDate.of(2020, 7, 2), rentalAgreement.getCheckoutDate());
        Assertions.assertEquals(LocalDate.of(2020, 7, 6), rentalAgreement.getDueDate());
        Assertions.assertEquals(new BigDecimal("2.99"), rentalAgreement.getDailyCharge());
        Assertions.assertEquals(1, rentalAgreement.getChargeDays());
        Assertions.assertEquals(new BigDecimal("2.99"), rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals(50, rentalAgreement.getDiscountPercent());
        Assertions.assertEquals(new BigDecimal("1.50"), rentalAgreement.getDiscountAmount());
        Assertions.assertEquals(new BigDecimal("1.49"), rentalAgreement.getFinalCharge());
    }

    @Test
    public void testCreateRentalAgreementNullCheckout() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.checkoutService.createRentalAgreement(null));

        Assertions.assertEquals("checkout must be present.", exception.getMessage());
    }
}
