package com.example.reportnba.controller;

import com.example.reportnba.report.ReportCountPlayersByTeam;
import com.example.reportnba.repository.NBAPlayerRepository;
import com.example.reportnba.report.ReportLongestCareer;
import com.example.reportnba.service.NBAPlayerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "NBAReport")
@RestController
@RequestMapping(path = "api/v1/nba-players")
public class NBAPlayerController {
    private final NBAPlayerService service;

    @Autowired
    public NBAPlayerController(NBAPlayerRepository repository) {
        this.service = new NBAPlayerService(repository);
    }

    @GetMapping("/report-longest-careers")
    public ResponseEntity<?> getReportLongestCareers(@RequestParam(name = "format", defaultValue = "json") String format) {
        switch (format) {
            case "json"->{
                List<ReportLongestCareer> players = service.getLongestCareers();
                return players.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(players);
            }
            case "csv"->{
                String csvReport = service.getLongestCareersCSV();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType("text/csv"));
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report-longest-careers.csv");
                return ResponseEntity.ok().headers(headers).body(csvReport);
            }
            default -> {
                return ResponseEntity.badRequest().body("Unsupported format: " + format);
            }
        }
    }

    @GetMapping("/report-count-players-by-team")
    public ResponseEntity<?> getCountPlayersByTeam(@RequestParam(name = "format", defaultValue = "json") String format) {
        switch (format) {
            case "json"->{
                List<ReportCountPlayersByTeam> players = service.getCountPlayersByTeam();
                return players.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(players);
            }
            case "csv"->{
                String csvReport = service.getCountPlayersByTeamCSV();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType("text/csv"));
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report-count-players-by-team.csv");
                return ResponseEntity.ok().headers(headers).body(csvReport);
            }
            default -> {
                return ResponseEntity.badRequest().body("Unsupported format: " + format);
            }
        }
    }
}

