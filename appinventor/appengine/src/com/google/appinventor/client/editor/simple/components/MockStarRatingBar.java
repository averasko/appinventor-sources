// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import java.util.Iterator;

/**
 * @author Aleh Veraskouski (aleh.veraskouski@gmail.com)
 */
public final class MockStarRatingBar extends MockVisibleComponent {

  /**
   * Component type name.
   */
  public static final String TYPE = "StarRatingBar";

  // Widget for showing a full star rating bar
  private final HorizontalPanel hzPanel;

  //TODO: Consider using GWT star rating bar equivalent instead of just an image.
  //https://code.google.com/p/gwtquery-plugins/wiki/RatingsPluginGettingStarted

  /**
   * Creates a new MockStarRatingBar component.
   *
   * @param editor editor of source file the component belongs to
   */
  public MockStarRatingBar(SimpleEditor editor) {
    super(editor, TYPE, images.starFull());

    // Initialize a mock full rating bar UI
    hzPanel = new HorizontalPanel();
    hzPanel.setStylePrimaryName("ode-SimpleMockComponent");

    //adding the correct number of stars
    updateRatingBarUI();

    initComponent(hzPanel);
  }

  private int numStars = 5;
  private int rating = 2;

  private void setNumStarsProperty(String text) {
    this.numStars = Integer.valueOf(text);
    updateRatingBarUI();
  }

  private void setRatingProperty(String text) {
    this.rating = Integer.valueOf(text);
    updateRatingBarUI();
  }

  private void updateRatingBarUI() {
    hzPanel.clear();
    for(int i = 0; i < this.rating; i++) {
      Widget starFullWidget = new Image(images.starFull());
      hzPanel.add(starFullWidget);
    }
    for(int i = this.rating; i < this.numStars; i++) {
      Widget starEmptyWidget = new Image(images.starEmpty());
      hzPanel.add(starEmptyWidget);
    }
  }

  // PropertyChangeListener implementation

  @Override
  public void onPropertyChange(String propertyName, String newValue) {
    super.onPropertyChange(propertyName, newValue);

    // Apply changed properties to the mock component
    if (propertyName.equals("NumStars")) {
      setNumStarsProperty(newValue);
    } else if (propertyName.equals("Rating")) {
      setRatingProperty(newValue);
    }
  }

  @Override
  public int getPreferredWidth() {
    return this.numStars * 16;
  }

  @Override
  public int getPreferredHeight() {
    return 16;
  }

}
