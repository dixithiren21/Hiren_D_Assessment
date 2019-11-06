package com.hirend.assessment.model.dto.response;

import java.util.List;

/**
 * Awesome Pojo Generator
 */
public class InfoResponse {
    private List<Data> rows;
    private String title;

    public InfoResponse(List<Data> rows, String title) {
        this.rows = rows;
        this.title = title;
    }

    public void setRows(List<Data> rows) {
        this.rows = rows;
    }

    public List<Data> getRows() {
        return rows;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}