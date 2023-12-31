package com.metrodata.serverapp.model.response;

import com.metrodata.serverapp.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse {
    private long id;
    private String code;
    private String name;
    private RegionResponse region;
}
