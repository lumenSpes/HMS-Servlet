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

public class PatientHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Login");
        RequestDispatcher requestDispatcherLg = req.getRequestDispatcher("views/patientHome.jsp");
        requestDispatcherLg.forward(req, resp);

        HttpSession httpSession = req.getSession();
        String username = (String) httpSession.getAttribute("username");
        System.out.println(username);

        try {
            UserRepository userRepository = new UserRepository();
            userRepository.showAppointment(username,req);
            System.out.println("requesting data");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
