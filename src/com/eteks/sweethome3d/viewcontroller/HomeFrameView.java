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
}
