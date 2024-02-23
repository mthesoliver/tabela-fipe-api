package com.fipeproj.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleModel(List<VehicleTypeData> modelos){

}