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
    public void testPublicSample() {

        SampleDto mockedSample = new SampleDto(1, "sample");
        when(sampleService.getSample(1)).thenReturn(mockedSample);

        Model model = new ExtendedModelMap();
        String webPage = sampleWebController.publicSample("user", model);

        Assert.assertEquals("sample", webPage);
    }

    @Test
    public void testPrivateSample() {

        SampleDto mockedSample = new SampleDto(2, "sample");
        when(sampleService.getSample(2)).thenReturn(mockedSample);

        Model model = new ExtendedModelMap();
        String webPage = sampleWebController.privateSample("user", model);

        Assert.assertEquals("sample", webPage);
    }

    @Test
    public void testAdminSample() {

        SampleDto mockedSample = new SampleDto(3, "sample");
        when(sampleService.getSample(3)).thenReturn(mockedSample);

        Model model = new ExtendedModelMap();
        String webPage = sampleWebController.adminSample("user", model);

        Assert.assertEquals("sample", webPage);
    }
}
