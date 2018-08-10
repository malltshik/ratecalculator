package ru.malltshik.ratecalculationsystem.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.File;
import java.util.List;

public interface ArgsProcessService {

    @Nullable
    ArgsModel validate(@NonNull List<String> args);

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class ArgsModel {
        private File marketFile;
        private Integer loanAmount;
    }
}
