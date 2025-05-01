package com.example.task1c;

public class NewsItem {
    private String title;
    private String description;
    private String imageUrl;
    private boolean isTopStory;

    public NewsItem(String title, String description, String imageUrl, boolean isTopStory) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isTopStory = isTopStory;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public boolean isTopStory() { return isTopStory; }
}
