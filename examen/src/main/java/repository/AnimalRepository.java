package repository;

import domain.AdoptionCentre;
import domain.Animal;
import domain.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnimalRepository implements Repository<Integer,Animal>{

    Connection connection;

    public AnimalRepository() {
        try{
            this.connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Adoptie","postgres","feliciamami");

        }catch (SQLException e){
            e.printStackTrace();
        };
    }


    @Override
    public Animal findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Animal> findAll() {
        HashMap<Integer, Animal> animale = new HashMap<>();
        try(PreparedStatement preparedStatement=connection.prepareStatement("select * from  Animal");
            ResultSet resultSet=preparedStatement.executeQuery()){
            while(resultSet.next()){
                Integer id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                Integer centreId=resultSet.getInt("centreId");
                Type type = Type.valueOf(resultSet.getString("type"));
                Animal animal=new Animal(name,centreId,type);
                animal.setId(id);
                animale.put(animal.getId(),animal);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return animale.values();
    }
    public Iterable<Animal> findByCentreId(Integer centreId) {
        List<Animal> animale = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Animal WHERE centreId = ?")) {
            preparedStatement.setInt(1, centreId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Type type = Type.valueOf(resultSet.getString("type"));
                    Animal animal = new Animal(name, centreId, type);
                    animal.setId(id);
                    animale.add(animal);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animale;
    }
    public Iterable<Animal> findAnimalByTypeAndCentreId(Type type, Integer centreId) {
        List<Animal> animale = new ArrayList<>();

        // Creăm interogarea SQL pentru a selecta animalele după tip și centreId
        String sqlQuery = "SELECT * FROM Animal WHERE type = ? AND centreId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            // Setăm valorile pentru tip (convertind enum-ul în șir) și centreId în interogare
            preparedStatement.setString(1, type.name());   // Setăm tipul ca string (de exemplu, "DOG")
            preparedStatement.setInt(2, centreId); // Setăm centreId-ul

            // Executăm interogarea
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    // Covertim tipul din baza de date în enum
                    Type animalType = Type.valueOf(resultSet.getString("type"));
                    Animal animal = new Animal(name, centreId, animalType);
                    animal.setId(id);
                    animale.add(animal);  // Adăugăm animalul la lista rezultatelor
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animale;
    }


    @Override
    public Animal save(Animal entity) {
        return null;
    }

    @Override
    public Animal delete(Integer integer) {
        return null;
    }

    @Override
    public Animal update(Animal entity) {
        return null;
    }
}
