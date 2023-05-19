package org.example.Database;

import javafx.scene.image.Image;
import org.example.Car.Car;
import org.example.User.User;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

public class Database
{
    private Connection connection;

    void connectToDatabase() {
        String url = "jdbc:sqlite:database.db";

        connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            System.exit(-1);
        }

    }

    void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing the database " + e.getMessage());
            System.exit(-2);
        }

    }
    public void initializeDatabase() {
        File dbFile = new File("database.db");

        if (!dbFile.exists()) {
            try {
                if (dbFile.createNewFile()) {
                    connectToDatabase();
                    String createTableSQL = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, password TEXT, firstname TEXT, lastname TEXT, pesel TEXT, admin INTEGER)";
                    String carTableSQL = "CREATE TABLE cars (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "model TEXT," +
                            "mark TEXT," +
                            "color TEXT," +
                            "buyer_id INTEGER REFERENCES users(id)," +
                            "seller_id INTEGER REFERENCES users(id)," +
                            "image BLOB," +
                            "cost INTEGER," +
                            "date_start TEXT" +
                            ")";
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(createTableSQL);
                        Statement car = connection.createStatement();
                        car.execute(carTableSQL);
                    } catch (SQLException e) {
                        System.err.println("Error creating table: " + e.getMessage());
                        System.exit(-3);
                    }
                    closeConnection();
                }
            } catch (IOException e) {
                System.out.println("Error creating database file: " + e.getMessage());
                System.exit(-4);
            }
        }
    }
    public void addUser(String login, String password, String firstnane, String lastname, String pesel) {
        connectToDatabase();

        String insertUserSQL = "INSERT INTO users (login, password, firstname, lastname, pesel, admin) VALUES (?, ?, ?, ?, ?, 1)";
        try {
            PreparedStatement statement = connection.prepareStatement(insertUserSQL);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, firstnane);
            statement.setString(4, lastname);
            statement.setString(5, pesel);
            statement.executeUpdate();
            System.out.println("User inserted"); //test

        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
            System.exit(1);
        }
        closeConnection();
    }
        public boolean checkUsername(String username) {
        connectToDatabase();
        String selectUserSQL = "SELECT * FROM users WHERE login = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(selectUserSQL);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                closeConnection();
                return false;
            } else {
                closeConnection();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error Checking username: " + e.getMessage());
            System.exit(1);
        }
        closeConnection();
        return false;
    }

    public boolean login(String username, String password)
    {
        connectToDatabase();
        String selectUserSQL = "SELECT * FROM users WHERE login = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(selectUserSQL);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = User.getInstance();
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setId(resultSet.getInt("id"));
                user.setPesel(resultSet.getString("pesel"));
                user.setAdmin(resultSet.getInt("admin"));
                closeConnection();
                return true;
            } else {
                closeConnection();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error Checking username: " + e.getMessage());
            System.exit(1);
        }
        closeConnection();
        return false;
    }
    public void changeAccountSettings(String login, String firstname, String lastname, String pesel, String newPassword) {
        connectToDatabase();
        String sql = "UPDATE users " +
                "SET password = ?, " +
                "firstname = ?, " +
                "lastname = ?, " +
                "pesel = ? " +
                "WHERE login = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            if (newPassword.isEmpty()) {
                statement.setString(1, User.getInstance().getPassword());
            } else {
                statement.setString(1, newPassword);
            }
            if (firstname.isEmpty()) {
                statement.setString(2, User.getInstance().getFirstName());
            } else {
                statement.setString(2, firstname);
            }
            if (lastname.isEmpty()) {
                statement.setString(3, User.getInstance().getLastName());
            } else {
                statement.setString(3, lastname);
            }
            if (pesel.isEmpty()) {
                statement.setString(4, User.getInstance().getPesel());
            } else {
                statement.setString(4, pesel);
            }
            statement.setString(5, login);
            statement.executeUpdate();
            closeConnection();

        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            System.exit(1);
        }

        closeConnection();
    }
    public boolean addAdmin(String login)
    {
        connectToDatabase();
        String sql = "UPDATE users " +
                "SET admin = 1 " +
                "WHERE login = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,login);
            int resultSet = statement.executeUpdate();
            closeConnection();
            if (resultSet > 0)
            {
                return true;
            }
            else
            {
                return false;
            }

        }catch (SQLException e)
        {
            System.out.println("Error adding admin: " + e.getMessage());
            System.exit(1);
        }

        return false;
    }
    public int addCar(String model, String mark, String color, Blob image, int id, int cost, String date_start) {
        connectToDatabase();

        String insertUserSQL = "INSERT INTO cars (model, mark, color, buyer_id, seller_id, image, cost, date_start) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(insertUserSQL);
            statement.setString(1, model);
            statement.setString(2, mark);
            statement.setString(3, color);
            statement.setInt(4, -1);
            statement.setInt(5, id);
            byte[] imageBytes = image.getBytes(1, (int) image.length());
            statement.setBytes(6, imageBytes);
            statement.setInt(7,cost);
            statement.setString(8,date_start);
            statement.executeUpdate();
            System.out.println("Car inserted");
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int insertedCarId = generatedKeys.getInt(1);
                closeConnection();
                return insertedCarId;
            }
        } catch (SQLException e) {
            System.out.println("Error inserting car: " + e.getMessage());
            System.exit(1);
        }
        closeConnection();
        return -1;
    }
    public void carsToList(List<Car> carList)
    {
        carList.clear();
        connectToDatabase();
        Image image;
        String sql = "SELECT * FROM cars";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                InputStream binaryStream = resultSet.getBinaryStream("image");
                if (binaryStream != null) {
                    byte[] imageBytes = binaryStream.readAllBytes();
                    image = new Image(new ByteArrayInputStream(imageBytes));
                    binaryStream.close();

                    carList.add(new Car(resultSet.getString("color"),resultSet.getString("model"),resultSet.getString("mark"),resultSet.getFloat("cost"),resultSet.getString("date_start"),resultSet.getInt("seller_id"),resultSet.getInt("buyer_id"), image,resultSet.getInt("id")));
                }
            }
        }catch (SQLException e) {
            System.out.println("Error getting car to list: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void rentCarUpdate(Car car, String date)
    {
        connectToDatabase();
        String sql = "UPDATE cars " +
                "SET buyer_id = ?, " +
                "date_start = ?"+
                "WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, User.getInstance().getId());
            statement.setInt(3, car.getId());
            statement.setString(2,date);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error set seller: " + e.getMessage());
            System.exit(1);
        }
        closeConnection();
    }
    public void rentCarEnd(int id)
    {
        connectToDatabase();
        String sql = "UPDATE cars " +
                "SET buyer_id = -1 " +
                "WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error set buyer: " + e.getMessage());
            System.exit(1);
        }
        closeConnection();
    }
}
