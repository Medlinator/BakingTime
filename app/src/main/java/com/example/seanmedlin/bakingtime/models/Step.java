package com.example.seanmedlin.bakingtime.models;

import java.io.Serializable;

/**
 * A {@link Step} object contains information related to a recipe step
 */
public class Step implements Serializable {

    /**
     * ID of the step
     */
    private final int mId;

    /**
     * Short description of the step
     */
    private final String mShortDescription;

    /**
     * Description of the step
     */
    private final String mDescription;

    /**
     * Video for the step
     */
    private final String mVideoUrl;

    /**
     * Thumbnail for the step
     */
    private final String mThumbnailUrl;

    /**
     * ID of the recipe
     */
    private final int mRecipeId;

    /**
     * Constructs a new {@link Step} object
     *
     * @param id step ID
     * @param shortDescription short description of the step
     * @param description description of the step
     * @param videoUrl URL for the video of step
     * @param thumbnailUrl step thumbnail
     * @param recipeId ID of the recipe that the step belongs to
     */
    public Step(int id, String shortDescription, String description, String videoUrl,
                String thumbnailUrl, int recipeId) {
        mId = id;
        mShortDescription = shortDescription;
        mDescription = description;
        mVideoUrl = videoUrl;
        mThumbnailUrl = thumbnailUrl;
        mRecipeId = recipeId;
    }

    /**
     * @return ID of the step
     */
    public int getId() { return mId; }

    /**
     * @return short description of step
     */
    public String getShortDescription() { return mShortDescription; }

    /**
     * @return description of step
     */
    public String getDescription() { return mDescription; }

    /**
     * @return video URL for step
     */
    public String getVideoUrl() { return mVideoUrl; }

    /**
     * @return thumbnail URL for step
     */
    public String getThumbnailUrl() { return mThumbnailUrl; }

    /**
     * @return return ID of the recipe this step belongs to
     */
    public int getRecipeId() { return mRecipeId; }
}
