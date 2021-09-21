package com.covidtrackerbackend.latestcountrydata.dal.dao;

import com.covidtrackerbackend.latestcountrydata.dal.entity.LatestCountryData;

import java.util.List;

public interface LatestCountryDataDao {
    public List<LatestCountryData> getAll();

    public LatestCountryData getLatestCountryDataByCode(String code);

    public LatestCountryData getLatestCountryDataByCodeFromDB(String code);

    public LatestCountryData getLatestCountryDataByCodeExternally(String code);
}
