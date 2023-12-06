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
import java.util.Date;
import java.util.List;
import mx.itson.amenbts.persistence.MySQLConnection;

/**
 *
 * @author marcmartinez
 */
public class Function {
    
    private int idFuntion;
    private Movie idMovie;
    private MovieTheater idMovieTheater;
    private Date dateFunction;
    
    
    //hello
    
    
    
    /**
     * Gets a list of movies filtered by title.
     *
     * @param filtro Text string to filter movies by title. HE will search for
     * movies whose titles contain this string. The filter can be null or an
     * empty string to get all unfiltered movies.
     * @return List of movies that match the given filter. The list will be
     * empty if no matches are found.
     */
    public static List<Function> getAll(String filtro) {
        List<Function> function = new ArrayList<>();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM function WHERE title LIKE ?");
            statement.setString(1, "%" + filtro + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Function f = new Function();
                f.setIdFuntion(resultSet.getInt(1));
                List<Movie> movies = new Movie().getAll("");
                for (Movie m : movies) {
                    if (resultSet.getInt(2) == m.getId()) {
                        f.setIdMovie(m);
                    }
                }
                List<MovieTheater> moviesTheaters = new MovieTheater().getAll("");
                for (MovieTheater mT : moviesTheaters) {
                    if (resultSet.getInt(3) == mT.getIdMovieTheater()) {
                        f.setIdMovieTheater(mT);
                    }
                }
                
                f.setDateFunction(resultSet.getDate(3));
                
                function.add(f);
            }
        } catch (SQLException ex) {
            
        }
        return function;
    }
    
    
    
    /**
     * Save a new function to the database.
     *
     * @param idFuntion Title of the function to be saved.
     * @param idMovie  idMovie function.
     * @param idMovieTheater idMovieTheater of the function.
     * @param date date of the function.
     * @return true if the movie was saved successfully, false if a mistake.
     */
    public boolean save(int idFuntion, int idMovie, int idMovieTheater, Date date) {
        boolean result = false;

        try {
            Connection conexion = MySQLConnection.get();
            String query = "INSERT INTO function (title, category, genre, synosis, duration) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, idFuntion);
            statement.setInt(2, idMovie);
            statement.setInt(3, idMovieTheater);
            statement.setDate(4, (java.sql.Date) date);
            statement.execute();

            result = statement.getUpdateCount() == 1;
            conexion.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return result;
    }
    
     /**
     * Removes a movie from the database based on the ID provided.
     *
     * @param id The ID of the movie to delete.
     * @return true if the movie was successfully deleted, false otherwise
     * contrary.
     */
    public boolean delete(int id) {
        boolean result = false;

        try {
            Connection conexion = MySQLConnection.get();
            String query = "DELETE FROM function WHERE id = ?";
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
     * Updates the information of a function in the database.
     * @param idFuntion The ID of the function to update.
     * @param idMovie The new id of the movie.
     * @param idMovieTheater The new id of the Movie Theather.
     * @param dateFunction The new Date of the funcion.
     * @return 
     */
   
    public boolean update(int idFuntion, int idMovie, int idMovieTheater,Date dateFunction) {
        boolean result = false;

        try {
            Connection conexion = MySQLConnection.get();
            String query = "UPDATE function SET idFuntion = ?, idMovie = ?, idMovieTheater = ?, dateFunction = ? WHERE id= ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, idFuntion);
            statement.setInt(2, idMovie);
            statement.setInt(3, idMovieTheater);
            statement.setDate(4, (java.sql.Date) dateFunction);
            
            statement.execute();

            result = statement.getUpdateCount() == 1;
            conexion.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return result;
    }
    
    
    

    /**
     * @return the idFuntion
     */
    public int getIdFuntion() {
        return idFuntion;
    }

    /**
     * @param idFuntion the idFuntion to set
     */
    public void setIdFuntion(int idFuntion) {
        this.idFuntion = idFuntion;
    }

    /**
     * @return the dateFunction
     */
    public Date getDateFunction() {
        return dateFunction;
    }

    /**
     * @param dateFunction the dateFunction to set
     */
    public void setDateFunction(Date dateFunction) {
        this.dateFunction = dateFunction;
    }

    /**
     * @return the idMovie
     */
    public Movie getIdMovie() {
        return idMovie;
    }

    /**
     * @param idMovie the idMovie to set
     */
    public void setIdMovie(Movie idMovie) {
        this.idMovie = idMovie;
    }

    /**
     * @return the idMovieTheater
     */
    public MovieTheater getIdMovieTheater() {
        return idMovieTheater;
    }

    /**
     * @param idMovieTheater the idMovieTheater to set
     */
    public void setIdMovieTheater(MovieTheater idMovieTheater) {
        this.idMovieTheater = idMovieTheater;
    }
    
}
