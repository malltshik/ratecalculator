package ru.malltshik.ratecalculationsystem.services.implementation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.malltshik.ratecalculationsystem.models.Lander;
import ru.malltshik.ratecalculationsystem.models.Quote;
import ru.malltshik.ratecalculationsystem.persistance.LanderRepository;
import ru.malltshik.ratecalculationsystem.services.RateCalculationService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Import(RateCalculationServiceImpl.class)
@ActiveProfiles("test")
@SpringBootTest
public class RateCalculationServiceImplTest {


    @Autowired
    private RateCalculationService rateCalculationService;

    @MockBean
    private LanderRepository landerRepository;

    private final static List<Lander> LANDERS = Arrays.asList(
            new Lander("Bob", BigDecimal.valueOf(0.023), 200),
            new Lander("John", BigDecimal.valueOf(0.035), 100)
    );

    @Before
    public void before() {
        when(landerRepository.findAll()).thenReturn(LANDERS);
    }

    @Test
    public void calculateTest() throws Exception {
        Quote quote = rateCalculationService.calculate(156);
        assertNotNull(quote);
        assertTrue(quote.isPossible());
        assertEquals(quote.getRequestAmount(), 156);
        assertThat(quote.getRate(), equalTo(0.023));
        assertThat(quote.getMonthlyRepayment(), equalTo(4.496));
        assertThat(quote.getTotalRepayment(), equalTo(161.856));
    }

    @Test
    public void calculateOverTest() throws Exception {
        Quote quote = rateCalculationService.calculate(1000);
        assertNotNull(quote);
        assertFalse(quote.isPossible());
    }

    @Test
    public void calculateMoreThenOneLanderTest() throws Exception {
        Quote quote = rateCalculationService.calculate(300);
        assertNotNull(quote);
        assertTrue(quote.isPossible());
        assertEquals(quote.getRequestAmount(), 300);
        assertThat(quote.getRate(), equalTo(0.029));
        assertThat(quote.getMonthlyRepayment(), equalTo(8.699));
        assertThat(quote.getTotalRepayment(), equalTo(313.164));
    }


}