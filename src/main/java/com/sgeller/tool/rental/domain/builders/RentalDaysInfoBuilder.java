package com.sgeller.tool.rental.domain.builders;

import com.sgeller.tool.rental.domain.RentalDaysInfo;

import java.time.LocalDate;

public class RentalDaysInfoBuilder implements Builder<RentalDaysInfo> {

    private Integer chargeDays;
    private LocalDate dueDate;

    public RentalDaysInfoBuilder withChargeDays(Integer chargeDays) {
        this.chargeDays = chargeDays;
        return this;
    }

    public RentalDaysInfoBuilder withDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    @Override
    public RentalDaysInfo build() {
        return new RentalDaysInfo(this.chargeDays, this.dueDate);
    }
}
