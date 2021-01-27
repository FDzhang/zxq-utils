package com.fd.demo.utils.city;

import lombok.Data;

@Data
public class City {
    /**
     * 城市名称
     */
    private String city;
    /**
     * 城市cityCode
     */
    private String city_code;
    /**
     * 父级城市名称
     */
    private String parent_city;
    /**
     * 父级城市cityCode
     */
    private String parent_city_code;

    private Integer level;
}
