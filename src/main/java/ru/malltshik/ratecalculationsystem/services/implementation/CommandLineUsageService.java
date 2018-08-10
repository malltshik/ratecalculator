package ru.malltshik.ratecalculationsystem.services.implementation;

import org.springframework.stereotype.Service;
import ru.malltshik.ratecalculationsystem.services.UsageService;


@Service
public class CommandLineUsageService implements UsageService {
    @Override
    public void usage() {
        System.out.println("\nUsage: [market_file] [loan_amount]\n");
    }
}
