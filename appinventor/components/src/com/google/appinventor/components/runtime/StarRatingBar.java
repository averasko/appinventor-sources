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
    private final static String LOG_TAG = "RatingBar";
    private static final boolean DEBUG = false;

    private final RatingBar ratingBar;

    // the number of stars and the current rating value
    private int numStars;
    private float rating;

    /**
     * Creates a new RatingBar component.
     *
     * @param container container that the component will be placed in
     */
    public StarRatingBar(ComponentContainer container) {
        super(container);
        ratingBar = new RatingBar(container.$context());

        // Adds the component to its designated container
        container.$add(this);

        // Initial property values
        numStars = 5;
        rating = (float)numStars / 2;

        ratingBar.setOnRatingBarChangeListener(this);
    }

    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_INTEGER,
            defaultValue = 5 + "")
    @SimpleProperty(description = "?description?",
            userVisible = true)
    public void NumStars(int numStars) {
        this.numStars = Math.max(0, numStars);
        if (DEBUG) {
            Log.d(LOG_TAG, "NumStars value is set to: " + numStars);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE,
            description = "?description?", userVisible = true)
    public int NumStars() {
        return this.numStars;
    }

    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT,
            defaultValue = 2.5f + "")
    @SimpleProperty(description = "?description?",
            userVisible = true)
    public void Rating(float rating) {
        this.rating = rating;
        if (DEBUG) {
            Log.d(LOG_TAG, "Rating value is set to: " + rating);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE,
            description = "?description?", userVisible = true)
    public float Rating() {
        return rating;
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
        PositionChanged(rating);
    }

    /**
     * Called whenever the rating got changed.
     */
    @SimpleEvent
    public void PositionChanged(float rating) {
        EventDispatcher.dispatchEvent(this, "PositionChanged", rating);
    }

}
