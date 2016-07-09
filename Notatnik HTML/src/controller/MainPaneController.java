package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class MainPaneController implements Initializable{

    @FXML
    private MenuItem Zakoncz;

    @FXML
    private MenuItem Nowy;

    @FXML
    private MenuItem Zapisz;

    @FXML
    private HTMLEditor HTMLEditor;

    @FXML
    private MenuItem ZapiszJako;

    @FXML
    private MenuItem Otworz;

    @FXML
    private Button NieZapisujButton;

    @FXML
    private Button AnulujButton;

    @FXML
    private Button ZapiszButton;

    private FileController fileController;

    public static Stage primaryStage;

	@FXML
	public void nowy() throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Notatnik HTML");
		alert.setHeaderText("Czy chcesz zapisac plik?");

		ButtonType buttonTypeZapisz = new ButtonType("Zapisz");
		ButtonType buttonTypeNieZapisuj = new ButtonType("Nie zapisuj");
		ButtonType buttonTypeCancel = new ButtonType("Anuluj", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeZapisz, buttonTypeNieZapisuj, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeZapisz){
			zapisz();
			HTMLEditor.setHtmlText("");
		} else if (result.get() == buttonTypeNieZapisuj) {
			HTMLEditor.setHtmlText("");
		}
	}

	@FXML
	public void otworz() throws IOException{
		HTMLEditor.setHtmlText(fileController.otworz());
	}
	@FXML
	public void zapisz() throws IOException{
		fileController.zapisz(HTMLEditor.getHtmlText());
	}
	@FXML
	public void zapiszJako() throws IOException{
		fileController.setFile(null);
		fileController.zapisz(HTMLEditor.getHtmlText());
	}
	@FXML
	public void zakoncz() throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Notatnik HTML");
		alert.setHeaderText("Czy chcesz zapisac plik przed zamkniêciem?");

		ButtonType buttonTypeZapisz = new ButtonType("Zapisz");
		ButtonType buttonTypeNieZapisuj = new ButtonType("Nie zapisuj");
		ButtonType buttonTypeCancel = new ButtonType("Anuluj", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeZapisz, buttonTypeNieZapisuj, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeZapisz){
			zapisz();
			Platform.exit();
		} else if (result.get() == buttonTypeNieZapisuj) {
			Platform.exit();
		}
	}

	@FXML
	public void oProgramie() throws IOException{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("O programie");
		alert.setHeaderText("Notatnik HTML");
		alert.setContentText("Notatnik HTML to prosty edytor strony HTML \nAutor: Jêdrzej Ostrowski");
		alert.showAndWait();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fileController = new FileController();
	}
}
