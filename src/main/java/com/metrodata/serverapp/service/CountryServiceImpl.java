package com.metrodata.serverapp.service;

import com.metrodata.serverapp.entity.Country;
import com.metrodata.serverapp.exception.CustomException;
import com.metrodata.serverapp.model.request.CountryRequest;
import com.metrodata.serverapp.model.response.CountryResponse;
import com.metrodata.serverapp.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService{

    private CountryRepository countryRepository;


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
                        "COUNTRY_NOT_FOUND",404));
        return mappingCountryToCountryResponse(country);
    }

    @Override
    public CountryResponse create(CountryRequest countryRequest) {
        if (countryRepository.findByNameOrCode(countryRequest.getName(), countryRequest.getCode()).isPresent()){
            throw new CustomException("Country with name : " + countryRequest.getName() + " is already exists",
                    "COUNTRY_NAME_EXISTS",400);
        };
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
        BeanUtils.copyProperties(country,countryResponse);
        return countryResponse;
    }

    public Country mappingCountryRequestToCountry(CountryRequest countryRequest, Country country){
        BeanUtils.copyProperties(countryRequest,country);
        return country;
    }

    public Country mappingCountryResponseToCountry(CountryResponse countryResponse){
        Country country = new Country();
        BeanUtils.copyProperties(countryResponse,country);
        return country;
    }

}

