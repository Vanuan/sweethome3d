package com.eteks.sweethome3d.viewcontroller;

import com.eteks.sweethome3d.HomeFrameController;
import com.eteks.sweethome3d.model.Home;
import com.eteks.sweethome3d.model.HomeApplication;
import com.eteks.sweethome3d.tools.OperatingSystem;

public abstract class HomeFrameView implements DialogView {
  protected HomeFrameController controller;
  protected Home                home;
  protected HomeApplication     application;
  protected static int          newHomeCount;
  protected int                 newHomeNumber;
  protected ContentManager      contentManager;

  public abstract Object getObject();

  public HomeFrameView(Home home, HomeApplication application, ContentManager contentManager,
                       HomeFrameController controller) {
    this.home = home;
    this.controller = controller;
    this.application = application;
    this.contentManager = contentManager;
    // If home is unnamed, give it a number
    if (home.getName() == null) {
      this.newHomeNumber = ++newHomeCount;
    }

  }

  /**
   * Builds and shows the frame that displays this pane.
   */
  public void displayView(View parentView) {
    createHomeFrame();
    updateFrameImages();
    updateFrameTitle();
    setOrientation();
    computeFrameBounds();
    enableAutoResize();
    setMenuMacOs();
    addListeners();
    showHomeFrame();
  }

  /**
   * Updates <code>frame</code> title from <code>home</code> and
   * <code>application</code> name.
   */
  protected void updateFrameTitle() {
    String homeName = home.getName();
    String homeDisplayedName;
    if (homeName == null) {
      homeDisplayedName = application.getUserPreferences().getLocalizedString(HomeFrameView.class, "untitled");
      if (newHomeNumber > 1) {
        homeDisplayedName += " " + newHomeNumber;
      }
    } else {
      homeDisplayedName = this.contentManager.getPresentationName(homeName, ContentManager.ContentType.SWEET_HOME_3D);
    }

    if (home.isRecovered()) {
      homeDisplayedName += " " + application.getUserPreferences().getLocalizedString(HomeFrameView.class, "recovered");
    }
    String title = homeDisplayedName;
    if (OperatingSystem.isMacOSX()) {
      updateMacOsTitle(homeName);
    } else {
      title += " - " + application.getName();
      if (home.isModified() || home.isRecovered()) {
        title = "* " + title;
      }
    }
    setTitle(title);
  }

  protected void updateMacOsTitle(String homeName) {
  }

  protected abstract void setTitle(String title);

  protected void updateFrameImages() {
    String [] resourceNames = {"/com/eteks/sweethome3d/resources/frameIcon.png",
        "/com/eteks/sweethome3d/resources/frameIcon32x32.png"};
    setFrameImages(resourceNames);
  }

  protected abstract void setFrameImages(String [] resourceNames);

  protected abstract void showHomeFrame();

  protected abstract void addListeners();

  protected abstract void setMenuMacOs();

  protected abstract void enableAutoResize();

  protected abstract void computeFrameBounds();

  protected abstract void setOrientation();

  protected abstract void createHomeFrame();

}
