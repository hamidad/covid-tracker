package com.covidtrackerbackend.latestcountrydata.service;

import com.covidtrackerbackend.exceptions.Covid19Exception;
import com.covidtrackerbackend.latestcountrydata.dal.entity.LatestCountryData;
import java.util.List;

public interface LatestCountryDataService {
    public List<LatestCountryData> findAll();

    public LatestCountryData getLatestCountryDataByCode(String code) throws Covid19Exception;
}
