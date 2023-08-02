package hms.repository;

import hms.controller.ChangePassword;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepository extends HttpServlet {
//    private final Connection conncection;
private static final Logger logger =Logger.getLogger(UserRepository.class.getName());
    private final Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public UserRepository() throws NamingException, SQLException {
//        this.conncection = conncection;
        Context context = new InitialContext();
        DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/hms");
        this.connection = dataSource.getConnection();
        this.preparedStatement = null;
        this.resultSet = null;


    }
    public boolean login(String username, String password, HttpServletRequest req) throws SQLException, NamingException {

        Context context = new InitialContext();
        DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/hms");
        try (Connection connection = dataSource.getConnection();) {
        String sql = "select firstname,lastname,email,username,password,age,role from users where username = ? and password = ? ;";

        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setString(1, username);
        this.preparedStatement.setString(2, password);
        this.resultSet = this.preparedStatement.executeQuery();
        boolean res = this.resultSet.next();

        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        String email = resultSet.getString("email");
        String user_username = resultSet.getString("username");
        String age = resultSet.getString("age");
        String role = resultSet.getString("role");
            System.out.println(firstname);
            System.out.println(lastname);
            System.out.println(email);
            System.out.println(user_username);
            System.out.println(age);
            System.out.println(role);
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("DBfirstname", firstname);
        httpSession.setAttribute("DBlastname", lastname);
        httpSession.setAttribute("DBemail", email);
        httpSession.setAttribute("DBuser_username", user_username);
        httpSession.setAttribute("DBage", age);
        httpSession.setAttribute("DBrole", role);
            HttpSession session = req.getSession();
            if (role != null) {
                if (role.equals("doctor")) {
                    session.setAttribute("DB_doctor", "doctor");
                } else if (role.equals("patient")) {
                    session.setAttribute("DB_patient", "patient");
                } else if (role.equals("admin")) {
                    session.setAttribute("DB_admin", "admin");
                }
                return true;
            } else {
                return false;
            }
//        this.close();
//        return res;
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Exception occurred while updating password: " + e.getMessage());
        throw e;
    }

    }

    public boolean register(String fistname, String lastname, String email, String username, String password, String confirmpassword, String age, String role) throws SQLException {
        String sql = "insert into users (firstname, lastname, email, username, password, confirmpassword, age, role)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setString(1, fistname);
        this.preparedStatement.setString(2, lastname);
        this.preparedStatement.setString(3, email);
        this.preparedStatement.setString(4, username);
        this.preparedStatement.setString(5, password);
        this.preparedStatement.setString(6, confirmpassword);
        this.preparedStatement.setString(7, age);
        this.preparedStatement.setString(8, role);
//        this.resultSet = this.preparedStatement.executeQuery();
        boolean res = this.preparedStatement.execute();
        this.close();
        return res;
    }

    public boolean updatePassword(String newpassword, String confirmpassword, String username) throws SQLException, NamingException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/hms");
        try (Connection connection = dataSource.getConnection();) {
            String sql = "UPDATE users SET password = ?, confirmpassword = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newpassword);
            preparedStatement.setString(2, confirmpassword);
            preparedStatement.setString(3, username);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception occurred while updating password: " + e.getMessage());
            throw e;
        }
    }

    public boolean updateProfile(String firstname, String lastname, String email, String username, String age, String role, String db_username) throws NamingException, SQLException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/hms");
        try (Connection connection = dataSource.getConnection();) {


            String sql = "UPDATE users SET firstname = ?, lastname = ?, email = ?, username = ?, age = ?, role = ?  WHERE username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, age);
            preparedStatement.setString(6, role);
            preparedStatement.setString(7, db_username);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception occurred while updating password: " + e.getMessage());
            throw e;
        }
    }

    public boolean showAppointment(String username, HttpServletRequest req) throws NamingException, SQLException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/hms");
        try (Connection connection = dataSource.getConnection();) {
            String sql = "select firstname,lastname,email,date,assignedTo from appointment where username = ?;";

            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setString(1, username);

            this.resultSet = this.preparedStatement.executeQuery();
            boolean res = this.resultSet.next();

//            while(resultSet.next()){
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String date = resultSet.getString("date");
            String assignedTo = resultSet.getString("assignedTo");
                System.out.println(firstname);
                System.out.println(lastname);
                System.out.println(email);
                System.out.println(date);
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("DB_patient_firstname", firstname);
                httpSession.setAttribute("DB_patient_lastname", lastname);
                httpSession.setAttribute("DB_patient_email", email);
                httpSession.setAttribute("DB_patient_date", date);
            httpSession.setAttribute("DB_patient_assignedTo", assignedTo);
//            }
            this.close();
            return res;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception occurred while updating password: " + e.getMessage());
            throw e;
        }
    }

    public boolean setAppointment(String firstname, String lastname, String email, String username, String date, String assignedTo) throws SQLException {
        String sql = "insert into appointment set firstname = ?,lastname = ?,email = ?,username = ?,date = ?,assignedTo =  ?;";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setString(1, firstname);
        this.preparedStatement.setString(2, lastname);
        this.preparedStatement.setString(3, email);
        this.preparedStatement.setString(4, username);
        this.preparedStatement.setString(5, date);
        this.preparedStatement.setString(6, assignedTo);
//        this.resultSet = this.preparedStatement.executeQuery();
        boolean res = this.preparedStatement.execute();
        this.close();
        return res;
    }
