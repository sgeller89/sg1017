package com.sgeller.tool.rental.domain;

import java.time.LocalDate;

public class RentalDaysInfo {

    private final Integer chargeDays;
    private final LocalDate dueDate;

    public RentalDaysInfo(Integer chargeDays, LocalDate dueDate) {
        this.chargeDays = chargeDays;
        this.dueDate = dueDate;
    }

    public Integer getChargeDays() {
        return this.chargeDays;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }
}
