package com.fipeproj.consultafipe.main;

import com.fipeproj.consultafipe.services.OperationsRequest;

import java.util.Scanner;

public class Main {

    private Scanner scanner = new Scanner(System.in);
    private OperationsRequest operations = new OperationsRequest();

    public void searchMenu(){
        System.out.println("""
                *** Escolha o tipo de consulta que deseja fazer: ***
                
                1 - Carro
                2 - Moto
                3 - Caminhão
                
                Digite qual das opções deseja:
                """);
        var vehicleType = scanner.nextLine();
        operations.requestTypeVehicle(vehicleType);

        System.out.println("*** Informe agora o a marca pelo código: ***");
        var model = scanner.nextInt();
        scanner.nextLine();
        operations.requestVehiclesModel(model);

        System.out.println("*** Digite um trecho do nome do veículo: ***");
        var name = scanner.nextLine();
        operations.filterVehiclesByName(name);

        System.out.println("*** Digite o código do modelo para consultar os valores: ***");
        var modelCode = scanner.nextInt();
        scanner.nextLine();
        operations.requestModelCode(model, modelCode);
    }

}
