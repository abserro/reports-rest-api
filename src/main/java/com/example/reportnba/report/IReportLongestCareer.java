package com.example.reportnba.report;

public interface IReportLongestCareer {
    Long getPersonId();

    String getDisplayFirstLast();

    Integer getFromYear();

    Integer getToYear();

    Integer getCareer();
}
