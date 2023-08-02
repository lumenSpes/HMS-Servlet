package hms.controller;
//
import hms.repository.UserRepository;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegisterServlet extends HttpServlet {
    private static final Logger logger =Logger.getLogger(RegisterServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Register");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmpassword = req.getParameter("confirmpassword");
        String age = req.getParameter("age");
        String role = req.getParameter("role");
        boolean flag = true;
        boolean isUser = true;


        if (firstname == null || firstname.isEmpty()) {
            req.setAttribute("firstnameErrMsg", "Please fill up the firstname");
            flag = false;
        }

        if (lastname == null || lastname.isEmpty()) {
            req.setAttribute("lastnameErrMsg", "Please fill up the lastname");
            flag = false;
        }

        if (email == null || email.isEmpty()) {
            req.setAttribute("emailErrMsg", "Please fill up the email");
            flag = false;
        }

        if (username == null || username.isEmpty()) {
            req.setAttribute("usernameErrMsg", "Please fill up the username");
            flag = false;
        }

        if (password == null || password.isEmpty()) {
            req.setAttribute("passwordErrMsg", "Please fill up the password");
            flag = false;
        }

        if (confirmpassword == null || confirmpassword.isEmpty()) {
            req.setAttribute("confirmpasswordErrMsg", "Please fill up the confirmpassword");
            flag = false;
        }

        if (age == null || age.isEmpty()) {
            req.setAttribute("ageErrMsg", "Please fill up the age");
            flag = false;
        }

        if (role == null || role.isEmpty()) {
            req.setAttribute("roleErrMsg", "Please fill up the role");
            flag = false;
        }

        if (!password.equals(confirmpassword)) {
            req.setAttribute("passMismatchErrMsg", "Please retype the password");
            flag = false;
        }

        if (flag) {
            try {
                UserRepository userRepository = new UserRepository();
                boolean isValid = userRepository.register(firstname, lastname, email, username, password, confirmpassword, age, role);
                if (isValid) {
                    HttpSession httpSession = req.getSession();
                    httpSession.setAttribute("username", username);
                    resp.sendRedirect(req.getContextPath() + "/home");
//                    HttpSession httpSession = req.getSession();
                    httpSession.setAttribute("successMsg", "successMsg");
                    req.setAttribute("successMsg", "User successfully registerd...");
                    return;
                }
                isUser = true;
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("successMsg", "successMsg");
                req.setAttribute("successMsg", "User successfully registerd...");
            } catch (NamingException var9) {
                logger.log(Level.SEVERE, "Naming exception occurred while accessing UserRepository");
                logger.log(Level.SEVERE, var9.getMessage());
            } catch (SQLException var10) {
                logger.log(Level.SEVERE, "SQL exception occurred while accessing UserRepository");
                logger.log(Level.SEVERE, var10.getMessage());
            }
        }

        req.setAttribute("firstname", firstname);
        req.setAttribute("lastname", lastname);
        req.setAttribute("email", email);
        req.setAttribute("username", username);
        req.setAttribute("password", password);
        req.setAttribute("confirmpassword", confirmpassword);
        req.setAttribute("age", age);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/register.jsp");
        requestDispatcher.forward(req, resp);
    }
}

//import javax.naming.NamingException;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
////import hms_admin.repository.UserRepository;
//
//public class RegisterServlet extends HttpServlet {
//
//    private static final Logger logger = Logger.getLogger(RegisterServlet.class.getName());
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("title", "Registration");
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/register.jsp");
//        requestDispatcher.forward(req, resp);
//    }
//
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String firstname = req.getParameter("firstname");
//        String lastname = req.getParameter("lastname");
//        String email = req.getParameter("email");
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String confirmpassword = req.getParameter("confirmpassword");
//        String age = req.getParameter("age");
//        boolean flag = true;
//
//        if (firstname == null || firstname.isEmpty()) {
//            req.setAttribute("firstnameErrMsg", "Please fill up the first name");
//            flag = false;
//        }
//        if (lastname == null || lastname.isEmpty()) {
//            req.setAttribute("lastnameErrMsg", "Please fill up the last name");
//            flag = false;
//        }
//
//        if (email == null || email.isEmpty()) {
//            req.setAttribute("emailErrMsg", "Please fill up the email");
//            flag = false;
//        }
//        if (username == null || username.isEmpty()) {
//            req.setAttribute("usernameErrMsg", "Please fill up the username");
//            flag = false;
//        }
//
//        if (password == null || password.isEmpty()) {
//            req.setAttribute("passwordErrMsg", "Please fill up the password");
//            flag = false;
//        }
//
//        if (confirmpassword == null || confirmpassword.isEmpty()) {
//            req.setAttribute("confirmpasswordErrMsg", "Please fill up the confirm password");
//            flag = false;
//        }
//        if (age == null || age.isEmpty()) {
//            req.setAttribute("ageErrMsg", "Please fill up the age");
//            flag = false;
//        }
//
//
//        if (flag) {
//            if (! password.equals(confirmpassword)) {
//                req.setAttribute("passwordMismatchErrMsg", "Password and confirm password does not match");
//                flag = false;
//            }
//            if (flag) {
//                try {
//                    UserRepository userRepository = new UserRepository();
//                    boolean res = userRepository.register(firstname,lastname,email,username, password,confirmpassword,age);
//                    if (!res) {
//                        req.setAttribute("successMsg", "Registration successful...");
//                    }
//                    else {
//                        req.setAttribute("errMsg", "Registration failed...");
//                    }
//                } catch (NamingException var9) {
//                    logger.log(Level.SEVERE, "Naming exception occurred while accessing UserRepository");
//                    logger.log(Level.SEVERE, var9.getMessage());
//                } catch (SQLException var10) {
//                    logger.log(Level.SEVERE, "SQL exception occurred while accessing UserRepository");
//                    logger.log(Level.SEVERE, var10.getMessage());
//                }
//            }
//        }
//        req.setAttribute("firstname", firstname);
//        req.setAttribute("lastname", lastname);
//        req.setAttribute("email", email);
//        req.setAttribute("username", username);
//        req.setAttribute("password", password);
//        req.setAttribute("confirmpassword", confirmpassword);
//        req.setAttribute("age", age);
//
//
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/register.jsp");
//        requestDispatcher.forward(req, resp);
//    }
//}

