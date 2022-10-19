package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.DayType;

import java.time.LocalDate;

public interface DayTypeService {

    DayType getDayType(LocalDate localDate);
}
