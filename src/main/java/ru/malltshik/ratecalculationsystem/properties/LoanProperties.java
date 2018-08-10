package ru.malltshik.ratecalculationsystem.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@ConfigurationProperties("loan")
public class LoanProperties {

    /**
     * The loan request minimum (default 1000)
     */
    @NotNull(message = "Loan request min value should be exist")
    private Integer min = 1000;


    /**
     * The loan request maximum (default 15000)
     */
    @NotNull(message = "Loan request max value should be exist")
    private Integer max = 15000;

    /**
     * The loan request length in month (default 36 month)
     */
    @NotNull(message = "Loan request length value should be exist")
    private Integer length = 36;
}
