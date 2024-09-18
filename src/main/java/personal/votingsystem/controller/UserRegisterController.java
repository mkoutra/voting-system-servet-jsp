package personal.votingsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import personal.votingsystem.dao.IUserDAO;
import personal.votingsystem.dao.UserDAOImpl;
import personal.votingsystem.dao.exceptions.UserDAOException;
import personal.votingsystem.dto.UserInsertDTO;
import personal.votingsystem.dto.UserInsertErrorsDTO;
import personal.votingsystem.dto.UserReadOnlyDTO;
import personal.votingsystem.model.User;
import personal.votingsystem.service.IUserService;
import personal.votingsystem.service.UserServiceImpl;
import personal.votingsystem.validator.Validator;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/users/register")
public class UserRegisterController extends HttpServlet {
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/insert-user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserInsertDTO userInsertDTO = createUserInsertDTO(request);
        Map<String, String> errors = new HashMap<>();

        // Validation
        errors = Validator.validate(userInsertDTO);
        if (!errors.isEmpty()) {
            String usernameErrorMessage = errors.getOrDefault("username", "");
            String emailErrorMessage = errors.getOrDefault("email", "");
            String dateOfBirthErrorMessage = errors.getOrDefault("dateOfBirth", "");
            String firstnameErrorMessage = errors.getOrDefault("firstname", "");
            String lastnameErrorMessage = errors.getOrDefault("lastname", "");
            String passwordErrorMessage = errors.getOrDefault("password", "");
            String reEnteredPasswordErrorMessage = errors.getOrDefault("reEnteredPassword", "");

            UserInsertErrorsDTO userInsertErrorsDTO = new UserInsertErrorsDTO(usernameErrorMessage, emailErrorMessage,
                    dateOfBirthErrorMessage, firstnameErrorMessage, lastnameErrorMessage,
                    passwordErrorMessage, reEnteredPasswordErrorMessage);

            request.setAttribute("userInsertDTO", userInsertDTO);
            request.setAttribute("userInsertErrorsDTO", userInsertErrorsDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/insert-user.jsp").forward(request, response);
            return;
        }

        try {
            User user = userService.insertUser(userInsertDTO);
            UserReadOnlyDTO userReadOnlyDTO = mapToUserReadOnlyDTO(user);

            request.setAttribute("userReadOnlyDTO", userReadOnlyDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/insert-user-success.jsp").forward(request, response);
        } catch (UserDAOException e) {
               e.printStackTrace();
        }
    }

    private UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getUsername(), user.getFirstname(), user.getLastname());
    }

    /**
     * Binds data taken from form and returns the corresponding {@link UserInsertDTO}.
     *
     * @param request   The Http request.
     * @return          The {@link UserInsertDTO} object with the user information.
     */
    private UserInsertDTO createUserInsertDTO(HttpServletRequest request) {
        String username = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        Date dateOfBirth = strToDate(request.getParameter("dateOfBirth"));
        String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();
        String password = request.getParameter("password").trim();
        String reEnteredPassword = request.getParameter("reEnteredPassword").trim();

        return new UserInsertDTO(username, email, firstname, lastname, dateOfBirth, password, reEnteredPassword);
    }

    /**
     * Transform the string taken from the form to Date object.
     *
     * @param dateString    The date expected in {@code yyyy-mm-dd} format.
     * @return              The Date object with the corresponding date.
     */
    private Date strToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
