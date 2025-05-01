package com.example.vibetube.data;

public class ClipItem {
    private final String label;
    private final String videoId;

    public ClipItem(String label, String videoId) {
        this.label = label;
        this.videoId = videoId;
    }

    public String getLabel() { return label; }
    public String getVideoId() { return videoId; }
}
