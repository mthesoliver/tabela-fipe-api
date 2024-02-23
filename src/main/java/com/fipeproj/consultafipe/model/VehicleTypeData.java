package com.fipeproj.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleTypeData(String codigo,
                              String nome){
}