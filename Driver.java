package Phase2;

import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Driver extends Application{
	private static BinarySearchTree districts = new BinarySearchTree();
	private Alert alert;
	private static File file;
	@Override
	public void start(Stage stg) throws Exception {
		alert = new Alert(AlertType.WARNING);
		alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
		alert.setTitle("Warning Dialog");
        alert.setHeaderText("This is a warning!");
        alert.setContentText("The last changes aren't saved, are you sure to close the program");
        ButtonType okButton = alert.getButtonTypes().get(0);
		stg.setTitle("FREE PALESTINE");
		Image icon = new Image(new FileInputStream("palestine.png"));
		stg.setOnCloseRequest(e->{
			if(!MenuFX.isSaved()) {
				alert.showAndWait();
				if (alert.getResult() == okButton) {
					MenuFX.writeToFile();
					stg.close();
				}
				else stg.close();
			}
		});
		stg.getIcons().add(icon);
		stg.setScene(new Scene(new MenuFX().getVb()));
		stg.setMaximized(true);
		stg.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static BinarySearchTree getDistricts() {
		return districts;
	}

	public static void setDistricts(BinarySearchTree districts) {
		Driver.districts = districts;
	}

	public static File getFile() {
		return file;
	}

	public static void setFile(File file) {
		Driver.file = file;
	}
	

}
