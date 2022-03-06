package com.covidtrackerbackend.utils.rapidapi;

import com.covidtrackerbackend.constants.RapidApiConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

public class RapidApiHttpHeadersBuilder {

    private HttpHeaders httpHeaders;

    public RapidApiHttpHeadersBuilder() {};

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public RapidApiHttpHeadersBuilder createHttpHeaders() {
        httpHeaders = new HttpHeaders();

        return this;
    }

    public RapidApiHttpHeadersBuilder setHttpHeadersAcceptApplicationJson() {
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return this;
    }

    public RapidApiHttpHeadersBuilder setHttpHeadersKey() {
        httpHeaders.set("x-rapidapi-key", RapidApiConstants.RAPID_API_KEY);

        return this;
    }

    public RapidApiHttpHeadersBuilder setHttpHeadersHost() {
        httpHeaders.set("x-rapidapi-host", RapidApiConstants.RAPID_API_HOST);

        return this;
    }

}
