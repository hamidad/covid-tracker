package com.covidtrackerbackend.dailycountryreport.dal.dao;

import com.covidtrackerbackend.dailycountryreport.dal.model.DailyReportByCountry;
import com.covidtrackerbackend.exceptions.Covid19Exception;
import org.springframework.stereotype.Repository;

@Repository
public class DailyReportByCountryDaoImpl implements DailyReportByCountryDao {

    @Override
    public DailyReportByCountry getDailyReportByCountryCode(String code, String date)
            throws Covid19Exception {
        return null;
    }

    @Override
    public DailyReportByCountry getDailyReportByCountryCodeExternally(String code, String date)
            throws Covid19Exception {
        return null;
    }
}
