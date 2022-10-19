package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.Checkout;
import com.sgeller.tool.rental.domain.DayType;
import com.sgeller.tool.rental.domain.RentalDaysInfo;
import com.sgeller.tool.rental.domain.Tool;
import com.sgeller.tool.rental.domain.builders.RentalDaysInfoBuilder;

import java.time.LocalDate;
import java.util.Optional;

public class DefaultRentalDaysService implements RentalDaysService {

    private final DayTypeService dayTypeService;

    public DefaultRentalDaysService(DayTypeService dayTypeService) {
        this.dayTypeService = dayTypeService;
    }

    @Override
    public RentalDaysInfo calculateChargeDays(Checkout checkout, Tool tool) {
        LocalDate checkoutDate = Optional.ofNullable(checkout)
                .map(Checkout::getCheckoutDate)
                .orElseThrow(() -> new IllegalArgumentException("checkoutDate must be present."));

        Integer rentalDays = Optional.ofNullable(checkout)
                .map(Checkout::getRentalDayCount)
                .filter(days -> days > 0)
                .orElseThrow(() -> new IllegalArgumentException("rentalDayCount must be 1 or greater."));

        LocalDate currentDate = checkoutDate.plusDays(1);
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        Integer chargeDays = 0;

        while (currentDate.isBefore(dueDate) || currentDate.isEqual(dueDate)) {
            DayType dayType = this.dayTypeService.getDayType(currentDate);

            if (dayType == DayType.WEEKDAY && tool.getWeekdayCharge()) {
                chargeDays++;
            } else if (dayType == DayType.WEEKEND && tool.getWeekendCharge()) {
                chargeDays++;
            } else if (dayType == DayType.HOLIDAY && tool.getHolidayCharge()) {
                chargeDays++;
            }

            currentDate = currentDate.plusDays(1);
        }

        return new RentalDaysInfoBuilder()
                .withChargeDays(chargeDays)
                .withDueDate(dueDate)
                .build();
    }
}
