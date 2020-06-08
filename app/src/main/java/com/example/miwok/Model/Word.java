package com.example.miwok.Model;

public class Word {
    private static final int NO_IMAGE_PROVIDED = -1;

    private String miwokTranslation;
    private String defaultTranslation;
    private int imageResourceId = NO_IMAGE_PROVIDED;
    private int audioResourceId;

    public Word() {
    }

    public Word(String miwokTranslation, String defaultTranslation, int audioResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.audioResourceId = audioResourceId;
    }

    public Word(String miwokTranslation, String defaultTranslation, int imageResourceId,
                int audioResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.imageResourceId = imageResourceId;
        this.audioResourceId = audioResourceId;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public void setMiwokTranslation(String miwokTranslation) {
        this.miwokTranslation = miwokTranslation;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public void setDefaultTranslation(String defaultTranslation) {
        this.defaultTranslation = defaultTranslation;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getAudioResourceId() {
        return audioResourceId;
    }

    public void setAudioResourceId(int audioResourceId) {
        this.audioResourceId = audioResourceId;
    }

    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_PROVIDED;
    }
}
