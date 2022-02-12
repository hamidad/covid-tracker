package com.covidtrackerbackend.dailycountryreport.service;

import com.covidtrackerbackend.dailycountryreport.dal.model.DailyReportByCountry;
import com.covidtrackerbackend.exceptions.Covid19Exception;

import java.util.Date;

public interface DailyReportByCountryService {

    public DailyReportByCountry getDailyReportByCountryCode(String code, String date)
            throws Covid19Exception;
}
