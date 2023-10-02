package com.metrodata.serverapp.service;

import com.metrodata.serverapp.model.request.CountryRequest;
import com.metrodata.serverapp.model.response.CountryResponse;

import java.util.List;

public interface CountryService {

    List<CountryResponse> getAll();
    CountryResponse getById(long id);
    CountryResponse create(CountryRequest countryRequest);
    CountryResponse update(long id, CountryRequest countryRequest);
    CountryResponse delete(long id);



}
