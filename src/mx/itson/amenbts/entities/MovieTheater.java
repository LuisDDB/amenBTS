/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.amenbts.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.itson.amenbts.persistence.MySQLConnection;

/**
 *
 * @author marcmartinez
 */
public class MovieTheater {

    private int idMovieTheater;
    private String name;
    private boolean availability;

    /**
     * Retrieve a list of movie theaters filtered by name.
     *
     * @param filtro The filter to apply to the names of movie theaters.
     * @return A list of MovieTheater objects that match the name filter.
     */
    public static List<MovieTheater> getAll(String filtro) {
        List<MovieTheater> movieTheater = new ArrayList<>();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM MovieTheater WHERE name LIKE ?");
            statement.setString(1, "%" + filtro + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                MovieTheater m = new MovieTheater();
                m.setIdMovieTheater(resultSet.getInt(1));
                m.setName(resultSet.getString(2));
                m.setAvailability(resultSet.getBoolean(3));
                movieTheater.add(m);

            }
        } catch (SQLException ex) {

        }
        return movieTheater;
    }

    /**
     * Save a new movie theater to the database.
     *
     * @param name The name of the movie theater to be saved.
     * @param availability The availability status of the movie theater.
     * @return true if the movie theater was successfully saved, false
     * otherwise.
     */
    public boolean save(String name, boolean availability) {
        boolean result = false;

        try {
            Connection conexion = MySQLConnection.get();
            String query = "INSERT INTO MovieTheater (name, availability) VALUES (?, ?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setBoolean(2, availability);
            statement.execute();

            result = statement.getUpdateCount() == 1;
            conexion.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return result;
    }

    /**
     * Delete a movie theater from the database based on its ID.
     *
     * @param id The ID of the movie theater to be deleted.
     * @return true if the movie theater was successfully deleted, false
     * otherwise.
     */
    public boolean delete(int id) {
        boolean result = false;

        try {
            Connection conexion = MySQLConnection.get();
            String query = "DELETE FROM MovieTheater WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;
            conexion.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return result;
    }

    /**
     * Update the information of a movie theater in the database.
     *
     * @param id The ID of the movie theater to be updated.
     * @param name The new name for the movie theater.
     * @param availability The new availability status of the movie theater.
     * @return true if the movie theater was successfully updated, false
     * otherwise.
     */
    public boolean update(int id, String name, boolean availability) {
        boolean result = false;

        try {
            Connection conexion = MySQLConnection.get();
            String query = "UPDATE MovieTheater SET name = ?, availability  = ? WHERE id= ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, name);
            statement.setBoolean(2, availability);
            statement.setInt(3, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;
            conexion.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return result;
    }

    /**
     * @return the idMovieTheater
     */
    public int getIdMovieTheater() {
        return idMovieTheater;
    }

    /**
     * @param idMovieTheater the idMovieTheater to set
     */
    public void setIdMovieTheater(int idMovieTheater) {
        this.idMovieTheater = idMovieTheater;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the availability
     */
    public boolean isAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

   

}
