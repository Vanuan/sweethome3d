package com.eteks.sweethome3d.swt;

import java.util.List;
import java.util.concurrent.Callable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import com.eteks.sweethome3d.model.Camera;
import com.eteks.sweethome3d.model.Home;
import com.eteks.sweethome3d.model.RecorderException;
import com.eteks.sweethome3d.model.UserPreferences;
import com.eteks.sweethome3d.swing.HomePane;
import com.eteks.sweethome3d.viewcontroller.HomeController;
import com.eteks.sweethome3d.viewcontroller.HomeView;
import com.eteks.sweethome3d.viewcontroller.View;

public class SwtHomeView implements HomeView {
  private Composite composite;
  class Constants {
    final static String FILE_MENU = "FILE_MENU";
    final static String EDIT_MENU = "EDIT_MENU";
    final static String FURNITURE_MENU = "FURNITURE_MENU";
    final static String PLAN_MENU = "PLAN_MENU";
    final static String VIEW_3D_MENU = "VIEW_3D_MENU";
    final static String HELP_MENU = "HELP_MENU";
    final static String OPEN_RECENT_HOME_MENU = "OPEN_RECENT_HOME_MENU";
    final static String ALIGN_OR_DISTRIBUTE_MENU = "ALIGN_OR_DISTRIBUTE_MENU";
    final static String SORT_HOME_FURNITURE_MENU = "SORT_HOME_FURNITURE_MENU";
    final static String DISPLAY_HOME_FURNITURE_PROPERTY_MENU = "DISPLAY_HOME_FURNITURE_PROPERTY_MENU";
    final static String MODIFY_TEXT_STYLE = "MODIFY_TEXT_STYLE";
    final static String GO_TO_POINT_OF_VIEW = "GO_TO_POINT_OF_VIEW";
    final static String SELECT_OBJECT_MENU = "SELECT_OBJECT_MENU";

  };
  
  SwtHomeView(Home home, UserPreferences preferences, HomeController homeController) {
    Shell shell = new Shell();
    composite = new Composite(shell, 0);
    Menu menu = createMenuBar(shell, preferences);
    shell.setMenuBar(menu);
    createToolBar();
    createMainPane();
  }

  private void createMainPane() {
    // TODO Auto-generated method stub
    
  }

  private void createToolBar() {
    // TODO Auto-generated method stub
    
  }

  private static String getMenuLabel(String key, UserPreferences preferences) {
    String label = preferences.getLocalizedString(HomePane.class, key + ".Name");
    String mnemonic = preferences.getLocalizedString(HomePane.class, key + ".MnemonicKey");
    int index = label.toLowerCase().indexOf(mnemonic.toLowerCase());
    if (index != -1) {
      return label.substring(0, index) + "&" + label.substring(index, label.length());
    } else {
      return label;
    }
  }
  
  private Menu createMenuBar(Shell shell, UserPreferences preferences) {
    Menu menuBar = new Menu(shell, SWT.BAR);
    String [][] menus = {{Constants.FILE_MENU},
                         {Constants.EDIT_MENU},
                         {Constants.FURNITURE_MENU},
                         {Constants.PLAN_MENU},
                         {Constants.VIEW_3D_MENU},
                         {Constants.HELP_MENU}};
    for (String [] menuLabels: menus) {
      MenuItem menuHeader = new MenuItem(menuBar, SWT.CASCADE);
      menuHeader.setText(getMenuLabel(menuLabels[0], preferences));
      Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
      menuHeader.setMenu(fileMenu);
    }
//    MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
//    Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
//    fileMenuHeader.setText(getMenuLabel(, preferences));
//    fileMenuHeader.setMenu(fileMenu);
//
//    MenuItem fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
//    fileSaveItem.setText(getMenuLabel(Constants.EDIT_MENU, preferences));
//
//    MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
//    fileExitItem.setText("E&xit");
//
//    MenuItem helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
//    helpMenuHeader.setText("&Help");
//
//    Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
//    helpMenuHeader.setMenu(helpMenu);
//
//    MenuItem helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
//    helpGetHelpItem.setText("&Get Help");

    return menuBar;
}

  public void setEnabled(ActionType actionType, boolean enabled) {
    // TODO Auto-generated method stub

  }

  public void setUndoRedoName(String undoText, String redoText) {
    // TODO Auto-generated method stub

  }

  public void setTransferEnabled(boolean enabled) {
    // TODO Auto-generated method stub

  }

  public void detachView(View view) {
    // TODO Auto-generated method stub

  }

  public void attachView(View view) {
    // TODO Auto-generated method stub

  }

  public String showOpenDialog() {
    // TODO Auto-generated method stub
    return null;
  }

  public String showImportLanguageLibraryDialog() {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean confirmReplaceLanguageLibrary(String languageLibraryName) {
    // TODO Auto-generated method stub
    return false;
  }

  public String showImportFurnitureLibraryDialog() {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean confirmReplaceFurnitureLibrary(String furnitureLibraryName) {
    // TODO Auto-generated method stub
    return false;
  }

  public String showImportTexturesLibraryDialog() {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean confirmReplaceTexturesLibrary(String texturesLibraryName) {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean confirmReplacePlugin(String pluginName) {
    // TODO Auto-generated method stub
    return false;
  }

  public String showSaveDialog(String homeName) {
    // TODO Auto-generated method stub
    return null;
  }

  public SaveAnswer confirmSave(String homeName) {
    // TODO Auto-generated method stub
    return SaveAnswer.DO_NOT_SAVE;
  }

  public boolean confirmSaveNewerHome(String homeName) {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean confirmDeleteCatalogSelection() {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean confirmExit() {
    // TODO Auto-generated method stub
    return false;
  }

  public void showError(String message) {
    // TODO Auto-generated method stub

  }

  public void showMessage(String message) {
    // TODO Auto-generated method stub

  }

  public boolean showActionTipMessage(String actionTipKey) {
    // TODO Auto-generated method stub
    return false;
  }

  public void showAboutDialog() {
    // TODO Auto-generated method stub

  }

  public Callable<Void> showPrintDialog() {
    // TODO Auto-generated method stub
    return null;
  }

  public String showPrintToPDFDialog(String homeName) {
    // TODO Auto-generated method stub
    return null;
  }

  public void printToPDF(String pdfFile) throws RecorderException {
    // TODO Auto-generated method stub

  }

  public String showExportToCSVDialog(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  public void exportToCSV(String csvName) throws RecorderException {
    // TODO Auto-generated method stub

  }

  public String showExportToSVGDialog(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  public void exportToSVG(String svgName) throws RecorderException {
    // TODO Auto-generated method stub

  }

  public String showExportToOBJDialog(String homeName) {
    // TODO Auto-generated method stub
    return null;
  }

  public void exportToOBJ(String objFile) throws RecorderException {
    // TODO Auto-generated method stub

  }

  public String showStoreCameraDialog(String cameraName) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<Camera> showDeletedCamerasDialog() {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean isClipboardEmpty() {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean showUpdatesMessage(String updatesMessage, boolean showOnlyMessage) {
    // TODO Auto-generated method stub
    return false;
  }

  public void invokeLater(Runnable runnable) {
    // TODO Auto-generated method stub

  }

  public Object getObject() {
    return composite;
  }

}
