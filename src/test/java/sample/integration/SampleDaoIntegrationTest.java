package sample.integration;

import com.ranking.RankingApplication;
import com.ranking.sample.SampleDao;
import com.ranking.sample.SampleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RankingApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class SampleDaoIntegrationTest {

    @Autowired
    private SampleDao sampleDao;

    @Test
    public void testGetExistingSample() {

        Integer existingId = 1;
        SampleDto value = sampleDao.getSample(existingId);
        assertEquals("sample 1", value.getSample());
        assertEquals(existingId, value.getId());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testGetNonExistingSample() {

        sampleDao.getSample(2);
    }
}
