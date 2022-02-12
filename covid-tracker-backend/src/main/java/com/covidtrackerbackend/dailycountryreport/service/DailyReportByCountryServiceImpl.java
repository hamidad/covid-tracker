package com.covidtrackerbackend.dailycountryreport.service;

import com.covidtrackerbackend.dailycountryreport.dal.dao.DailyReportByCountryDao;
import com.covidtrackerbackend.dailycountryreport.dal.model.DailyReportByCountry;
import com.covidtrackerbackend.exceptions.Covid19Exception;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DailyReportByCountryServiceImpl implements DailyReportByCountryService {

    private final DailyReportByCountryDao dailyReportByCountryDao;

    public DailyReportByCountryServiceImpl(DailyReportByCountryDao dailyReportByCountryDao) {
        this.dailyReportByCountryDao = dailyReportByCountryDao;
    }

    @Override
    public DailyReportByCountry getDailyReportByCountryCode(String code, String date)
            throws Covid19Exception {
        return dailyReportByCountryDao.getDailyReportByCountryCode(code, date);
    }
}
