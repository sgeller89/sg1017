package com.sgeller.tool.rental.domain;

import java.math.BigDecimal;

public abstract class Tool {

    private final String toolType;
    private final String toolCode;
    private final String brand;

    public Tool(String toolType, String toolCode, String brand) {
        this.toolType = toolType;
        this.toolCode = toolCode;
        this.brand = brand;
    }

    public String getToolType() {
        return this.toolType;
    }

    public String getToolCode() {
        return this.toolCode;
    }

    public String getBrand() {
        return this.brand;
    }

    public abstract BigDecimal getDailyCharge();

    public abstract boolean getWeekdayCharge();

    public abstract boolean getWeekendCharge();

    public abstract boolean getHolidayCharge();
}
