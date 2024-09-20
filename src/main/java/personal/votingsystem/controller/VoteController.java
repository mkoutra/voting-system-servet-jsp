package personal.votingsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import personal.votingsystem.dao.IUserDAO;
import personal.votingsystem.dao.UserDAOImpl;
import personal.votingsystem.dao.exceptions.CandidateDAOException;
import personal.votingsystem.dao.exceptions.UserDAOException;
import personal.votingsystem.service.IUserService;
import personal.votingsystem.service.UserServiceImpl;
import personal.votingsystem.service.exceptions.CandidateNotFoundException;
import personal.votingsystem.service.exceptions.UserNotFoundException;

import java.io.IOException;

@WebServlet("/voting/candidate/vote")
public class VoteController extends HttpServlet {
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer cid = Integer.parseInt(request.getParameter("cid").trim());
        String username = null;
        Boolean hasAlreadyVoted = false;

        HttpSession session = request.getSession(false);
        if (session != null) {
            username = (String) session.getAttribute("username");
            hasAlreadyVoted = (Boolean) session.getAttribute("hasVoted");
        }

        // Validation
        if (username == null || cid == null || hasAlreadyVoted == true) {
            return;
        }

        try {
            userService.voteACandidate(username, cid);

            // Write to session object
            session.setAttribute("hasVoted", true);

            request.setAttribute("votedCid", cid);
            request.getRequestDispatcher("/WEB-INF/jsp/vote-candidate-success.jsp").forward(request, response);
        } catch (CandidateNotFoundException | UserNotFoundException | UserDAOException | CandidateDAOException e) {
            e.printStackTrace();
        }
    }
}
