package sample.unit;

import com.ranking.sample.SampleDto;
import com.ranking.sample.SampleJsonController;
import com.ranking.sample.SampleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SampleJsonControllerUnitTest {

    @Mock
    private SampleService sampleService;

    private SampleJsonController sampleJsonController;

    @Before
    public void prepareMocks() {
        sampleJsonController = new SampleJsonController(sampleService);
    }

    @Test
    public void testSample() {

        SampleDto mockedSample = new SampleDto(1, "sample");
        when(sampleService.getSample(1)).thenReturn(mockedSample);

        ResponseEntity<SampleDto> responseEntity = sampleJsonController.publicSample();

        Assert.assertEquals(mockedSample, responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
