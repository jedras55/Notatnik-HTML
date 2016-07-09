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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.web.HTMLEditor;

public class MainPaneController implements Initializable{

	@FXML
    private MenuItem Nowy;

	@FXML
    private MenuItem Otworz;

	@FXML
    private MenuItem Zapisz;

	@FXML
    private MenuItem ZapiszJako;

    @FXML
    private MenuItem Zakoncz;

    @FXML
    private HTMLEditor HTMLEditor;

    private FileController fileController;

	@FXML
	public void nowy() throws IOException{ // Wyœwietla alert pytaj¹cy czy zapisaæ zawartoœæ edytora przed wyczyszczeniem
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
		// Wywo³uje metodê klasy FileController, która pobiera z pliku zawartoœæ tekstow¹ i zwraca j¹
		HTMLEditor.setHtmlText(fileController.otworz());
	}

	@FXML
	public void zapisz() throws IOException{
		// Wywo³uje metodê klasy FileController, która zapisuje do pliku tekst z argumentu
		fileController.zapisz(HTMLEditor.getHtmlText());
	}

	@FXML
	public void zapiszJako() throws IOException{
		// Wywo³uje metodê klasy FileController, która zapisuje do pliku tekst z argumentu
		fileController.setFile(null); // Ustawia pole File na null, dziêki temu przy zapisywaniu zostaje wywo³any FileChooser
		fileController.zapisz(HTMLEditor.getHtmlText());
	}

	@FXML
	public void zakoncz() throws IOException{ //Wyœwietla alert pytaj¹cy czy zapisaæ plik przed zamkniêciem
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
	public void oProgramie() throws IOException{ // Wyœwietla nowe okno z informacj¹ o programie
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("O programie");
		alert.setHeaderText("Notatnik HTML");
		alert.setContentText("Notatnik HTML to prosty edytor strony HTML \nAutor: Jêdrzej Ostrowski");
		alert.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fileController = new FileController(); // Przy inicjalizacji okna tworzony jest obiekt klasy s³u¿¹cej do otwierania i zapisu plików
	}
}