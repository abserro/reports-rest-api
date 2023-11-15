package com.example.reportnba.controller;

import com.example.reportnba.repository.INBAReportRepository;
import com.example.reportnba.service.NBAReportService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
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

@OpenAPIDefinition(
        info = @Info(
                title = "NBA players API",
                version = "v.1.0.0",
                description = "This is a custom API for downloading player reports."
        )
)
@Tag(name = "NBAReport")
@RestController
@RequestMapping(path = "api/v1/nba-players")
public class NBAReportController {
    private final NBAReportService service;

    @Autowired
    public NBAReportController(INBAReportRepository repository) {
        this.service = new NBAReportService(repository);
    }

    @Operation(
            summary = "Get a report on the longest careers of NBA players",
            description = "Returns a report of the longest careers of NBA players in the specified format (json / csv)."
    )
    @GetMapping("/report-longest-careers")
    public ResponseEntity<?> getReportLongestCareers(@RequestParam(name = "format", defaultValue = "json") String format) {
        return switch (format) {
            case "json" -> handleJsonResponse(service.getLongestCareers());
            case "csv" -> handleCsvResponse(service.getLongestCareersCSV(), "report-longest-careers.csv");
            default -> ResponseEntity.badRequest().body("Unsupported format: " + format);
        };
    }

    @Operation(
            summary = "Get player count report by NBA team",
            description = "Returns a report on the number of players by NBA team in the specified format (json / csv)."
    )
    @GetMapping("/report-count-players-by-team")
    public ResponseEntity<?> getCountPlayersByTeam(@RequestParam(name = "format", defaultValue = "json") String format) {
        return switch (format) {
            case "json" -> handleJsonResponse(service.getCountPlayersByTeam());
            case "csv" -> handleCsvResponse(service.getCountPlayersByTeamCSV(), "report-count-players-by-team.csv");
            default -> ResponseEntity.badRequest().body("Unsupported format: " + format);
        };
    }

    private ResponseEntity<?> handleJsonResponse(List<?> data) {
        return data.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(data);
    }

    private ResponseEntity<?> handleCsvResponse(String csvReport, String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return ResponseEntity.ok().headers(headers).body(csvReport);
    }
}

