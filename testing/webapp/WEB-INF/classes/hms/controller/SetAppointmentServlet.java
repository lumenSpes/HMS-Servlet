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

public class SetAppointmentServlet extends HttpServlet {
    private static final Logger logger =Logger.getLogger(RegisterServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Login");
        RequestDispatcher requestDispatcherLg = req.getRequestDispatcher("views/setAppointment.jsp");
        requestDispatcherLg.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String date = req.getParameter("date");
        String assignedTo = req.getParameter("assignedTo");
        boolean flag = true;


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

        if (date == null || date.isEmpty()) {
            req.setAttribute("dateErrMsg", "Please fill up the date");
            flag = false;
        }

        if (assignedTo == null || assignedTo.isEmpty()) {
            req.setAttribute("assignedToErrMsg", "Please fill up the assigned doctor name");
            flag = false;
        }



        if (flag) {
            try {
                UserRepository userRepository = new UserRepository();
                boolean isValid = userRepository.setAppointment(firstname, lastname, email, username, date, assignedTo);
                System.out.println("i am setting appointment");
                if (isValid) {
                    HttpSession httpSession = req.getSession();
                    resp.sendRedirect(req.getContextPath() + "/home");
//                    HttpSession httpSession = req.getSession();
                    httpSession.setAttribute("successMsg", "successMsg");
                    req.setAttribute("successMsg", "Appointment successfully added...");
                    return;
                }

                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("successMsg", "successMsg");
                req.setAttribute("successMsg", "Appointment successfully added...");
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
        req.setAttribute("date", date);
        req.setAttribute("confirmpassword", assignedTo);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/home.jsp");
        requestDispatcher.forward(req, resp);
    }
}
