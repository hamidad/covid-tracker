package com.covidtrackerbackend.dailycountryreport.controller;

import com.covidtrackerbackend.dailycountryreport.dal.model.DailyReportByCountry;
import com.covidtrackerbackend.dailycountryreport.service.DailyReportByCountryService;
import com.covidtrackerbackend.exceptions.Covid19Exception;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${cross.allowed.origins}")
@RequestMapping("/v1")
public class DailyReportByCountryController {

    private final DailyReportByCountryService dailyReportByCountryService;

    public DailyReportByCountryController(DailyReportByCountryService dailyReportByCountryService) {
        this.dailyReportByCountryService = dailyReportByCountryService;
    }

    @GetMapping("report/country/code")
    public DailyReportByCountry getDailyReportByCountryCode(@RequestParam String code, @RequestParam String date)
            throws Covid19Exception {
        return dailyReportByCountryService.getDailyReportByCountryCode(code, date);
    }
}
