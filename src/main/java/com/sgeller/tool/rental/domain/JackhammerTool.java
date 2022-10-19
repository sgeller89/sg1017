package com.sgeller.tool.rental.domain;

import java.math.BigDecimal;

public class JackhammerTool extends Tool {

    public static final String TOOL_TYPE = "Jackhammer";
    private static final BigDecimal DAILY_CHARGE = new BigDecimal("2.99");

    public JackhammerTool(String toolCode, String brand) {
        super(TOOL_TYPE, toolCode, brand);
    }

    @Override
    public BigDecimal getDailyCharge() {
        return DAILY_CHARGE;
    }

    @Override
    public boolean getWeekdayCharge() {
        return true;
    }

    @Override
    public boolean getWeekendCharge() {
        return false;
    }

    @Override
    public boolean getHolidayCharge() {
        return false;
    }
}
