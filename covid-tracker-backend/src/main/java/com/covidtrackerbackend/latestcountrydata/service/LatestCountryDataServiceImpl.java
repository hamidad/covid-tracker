package com.covidtrackerbackend.latestcountrydata.service;

import com.covidtrackerbackend.exceptions.Covid19Exception;
import com.covidtrackerbackend.latestcountrydata.dal.dao.LatestCountryDataDao;
import com.covidtrackerbackend.latestcountrydata.dal.entity.LatestCountryData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LatestCountryDataServiceImpl implements LatestCountryDataService {

    private final LatestCountryDataDao latestCountryDataDao;

    public LatestCountryDataServiceImpl(LatestCountryDataDao latestCountryDataDao) {
        this.latestCountryDataDao = latestCountryDataDao;
    }

    @Override
    public List<LatestCountryData> findAll() {
        return latestCountryDataDao.findAll();
    }

    @Override
    public LatestCountryData getLatestCountryDataByCode(String code) throws Covid19Exception {
        return latestCountryDataDao.getLatestCountryDataByCode(code);
    }
}
