package com.gmail_bssushant2003.bottomnavbar;

public class Places {
    private String name;
    private String imageUrl;
    private String bestTimeToVisit;

    public Places(String name, String imageUrl,String bestTimeToVisit) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.bestTimeToVisit = bestTimeToVisit;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public String getBestTimeToVisit(){return bestTimeToVisit;}
}
