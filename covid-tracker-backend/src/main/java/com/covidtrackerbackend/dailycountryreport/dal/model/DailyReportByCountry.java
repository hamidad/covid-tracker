package com.covidtrackerbackend.dailycountryreport.dal.model;

import java.util.List;

public class DailyReportByCountry {

    public String country;

    public List<Province> provinces;

    public double latitude;

    public double longitude;

    // DATE (YYYY-MM-DD)
    public String date;
}
