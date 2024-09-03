package com.example.miwok;

public class Word {
    private String miwokTranslation;
    private String defaultTranslation;

    private static final int NO_IMAGE_PROVIDED = -1;
    private int mimageResourceld = NO_IMAGE_PROVIDED;

    private int mAudioResourceId;

    public String getMiwokTranslation() {
        return this.miwokTranslation;
    }

    public String getDefaultTranslation() {
        return this.defaultTranslation;
    }

    public int getMimageResourceld() {
        return this.mimageResourceld;
    }

    public int getmAudioResourceId() {
        return this.mAudioResourceId;
    }


    public Word(String defaultTranslation, String miwokTranslation, int mAudioResourceId) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.mAudioResourceId = mAudioResourceId;
    }

    public boolean hasImage() {
        return mimageResourceld != NO_IMAGE_PROVIDED;
    }

    public Word(String defaultTranslation, String miwokTranslation, int mimageResourceld, int mAudioResourceId) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.mimageResourceld = mimageResourceld;
        this.mAudioResourceId = mAudioResourceId;
    }

}
