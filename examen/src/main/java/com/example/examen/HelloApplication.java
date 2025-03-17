package com.example.examen;

import domain.AdoptionCentre;
import domain.Animal;
import domain.Type;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.AdoptionCentreRepository;
import repository.AnimalRepository;
import service.Service;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.List;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        Service service = new Service();

        // Preia toate centrele de adopție din repository
        Iterable<AdoptionCentre> centres = service.getAdoptionCentreRepository().findAll();

        // Parcurge fiecare centru de adopție și creează o fereastră pentru fiecare
        for (AdoptionCentre centre : centres) {
            // Creează o fereastră nouă pentru fiecare centru
            Stage stage = new Stage();

            // Creează un container vertical pentru elementele UI
            VBox vbox = new VBox();

            // Crează și adaugă etichetele pentru informațiile centrului
            Label nameLabel = new Label("Name: " + centre.getName());
            Label locationLabel = new Label("Location: " + centre.getLocation());
            Label capacityLabel = new Label("Capacity: " + centre.getCapacity());
            Label occupancyLabel = new Label("Occupancy: " + service.getOccupancy(centre.getId()) + "%");

            // Crează tabelul pentru animale
            TableView<Animal> animalTable = new TableView<>();

            // Creează coloanele tabelului (pentru Nume și Tip animal)
            TableColumn<Animal, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn<Animal, String> typeColumn = new TableColumn<>("Type");
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

            // Creăm coloana cu butonul "Request Transfer"
            TableColumn<Animal, Void> transferColumn = new TableColumn<>("Action");
            transferColumn.setCellFactory(param -> new TableCell<Animal, Void>() {
                private final Button transferButton = new Button("Request Transfer");

                {
                    transferButton.setOnAction(event -> {
                        Animal animal = getTableView().getItems().get(getIndex());

                        System.out.println("Request Transfer for animal: " + animal.getName());

                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(transferButton);
                    }
                }
            });

            // Preia lista de animale disponibile pentru adopție în acest centru
            List<Animal> animals = (List<Animal>) service.getAnimalRepository().findByCentreId(centre.getId());

            // Adaugă coloanele în tabel
            animalTable.getColumns().addAll(nameColumn, typeColumn, transferColumn);

            // Adaugă animalele în tabel
            ObservableList<Animal> animalObservableList = FXCollections.observableArrayList(animals);
            animalTable.setItems(animalObservableList);

            // Creează ComboBox pentru selecția tipului de animal
            ComboBox<String> animalTypeComboBox = new ComboBox<>();
            animalTypeComboBox.getItems().addAll("All Types", "Dog", "Cat");
            animalTypeComboBox.setValue("Animal Type");

            // Filtrare animale după tip
            animalTypeComboBox.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    ObservableList<Animal> filteredAnimals;

                    // Convertește Iterable<Animal> în List<Animal> pentru a putea utiliza FXCollections.observableArrayList
                    if ("Dog".equals(newValue)) {
                        filteredAnimals = FXCollections.observableArrayList(
                                (List<Animal>) service.getAnimalByTypeAndCentreId(Type.DOG, centre.getId())
                        );
                    } else if ("Cat".equals(newValue)) {
                        filteredAnimals = FXCollections.observableArrayList(
                                (List<Animal>) service.getAnimalByTypeAndCentreId(Type.CAT, centre.getId())
                        );
                    } else {
                        filteredAnimals = FXCollections.observableArrayList(animals);
                    }

                    // Actualizează tabelul cu animalele filtrate
                    animalTable.setItems(filteredAnimals);
                }

            });

            // Adaugă elementele UI în VBox
            vbox.getChildren().addAll(nameLabel, locationLabel, capacityLabel, occupancyLabel, animalTypeComboBox, animalTable);

            // Crează scena pentru fereastră și setează VBox ca root
            Scene scene = new Scene(vbox, 400, 400);
            stage.setScene(scene);

            // Setează titlul ferestrei cu numele centrului de adopție
            stage.setTitle("Adoption Centre: " + centre.getName());

            // Afișează fereastra
            stage.show();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
