package hms.controller;

import hms.repository.UserRepository;
import sun.plugin.dom.core.Element;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {
    private static final Logger logger =Logger.getLogger(LoginServlet.class.getName());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Login");
        RequestDispatcher requestDispatcherLg = req.getRequestDispatcher("views/login.jsp");
        requestDispatcherLg.forward(req, resp);
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        boolean flag = true;
//        Context context = null;
//        try {
//            context = new InitialContext();
//        } catch (NamingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/hms");
//            Connection connection = dataSource.getConnection();
//            String sql = "select firstname,lastname,email,username,password,age from users where username = ?;";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, req.getParameter("username"));
////            ps.setString(2, "456");
////            ps.execute();
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                if (rs.getString("username") == req.getParameter("username")) {
//                    RequestDispatcher requestDispatcherhm = req.getRequestDispatcher("views/home.jsp");
//                    requestDispatcherhm.forward(req, resp);
//                }
//            }
//        } catch (NamingException | SQLException e) {
//            throw new RuntimeException(e);
//        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean flag = true;
        boolean isUser = true;
        HttpSession httpSession = req.getSession();
        String isDoctor = (String) httpSession.getAttribute("DB_doctor");
        String isPatient = (String) httpSession.getAttribute("DB_patient");
        String isAdmin = (String) httpSession.getAttribute("DB_admin");

        System.out.println(isDoctor);
        System.out.println(isPatient);
        System.out.println(isAdmin);

        RequestDispatcher requestDispatcher;

        if (username == null || username.isEmpty()) {
            req.setAttribute("usernameErrMsg", "Please fill up the username");
            flag = false;
        }

        if (password == null || password.isEmpty()) {
            req.setAttribute("passwordErrMsg", "Please fill up the password");
            flag = false;
        }

        if (flag) {
            try {
                UserRepository userRepository = new UserRepository();
                boolean isValid = userRepository.login(username, password, req);
                if (isValid) {


                    if(Objects.equals(isDoctor, "doctor")){
                        httpSession.setAttribute("username", username);
                        resp.sendRedirect(req.getContextPath() + "/home");
                        isDoctor = "";
                    }

                    if(Objects.equals(isPatient, "patient")){
                        httpSession.setAttribute("username", username);
                        resp.sendRedirect(req.getContextPath() + "/patient");
                        System.out.println("i am here");
                        isPatient = "";
                    }

                    if(Objects.equals(isAdmin, "doctor")){
                        httpSession.setAttribute("username", username);
                        resp.sendRedirect(req.getContextPath() + "/admin");
                        isAdmin = "";
                    }
                    isDoctor = "";
                    isPatient = "";
                    isAdmin = "";
                    return;
                }



                httpSession.setAttribute("errMsg", "errMsg");
                req.setAttribute("errMsg", "Login failed...");
            } catch (NamingException var9) {
                logger.log(Level.SEVERE, "Naming exception occurred while accessing UserRepository");
                logger.log(Level.SEVERE, var9.getMessage());
            } catch (SQLException var10) {
                logger.log(Level.SEVERE, "SQL exception occurred while accessing UserRepository");
                logger.log(Level.SEVERE, var10.getMessage());
            }
            req.setAttribute("errMsg", "Wrong credentials");

        }


        req.setAttribute("username", username);
        req.setAttribute("password", password);
        requestDispatcher = req.getRequestDispatcher("views/login.jsp");
        requestDispatcher.forward(req, resp);


    }
}
