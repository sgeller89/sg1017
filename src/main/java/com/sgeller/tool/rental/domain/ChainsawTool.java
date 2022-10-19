package com.sgeller.tool.rental.domain;

import java.math.BigDecimal;

public class ChainsawTool extends Tool {

    public static final String TOOL_TYPE = "Chainsaw";
    private static final BigDecimal DAILY_CHARGE = new BigDecimal("1.49");

    public ChainsawTool(String toolCode, String brand) {
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
        return true;
    }

    @Override
    public boolean getHolidayCharge() {
        return false;
    }
}
