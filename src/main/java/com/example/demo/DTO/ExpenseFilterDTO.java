package com.example.demo.DTO;

import lombok.Data;

public class ExpenseFilterDTO {

    private String keyword;

    private String sortBy;
    private String startDate;
    private String endDate;

    public ExpenseFilterDTO() {
    }

    public ExpenseFilterDTO(String keyword, String sortBy, String startDate, String endDate) {
        this.keyword = keyword;
        this.sortBy = sortBy;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ExpenseFilterDTO{" +
                "keyword='" + keyword + '\'' +
                ", sortBy='" + sortBy + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
