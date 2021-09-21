package com.covidtrackerbackend.latestcountrydata.dal.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "latest_country_data")
public class LatestCountryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    public String code;

    @Column(nullable = false)
    public String country;

    @Column(nullable = false)
    public long confirmed;

    @Column(nullable = false)
    public long recovered;

    @Column(nullable = false)
    public long critical;

    @Column(nullable = false)
    public long deaths;

    @Column(nullable = false)
    public double latitude;

    @Column(nullable = false)
    public double longitude;

    @Column(name = "last_change", nullable = false)
    public Date lastChange;

    @Column(name = "last_update", nullable = false)
    public Date lastUpdate;

    public LatestCountryData() {
        super();
    }
}