package application;

import javafx.collections.FXCollections;



import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.application.Platform;




import Exception.DataAccessException;
import Exception.DataReadingException;
import Service.*;

public class Main extends Application {
	
	private int idSucursal = 0;
	private double idEncargado = 0;
	private SucursalService sucuService1;
	private String[] listaSucursales;
	
	public static void main1(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		SucursalService sucuService1 = new SucursalService();
		String[] listaSucursales = sucuService1.obtenerIdSucursales();

		if (listaSucursales.length > 0) {
			cargarPantallaPrincipal(sucuService1, listaSucursales);	
		} else {
			crearSucursal(sucuService1);
	}
}
	
	private void cargarPantallaPrincipal (SucursalService sucuService1, String[] listaSucursales) {
		
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
				
				String[] listaSucursales1 = sucuService1.obtenerIdSucursales();
				ObservableList<String> opcionesSucursales = FXCollections.observableArrayList(listaSucursales1);
				sucursal.setItems(opcionesSucursales);
				
				Label encargados = new Label("Encargados");
				ComboBox<String> encargado = new ComboBox<>();

				
				sucursal.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
					System.out.println(newValue);
				    if (newValue != null) {
				    	
				    	String numericValue = newValue.replaceAll("[^\\d]", "");
				    	System.out.println(newValue);
				    	idSucursal = Integer.parseInt(numericValue);				    	
				    	try {
							sucuService1.cargarSucursal(idSucursal);
						} catch (DataReadingException e) {
							e.printStackTrace();
						} catch (DataAccessException e1) {
							e1.printStackTrace();
						}
				    	
				    	encargados.setVisible(true);
				    	encargado.setVisible(true);
				    	
				    	String[] listaEncargados = sucuService1.obtenerNombresEncargados();
						ObservableList<String> opcionesEncargados = FXCollections.observableArrayList(listaEncargados);
						encargado.setItems(opcionesEncargados);
				        
				    	
				    	} else {
				    	encargados.setVisible(false);
				    	encargado.setVisible(false);
				    
				    	}
				
						});
					
				
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

				
				
		   encargado.getSelectionModel().selectedItemProperty().addListener((observableE, oldValueE, newValueE) -> {

			    if (newValueE != null) {
			    	String numericValue = newValueE.replaceAll("[^\\d]", "");
			    	idEncargado = Double.parseDouble(numericValue);
			    	
			    	
			    	sucuService1.establecerEncargadoActivo(idEncargado);
			    				    	
			        button.setVisible(true);
					button2.setVisible(true);
					button3.setVisible(true);
					button4.setVisible(true);
					button5.setVisible(true);
			    } else {
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
						Stage datos = new Stage();
						datos.setTitle("Factura");
						GridPane mostrarDatos = new GridPane();
						mostrarDatos.setPadding(new Insets(10));
						mostrarDatos.setVgap(4);
						mostrarDatos.setHgap(6);
						
		// Obtener los datos que se ingresan
						String nombreCliente = textfield3.getText();
						double dniCliente = Double.parseDouble(textfield4.getText());
						String modeloVehiculo = textfield5.getText();
						String patenteVehiculo = textfield6.getText();
						
						String[] datosEvento = sucuService1.agregarEventoInOut(dniCliente, nombreCliente, patenteVehiculo, modeloVehiculo);
						ListView<String> facturaDatos = new ListView<>();
						ObservableList<String> datosF = FXCollections.observableArrayList(datosEvento);
						facturaDatos.setItems(datosF);
						mostrarDatos.add(facturaDatos, 0, 1);
						
						textfield3.setText("");
						textfield4.setText("");
						textfield5.setText("");
						textfield6.setText("");
		// Mostrar pantalla				
						Scene mostrarD = new Scene(mostrarDatos, 400, 200);
						datos.setScene(mostrarD);
						datos.show();
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
						Stage datos2 = new Stage();
						datos2.setTitle("Factura");
						GridPane mostrarDatos = new GridPane();
						mostrarDatos.setPadding(new Insets(10));
						mostrarDatos.setVgap(4);
						mostrarDatos.setHgap(6);
						Double idFactura = Double.parseDouble(textfield7.getText());
						String[] datosSalida = sucuService1.registrarSalida(idFactura);
						
						ListView<String> facturaDatos = new ListView<>();
						ObservableList<String> datosF = FXCollections.observableArrayList(datosSalida);
						facturaDatos.setItems(datosF);
						mostrarDatos.add(facturaDatos, 0, 1);
						
		// Mostrar pantalla				
						Scene mostrarD = new Scene(mostrarDatos, 400, 200);
						datos2.setScene(mostrarD);
						datos2.show();
									
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
						crearSucursal(sucuService1);
									});
					
					
		// Creacion y ajustes de pantalla Encargados
					button4.setOnAction(e -> {
						crearEncargado(sucuService1);
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
						double facturacionDelDia = sucuService1.calcularFacturacionDelDia();
						//devuelve un double con el total de la facturacion que hay que mostrar en pantalla
						
					});
		// Ajustes del boton terminar jornada
					finalizar.setOnAction(evento3 -> {
						
						boolean salida = sucuService1.obtenerEventosSinRegistroDeSalida();
						if(salida = true) {
							String cierreStatus = "Existen ingresos que no registran salida";
						} else {
							
							try {
								sucuService1.guardarSucursal();
							} catch (DataAccessException e1) {
								e1.printStackTrace();
							}
							
							Stage stage = (Stage) finalizar.getScene().getWindow();
						    stage.close();
						}
					
					
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
	
	
	private void crearSucursal (SucursalService sucuService1) {
		
		Stage ajustesS = new Stage();
		ajustesS.setTitle("Sucursales");
		GridPane Sucursal = new GridPane();
		Sucursal.setPadding(new Insets(10));
		Sucursal.setVgap(4);
		Sucursal.setHgap(6);
				
//Creacion de textos, cuadros de texto y botones
		Label datosS = new Label("Datos de la nueva sucursal");
		Label direccion = new Label("Direccion");
		TextField textfield7 = new TextField();
		Label valorXhora = new Label("Valor por hora");
		TextField textfield8 = new TextField();
		Button nuevaS = new Button("Nueva sucursal");			

//Ajustes del boton guardar
		nuevaS.setOnAction(evento2 -> {	
		//Obtener valores de sucursal
			String direccionNueva = textfield7.getText();
			double valorXhoraNuevo = Double.parseDouble(textfield8.getText());
			
			try {
				sucuService1.crearSucursal(direccionNueva, valorXhoraNuevo);
			} catch (DataAccessException e) {
				e.printStackTrace();
				}
			
			crearEncargado(sucuService1);
			
			Stage stage = (Stage) nuevaS.getScene().getWindow();
		    stage.close();
		});
			
			
							
//Agregar los botones a la pantalla y seleccionar su ubicacion
		Sucursal.add(datosS, 0, 1);
		Sucursal.add(direccion, 0, 3);
		Sucursal.add(textfield7, 1, 3);
		Sucursal.add(valorXhora, 0, 4);
		Sucursal.add(textfield8, 1, 4);
		Sucursal.add(nuevaS, 0, 6);
		Scene sucursalP = new Scene(Sucursal, 400, 200);

//Mostrar pagina
		ajustesS.setScene(sucursalP);
		ajustesS.show();
		
		}
	
	
	private void crearEncargado(SucursalService sucuService1) {
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
		Button nuevoE = new Button("Contratar Encargado");
		Button principalBtn = new Button("Pantalla Principal");

// Ajustes del boton guardar
		nuevoE.setOnAction(evento2_2 -> {
			String nombreEncargado = textfield9.getText();
			double dniEncargado = Double.parseDouble(textfield10.getText());
			
			try {
				sucuService1.crearEncargado(dniEncargado, nombreEncargado);
			} catch (DataAccessException e) {
				System.err.println("Error al crear un nuevo encargado");
				}
			textfield9.setText("");
			textfield10.setText("");
			
		});
		
		principalBtn.setOnAction(evento2_3 -> {
			Stage stage = (Stage) principalBtn.getScene().getWindow();
		    stage.close();
		    
		    Platform.runLater(() -> {
		    	Platform.runLater(() -> cargarPantallaPrincipal(sucuService1, listaSucursales));
		    });
		    
		    
		});
										
// Agregar los botones a la pantalla y seleccionar su ubicacion
		Encargado.add(datosE, 0, 1);
		Encargado.add(nombreE, 0, 3);
		Encargado.add(textfield9, 1, 3);
		Encargado.add(dniE, 0, 4);
		Encargado.add(textfield10, 1, 4);
		Encargado.add(nuevoE, 0, 6);
		Encargado.add(principalBtn,0,8);
		Scene encargadoP = new Scene(Encargado, 400, 200);

// Mostrar pagina
		ajustesE.setScene(encargadoP);
		ajustesE.show();
	}
	

	public static void main(String[] args) {
		launch(args);
	}

}