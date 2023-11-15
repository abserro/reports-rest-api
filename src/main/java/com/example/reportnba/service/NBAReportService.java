package com.example.reportnba.service;

import com.example.reportnba.report.IReportCountPlayersByTeam;
import com.example.reportnba.repository.INBAReportRepository;
import com.example.reportnba.report.IReportLongestCareer;
import liquibase.util.csv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;
import java.util.function.Function;

@Service
public class NBAReportService {
    private final INBAReportRepository repository;

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    public NBAReportService(INBAReportRepository repository) {
        this.repository = repository;
    }

    public List<IReportLongestCareer> getLongestCareers() {
        Pageable pageable = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        return repository.findPlayersByLongestCareer(pageable);
    }

    public List<IReportCountPlayersByTeam> getCountPlayersByTeam() {
        return repository.countPlayersByTeam();
    }

    public String getLongestCareersCSV() {
        List<IReportLongestCareer> report = getLongestCareers();
        String[] headers = {"PERSON_ID", "PLAYER_NAME", "FROM_YEAR", "TO_YEAR", "CAREER_DURATION"};
        return generateCSV(report, headers, this::mapLongestCareerToCSV);
    }

    public String getCountPlayersByTeamCSV() {
        List<IReportCountPlayersByTeam> report = getCountPlayersByTeam();
        String[] headers = {"TEAM_ID", "TEAM_CODE", "TEAM_CITY", "TEAM_ABBREVIATION", "TEAM_NAME", "COUNT"};
        return generateCSV(report, headers, this::mapCountPlayersByTeamToCSV);
    }

    private <T> String generateCSV(List<T> report, String[] headers, Function<T, String[]> mapper) {
        try (StringWriter writer = new StringWriter();
             CSVWriter csvWriter = new CSVWriter(writer)) {

            csvWriter.writeNext(headers);

            for (T item : report) {
                csvWriter.writeNext(mapper.apply(item));
            }

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String[] mapLongestCareerToCSV(IReportLongestCareer player) {
        return new String[]{
                String.valueOf(player.getPersonId()),
                player.getDisplayFirstLast(),
                String.valueOf(player.getFromYear()),
                String.valueOf(player.getToYear()),
                String.valueOf(player.getCareer())
        };
    }

    private String[] mapCountPlayersByTeamToCSV(IReportCountPlayersByTeam team) {
        return new String[]{
                String.valueOf(team.getTeamId()),
                team.getTeamCode(),
                team.getTeamCity(),
                team.getTeamAbbreviation(),
                team.getTeamName(),
                String.valueOf(team.getCount())
        };
    }
}


