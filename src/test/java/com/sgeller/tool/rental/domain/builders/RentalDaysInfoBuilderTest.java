package com.sgeller.tool.rental.domain.builders;

import com.sgeller.tool.rental.domain.RentalDaysInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class RentalDaysInfoBuilderTest {

    @Test
    public void testBuild() {
        RentalDaysInfo rentalDaysInfo = new RentalDaysInfoBuilder()
                .withChargeDays(1)
                .withDueDate(LocalDate.of(2022, 10, 17))
                .build();

        RentalDaysInfo expected = new RentalDaysInfo(1, LocalDate.of(2022, 10, 17));

        Assertions.assertEquals(expected.getChargeDays(), rentalDaysInfo.getChargeDays());
        Assertions.assertEquals(expected.getDueDate(), rentalDaysInfo.getDueDate());
    }
}
