package com.metrodata.serverapp.service;

import com.metrodata.serverapp.entity.Country;
import com.metrodata.serverapp.entity.Region;
import com.metrodata.serverapp.exception.CustomException;
import com.metrodata.serverapp.model.request.CountryRequest;
import com.metrodata.serverapp.model.response.CountryResponse;
import com.metrodata.serverapp.model.response.RegionResponse;
import com.metrodata.serverapp.repository.CountryRepository;
import com.metrodata.serverapp.repository.RegionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;
    private RegionRepository regionRepository;

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
                .orElseThrow(() -> new CustomException(
                        "Country with given id " + id + " Not Found",
                        "COUNTRY_NOT_FOUND", 404));
        log.info("getById with given id : {} is success",id);
        return mappingCountryToCountryResponse(country);
    }

    @Override
    public CountryResponse create(CountryRequest countryRequest) {
        if (countryRepository.findByNameOrCode(countryRequest.getName(), countryRequest.getCode()).isPresent()) {
            log.error("Name or Code is already exists : {}", countryRequest);
            throw new CustomException("Country with name : " + countryRequest.getName() + " is already exists",
                    "COUNTRY_NAME_EXISTS", 400);
        }
        Country country = countryRepository.save(mappingCountryRequestToCountry(countryRequest, new Country()));
        return mappingCountryToCountryResponse(country);
    }

    @Override
    public CountryResponse update(long id, CountryRequest countryRequest) {
        CountryResponse oldData = getById(id);

        // Mapping Country Response -> Country
        Country country = mappingCountryResponseToCountry(oldData);

        // Mapping Country Request -> Country
        country = mappingCountryRequestToCountry(countryRequest, country);

        countryRepository.save(country);

        return mappingCountryToCountryResponse(country);
    }

    @Override
    public CountryResponse delete(long id) {
        CountryResponse country = getById(id);
        countryRepository.deleteById(id);
        return country;
    }

    public CountryResponse mappingCountryToCountryResponse(Country country) {
        CountryResponse countryResponse = new CountryResponse();
        BeanUtils.copyProperties(country, countryResponse);

        if (country.getRegion() != null){
            RegionResponse regionResponse = new RegionResponse();
            BeanUtils.copyProperties(country.getRegion(),regionResponse);
            countryResponse.setRegion(regionResponse);
//            country.getRegion().setCountries(new ArrayList<>());
        }
        return countryResponse;
    }

    public Country mappingCountryRequestToCountry(CountryRequest countryRequest, Country country) {
        BeanUtils.copyProperties(countryRequest, country);
        // #1
//        Region region = new Region();
//        region.setId(countryRequest.getRegionId());

//        #2
        Region region = regionRepository.findById(countryRequest.getRegionId()).get();
        country.setRegion(region);
        return country;
    }

    public Country mappingCountryResponseToCountry(CountryResponse countryResponse) {
        Country country = new Country();
        BeanUtils.copyProperties(countryResponse, country);
        return country;
    }

}

