package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.DateType;

import java.time.LocalDate;

public interface DateTypeService {

    DateType getDateType(LocalDate localDate);
}
