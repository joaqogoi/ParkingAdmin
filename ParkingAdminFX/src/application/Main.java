package application;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.*;

import java.util.List;

import Domain.*;
import Service.*;

public class Main extends Application {

	public void start(Stage primaryStage) {
		
// creacion pantalla principal
		Stage pagP = new Stage();
		pagP.setTitle("Inicio");
		GridPane pantallaPrincipal = new GridPane();
		Scene inicio = new Scene(pantallaPrincipal, 400, 200);
		pantallaPrincipal.setPadding(new Insets(10));
		pantallaPrincipal.setVgap(4);
		pantallaPrincipal.setHgap(4);
		
// Creacion de textos, cuadros de texto y botones
		Label sucursales = new Label("Sucursales");
		ComboBox<String> sucursal = new ComboBox<>();
		SucursalService sucuService1 = new SucursalService();
		String[] listaSucursales = sucuService1.obtenerIdSucursalesPrueba();
		
		ObservableList<String> opcionesSucursales = FXCollections.observableArrayList(listaSucursales);
		
		sucursal.setItems(opcionesSucursales);
		
		Label encargados = new Label("Encargados");
		ComboBox encargado = new ComboBox();
		encargado.setItems(FXCollections.observableArrayList("Encargado 1", "Encargado 2", "Encargado 3"));
		Button button = new Button("Registrar ingreso");
		Button button2 = new Button("Registrar salida");
		Button button3 = new Button("Sucursales");
		Button button4 = new Button("Encargados");
		Button button5 = new Button("Finalizar turno");
		
// Agregar los botones a la pantalla y seleccionar su ubicacion
		pantallaPrincipal.add(sucursales, 0, 1);
		pantallaPrincipal.add(sucursal, 2, 1);
		pantallaPrincipal.add(encargados, 0, 4);
		pantallaPrincipal.add(encargado, 2, 4);
		pantallaPrincipal.add(button, 0, 6);
		pantallaPrincipal.add(button2, 2, 6);
		pantallaPrincipal.add(button3, 4, 6);
		pantallaPrincipal.add(button4, 0, 8);
		pantallaPrincipal.add(button5, 2, 8);

//Mantener las opciones ocultas hasta seleccionar una sucursal
		encargados.setVisible(false);
		encargado.setVisible(false);
		button.setVisible(false);
		button2.setVisible(false);
		button3.setVisible(false);
		button4.setVisible(false);
		button5.setVisible(false);

		
		sucursal.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		
		    if (newValue != null) {
		        encargados.setVisible(true);
		        encargado.setVisible(true);
		        button.setVisible(true);
		        button2.setVisible(true);
		        button3.setVisible(true);
		        button4.setVisible(true);
		        button5.setVisible(true);
		    
		    } else {
		        encargados.setVisible(false);
		        encargado.setVisible(false);
		        button.setVisible(false);
		        button2.setVisible(false);
		        button3.setVisible(false);
		        button4.setVisible(false);
		        button5.setVisible(false);
		    
		    }
		});
		
// Mostrar pagina
					pagP.setScene(inicio);
					pagP.show();
					
