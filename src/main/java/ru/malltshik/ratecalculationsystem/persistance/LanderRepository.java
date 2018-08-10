package ru.malltshik.ratecalculationsystem.persistance;

import ru.malltshik.ratecalculationsystem.models.Lander;

import java.io.File;
import java.util.List;

/**
 * Lender repository provide some methods for manipulate with lender information in datasource
 * !ATTENTION You have to set storage before request any kind of information
 * Otherwise you faced {@link IllegalStateException}
 *
 * @author Artem Gavrilov
 */
public interface LanderRepository {

    /**
     * Find all landers in our datasource
     *
     * @return list of landers
     */
    List<Lander> findAll();


    /**
     * Set datasource
     *
     * @param marketFile file with landers information
     */
    void setStorage(File marketFile);
}
