package com.covidtrackerbackend.latestcountrydata.service;

import com.covidtrackerbackend.exceptions.Covid19Exception;
import com.covidtrackerbackend.latestcountrydata.dal.dao.LatestCountryDataDao;
import com.covidtrackerbackend.latestcountrydata.dal.entity.LatestCountryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LatestCountryDataServiceImpl implements LatestCountryDataService {
    @Autowired
    private LatestCountryDataDao latestCountryDataDaoImpl;

    @Override
    public List<LatestCountryData> findAll() {
        return latestCountryDataDaoImpl.getAll();
    }

    @Override
    public LatestCountryData getLatestCountryDataByCode(String code) throws Covid19Exception {
        return latestCountryDataDaoImpl.getLatestCountryDataByCode(code);
    }
}
