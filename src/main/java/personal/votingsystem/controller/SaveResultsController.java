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
import personal.votingsystem.service.exceptions.CandidateIOException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@WebServlet("/voting/save-results")
public class SaveResultsController extends HttpServlet {
    private final ICandidateDAO candidateDAO = new CandidateDAOImpl();
    private final ICandidateService candidateService = new CandidateServiceImpl(candidateDAO);
    private final int BUFFER_SIZE = 256;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CandidatesWithVotesReadOnlyDTO> candidatesList = getCandidatesWithVotesDTOList();

        response.setContentType("text/csv");
        response.setHeader("Content-disposition", "attachment; filename=Voting_Results.csv");

        // Config file name
        String fileName = "results_" + LocalDateTime.now().toString();
        String filePath = Thread.currentThread().getContextClassLoader().getResource("/").getPath() + fileName;
        File file = new File(filePath);

        // Create file
        try {
            candidateService.saveVotingResults(candidatesList, file);
        } catch (CandidateIOException e) {
            e.printStackTrace();
            return;
        }

        // Read from file and send to output
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResource(fileName).openStream();
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[BUFFER_SIZE];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }

        file.delete();
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
            candidatesWithVotesReadOnlyDTOList.sort(Comparator.comparing(CandidatesWithVotesReadOnlyDTO::getCid));

            return candidatesWithVotesReadOnlyDTOList;
        } catch (CandidateDAOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
