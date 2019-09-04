package com.algalopez.ranking.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    private SampleDao sampleDao;

    @Autowired
    public SampleServiceImpl(SampleDao sampleDao) {
        this.sampleDao = sampleDao;
    }

    @Override
    public SampleDto getSample(Integer id) {
        return sampleDao.getSample(id);
    }
}
