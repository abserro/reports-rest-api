package com.example.reportnba.report;

public interface ReportCountPlayersByTeam {
    Long getTeamId();
    String getTeamCode();
    String getTeamCity();
    String getTeamAbbreviation();
    String getTeamName();
    Integer getCount();
}
