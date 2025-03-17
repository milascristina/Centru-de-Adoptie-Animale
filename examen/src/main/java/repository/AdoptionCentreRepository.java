package repository;

import domain.AdoptionCentre;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdoptionCentreRepository implements Repository<Integer, AdoptionCentre> {

    Connection connection;

    public AdoptionCentreRepository() {
        try{
            this.connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Adoptie","postgres","feliciamami");

        }catch (SQLException e){
            e.printStackTrace();
        };
    }

    public Iterable<AdoptionCentre> findCentreByLocation(String location) {
        List<AdoptionCentre> centres = new ArrayList<>();
        String query = "SELECT * FROM AdoptionCentre WHERE location = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, location);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String centreLocation = resultSet.getString("location");
                Integer capacity = resultSet.getInt("capacity");

                AdoptionCentre adoptionCentre = new AdoptionCentre(name, centreLocation, capacity);
                adoptionCentre.setId(id);
                centres.add(adoptionCentre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return centres;
    }



    @Override
    public AdoptionCentre findOne(Integer id) {
        String query = "SELECT * FROM AdoptionCentre WHERE id = ?";
        AdoptionCentre adoptionCentre = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name=resultSet.getString("name");
                String location=resultSet.getString("location");
                Integer capacity=resultSet.getInt("capacity");
                adoptionCentre=new AdoptionCentre(name,location,capacity);
                adoptionCentre.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return adoptionCentre;
    }

    @Override
    public Iterable<AdoptionCentre> findAll() {
        HashMap<Integer, AdoptionCentre> adoptioncentre = new HashMap<>();
        try(PreparedStatement preparedStatement=connection.prepareStatement("select * from  AdoptionCentre");
            ResultSet resultSet=preparedStatement.executeQuery()){
            while(resultSet.next()){
                Integer id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String location=resultSet.getString("location");
                Integer capacity=resultSet.getInt("capacity");
                AdoptionCentre adoptionCentre=new AdoptionCentre(name,location,capacity);
                adoptionCentre.setId(id);
                adoptioncentre.put(adoptionCentre.getId(),adoptionCentre);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return adoptioncentre.values();
    }



    @Override
    public AdoptionCentre save(AdoptionCentre entity) {
        return null;
    }

    @Override
    public AdoptionCentre delete(Integer integer) {
        return null;
    }

    @Override
    public AdoptionCentre update(AdoptionCentre entity) {
        return null;
    }
}
