package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.PriceInfo;

import java.math.BigDecimal;

public interface PriceService {

    PriceInfo getPriceInfo(BigDecimal dailyCharge, Integer chargeDays, Integer discountPercent);
}
