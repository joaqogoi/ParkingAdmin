package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.*;
import Domain.*;
import Service.*;

public class Main extends Application {

	public void start(Stage primaryStage) {
		
// creacion pantalla principal
		Button button = new Button("Administracion");
		Button button2 = new Button("Registrar ingreso");
		Button button3 = new Button("Registrar salida");
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(button, button2, button3);
		vbox.setAlignment(Pos.CENTER);

		BorderPane ventanaPrincipal = new BorderPane();
		ventanaPrincipal.setCenter(vbox);

		Scene scene = new Scene(ventanaPrincipal, 300, 250);
		primaryStage.setTitle("Bienvenido");
		primaryStage.setScene(scene);
		primaryStage.show();

// Ajustes de pantalla administracion
		button.setOnAction(e -> {
			Stage admiP = new Stage();
			admiP.setTitle("Administracion");
			GridPane registrarIngresoA = new GridPane();
			Scene ingresoA = new Scene(registrarIngresoA, 400, 200);
			registrarIngresoA.setPadding(new Insets(10));
			registrarIngresoA.setVgap(4);
			registrarIngresoA.setHgap(6);
			
// Creacion de textos, cuadros de texto y botones
			Label sucursales = new Label("Sucursales");
			ComboBox<String> sucursal = new ComboBox<String>();
			sucursal.setItems(FXCollections.observableArrayList("Sucursal 1", "Sucursal 2", "Sucursal 3"));
			Label encargado = new Label("Datos del encargado");
			Label encargadoN = new Label("Nombre");
			TextField textfield1 = new TextField();
			Label encargadoD = new Label("DNI");
			TextField textfield2 = new TextField();
			Button guardar2 = new Button("Guardar");
			
// Agregar los botones a la pantalla y seleccionar su ubicacion
			registrarIngresoA.add(sucursales, 0, 1);
			registrarIngresoA.add(sucursal, 2, 1);
			registrarIngresoA.add(encargado, 0, 4);
			registrarIngresoA.add(encargadoN, 0, 5);
			registrarIngresoA.add(textfield1, 2, 5);
			registrarIngresoA.add(encargadoD, 0, 6);
			registrarIngresoA.add(textfield2, 2, 6);
			registrarIngresoA.add(guardar2, 0, 8);
// Mostrar pagina
			admiP.setScene(ingresoA);
			admiP.show();
		});

// Ajustes de pantalla Registrar ingreso
		button2.setOnAction(e -> {
			Stage ingresoP = new Stage();
			ingresoP.setTitle("Registrar ingreso");
			GridPane registrarIngresoP = new GridPane();
			registrarIngresoP.setPadding(new Insets(10));
			registrarIngresoP.setVgap(4);
			registrarIngresoP.setHgap(6);

// Creacion de textos, cuadros de texto y botones
			Label cliente = new Label("Datos del cliente");
			Label clienteN = new Label("Nombre");
			TextField textfield3 = new TextField();
			Label clienteD = new Label("DNI");
			TextField textfield4 = new TextField();
			Label vehiculo = new Label("Datos del vehiculo");
			Label modelo = new Label("Modelo");
			TextField textfield5 = new TextField();
			Label patente = new Label("Patente");
			TextField textfield6 = new TextField();
			Button guardar = new Button("Guardar");

// Ajustes del boton guardar
			guardar.setOnAction(evento -> {
				
// Obtener los datos que se ingresan
				String nombreCliente = textfield3.getText();
				double dniCliente = Double.parseDouble(textfield4.getText());
				String modeloVehiculo = textfield5.getText();
				String patenteVehiculo = textfield6.getText();
				

// Instancia de la clase Cliente
				SucursalService sucursalService = new SucursalService(null);
sucursalService.agregarEventoInOut(dniCliente, nombreCliente, patenteVehiculo, modeloVehiculo);

			});
			
// Agregar los botones a la pantalla y seleccionar su ubicacion
			registrarIngresoP.add(cliente, 0, 1);
			registrarIngresoP.add(clienteN, 2, 1);
			registrarIngresoP.add(textfield3, 3, 1);
			registrarIngresoP.add(clienteD, 2, 2);
			registrarIngresoP.add(textfield4, 3, 2);
			registrarIngresoP.add(vehiculo, 0, 4);
			registrarIngresoP.add(modelo, 2, 4);
			registrarIngresoP.add(textfield5, 3, 4);
			registrarIngresoP.add(patente, 2, 5);
			registrarIngresoP.add(textfield6, 3, 5);
			registrarIngresoP.add(guardar, 0, 7);
			Scene ingreso = new Scene(registrarIngresoP, 400, 200);

// Mostrar pagina
			ingresoP.setScene(ingreso);
			ingresoP.show();
			 
		});
		
// Ajustes de pantalla Registrar salida
		button3.setOnAction(e -> {
			Stage newStage = new Stage();
			newStage.setTitle("Registrar Salida");
			VBox registrarSalida = new VBox();
			Scene salida = new Scene(registrarSalida, 200, 200);
			newStage.setScene(salida);
			newStage.show();

		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
