// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Image;

/**
 * @author Aleh Veraskouski (aleh.veraskouski@gmail.com)
 */
public final class MockStarRatingBar extends MockVisibleComponent {

  /**
   * Component type name.
   */
  public static final String TYPE = "StarRatingBar";

  // Widget for showing a full star rating bar
  private final SimplePanel uiWidget;

  //TODO: Consider using GWT star rating bar equivalent instead of just an image.
  //https://code.google.com/p/gwtquery-plugins/wiki/RatingsPluginGettingStarted

  /**
   * Creates a new MockStarRatingBar component.
   *
   * @param editor editor of source file the component belongs to
   */
  public MockStarRatingBar(SimpleEditor editor) {
    super(editor, TYPE, images.starRatingBar());

    // Initialize a mock full rating bar UI
    uiWidget = new SimplePanel();
    uiWidget.setStylePrimaryName("ode-SimpleMockComponent");

    uiWidget.setWidget(new Image(images.starRatingBarFull()));

    initComponent(uiWidget);
  }

}
