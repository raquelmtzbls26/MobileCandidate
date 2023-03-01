package com.example.mobile_candidate.Modelo;

public class Info {

    private String seed;
    private long results;
    private long page;
    private String version;

    public String getSeed() {
        return seed;
    }

    public void setSeed(String value) {
        this.seed = value;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long value) {
        this.results = value;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long value) {
        this.page = value;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String value) {
        this.version = value;
    }
}
