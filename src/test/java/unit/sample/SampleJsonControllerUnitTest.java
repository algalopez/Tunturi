package unit.sample;

import com.ranking.sample.SampleJsonController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class SampleJsonControllerUnitTest {


    private SampleJsonController sampleJsonController;

    public SampleJsonControllerUnitTest() {
        sampleJsonController = new SampleJsonController();
    }

    @Test
    public void testSample() {

        ResponseEntity<String> responseEntity = sampleJsonController.sample();

        String expectedBody = "{\"response\":\"sample\"}";
        Assert.assertEquals(expectedBody,responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
}
