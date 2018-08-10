package ru.malltshik.ratecalculationsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.malltshik.ratecalculationsystem.models.Quote;
import ru.malltshik.ratecalculationsystem.persistance.LanderRepository;
import ru.malltshik.ratecalculationsystem.properties.LoanProperties;
import ru.malltshik.ratecalculationsystem.services.ArgsProcessService;
import ru.malltshik.ratecalculationsystem.services.ArgsProcessService.ArgsModel;
import ru.malltshik.ratecalculationsystem.services.RateCalculationService;
import ru.malltshik.ratecalculationsystem.services.UsageService;

import static org.springframework.boot.Banner.Mode.OFF;

@SpringBootApplication
@EnableConfigurationProperties(LoanProperties.class)
public class RateCalculationSystemApplication implements ApplicationRunner {

    private final RateCalculationService rateCalculationService;
    private final ArgsProcessService argsProcessService;
    private final UsageService usageService;
    private final LanderRepository landerRepository;

    @Autowired
    public RateCalculationSystemApplication(
            RateCalculationService rateCalculationService,
            ArgsProcessService argsProcessService,
            UsageService usageService,
            LanderRepository landerRepository) {
        this.rateCalculationService = rateCalculationService;
        this.argsProcessService = argsProcessService;
        this.usageService = usageService;
        this.landerRepository = landerRepository;
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RateCalculationSystemApplication.class);
        application.setBannerMode(OFF);
        application.run("src/main/resources/market.csv", "1000");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ArgsModel argsModel = argsProcessService.validate(args.getNonOptionArgs());
        if (argsModel == null) {
            usageService.usage();
            return;
        }
        landerRepository.setStorage(argsModel.getMarketFile());
        try {
            Quote quote = rateCalculationService.calculate(argsModel.getLoanAmount());
            System.out.println(quote);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            usageService.usage();
        }

    }
}
