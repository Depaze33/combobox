package fr.afpa;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        // Fetching the list of countries
        ObservableList<Country> allCountries = FXCollections.observableArrayList(Country.getCountriesList());

        // Creating a separate list for ComboBox and an empty list for ListView
        ObservableList<Country> comboBoxItems = FXCollections.observableArrayList(allCountries);
        ObservableList<Country> listViewItems = FXCollections.observableArrayList();

        // Creating UI elements
        Label labelForm = new Label("Country available");
        Button upButton = new Button("Up");
        Button downButton = new Button("Down");
        Button addOneButton = new Button(">");
        Button addAllButton = new Button(">>");
        Button removeOneButton = new Button("<");
        Button removeAllButton = new Button("<<");
        Button exitButton = new Button("Exit");

        // Creating ComboBox and ListView
        ComboBox<Country> comboBoxCountry = new ComboBox<>(comboBoxItems);
        ListView<Country> countryListView = new ListView<>(listViewItems);

        // Creating layout and adding components
        VBox countryVBox = new VBox(10, comboBoxCountry, labelForm);
        HBox buttonUpDownHbox = new HBox(10, upButton, downButton);
        VBox buttonControlVBox = new VBox(10, addOneButton, addAllButton, removeOneButton, removeAllButton);

        HBox buttonControlListViewHBox = new HBox(10, countryListView, buttonControlVBox);
        VBox controlListViewVBox = new VBox(10, buttonUpDownHbox, buttonControlListViewHBox, exitButton);

        HBox hboxDisplay = new HBox(10, countryVBox, controlListViewVBox);

        //css
labelForm.setStyle("-fx-text-fill: white;");
        hboxDisplay.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: red;" +
                "-fx-background-color : black;" +
                "-fx-effect: dropshadow(gaussian,  grey, 10, 0, 5, 5);");
        hboxDisplay.setMaxWidth(529);

        // Setting up the scene
        
        // Adding event handlers
        addOneButton.setOnAction(value -> {
            Country selectedCountry = comboBoxCountry.getValue();
            if (selectedCountry != null) {
                listViewItems.add(selectedCountry);
                comboBoxItems.remove(selectedCountry);
            }
        });

        addAllButton.setOnAction(value -> {
            listViewItems.addAll(comboBoxItems);
            comboBoxItems.clear();
        });

        removeOneButton.setOnAction(value -> {
            Country selectedCountry = countryListView.getSelectionModel().getSelectedItem();
            if (selectedCountry != null) {
                comboBoxItems.add(selectedCountry);
                listViewItems.remove(selectedCountry);
            }
        });

        removeAllButton.setOnAction(value -> {
            comboBoxItems.addAll(listViewItems);
            listViewItems.clear();
        });

        upButton.setOnAction(value -> {
            ListViewMoveManager.moveSelectedItemUp(countryListView);
        });

        downButton.setOnAction(value -> {
            ListViewMoveManager.moveSelectedItemDown(countryListView);
        });

        exitButton.setOnAction(value -> {
            System.exit(0); // Assure que l'application se ferme complètement
        });

        Scene scene = new Scene(hboxDisplay, 700, 400);
        stage.setScene(scene);
        stage.setTitle("Country Selector");
        stage.show();

    }
}
