package com.algalopez.ranking.sample.unit;

import com.algalopez.ranking.sample.SampleDto;
import com.algalopez.ranking.sample.SampleJsonController;
import com.algalopez.ranking.sample.SampleService;
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
    public void testPublicSample() {

        SampleDto mockedSample = new SampleDto(1, "sample");
        when(sampleService.getSample(1)).thenReturn(mockedSample);

        ResponseEntity<SampleDto> responseEntity = sampleJsonController.publicSample("user");

        Assert.assertEquals(mockedSample, responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testPrivateSample() {

        SampleDto mockedSample = new SampleDto(2, "sample");
        when(sampleService.getSample(1)).thenReturn(mockedSample);

        ResponseEntity<SampleDto> responseEntity = sampleJsonController.privateSample("user");

        Assert.assertEquals(mockedSample, responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testAdminSample() {

        SampleDto mockedSample = new SampleDto(3, "sample");
        when(sampleService.getSample(1)).thenReturn(mockedSample);

        ResponseEntity<SampleDto> responseEntity = sampleJsonController.adminSample("user");

        Assert.assertEquals(mockedSample, responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