//    insert into medicine set firstname = "kljflsdjf", lastname = "jflsdnflj", username = "fnsdnfwenf", med1 = "lkdfkls", med2 ="lknflwef", med3 = "lkflkmfw", med4 = "kmsdfmwl", med5 = "lkklvmerfw";

    public boolean prescribeMedicine(String firstname, String lastname, String username, String med1, String med2, String med3, String med4, String med5) throws SQLException {
        String sql = "insert into medicine set firstname = ?, lastname = ?, username = ?, med1 = ?, med2 = ?, med3 = ?, med4 = ?, med5 = ?;";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setString(1, firstname);
        this.preparedStatement.setString(2, lastname);
        this.preparedStatement.setString(3, username);
        this.preparedStatement.setString(4, med1);
        this.preparedStatement.setString(5, med2);
        this.preparedStatement.setString(6, med3);
        this.preparedStatement.setString(7, med4);
        this.preparedStatement.setString(8, med5);
//        this.resultSet = this.preparedStatement.executeQuery();
        boolean res = this.preparedStatement.execute();
        this.close();
        return res;
    }

//    select firstname, lastname, username, med1,med2,med3,med4,med5 from medicine where username = "kabir917";
    public boolean getMedicine(String username,HttpServletRequest req) throws NamingException, SQLException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/hms");
        try (Connection connection = dataSource.getConnection();) {
            String sql = "select firstname, lastname, username, med1,med2,med3,med4,med5 from medicine where username =  ?";

            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setString(1, username);

            this.resultSet = this.preparedStatement.executeQuery();
            boolean res = this.resultSet.next();

//            while(resultSet.next()){
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String medUsername = resultSet.getString("username");
            String med1 = resultSet.getString("med1");
            String med2 = resultSet.getString("med2");
            String med3 = resultSet.getString("med3");
            String med4 = resultSet.getString("med4");
            String med5 = resultSet.getString("med5");
            System.out.println(firstname);
            System.out.println(lastname);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("DB_med_firstname", firstname);
            httpSession.setAttribute("DB_med_lastname", lastname);
            httpSession.setAttribute("DB_med_username", medUsername);
            httpSession.setAttribute("DB_med_med1", med1);
            httpSession.setAttribute("DB_med_med2", med2);
            httpSession.setAttribute("DB_med_med3", med3);
            httpSession.setAttribute("DB_med_med4", med4);
            httpSession.setAttribute("DB_med_med5", med5);
//            }
            this.close();
            return res;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception occurred while updating password: " + e.getMessage());
            throw e;
        }
    }


    private void close() throws SQLException {
        if (this.resultSet != null) {
            this.resultSet.close();
        }
        this.preparedStatement.close();
        this.connection.close();
    }

}