// Creacion y ajustes de pantalla Registrar ingreso
		button.setOnAction(e -> {
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
				double patenteVehiculo = Double.parseDouble(textfield6.getText());
				

// Instancia de la clase Cliente
				//SucursalService sucursalService = new SucursalService(null);
//sucursalService.agregarEventoInOut(dniCliente, nombreCliente, patenteVehiculo, modeloVehiculo);

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
		
// Creacion y ajustes de pantalla Registrar salida
		button2.setOnAction(e -> {
			Stage salida = new Stage();
			salida.setTitle("Registrar salida");
			GridPane registrarSalida = new GridPane();
			registrarSalida.setPadding(new Insets(10));
			registrarSalida.setVgap(4);
			registrarSalida.setHgap(6);
			
// Creacion de textos, cuadros de texto y botones
			Label factura = new Label("Factura");
			TextField textfield7 = new TextField();
			//cuadro info
			Button finalizar = new Button("Finalizar");			

// Ajustes del boton guardar
			finalizar.setOnAction(evento2 -> {	
						});
						
// Agregar los botones a la pantalla y seleccionar su ubicacion
			registrarSalida.add(factura, 0, 1);
			registrarSalida.add(textfield7, 2, 1);
			//cuadro con los datos
			registrarSalida.add(finalizar, 3, 1);
			Scene salidaP = new Scene(registrarSalida, 400, 200);

// Mostrar pagina
			salida.setScene(salidaP);
			salida.show();	 
					});

// Creacion y ajustes de pantalla Sucursales
			button3.setOnAction(e -> {
			Stage ajustesS = new Stage();
			ajustesS.setTitle("Sucursales");
			GridPane Sucursal = new GridPane();
			Sucursal.setPadding(new Insets(10));
			Sucursal.setVgap(4);
			Sucursal.setHgap(6);
					
// Creacion de textos, cuadros de texto y botones
			Label datosS = new Label("Datos de la nueva sucursal");
			Label direccion = new Label("Direccion");
			TextField textfield7 = new TextField();
			Label valorXhora = new Label("Valor por hora");
			TextField textfield8 = new TextField();
			Button nuevaS = new Button("Nueva sucursal");			

// Ajustes del boton guardar
			nuevaS.setOnAction(evento2 -> {	
								});
								
// Agregar los botones a la pantalla y seleccionar su ubicacion
			Sucursal.add(datosS, 0, 1);
			Sucursal.add(direccion, 0, 3);
			Sucursal.add(textfield7, 1, 3);
			Sucursal.add(valorXhora, 0, 4);
			Sucursal.add(textfield8, 1, 4);
			Sucursal.add(nuevaS, 0, 6);
			Scene sucursalP = new Scene(Sucursal, 400, 200);

// Mostrar pagina
			ajustesS.setScene(sucursalP);
			ajustesS.show();	 
							});	
			
// Creacion y ajustes de pantalla Encargados
			button4.setOnAction(e -> {
			Stage ajustesE = new Stage();
			ajustesE.setTitle("Encargados");
			GridPane Encargado = new GridPane();
			Encargado.setPadding(new Insets(10));
			Encargado.setVgap(4);
			Encargado.setHgap(6);		
			
// Creacion de textos, cuadros de texto y botones
			Label datosE = new Label("Datos del nuevo encargado");
			Label nombreE = new Label("Nombre");
			TextField textfield9 = new TextField();
			Label dniE = new Label("DNI");
			TextField textfield10 = new TextField();
			Button nuevoE = new Button("Contratar encargado");			

// Ajustes del boton guardar
			nuevoE.setOnAction(evento2 -> {	
											});
											
// Agregar los botones a la pantalla y seleccionar su ubicacion
			Encargado.add(datosE, 0, 1);
			Encargado.add(nombreE, 0, 3);
			Encargado.add(textfield9, 1, 3);
			Encargado.add(dniE, 0, 4);
			Encargado.add(textfield10, 1, 4);
			Encargado.add(nuevoE, 0, 6);
			Scene encargadoP = new Scene(Encargado, 400, 200);

// Mostrar pagina
			ajustesE.setScene(encargadoP);
			ajustesE.show();	 
							});	
// Creacion y ajustes de pantalla Facturar
			button5.setOnAction(e -> {
			Stage facturar = new Stage();
			facturar.setTitle("Facturar");
			GridPane facturacion = new GridPane();
			facturacion.setPadding(new Insets(10));
			facturacion.setVgap(4);
			facturacion.setHgap(6);		
						
// Creacion de textos, cuadros de texto y botones
			Label facturarDia = new Label("Calcular la facturacion de la jornada");
			Button calcular = new Button("Calcular");
			//cuadro con los datos
			Button guardarF = new Button("Guardar");		
			Button finalizar = new Button("Terminar jornada");
// Ajustes del boton guardar
			guardarF.setOnAction(evento2 -> {	
														});
// Ajustes del boton terminar jornada
			finalizar.setOnAction(evento3 -> {	
			Stage stage = (Stage) finalizar.getScene().getWindow();
		    stage.close();
				  });

								
														
// Agregar los botones a la pantalla y seleccionar su ubicacion
			facturacion.add(facturarDia, 0, 1);
			facturacion.add(calcular, 0, 3);
			//cuadro con los datos
			facturacion.add(guardarF, 0, 6);
			facturacion.add(finalizar, 1, 6);
			
			Scene facturacionP = new Scene(facturacion, 400, 200);

// Mostrar pagina
			facturar.setScene(facturacionP);
			facturar.show();	 
										});	
			
			
		}
	

	public static void main(String[] args) {
		launch(args);
	}

}