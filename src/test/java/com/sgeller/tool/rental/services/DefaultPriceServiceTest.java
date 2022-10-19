package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.PriceInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class DefaultPriceServiceTest {

    private PriceService priceService;

    @BeforeEach
    public void setup() {
        this.priceService = new DefaultPriceService();
    }

    @Test
    public void testGetPriceInfo() {
        PriceInfo priceInfo = this.priceService.getPriceInfo(new BigDecimal("1.99"), 5, 10);

        Assertions.assertEquals(new BigDecimal("9.95"), priceInfo.getPreDiscountCharge());
        Assertions.assertEquals(new BigDecimal("1.00"), priceInfo.getDiscountAmount());
        Assertions.assertEquals(new BigDecimal("8.95"), priceInfo.getFinalCharge());
    }

    @Test
    public void testGetPriceInfoDiscount100() {
        PriceInfo priceInfo = this.priceService.getPriceInfo(new BigDecimal("1.99"), 5, 100);

        Assertions.assertEquals(new BigDecimal("9.95"), priceInfo.getPreDiscountCharge());
        Assertions.assertEquals(new BigDecimal("9.95"), priceInfo.getDiscountAmount());
        Assertions.assertEquals(new BigDecimal("0.00"), priceInfo.getFinalCharge());
    }

    @Test
    public void testGetPriceInfoDiscount0() {
        PriceInfo priceInfo = this.priceService.getPriceInfo(new BigDecimal("1.99"), 5, 0);

        Assertions.assertEquals(new BigDecimal("9.95"), priceInfo.getPreDiscountCharge());
        Assertions.assertEquals(new BigDecimal("0.00"), priceInfo.getDiscountAmount());
        Assertions.assertEquals(new BigDecimal("9.95"), priceInfo.getFinalCharge());
    }

    @Test
    public void testGetPriceInfoNullDiscount() {
        PriceInfo priceInfo = this.priceService.getPriceInfo(new BigDecimal("1.99"), 5, null);

        Assertions.assertEquals(new BigDecimal("9.95"), priceInfo.getPreDiscountCharge());
        Assertions.assertEquals(BigDecimal.ZERO, priceInfo.getDiscountAmount());
        Assertions.assertEquals(new BigDecimal("9.95"), priceInfo.getFinalCharge());
    }

    @Test
    public void testGetPriceInfoNullDailyCharge() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.priceService.getPriceInfo(null, 5, 10));

        Assertions.assertEquals("dailyCharge must be present.", exception.getMessage());
    }

    @Test
    public void testGetPriceInfoNullChargeDays() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.priceService.getPriceInfo(new BigDecimal("1.99"), null, 10));

        Assertions.assertEquals("chargeDays must be present.", exception.getMessage());
    }

    @Test
    public void testGetPriceInfoDiscountUnder0() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.priceService.getPriceInfo(new BigDecimal("1.99"), 5, -8));

        Assertions.assertEquals("invalid discountPercent: -8", exception.getMessage());
    }

    @Test
    public void testGetPriceInfoDiscountOver100() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.priceService.getPriceInfo(new BigDecimal("1.99"), 5, 105));

        Assertions.assertEquals("invalid discountPercent: 105", exception.getMessage());
    }
}
