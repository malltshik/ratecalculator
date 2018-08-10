package ru.malltshik.ratecalculationsystem.services;

import ru.malltshik.ratecalculationsystem.models.Quote;

/**
 * Main business service, whose job is providing quotes with loan information
 *
 * @author Artem Gavrilov
 */
public interface RateCalculationService {
    /**
     * Calculation method
     *
     * @param loanAmount loan amount
     * @return {@link Quote} instance with all information about loan
     */
    Quote calculate(Integer loanAmount);
}
