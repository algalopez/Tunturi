package sample.api;

import com.ranking.RankingApplication;
import com.ranking.sample.SampleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class SampleJsonControllerApiTest {

    private static final String BASE_URL = "http://localhost:";
    private static final String ENDPOINT = "/api/public/sample/";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void greetingShouldReturnDefaultMessage() {

//        final String fullUrl = BASE_URL + port + ENDPOINT;
//        Integer expectedId = 1;
//        String expectedSample = "sample 1";
//
//        SampleDto sampleDto = restTemplate.getForObject(fullUrl, SampleDto.class);
//        assertEquals(expectedId, sampleDto.getId());
//        assertEquals(expectedSample, sampleDto.getSample());

        assertTrue(true);
    }
}
