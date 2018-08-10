package ru.malltshik.ratecalculationsystem.services;

import ru.malltshik.ratecalculationsystem.models.Quote;

public interface RateCalculationService {
    Quote calculate(Integer loanAmount);
}
