package ru.malltshik.ratecalculationsystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lander {
    private String lander;
    private BigDecimal rate;
    private Integer available;
}
