package com.example.reportnba.report;

public interface IReportCountPlayersByTeam {
    Long getTeamId();

    String getTeamCode();

    String getTeamCity();

    String getTeamAbbreviation();

    String getTeamName();

    Integer getCount();
}
