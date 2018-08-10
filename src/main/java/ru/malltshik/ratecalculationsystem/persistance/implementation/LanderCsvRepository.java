package ru.malltshik.ratecalculationsystem.persistance.implementation;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.stereotype.Repository;
import ru.malltshik.ratecalculationsystem.models.Lander;
import ru.malltshik.ratecalculationsystem.persistance.LanderRepository;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Repository
public class LanderCsvRepository implements LanderRepository {

    private static List<Lander> LANDERS;
    private File FILESTORAGE;

    @Override
    public List<Lander> findAll() {
        if (FILESTORAGE == null || !FILESTORAGE.exists()) {
            throw new IllegalStateException("Illegal state. You have to set storage before request data");
        }
        if (LANDERS != null) return LANDERS;
        LANDERS = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(FILESTORAGE));
            boolean firstList = true;
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (firstList) {
                    firstList = false;
                } else {
                    LANDERS.add(new Lander(line[0], new BigDecimal(line[1]), parseInt(line[2])));
                }
            }
        } catch (Exception e) {
            LANDERS = null;
            throw new IllegalArgumentException("Unable to load lander from market file. Please, choose another file.");
        }
        return LANDERS;
    }

    @Override
    public void setStorage(File marketFile) {
        this.FILESTORAGE = marketFile;
    }
}
