package ru.malltshik.ratecalculationsystem.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.File;
import java.util.List;


/**
 * Arguments validation service validate our market filename and loan amount
 *
 * @author Artem Gavrilov
 */
public interface ArgsProcessService {

    /**
     * Main validation method.
     * Return value could be null, which means than validation failed.
     * Every single message has been showed to user
     *
     * @param args user input arguments
     * @return {@link ArgsModel} instance with arguments. Market file as {@link File}
     * instance and loan amount as {@link Integer}
     */
    @Nullable
    ArgsModel validate(@NonNull List<String> args);


    /**
     * Data transfer object for validation result
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class ArgsModel {
        private File marketFile;
        private Integer loanAmount;
    }
}
