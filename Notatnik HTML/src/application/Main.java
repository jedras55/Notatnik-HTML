package application;

import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent parent = (Parent)FXMLLoader.load(getClass().getResource("/view/MainPane.fxml"));
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Notatnik HTML");
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() { // Event który pyta czy na pewno zamkn¹æ okno - wywo³ywany przy zamkniêciu aplikacji krzy¿ykiem
			@Override
			public void handle(WindowEvent event) {
				event.consume();

		        Alert alert = new Alert(AlertType.CONFIRMATION);
		        alert.setTitle("Czy zamkn¹æ");
		        alert.setHeaderText("Czy na pewno chcesz zamkn¹æ?");
		        alert.initOwner( primaryStage);

		        Optional<ButtonType> result = alert.showAndWait();
		        if (result.get() == ButtonType.OK){
		            Platform.exit();
		        }
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}