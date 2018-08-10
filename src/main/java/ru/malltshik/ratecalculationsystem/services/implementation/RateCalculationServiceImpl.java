package ru.malltshik.ratecalculationsystem.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.malltshik.ratecalculationsystem.models.Lander;
import ru.malltshik.ratecalculationsystem.models.Quote;
import ru.malltshik.ratecalculationsystem.persistance.LanderRepository;
import ru.malltshik.ratecalculationsystem.services.RateCalculationService;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

@Service
public class RateCalculationServiceImpl implements RateCalculationService {

    private final LanderRepository landerRepository;

    @Value("${loan.length}")
    private int LOAN_LENGTH;

    @Autowired
    public RateCalculationServiceImpl(LanderRepository landerRepository) {
        this.landerRepository = landerRepository;
    }

    @Override
    public Quote calculate(Integer loanAmount) {
        Quote quote = new Quote();
        List<Lander> landers = landerRepository.findAll();
        if (landers.isEmpty() || landers.stream().mapToLong(Lander::getAvailable).sum() < loanAmount) {
            quote.setRequestAmount(loanAmount);
            quote.setPossible(false);
        } else {
            quote.setPossible(true);
            calculate(loanAmount, landers, quote);
        }
        return quote;
    }

    private void calculate(Integer loanAmount, List<Lander> landers, Quote quote) {
        landers.sort(Comparator.comparing(Lander::getRate));
        for (Lander lander : landers) {
            Quote subQuote = calculateQuote(lander, loanAmount);
            loanAmount = loanAmount - subQuote.getRequestAmount();
            quote.add(subQuote);
            if (loanAmount <= 0) break;
        }
    }

    private Quote calculateQuote(Lander lander, Integer loanAmount) {
        Integer available = lander.getAvailable();
        int subLoanAmount = available >= loanAmount ? loanAmount : available;
        BigDecimal monthlyRate = lander.getRate().divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP);
        Quote quote = new Quote();
        quote.setRequestAmount(subLoanAmount);
        quote.setRate(lander.getRate().doubleValue());
        BigDecimal monthlyPayment = monthlyRate.multiply(BigDecimal.valueOf(subLoanAmount))
                .divide(BigDecimal.valueOf(1).subtract((BigDecimal.valueOf(1).add(monthlyRate)
                        .pow(-LOAN_LENGTH, MathContext.DECIMAL64))), RoundingMode.HALF_UP);
        quote.setMonthlyRepayment(monthlyPayment.doubleValue());
        quote.setTotalRepayment(monthlyPayment.multiply(BigDecimal.valueOf(LOAN_LENGTH)).doubleValue());
        quote.setPossible(true);
        return quote;
    }

}
