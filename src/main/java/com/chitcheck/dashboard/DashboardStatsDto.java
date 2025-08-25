package com.chitcheck.dashboard;

import java.util.Objects;

public class DashboardStatsDto {

    private long totalDefaulters;

    // Constructors
    public DashboardStatsDto() {
    }

    public DashboardStatsDto(long totalDefaulters) {
        this.totalDefaulters = totalDefaulters;
    }

    // Getter and Setter
    public long getTotalDefaulters() {
        return totalDefaulters;
    }

    public void setTotalDefaulters(long totalDefaulters) {
        this.totalDefaulters = totalDefaulters;
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DashboardStatsDto that = (DashboardStatsDto) o;
        return totalDefaulters == that.totalDefaulters;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalDefaulters);
    }

    @Override
    public String toString() {
        return "DashboardStatsDto{" +
                "totalDefaulters=" + totalDefaulters +
                '}';
    }
}