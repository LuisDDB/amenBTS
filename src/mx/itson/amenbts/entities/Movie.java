package mx.itson.amenbts.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.itson.amenbts.persistence.MySQLConnection;

/**
 * Represents a movie entity and provides methods to interact with the database.
 * The methods include obtaining movies, saving and movie removal.
 */
public class Movie {

    private int id;
    private int duration;
    private String genre;
    private String category;
    private String synosis;
    private String title;

    /**
     * Gets a list of movies filtered by title.
     *
     * @param filtro Text string to filter movies by title. HE will search for
     * movies whose titles contain this string. The filter can be null or an
     * empty string to get all unfiltered movies.
     * @return List of movies that match the given filter. The list will be
     * empty if no matches are found.
     */
    public static List<Movie> getAll(String filtro) {
        List<Movie> movies = new ArrayList<>();
        try {
            Connection conexion = MySQLConnection.get();
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM movie WHERE title LIKE ?");
            statement.setString(1, "%" + filtro + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Movie m = new Movie();
                m.setId(resultSet.getInt(1));
                m.setTitle(resultSet.getString(2));
                m.setCategory(resultSet.getString(3));
                m.setGenre(resultSet.getString(4));
                m.setSynosis(resultSet.getString(5));
                m.setDuration(resultSet.getInt(6));
                movies.add(m);

            }
        } catch (SQLException ex) {

        }
        return movies;
    }

    /**
     * Save a new movie to the database.
     *
     * @param title Title of the movie to be saved.
     * @param category Movie category.
     * @param genre Genre of the movie.
     * @param synosis Synopsis or description of the movie.
     * @param duration Duration of the movie in minutes.
     * @return true if the movie was saved successfully, false if a mistake.
     */
    public boolean save(String title, String category, String genre, String synosis, int duration) {
        boolean result = false;

        try {
            Connection conexion = MySQLConnection.get();
            String query = "INSERT INTO movie (title, category, genre, synosis, duration) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, category);
            statement.setString(3, genre);
            statement.setString(4, synosis);
            statement.setInt(5, duration);
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
            String query = "DELETE FROM movie WHERE id = ?";
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
     * Updates the information of a movie in the database.
     *
     * @param id The ID of the movie to update.
     * @param title The new title of the movie.
     * @param category The new category of the movie.
     * @param genre The new genre of the movie.
     * @param synosis The new synopsis of the movie.
     * @param duration The new duration of the movie in minutes.
     * @return Returns true if the update was successful, false otherwise
     * contrary.
     */
    public boolean update(int id, String title, String category, String genre, String synosis, int duration) {
        boolean result = false;

        try {
            Connection conexion = MySQLConnection.get();
            String query = "UPDATE movie SET title = ?, category = ?, genre = ?, synosis = ?, duration = ? WHERE id= ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, category);
            statement.setString(3, genre);
            statement.setString(4, synosis);
            statement.setInt(5, duration);
            statement.setInt(6, id);
            statement.execute();

            result = statement.getUpdateCount() == 1;
            conexion.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return result;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the synosis
     */
    public String getSynosis() {
        return synosis;
    }

    /**
     * @param synosis the synosis to set
     */
    public void setSynosis(String synosis) {
        this.synosis = synosis;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }



}
