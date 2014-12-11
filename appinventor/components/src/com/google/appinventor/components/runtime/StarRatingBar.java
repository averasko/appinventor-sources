package com.google.appinventor.components.runtime;

import android.R;
import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;

/**
 *
 * @author Aleh Veraskouski (aleh.veraskouski@gmail.com)
 */
@DesignerComponent(version = YaVersion.STARRATINGBAR_COMPONENT_VERSION,
    description = "A RatingBar is a ProgressBar that shows a rating in stars. " +
            "The user can touch/drag or use arrow keys to set the rating.",
    category = ComponentCategory.USERINTERFACE)
@SimpleObject
public class StarRatingBar extends AndroidViewComponent implements RatingBar.OnRatingBarChangeListener {
    private final static String LOG_TAG = "StarRatingBar";
    private static final boolean DEBUG = false;

    private final RatingBar ratingBar;

    private int numStars; // the number of stars and the current rating value
    private int rating; //we do not support fractional values, in contrast to the AndroidSDK

    /**
     * Creates a new RatingBar component.
     *
     * @param container container that the component will be placed in
     */
    public StarRatingBar(ComponentContainer container) {
        super(container);
        ratingBar = new RatingBar(container.$context(), null, android.R.attr.ratingBarStyle);

        // Adds the component to its designated container
        container.$add(this);

        // Initial property values
        NumStars(Component.STARRATINGBAR_NUM_STARS);
        Rating(Component.STARRATINGBAR_RATING);
        ratingBar.setStepSize(1.0f); //we do not want fractional ratings

        ratingBar.setOnRatingBarChangeListener(this);
    }

    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER,
            defaultValue = Component.STARRATINGBAR_NUM_STARS + "")
    @SimpleProperty(description = "?description?",
            userVisible = true)
    public void NumStars(int numStars) {
        ratingBar.setNumStars(numStars);
        this.numStars = ratingBar.getNumStars();

        if (DEBUG) {
            Log.d(LOG_TAG, "NumStars value is set to: " + this.numStars);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE,
            description = "?description?", userVisible = true)
    public int NumStars() {
        return this.numStars;
    }

    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER,
            defaultValue = Component.STARRATINGBAR_RATING + "")
    @SimpleProperty(description = "?description?",
            userVisible = true)
    public void Rating(int rating) {
        ratingBar.setRating((float)rating);
        this.rating = (int)ratingBar.getRating(); //we allow AndroidSDK to validate the value

        if (DEBUG) {
            Log.d(LOG_TAG, "Rating value is set to: " + this.rating);
        }

        RatingChanged(this.rating);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE,
            description = "?description?", userVisible = true)
    public int Rating() {
        return this.rating;
    }

    @Override
    public View getView() {
        return ratingBar;
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if (DEBUG) {
            Log.d(LOG_TAG, "onRatingChanged to: " + rating
                    + ", reporting to user as: " + rating);
        }
        RatingChanged((int)rating);
    }

    /**
     * Called whenever the rating got changed.
     */
    @SimpleEvent
    public void RatingChanged(int rating) {
        EventDispatcher.dispatchEvent(this, "RatingChanged", rating);
    }

    /**
     * Returns the component's vertical height, measured in pixels.
     *
     * @return height in pixels
     */
    @Override
    public int Height() {
        //overriding and removing the annotation as we don't want to give the user
        //ability to change the ratingbar's height and want to hide it in the block editor
        return getView().getHeight();
    }

    /**
     * Specifies the component's vertical height, measured in pixels.
     *
     * @param height in pixels
     */
    @Override
    public void Height(int height) {
        //overriding and removing the annotation as we don't want to give the user
        //ability to change the ratingbar's height and want to hide it in the block editor
        container.setChildHeight(this, height);
    }


}
