package ru.malltshik.ratecalculationsystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Quote data transfer object
 *
 * @author Artem Gavrilov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    private int requestAmount;
    private double rate;
    private double monthlyRepayment;
    private double totalRepayment;
    private boolean possible;

    public void add(Quote quote) {
        this.requestAmount = requestAmount + quote.requestAmount;
        this.rate = rate > 0 ? (rate + quote.rate) / 2 : quote.rate;
        this.monthlyRepayment = monthlyRepayment + quote.monthlyRepayment;
        this.totalRepayment = totalRepayment + quote.totalRepayment;
    }

    @Override
    public String toString() {
        if (possible) return String.format(
                "\nRequested amount: £%s\nRate: %s%%\nMonthly repayment: £%s\nTotal repayment: £%s\n",
                requestAmount, round(rate, 1), round(monthlyRepayment, 2), round(totalRepayment, 2));
        else return String.format("\nLoan for £%s is not possible.\n", requestAmount);
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
