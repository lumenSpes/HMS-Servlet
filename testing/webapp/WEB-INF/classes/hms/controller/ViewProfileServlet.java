package hms.controller;

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

public class ViewProfileServlet extends HttpServlet {
    private static final Logger logger =Logger.getLogger(ViewProfileServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Profile");
        RequestDispatcher requestDispatcherLg = req.getRequestDispatcher("views/profile.jsp");
        requestDispatcherLg.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String age = req.getParameter("age");
        String role = req.getParameter("role");
        HttpSession httpSession = req.getSession();
        String db_username = (String) httpSession.getAttribute("DBuser_username");
        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(email);
        System.out.println(username);System.out.println(age);



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

//        if (password == null || password.isEmpty()) {
//            req.setAttribute("passwordErrMsg", "Please fill up the password");
//            flag = false;
//        }
//
//        if (confirmpassword == null || confirmpassword.isEmpty()) {
//            req.setAttribute("confirmpasswordErrMsg", "Please fill up the confirmpassword");
//            flag = false;
//        }

        if (age == null || age.isEmpty()) {
            req.setAttribute("ageErrMsg", "Please fill up the age");
            flag = false;
        }

        if (role == null || role.isEmpty()) {
            req.setAttribute("ageErrMsg", "Please fill up the Role");
            flag = false;
        }

//        if (!password.equals(confirmpassword)) {
//            req.setAttribute("passMismatchErrMsg", "Please retype the password");
//            flag = false;
//        }

        if (flag) {
            try {
                UserRepository userRepository = new UserRepository();
                System.out.println("i am here");
                boolean isValid = userRepository.updateProfile(firstname, lastname, email, username, age, role, db_username);
                if (isValid) {
//                    HttpSession httpSession = req.getSession();
//                    httpSession.setAttribute("username", username);
                    resp.sendRedirect(req.getContextPath() + "/home");
////                    HttpSession httpSession = req.getSession();
//                    httpSession.setAttribute("successMsg", "successMsg");
                    req.setAttribute("successMsg", "information successfully updated...");
                    return;
                }
                isUser = true;
//                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("successMsg", "successMsg");
                req.setAttribute("successMsg", "information successfully updated...");
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
        req.setAttribute("age", age);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/profile.jsp");
        requestDispatcher.forward(req, resp);
    }
}
