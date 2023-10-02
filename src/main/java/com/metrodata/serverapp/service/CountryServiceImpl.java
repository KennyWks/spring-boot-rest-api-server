package com.metrodata.serverapp.service;

import com.metrodata.serverapp.entity.Country;
import com.metrodata.serverapp.model.request.CountryRequest;
import com.metrodata.serverapp.model.response.CountryResponse;
import com.metrodata.serverapp.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService{

    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryResponse> getAll() {
        return countryRepository.findAll()
                .stream()
                .map(country -> {
                    return mappingCountryToCountryResponse(country);
                }).collect(Collectors.toList());
    }

    @Override
    public CountryResponse getById(long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country with given id " + id + " Not Found"));
        return mappingCountryToCountryResponse(country);
    }

    @Override
    public CountryResponse create(CountryRequest countryRequest) {
        Country country = countryRepository.save(mappingCountryRequestToCountry(countryRequest, new Country()));
//        country.setId(UUID.randomUUID().toString());
        return mappingCountryToCountryResponse(country);
    }

    @Override
    public CountryResponse update(long id, CountryRequest countryRequest) {
        CountryResponse oldData = getById(id);

        // Mapping Country Response -> Country
        Country country = mappingCountryResponseToCountry(oldData);

        // Mapping Country Request -> Country
        country = mappingCountryRequestToCountry(countryRequest,country);

        countryRepository.save(country);

        return mappingCountryToCountryResponse(country);
    }

    @Override
    public CountryResponse delete(long id) {
        CountryResponse country = getById(id);
        countryRepository.deleteById(id);
        return country;
    }

    public CountryResponse mappingCountryToCountryResponse(Country country){
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setId(country.getId());
        countryResponse.setName(country.getName());
        countryResponse.setCode(country.getCode());
        countryResponse.setRegionId(country.getRegionId());
        return countryResponse;
    }

    public Country mappingCountryRequestToCountry(CountryRequest countryRequest, Country country){
        country.setCode(countryRequest.getCode());
        country.setName(countryRequest.getName());
        country.setRegionId(countryRequest.getRegionId());
        return country;
    }

    public Country mappingCountryResponseToCountry(CountryResponse countryResponse){
        Country country = new Country();
        country.setId(countryResponse.getId());
        country.setName(countryResponse.getName());
        country.setCode(countryResponse.getCode());
        country.setRegionId(countryResponse.getRegionId());
        return country;
    }

}

