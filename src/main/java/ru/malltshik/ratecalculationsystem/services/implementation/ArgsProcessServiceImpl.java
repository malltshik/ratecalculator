package ru.malltshik.ratecalculationsystem.services.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.malltshik.ratecalculationsystem.services.ArgsProcessService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class ArgsProcessServiceImpl implements ArgsProcessService {

    @Value("${loan.min}")
    private int MIN_LOAN;

    @Value("${loan.max}")
    private int MAX_LOAN;

    @Override
    @Nullable
    public ArgsModel validate(@NonNull List<String> args) {
        if(args.size() < 2) {
            System.out.println("Count of arguments should be 2.");
            return null;
        }
        File marketFile = validateMarketFile(args.get(0));
        Integer loanAmount = validateLoanAmount(args.get(1));
        if(marketFile == null || loanAmount == null) return null;
        return new ArgsModel(marketFile,loanAmount);
    }

    @Nullable
    private Integer validateLoanAmount(String arg) {
        try {
            int amount = parseInt(arg);
            if(amount < MIN_LOAN) {
                System.out.println("Amount should be more then £1000.");
                return null;
            }
            if(amount > MAX_LOAN) {
                System.out.println("Amount should be less then £15000.");
                return null;
            }
            return amount;
        } catch (Exception e) {
            System.out.println("Amount should be integer number between £1000 and £15000.");
            return null;
        }
    }

    @Nullable
    private File validateMarketFile(String arg) {
        Path path = Paths.get(arg);
        if(!Files.exists(path)) {
            System.out.println(String.format("File %s does not exist", arg));
            return null;
        } else return path.toFile();
    }

}
