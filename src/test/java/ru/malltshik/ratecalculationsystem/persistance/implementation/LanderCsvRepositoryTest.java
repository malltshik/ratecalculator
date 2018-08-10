package ru.malltshik.ratecalculationsystem.persistance.implementation;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import ru.malltshik.ratecalculationsystem.models.Lander;
import ru.malltshik.ratecalculationsystem.persistance.LanderRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@Import(LanderCsvRepository.class)
public class LanderCsvRepositoryTest {

    @Autowired
    private LanderRepository landerRepository;

    @Before
    public void setUp() throws Exception {
        landerRepository.setStorage(null);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    // TODO fix test
    public void findAllWithBadFile() throws Exception {
        landerRepository.setStorage(ResourceUtils.getFile("classpath:failed.csv"));
        landerRepository.findAll();
    }

    @Test(expected = IllegalStateException.class)
    public void findAllWithoutStorage() throws Exception {
        landerRepository.findAll();
    }

    @Test
    public void findAll() throws Exception {
        landerRepository.setStorage(ResourceUtils.getFile("classpath:market.csv"));
        List<Lander> all = landerRepository.findAll();
        assertThat(all, hasSize(7));
    }


}