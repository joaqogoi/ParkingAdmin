module ParkingAdminFX {
    requires javafx.controls;
	requires com.google.gson;

    opens application to javafx.graphics, javafx.fxml;
    
    opens Domain to com.google.gson;
    
}