package personal.votingsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import personal.votingsystem.dao.CandidateDAOImpl;
import personal.votingsystem.dao.ICandidateDAO;
import personal.votingsystem.dao.exceptions.CandidateDAOException;
import personal.votingsystem.dto.CandidatesWithVotesReadOnlyDTO;
import personal.votingsystem.model.Candidate;
import personal.votingsystem.service.CandidateServiceImpl;
import personal.votingsystem.service.ICandidateService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@WebServlet("/voting/results")
public class VotingResultsController extends HttpServlet {
    private final ICandidateDAO candidateDAO = new CandidateDAOImpl();
    private final ICandidateService candidateService = new CandidateServiceImpl(candidateDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load candidates from the database with their votes.
        List<CandidatesWithVotesReadOnlyDTO> candidatesWithVotesReadOnlyDTOList = getCandidatesWithVotesDTOList();
        request.setAttribute("candidateWithVotesReadOnlyDTOs", candidatesWithVotesReadOnlyDTOList);

        request.getRequestDispatcher("/WEB-INF/jsp/voting-results.jsp").forward(request, response);
    }

    private List<CandidatesWithVotesReadOnlyDTO> getCandidatesWithVotesDTOList() {
        try {
            Map<Candidate, Integer> candidateWithVotesMap = candidateService.getAllCandidatesWithVotes();
            List<CandidatesWithVotesReadOnlyDTO> candidatesWithVotesReadOnlyDTOList = new ArrayList<>();

            for (Candidate candidate : candidateWithVotesMap.keySet()) {
                // Map to CandidatesWithVotesReadOnlyDTO
                CandidatesWithVotesReadOnlyDTO candidatesWithVotesReadOnlyDTO = new CandidatesWithVotesReadOnlyDTO();
                candidatesWithVotesReadOnlyDTO.setCid(candidate.getCid());
                candidatesWithVotesReadOnlyDTO.setFirstname(candidate.getFirstname());
                candidatesWithVotesReadOnlyDTO.setLastname(candidate.getLastname());
                candidatesWithVotesReadOnlyDTO.setTotalVotes(candidateWithVotesMap.get(candidate));

                candidatesWithVotesReadOnlyDTOList.add(candidatesWithVotesReadOnlyDTO);
            }

            // Sort list
            candidatesWithVotesReadOnlyDTOList.sort(Comparator.comparing(CandidatesWithVotesReadOnlyDTO::getTotalVotes).reversed());

            return candidatesWithVotesReadOnlyDTOList;
        } catch (CandidateDAOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
