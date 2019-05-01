package sample.unit;

import com.ranking.sample.SampleDto;
import com.ranking.sample.SampleService;
import com.ranking.sample.SampleWebController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SampleWebControllerUnitTest {

    @Mock
    private SampleService sampleService;

    private SampleWebController sampleWebController;

    @Before
    public void prepareMocks() {
        sampleWebController = new SampleWebController(sampleService);
    }

    @Test
    public void testSample() {

        when(sampleService.getSample(1)).thenReturn(new SampleDto(1, "sample"));

        Model model = new ExtendedModelMap();
        String webPage = sampleWebController.sample(model);

        Assert.assertEquals("sample", webPage);
    }
}
