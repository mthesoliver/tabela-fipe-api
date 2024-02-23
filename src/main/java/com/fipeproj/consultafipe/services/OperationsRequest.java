package com.fipeproj.consultafipe.services;

import com.fipeproj.consultafipe.model.Vehicle;
import com.fipeproj.consultafipe.model.VehicleModel;
import com.fipeproj.consultafipe.model.VehicleTypeData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OperationsRequest {
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    private ConsumoApi consumoApi = new ConsumoApi();
    private DataConverter converter = new DataConverter();
    private String vehycleBaseSearch;
    private List<VehicleTypeData> vehiclesList;

    public void requestTypeVehicle(String vehicleType){
        if(vehicleType.toLowerCase().contains("car")){
            this.vehycleBaseSearch = "carros";
        } else if (vehicleType.toLowerCase().contains("mot")) {
            this.vehycleBaseSearch = "motos";
        } else if (vehicleType.toLowerCase().contains("camin")) {
            this.vehycleBaseSearch = "caminhoes";
        }else {
            System.out.println("*** POR FAVOR DIGITE UM CAMPO VÁLIDO ***");
        }

        var data = consumoApi.recieveData(ENDERECO + this.vehycleBaseSearch + "/marcas/");
        var vehicleBrand = converter.recieveList(data, VehicleTypeData.class);
        vehicleBrand.stream()
                .sorted(Comparator.comparing( VehicleTypeData::codigo))
                .forEach(v ->{
                    System.out.println("Código: " + v.codigo());
                    System.out.println("Marca: " + v.nome() + "\n");
                });
    }

    public List<VehicleTypeData> requestVehiclesModel(int model) {
        var data = consumoApi.recieveData(ENDERECO + this.vehycleBaseSearch + "/marcas/" + model + "/modelos/");
        var vehicleModel = converter.recieveData(data, VehicleModel.class);

        List<VehicleTypeData> vehicleTypesList = vehicleModel.modelos().stream()
                .collect(Collectors.toList());

        vehicleTypesList.stream()
                .sorted(Comparator.comparing(VehicleTypeData::codigo))
                .forEach(v ->{
                    System.out.println("Código: " + v.codigo());
                    System.out.println("Modelo: " + v.nome() + "\n");
                });

        return vehiclesList = vehicleTypesList;
    }


    public void filterVehiclesByName(String name) {
        vehiclesList.stream()
                .filter(v -> v.nome().toLowerCase().contains(name.toLowerCase()))
                .forEach(v -> {
                    System.out.println("Código: " + v.codigo());
                    System.out.println("Modelo: " + v.nome() + "\n");
                });
    }

    public void requestModelCode(int model, int modelCode) {
        var data = consumoApi.recieveData(ENDERECO + this.vehycleBaseSearch + "/marcas/" + model + "/modelos/" + modelCode + "/anos/");
        var vehicleModel = converter.recieveList(data, VehicleTypeData.class);

        List<VehicleTypeData> listVehicles = vehicleModel.stream()
                        .collect(Collectors.toList());


        System.out.println("*** Todos os veículos com os valores por ano: ***");
        listVehicles.stream()
                        .forEach(v ->{
                            var dataYear = consumoApi.recieveData(ENDERECO + this.vehycleBaseSearch + "/marcas/" + model + "/modelos/" + modelCode + "/anos/" + v.codigo());
                            var vehicleInfos = converter.recieveData(dataYear, Vehicle.class);
                            System.out.println(String.format("""
                                    Marca: %s
                                    Modelo: %s
                                    Ano: %s
                                    Valor: %s
                                    Tipo de Combustível: %s
                                    """,
                                    vehicleInfos.marca(),
                                    vehicleInfos.modelo(),
                                    vehicleInfos.ano(),
                                    vehicleInfos.valor(),
                                    vehicleInfos.combustivel()));
                        });

    }

}