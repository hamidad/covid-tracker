package com.covidtrackerbackend.dailycountryreport.dal.dao;

import com.covidtrackerbackend.dailycountryreport.dal.model.DailyReportByCountry;
import com.covidtrackerbackend.exceptions.Covid19Exception;

public interface DailyReportByCountryDao {

    public DailyReportByCountry getDailyReportByCountryCode(String code, String date)
            throws Covid19Exception;

    public DailyReportByCountry getDailyReportByCountryCodeExternally(String code, String date)
            throws Covid19Exception;
}
