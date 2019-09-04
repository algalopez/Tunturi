package com.algalopez.ranking.sample.unit;

import com.algalopez.ranking.sample.SampleDao;
import com.algalopez.ranking.sample.SampleDto;
import com.algalopez.ranking.sample.SampleService;
import com.algalopez.ranking.sample.SampleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SampleServiceImplUnitTest {

    private static final int ONCE = 1;

    @Mock
    private SampleDao sampleDao;

    private SampleService sampleService;

    @Before
    public void prepareMocks() {
        sampleService = new SampleServiceImpl(sampleDao);
    }

    @Test
    public void testGetSample() {

        Integer id = 1;
        SampleDto mockedSample = new SampleDto();
        mockedSample.setId(id);
        mockedSample.setSample("example");

        when(sampleDao.getSample(anyInt())).thenReturn(mockedSample);

        SampleDto actualSample = sampleService.getSample(id);

        verify(sampleDao, times(ONCE)).getSample(id);
        assertEquals(mockedSample, actualSample);
    }

}
