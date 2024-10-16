package edu.gsu.cs4350.Models;

public class Website {
    private String websiteId;
    private String websiteName;
    private String url;

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    public String getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
