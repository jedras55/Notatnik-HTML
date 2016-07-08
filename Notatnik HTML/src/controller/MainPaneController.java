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

	@FXML
	public void Nowy() throws IOException{
		String nowy = ZamknijOkno();
		if(nowy == "Zapisz"){
			Zapisz();
			HTMLEditor.setHtmlText("");
		}
		else if(nowy == "Nie zapisuj"){
			HTMLEditor.setHtmlText("");
		}
		else if(nowy == "Anuluj"){

		}
	}
	@FXML
	public void Otworz() throws IOException{
		HTMLEditor.setHtmlText(fileController.otworz());
	}
	@FXML
	public void Zapisz() throws IOException{
		fileController.zapisz(HTMLEditor.getHtmlText());
	}
	@FXML
	public void ZapiszJako() throws IOException{
		fileController.setFile(null);
		fileController.zapisz(HTMLEditor.getHtmlText());
	}
	@FXML
	public void Zakoncz() throws IOException{
		String zakoncz = ZamknijOkno();
		if(zakoncz == "Zapisz"){
			Zapisz();
			Platform.exit();
		}
		else if(zakoncz == "Nie zapisuj"){
			Platform.exit();
		}
		else if(zakoncz == "Anuluj"){

		}
	}

	@FXML
	public void OProgramie() throws IOException{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("O programie");
		alert.setHeaderText("Notatnik HTML");
		alert.setContentText("Notatnik HTML to prosty edytor strony HTML \nAutor: Jêdrzej Ostrowski");
		alert.showAndWait();
	}

	public String ZamknijOkno() throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Notatnik HTML");
		alert.setHeaderText("Czy chcesz zapisac plik?");

		ButtonType buttonTypeOne = new ButtonType("Zapisz");
		ButtonType buttonTypeTwo = new ButtonType("Nie zapisuj");
		ButtonType buttonTypeCancel = new ButtonType("Anuluj", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
		} else if (result.get() == buttonTypeTwo) {
		}
		return result.get().getText();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fileController = new FileController();
	}

}
