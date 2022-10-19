package com.sgeller.tool.rental.domain;

import java.math.BigDecimal;

public class LadderTool extends Tool {

    public static final String TOOL_TYPE = "Ladder";
    private static final BigDecimal DAILY_CHARGE = new BigDecimal("1.99");

    public LadderTool(String toolCode, String brand) {
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
        return true;
    }
}
