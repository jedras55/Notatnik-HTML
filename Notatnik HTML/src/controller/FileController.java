package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileController {

	private File file = null;

	public void setFile(File file) {
		this.file = file;
	}

	public String otworz(){
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pliki HTML (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(extFilter);

        file = fileChooser.showOpenDialog(new Stage()); // Przypisuje do zmiennej file plik pobrany z FileChoosera

        StringBuilder stringBuffer = new StringBuilder();
        String hTMLText = null;
        try{
        	BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((hTMLText = bufferedReader.readLine()) != null) {
                stringBuffer.append(hTMLText); // Pobieranie tekstu z pliku
            }
        } catch(IOException e){
        	e.printStackTrace();
        }

        return hTMLText;
	}

	public void zapisz(String hTMLText){
		if(file == null){
			// Zmienna file = null wtedy, gdy ¿aden plik nie zosta³ jeszcze otwarty czy zapisany, wtedy plik wybierany jest przez FileChoosera
			FileChooser fileChooser = new FileChooser();
    		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pliki .html (*.html)", "*.html");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setTitle("Zapisz plik");

            file = fileChooser.showSaveDialog(new Stage());
		}
        try{
	        if (file != null) {
	        	FileWriter fileWriter = new FileWriter(file);
	            fileWriter.write(hTMLText); // Do pliku zapisywana jest wartoœæ tekstowa z parametru
	            fileWriter.close();
	        }
        } catch(IOException e){
        	e.printStackTrace();
        }

	}
}