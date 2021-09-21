package com.covidtrackerbackend.latestcountrydata.controller;

import com.covidtrackerbackend.latestcountrydata.dal.entity.LatestCountryData;
import com.covidtrackerbackend.latestcountrydata.service.LatestCountryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class LatestCountryDataRepositoryController {

    @Autowired
    private LatestCountryDataService latestCountryDataService;

    @GetMapping("/country/all")
    public List<LatestCountryData> index() {
        return latestCountryDataService.findAll();
    }

    @GetMapping("/country/code")
    public LatestCountryData getLatestCountryDataByCode(@RequestParam String code) {
        return latestCountryDataService.getLatestCountryDataByCode(code);
    }
}
