package com.poltavets;

import java.util.Date;

public class Line {

    private char typeLine; // C/D
    private String[] indicesService;
    private String[] indicesQuestion;
    private char typeResponse; // P/N
    private Date dateFrom;
    private int minutesWaited;
    private Date dateTo;

    public Line() {
    }

    public String[] getIndicesQuestion() {
        return indicesQuestion;
    }

    public void setIndicesQuestion(String[] indicesQuestion) {
        this.indicesQuestion = indicesQuestion;
    }

    public String[] getIndicesService() {
        return indicesService;
    }

    public void setIndicesService(String[] indicesService) {
        this.indicesService = indicesService;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getMinutesWaited() {
        return minutesWaited;
    }

    public void setMinutesWaited(int minutesWaited) {
        this.minutesWaited = minutesWaited;
    }

    public char getTypeLine() {
        return typeLine;
    }

    public void setTypeLine(char typeLine) {
        this.typeLine = typeLine;
    }

    public char getTypeResponse() {
        return typeResponse;
    }

    public void setTypeResponse(char typeResponse) {
        this.typeResponse = typeResponse;
    }
}
