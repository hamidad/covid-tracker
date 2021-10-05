package com.covidtrackerbackend.latestcountrydata.controller;

import com.covidtrackerbackend.exceptions.Covid19Exception;
import com.covidtrackerbackend.latestcountrydata.dal.entity.LatestCountryData;
import com.covidtrackerbackend.latestcountrydata.service.LatestCountryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "${cross.allowed.origins}")
@RequestMapping("/v1")
public class LatestCountryDataController {

    @Autowired
    private LatestCountryDataService latestCountryDataService;

    @GetMapping("/country/all")
    public List<LatestCountryData> index() {
        return latestCountryDataService.findAll();
    }

    @GetMapping("/country/code")
    public LatestCountryData getLatestCountryDataByCode(@RequestParam String code) throws Covid19Exception {
        return latestCountryDataService.getLatestCountryDataByCode(code);
    }
}
