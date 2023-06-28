package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class Main extends Application {
	
	public void start(Stage primaryStage) {
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

		button.setOnAction(e -> {
			Stage newStage = new Stage();
			newStage.setTitle("Administracion");
			VBox administracion = new VBox();
			Scene admi = new Scene(administracion, 200, 200);
			newStage.setScene(admi);
			newStage.show();

		});
		button2.setOnAction(e -> {
			Stage newStage = new Stage();
			newStage.setTitle("Registrar ingreso");
			GridPane registrarIngreso = new GridPane();
			registrarIngreso.setPadding(new Insets(10));
			registrarIngreso.setVgap(4);
			registrarIngreso.setHgap(9);
			
			Label encargado =new Label ("Datos del encargado");
			Label encargadoN =new Label ("Nombre");
			TextField textfield = new TextField();
			Label encargadoD =new Label ("DNI");
			TextField textfield2 = new TextField();
			Label cliente =new Label ("Datos del cliente");
			Label clienteN =new Label ("Nombre");
			TextField textfield3 = new TextField();
			Label clienteD =new Label ("DNI");
			TextField textfield4 = new TextField();
			Label vehiculo =new Label ("Datos del vehiculo");
			Label modelo =new Label ("Modelo");
			TextField textfield5 = new TextField();
			Label patente =new Label ("Patente");
			TextField textfield6 = new TextField();
			
			registrarIngreso.add(encargado, 0, 1);
			registrarIngreso.add(encargadoN, 2, 1);
			registrarIngreso.add(textfield, 3, 1);
			registrarIngreso.add(encargadoD, 2, 2);
			registrarIngreso.add(textfield2, 3, 2);
			registrarIngreso.add(cliente, 0, 4);
			registrarIngreso.add(clienteN, 2, 4);
			registrarIngreso.add(textfield3, 3, 4);
			registrarIngreso.add(clienteD, 2, 5);
			registrarIngreso.add(textfield4, 3, 5);
			registrarIngreso.add(vehiculo, 0, 7);
			registrarIngreso.add(modelo, 2, 7);
			registrarIngreso.add(textfield5, 3, 7);
			registrarIngreso.add(patente, 2, 8);
			registrarIngreso.add(textfield6, 3, 8);
			Scene ingreso = new Scene(registrarIngreso, 400, 300);
			
			newStage.setScene(ingreso);
			newStage.show();

		});
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
