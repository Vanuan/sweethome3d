package com.eteks.sweethome3d.swt;

import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.eteks.sweethome3d.HomeFrameController;
import com.eteks.sweethome3d.model.Home;
import com.eteks.sweethome3d.model.HomeApplication;
import com.eteks.sweethome3d.viewcontroller.ContentManager;
import com.eteks.sweethome3d.viewcontroller.HomeFrameView;
import com.eteks.sweethome3d.viewcontroller.HomeView;
import com.eteks.sweethome3d.viewcontroller.View;

class HomeFrameSWT extends HomeFrameView {

  private Shell shell;

  public HomeFrameSWT(Home home, HomeApplication application, ContentManager contentManager,
                      HomeFrameController homeFrameController) {
    super(home, application, contentManager, homeFrameController);
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
    updateFrameImages();
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

  @Override
  protected void setTitle(String title) {
    shell.setText(title);
  }

  @Override
  protected void setFrameImages(String [] resourceNames) {
    Image [] images = new Image [resourceNames.length];
    for (int i = 0; i < resourceNames.length; ++i) {
      images [i] = new Image(shell.getDisplay(), HomeFrameSWT.class.getResourceAsStream(resourceNames [i]));
    }
    shell.setImages(images);
  }

}