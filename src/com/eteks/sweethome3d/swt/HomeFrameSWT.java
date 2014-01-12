package com.eteks.sweethome3d.swt;

import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.eteks.sweethome3d.HomeFrameController;
import com.eteks.sweethome3d.model.Home;
import com.eteks.sweethome3d.model.HomeApplication;
import com.eteks.sweethome3d.viewcontroller.ContentManager;
import com.eteks.sweethome3d.viewcontroller.HomeFrameView;
import com.eteks.sweethome3d.viewcontroller.HomeView;
import com.eteks.sweethome3d.viewcontroller.View;

class HomeFrameSWT implements HomeFrameView {

  private Shell               shell;
  private HomeFrameController controller;
  private Home                home;
  private HomeApplication     application;
  private static int          newHomeCount;
  private int                 newHomeNumber;
  private ContentManager      contentManager;

  public HomeFrameSWT(Home home, HomeApplication application, ContentManager contentManager,
                      HomeFrameController homeFrameController) {
    // this.shell = new Shell();
    this.controller = homeFrameController;
    this.home = home;
    this.application = application;
    this.contentManager = contentManager;

    // If home is unnamed, give it a number
    if (home.getName() == null) {
      this.newHomeNumber = ++newHomeCount;
    }
    // Set controller view as a child pane
    HomeView homeView = this.controller.getHomeController().getView();
    this.shell = ((Composite) homeView.getObject()).getShell();
  }

  public Object getObject() {
    return this.shell;
  }

  public void displayView(View parentView) {
    this.shell.addShellListener(new ShellAdapter() {
      public void shellClosed(ShellEvent arg0) {
        controller.getHomeController().close();
      }
    });
    updateFrameTitle();
    // TODO Update frame image
    // TODO Change component orientation
    // TODO Compute frame size and location
    // TODO Enable windows to update their content while window resizing
    // TODO Add a listener that keeps track of window location and size
    // TODO Add a listener to preferences to apply component orientation to
    // frame
    // matching current language
    // TODO Dispose window when a home is deleted
    // TODO Update title when the name or the modified state of home changes

    this.shell.open();
  }

  /**
   * Updates <code>frame</code> title from <code>home</code> and
   * <code>application</code> name.
   */
  private void updateFrameTitle() {
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
    title += " - " + application.getName();
    if (home.isModified() || home.isRecovered()) {
      title = "* " + title;
    }
    shell.setText(title);
  }

}