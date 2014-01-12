package com.eteks.sweethome3d.swt;

import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.eteks.sweethome3d.HomeFrameController;
import com.eteks.sweethome3d.viewcontroller.HomeFrameView;
import com.eteks.sweethome3d.viewcontroller.HomeView;
import com.eteks.sweethome3d.viewcontroller.View;

class HomeFrameSWT implements HomeFrameView {

  private Shell shell;
  private HomeFrameController controller;

  public HomeFrameSWT(HomeFrameController homeFrameController) {
    //this.shell = new Shell();
    this.controller = homeFrameController;
    // Set controller view as a child pane
    HomeView homeView = this.controller.getHomeController().getView();
    this.shell = ((Composite)homeView.getObject()).getShell();
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
    this.shell.open();
  }
  
}