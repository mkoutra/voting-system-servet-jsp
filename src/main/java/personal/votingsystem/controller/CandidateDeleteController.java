package personal.votingsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import personal.votingsystem.dao.CandidateDAOImpl;
import personal.votingsystem.dao.ICandidateDAO;
import personal.votingsystem.dao.IUserDAO;
import personal.votingsystem.dao.UserDAOImpl;
import personal.votingsystem.dao.exceptions.CandidateDAOException;
import personal.votingsystem.dao.exceptions.UserDAOException;
import personal.votingsystem.service.CandidateServiceImpl;
import personal.votingsystem.service.ICandidateService;
import personal.votingsystem.service.IUserService;
import personal.votingsystem.service.UserServiceImpl;
import personal.votingsystem.service.exceptions.CandidateNotFoundException;

import java.io.IOException;

@WebServlet("/voting/candidate/delete")
public class CandidateDeleteController extends HttpServlet {
    private final ICandidateDAO candidateDAO = new CandidateDAOImpl();
    private final ICandidateService candidateService = new CandidateServiceImpl(candidateDAO);
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer cid = Integer.parseInt(request.getParameter("cid").trim());
        try {
            userService.removeAllVotesOfSpecificCid(cid);
            candidateService.deleteCandidate(cid);
            request.setAttribute("deletedCid", cid);
            request.getRequestDispatcher("/WEB-INF/jsp/delete-candidate-success.jsp").forward(request, response);
        } catch (CandidateDAOException | CandidateNotFoundException | UserDAOException e) {
            String errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/voting.jsp").forward(request, response);
        }
    }
}
