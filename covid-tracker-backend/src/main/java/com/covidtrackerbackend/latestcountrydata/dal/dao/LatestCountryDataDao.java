package com.covidtrackerbackend.latestcountrydata.dal.dao;

import com.covidtrackerbackend.exceptions.Covid19Exception;
import com.covidtrackerbackend.latestcountrydata.dal.entity.LatestCountryData;
import java.util.List;

public interface LatestCountryDataDao {

    public List<LatestCountryData> findAll();

    public LatestCountryData getLatestCountryDataByCode(String code) throws Covid19Exception;

    public LatestCountryData getLatestCountryDataByCodeFromDB(String code);

    public LatestCountryData getLatestCountryDataByCodeExternally(String code) throws Covid19Exception;
}
