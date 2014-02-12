package com.eteks.sweethome3d.swt;

import java.util.List;
import java.util.concurrent.Callable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

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

  SwtHomeView(Home home, UserPreferences preferences, HomeController homeController) {
    Shell shell = new Shell();

    Menu menu = createMenuBar(shell, preferences);
    shell.setMenuBar(menu);
    createToolBar(shell, preferences);

    GridLayout gridLayout = new GridLayout();
    shell.setLayout(gridLayout);
    composite = new Composite(shell, SWT.NONE);
    composite.setLayout (new FillLayout());
    GridData gridData = new GridData(GridData.FILL, GridData.FILL, true, true);
    composite.setLayoutData(gridData);

    createMainPane(composite);
  }

  private void createMainPane(Composite composite) {
    SashForm form = new SashForm(composite, SWT.HORIZONTAL);
    form.setLayout(new FillLayout());

    // catalog furniture
    Composite furniturePane = new Composite(form, SWT.NONE);
    furniturePane.setLayout(new FillLayout());
    SashForm furnitureForm = new SashForm(furniturePane, SWT.VERTICAL);
    furnitureForm.setLayout(new FillLayout());
    // furniture catalog
    Composite furnitureCatalogPane = new Composite(furnitureForm, SWT.BORDER);
    furnitureCatalogPane.setLayout(new FillLayout());
    new Label(furnitureCatalogPane, SWT.CENTER).setText("Furniture catalog pane");
    // furniture filter
    Composite furnitureFilterPane = new Composite(furnitureForm, SWT.BORDER);
    furnitureFilterPane.setLayout(new FillLayout());
    new Label(furnitureFilterPane, SWT.CENTER).setText("Furniture filter pane");

    // plan view 3d
    Composite planPane = new Composite(form, SWT.NONE);
    planPane.setLayout(new FillLayout());
    SashForm planForm = new SashForm(planPane, SWT.VERTICAL);
    planForm.setLayout(new FillLayout());
    // 2d
    Composite plan2DPane = new Composite(planForm, SWT.BORDER);
    plan2DPane.setLayout(new FillLayout());
    new Label(plan2DPane, SWT.CENTER).setText("Plan 2D");
    // 3d
    Composite plan3DPane = new Composite(planForm, SWT.BORDER);
    plan3DPane.setLayout(new FillLayout());
    new Label(plan3DPane, SWT.CENTER).setText("Plan 3D");
  }

  private void createToolBar(Shell shell, UserPreferences preferences) {
    AbstractMenuItem [] toolbarInfo = new AbstractMenuItem [] {
      new AbstractMenuItem(Constants.NEW_HOME),
      new AbstractMenuItem(Constants.OPEN),
      new AbstractMenuItem(Constants.SAVE),
      new AbstractMenuItem(Constants.PREFERENCES),
      new AbstractMenuItem(),
      new AbstractMenuItem(Constants.UNDO),
      new AbstractMenuItem(Constants.REDO),
      new AbstractMenuItem(),
      new AbstractMenuItem(Constants.CUT),
      new AbstractMenuItem(Constants.COPY),
      new AbstractMenuItem(Constants.PASTE),
      new AbstractMenuItem(),
      new AbstractMenuItem(Constants.ADD_HOME_FURNITURE),
      new AbstractMenuItem(),
      new AbstractMenuItem(Constants.SELECT),
      new AbstractMenuItem(Constants.PAN),
      new AbstractMenuItem(Constants.CREATE_WALLS),
      new AbstractMenuItem(Constants.CREATE_ROOMS),
      new AbstractMenuItem(Constants.CREATE_DIMENSION_LINES),
      new AbstractMenuItem(Constants.CREATE_LABELS),
      new AbstractMenuItem(),
      new AbstractMenuItem(Constants.INCREASE_TEXT_SIZE),
      new AbstractMenuItem(Constants.DECREASE_TEXT_SIZE),
      new AbstractMenuItem(Constants.TOGGLE_BOLD_STYLE),
      new AbstractMenuItem(Constants.TOGGLE_ITALIC_STYLE),
      new AbstractMenuItem(),
      new AbstractMenuItem(Constants.ZOOM_IN),
      new AbstractMenuItem(Constants.ZOOM_OUT),
      new AbstractMenuItem(),
      new AbstractMenuItem(Constants.CREATE_PHOTO),
      new AbstractMenuItem(Constants.CREATE_VIDEO),
      new AbstractMenuItem(),
      new AbstractMenuItem(Constants.HELP),
    };
    ToolBar toolBar = new ToolBar (shell, SWT.FLAT | SWT.BORDER | SWT.NO_FOCUS);
    //Rectangle clientArea = shell.getClientArea ();
    //toolBar.setLocation(clientArea.x, clientArea.y);
    for (int i = 0; i < toolbarInfo.length; ++i) {
      String key = toolbarInfo[i].getId();
      if (key != null) {
        ToolItem item = new ToolItem(toolBar, SWT.PUSH);
        Image image = getIcon(shell, preferences, key);
        item.setImage(image);
      } else {
        new ToolItem(toolBar, SWT.SEPARATOR);
      }
    }
    toolBar.pack ();
  }

  private Image getIcon(Shell shell, UserPreferences preferences, String key) {
    try {
      String imgPath = "/com/eteks/sweethome3d/swing/" + preferences.getLocalizedString(HomePane.class, key + ".SmallIcon");
      Image image = new Image(shell.getDisplay(), HomeFrameSWT.class.getResourceAsStream(imgPath));
      return image;
    } catch(IllegalArgumentException e) {
      return null;
    }
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
    for (AbstractMenuItem menuItem : mMenuInfo) {
      createSwtMenu(shell, preferences, menuBar, menuItem);
    }
    return menuBar;
  }

  private void createSwtMenu(Shell shell, UserPreferences preferences,
                             Menu menuBar, AbstractMenuItem menuItem) {
    String menuName = menuItem.getId();
    if (menuName != null) {
      AbstractMenuItem [] subMenuItems = menuItem.getChildren();
      MenuItem menuHeader;
      if (subMenuItems != null) {
        Menu currentMenu = new Menu(shell, SWT.DROP_DOWN);
        menuHeader = new MenuItem(menuBar, SWT.CASCADE);
        menuHeader.setMenu(currentMenu);
        for (AbstractMenuItem subMenuItem : subMenuItems) {
          createSwtMenu(shell, preferences, currentMenu, subMenuItem);
        }
      } else {
        menuHeader = new MenuItem(menuBar, SWT.PUSH);
      }
      menuHeader.setText(getMenuLabel(menuName, preferences));
      Image image = getIcon(shell, preferences, menuName);
      if(image != null) {
        menuHeader.setImage(image);
      }
    }
    else {
      new MenuItem(menuBar, SWT.SEPARATOR);
    }
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
