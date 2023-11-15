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

@Service
public class NBAReportService {
    private final INBAReportRepository repository;

    @Autowired
    public NBAReportService(INBAReportRepository repository) {
        this.repository = repository;
    }

    public List<IReportLongestCareer> getLongestCareers() {
        Pageable pageable = PageRequest.of(0, 20);
        return repository.findPlayersByLongestCareer(pageable);
    }

    public List<IReportCountPlayersByTeam> getCountPlayersByTeam() {
        return repository.countPlayersByTeam();
    }

    public String getLongestCareersCSV() {
        Pageable pageable = PageRequest.of(0, 20);
        List<IReportLongestCareer> report = repository.findPlayersByLongestCareer(pageable); // Используйте Object[] или создайте DTO класс

        try (StringWriter writer = new StringWriter();
             CSVWriter csvWriter = new CSVWriter(writer)) {

            // Добавьте заголовок CSV (если нужно)
            csvWriter.writeNext(new String[]{"Person ID", "Player Name", "From Year", "To Year", "Career Duration"});

            // Добавьте данные
            for (IReportLongestCareer player : report) {
                // Преобразуйте объекты в массив строк, соответствующих столбцам CSV
                String[] csvRow = {
                        String.valueOf(player.getPersonId()),         // Person ID
                        String.valueOf(player.getDisplayFirstLast()), // Player Name
                        String.valueOf(player.getFromYear()),         // From Year
                        String.valueOf(player.getToYear()),           // To Year
                        String.valueOf(player.getCareer())             // Career Duration
                };
                csvWriter.writeNext(csvRow);
            }

            return writer.toString();
        } catch (Exception e) {
            // Обработка ошибок
            e.printStackTrace();
            return null;
        }
    }

    public String getCountPlayersByTeamCSV() {
        List<IReportCountPlayersByTeam> report = repository.countPlayersByTeam();

        try (StringWriter writer = new StringWriter();
             CSVWriter csvWriter = new CSVWriter(writer)) {

            // Добавьте заголовок CSV (если нужно)
            csvWriter.writeNext(new String[]{"Team ID", "Team Code", "Team City", "Team Abbreviation", "Team Name", "Count"});

            // Добавьте данные
            for (IReportCountPlayersByTeam team : report) {
                // Преобразуйте объекты в массив строк, соответствующих столбцам CSV
                String[] csvRow = {
                        String.valueOf(team.getTeamId()),                  // Team ID
                        String.valueOf(team.getTeamCode()),                // Team Code
                        String.valueOf(team.getTeamCity()),                // Team City
                        String.valueOf(team.getTeamAbbreviation()),        // Team Abbreviation
                        String.valueOf(team.getTeamName()),                // Team Name
                        String.valueOf(team.getCount())                    // Count
                };
                csvWriter.writeNext(csvRow);
            }

            return writer.toString();
        } catch (Exception e) {
            // Обработка ошибок
            e.printStackTrace();
            return null;
        }
    }


//
//    public List<Map<String, Object>> getReport(String reportType) {
//        switch (reportType) {
//            case "report-longest-careers" -> {
//                Pageable pageable = PageRequest.of(0, 20);
//                return repository.findTop20Players(pageable);
//            }
//            case "report-count-players-by-team" -> {
//                return repository.countPlayersByTeam();
//            }
//            default -> {
//                throw new IllegalArgumentException("Unsupported report type: " + reportType);
//            }
//        }
//    }
//
//    public String getReportCSV(String reportType) {
//        List<Map<String, Object>> report = getReport(reportType);
//
//        try (StringWriter writer = new StringWriter();
//             CSVWriter csvWriter = new CSVWriter(writer)) {
//
//            String[] headers = getCSVHeaders(reportType);
//            csvWriter.writeNext(headers);
//
//            for (Map<String, Object> player : report) {
//                String[] csvRow = getCSVRow(player, reportType);
//                csvWriter.writeNext(csvRow);
//            }
//
//            return writer.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private String[] getCSVHeaders(String reportType) {
//        switch (reportType) {
//            case "report-longest-careers" -> {
//                return new String[]{"Person ID", "Player Name", "From Year", "To Year", "Career Duration"};
//            }
//            case "report-count-players-by-team" -> {
//                return new String[]{"Team ID", "Team Code", "Team Name", "Team Abbreviation", "Count"};
//            }
//            default -> {
//                throw new IllegalArgumentException("Unsupported report type: " + reportType);
//            }
//        }
//    }
//
//    private String[] getCSVRow(Map<String, Object> player, String reportType) {
//        switch (reportType) {
//            case "report-longest-careers" -> {
//                return new String[]{
//                        String.valueOf(player.get("person_id")),
//                        String.valueOf(player.get("display_first_last")),
//                        String.valueOf(player.get("from_year")),
//                        String.valueOf(player.get("to_year")),
//                        String.valueOf(player.get("career"))
//                };
//            }
//            case "report-count-players-by-team" -> {
//                return new String[]{
//                        String.valueOf(player.get("team_id")),
//                        String.valueOf(player.get("team_code")),
//                        String.valueOf(player.get("team_name")),
//                        String.valueOf(player.get("team_abbreviation")),
//                        String.valueOf(player.get("count"))
//                };
//            }
//            default -> {
//                throw new IllegalArgumentException("Unsupported report type: " + reportType);
//            }
//        }
//    }
}
