package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainPaneController {

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

    private File file = null;

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
		FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pliki HTML (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        file = fileChooser.showOpenDialog(new Stage());
        if(file != null){
            HTMLEditor.setHtmlText(otworzPlik(file));
        }
	}
	@FXML
	public void Zapisz() throws IOException{
		if(file == null){
			ZapiszJako();
		}
		else{
			zapiszPlik(HTMLEditor.getHtmlText(), file);
		}
	}
	@FXML
	public void ZapiszJako() throws IOException{
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pliki .html (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Zapisz plik");
        file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
        	zapiszPlik(HTMLEditor.getHtmlText(), file);
        }

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
	private String otworzPlik(File file) throws IOException{
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
            }

        return stringBuffer.toString();
    }

	private void zapiszPlik(String content, File file) throws IOException{
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.close();

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

}
