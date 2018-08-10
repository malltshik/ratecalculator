package ru.malltshik.ratecalculationsystem.persistance;

import ru.malltshik.ratecalculationsystem.models.Lander;

import java.io.File;
import java.util.List;

public interface LanderRepository {
    List<Lander> findAll();
    void setStorage(File marketFile);
}
