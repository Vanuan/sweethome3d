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

  class AbstractMenuItem {
    AbstractMenuItem[] children;
    String id;

    public AbstractMenuItem() {
      this.id = null;
      this.children = null;
    }

    public AbstractMenuItem(String id) {
      this.id = id;
      this.children = null;
    }

    public AbstractMenuItem(String id, AbstractMenuItem[] children) {
      this.id = id;
      this.children = children;
    }

    public String getId() {
      return this.id;
    }

    public AbstractMenuItem[] getChildren() {
      return this.children;
    }
  };

  class Constants {
    final static String FILE_MENU                            = "FILE_MENU";
    final static String NEW_HOME = "NEW_HOME";
    final static String OPEN = "OPEN";
    final static String OPEN_RECENT_HOME_MENU                = "OPEN_RECENT_HOME_MENU";
    final static String DELETE_RECENT_HOMES                = "DELETE_RECENT_HOMES";

    final static String CLOSE                = "CLOSE";
    final static String SAVE                = "SAVE";
    final static String SAVE_AS                = "SAVE_AS";
    final static String SAVE_AND_COMPRESS                = "SAVE_AND_COMPRESS";

    final static String PAGE_SETUP                = "PAGE_SETUP";
    final static String PRINT_PREVIEW                = "PRINT_PREVIEW";
    final static String PRINT                = "PRINT";
    final static String PRINT_TO_PDF                = "PRINT_TO_PDF";

    final static String PREFERENCES                = "PREFERENCES";
    final static String EXIT                = "EXIT";

    final static String EDIT_MENU                            = "EDIT_MENU";
    final static String UNDO                            = "UNDO";
    final static String REDO                            = "REDO";
    final static String CUT                            = "CUT";
    final static String COPY                            = "COPY";
    final static String PASTE                            = "PASTE";
    final static String DELETE                            = "DELETE";
    final static String SELECT_ALL                            = "SELECT_ALL";

    final static String FURNITURE_MENU                       = "FURNITURE_MENU";
    final static String ADD_HOME_FURNITURE                            = "ADD_HOME_FURNITURE";
    final static String DELETE_HOME_FURNITURE                            = "DELETE_HOME_FURNITURE";
    final static String MODIFY_FURNITURE                            = "MODIFY_FURNITURE";
    final static String GROUP_FURNITURE = "GROUP_FURNITURE";
    final static String UNGROUP_FURNITURE = "UNGROUP_FURNITURE";

    final static String ALIGN_OR_DISTRIBUTE_MENU             = "ALIGN_OR_DISTRIBUTE_MENU";
    final static String ALIGN_FURNITURE_ON_TOP = "ALIGN_FURNITURE_ON_TOP";
    final static String ALIGN_FURNITURE_ON_BOTTOM = "ALIGN_FURNITURE_ON_BOTTOM";
    final static String ALIGN_FURNITURE_ON_LEFT = "ALIGN_FURNITURE_ON_LEFT";
    final static String ALIGN_FURNITURE_ON_RIGHT = "ALIGN_FURNITURE_ON_RIGHT";
    final static String ALIGN_FURNITURE_ON_FRONT_SIDE = "ALIGN_FURNITURE_ON_FRONT_SIDE";
    final static String ALIGN_FURNITURE_ON_BACK_SIDE = "ALIGN_FURNITURE_ON_BACK_SIDE";
    final static String ALIGN_FURNITURE_ON_LEFT_SIDE = "ALIGN_FURNITURE_ON_LEFT_SIDE";
    final static String ALIGN_FURNITURE_ON_RIGHT_SIDE = "ALIGN_FURNITURE_ON_RIGHT_SIDE";
    final static String ALIGN_FURNITURE_SIDE_BY_SIDE = "ALIGN_FURNITURE_SIDE_BY_SIDE";
    final static String DISTRIBUTE_FURNITURE_HORIZONTALLY = "DISTRIBUTE_FURNITURE_HORIZONTALLY";
    final static String DISTRIBUTE_FURNITURE_VERTICALLY = "DISTRIBUTE_FURNITURE_VERTICALLY";

    final static String IMPORT_FURNITURE                            = "IMPORT_FURNITURE";
    final static String IMPORT_FURNITURE_LIBRARY                            = "IMPORT_FURNITURE_LIBRARY";
    final static String IMPORT_TEXTURE                            = "IMPORT_TEXTURE";
    final static String IMPORT_TEXTURES_LIBRARY                            = "IMPORT_TEXTURES_LIBRARY";

    final static String SORT_HOME_FURNITURE_MENU             = "SORT_HOME_FURNITURE_MENU";
    final static String SORT_HOME_FURNITURE_BY_CATALOG_ID                            = "SORT_HOME_FURNITURE_BY_CATALOG_ID";
    final static String SORT_HOME_FURNITURE_BY_NAME                            = "SORT_HOME_FURNITURE_BY_NAME";
    final static String SORT_HOME_FURNITURE_BY_WIDTH                            = "SORT_HOME_FURNITURE_BY_WIDTH";
    final static String SORT_HOME_FURNITURE_BY_DEPTH                            = "SORT_HOME_FURNITURE_BY_DEPTH";
    final static String SORT_HOME_FURNITURE_BY_HEIGHT                            = "SORT_HOME_FURNITURE_BY_HEIGHT";
    final static String SORT_HOME_FURNITURE_BY_X                            = "SORT_HOME_FURNITURE_BY_X";
    final static String SORT_HOME_FURNITURE_BY_Y                            = "SORT_HOME_FURNITURE_BY_Y";
    final static String SORT_HOME_FURNITURE_BY_ELEVATION                            = "SORT_HOME_FURNITURE_BY_ELEVATION";
    final static String SORT_HOME_FURNITURE_BY_ANGLE                            = "SORT_HOME_FURNITURE_BY_ANGLE";
    final static String SORT_HOME_FURNITURE_BY_LEVEL                            = "SORT_HOME_FURNITURE_BY_LEVEL";
    final static String SORT_HOME_FURNITURE_BY_COLOR                            = "SORT_HOME_FURNITURE_BY_COLOR";
    final static String SORT_HOME_FURNITURE_BY_TEXTURE                            = "SORT_HOME_FURNITURE_BY_TEXTURE";
    final static String SORT_HOME_FURNITURE_BY_MOVABILITY                            = "SORT_HOME_FURNITURE_BY_MOVABILITY";
    final static String SORT_HOME_FURNITURE_BY_TYPE                            = "SORT_HOME_FURNITURE_BY_TYPE";
    final static String SORT_HOME_FURNITURE_BY_VISIBILITY                            = "SORT_HOME_FURNITURE_BY_VISIBILITY";
    final static String SORT_HOME_FURNITURE_BY_PRICE                            = "SORT_HOME_FURNITURE_BY_PRICE";
    final static String SORT_HOME_FURNITURE_BY_VALUE_ADDED_TAX_PERCENTAGE                            = "SORT_HOME_FURNITURE_BY_VALUE_ADDED_TAX_PERCENTAGE";
    final static String SORT_HOME_FURNITURE_BY_VALUE_ADDED_TAX                            = "SORT_HOME_FURNITURE_BY_VALUE_ADDED_TAX";
    final static String SORT_HOME_FURNITURE_BY_PRICE_VALUE_ADDED_TAX_INCLUDED                            = "SORT_HOME_FURNITURE_BY_PRICE_VALUE_ADDED_TAX_INCLUDED";
    final static String SORT_HOME_FURNITURE_BY_DESCENDING_ORDER                            = "SORT_HOME_FURNITURE_BY_DESCENDING_ORDER";
    final static String DISPLAY_HOME_FURNITURE_CATALOG_ID                            = "DISPLAY_HOME_FURNITURE_CATALOG_ID";
    final static String DISPLAY_HOME_FURNITURE_NAME                            = "DISPLAY_HOME_FURNITURE_NAME";
    final static String DISPLAY_HOME_FURNITURE_WIDTH                            = "DISPLAY_HOME_FURNITURE_WIDTH";
    final static String DISPLAY_HOME_FURNITURE_DEPTH                            = "DISPLAY_HOME_FURNITURE_DEPTH";
    final static String DISPLAY_HOME_FURNITURE_HEIGHT = "DISPLAY_HOME_FURNITURE_HEIGHT";
    final static String DISPLAY_HOME_FURNITURE_X = "DISPLAY_HOME_FURNITURE_X";
    final static String DISPLAY_HOME_FURNITURE_Y = "DISPLAY_HOME_FURNITURE_Y";
    final static String DISPLAY_HOME_FURNITURE_ELEVATION = "DISPLAY_HOME_FURNITURE_ELEVATION";
    final static String DISPLAY_HOME_FURNITURE_ANGLE = "DISPLAY_HOME_FURNITURE_ANGLE";
    final static String DISPLAY_HOME_FURNITURE_COLOR = "DISPLAY_HOME_FURNITURE_COLOR";
    final static String DISPLAY_HOME_FURNITURE_LEVEL = "DISPLAY_HOME_FURNITURE_LEVEL";
    final static String DISPLAY_HOME_FURNITURE_TEXTURE = "DISPLAY_HOME_FURNITURE_TEXTURE";
    final static String DISPLAY_HOME_FURNITURE_MOVABLE = "DISPLAY_HOME_FURNITURE_MOVABLE";
    final static String DISPLAY_HOME_FURNITURE_DOOR_OR_WINDOW = "DISPLAY_HOME_FURNITURE_DOOR_OR_WINDOW";
    final static String DISPLAY_HOME_FURNITURE_VISIBLE = "DISPLAY_HOME_FURNITURE_VISIBLE";
    final static String DISPLAY_HOME_FURNITURE_PRICE = "DISPLAY_HOME_FURNITURE_PRICE";
    final static String DISPLAY_HOME_FURNITURE_VALUE_ADDED_TAX_PERCENTAGE = "DISPLAY_HOME_FURNITURE_VALUE_ADDED_TAX_PERCENTAGE";
    final static String DISPLAY_HOME_FURNITURE_VALUE_ADDED_TAX = "DISPLAY_HOME_FURNITURE_VALUE_ADDED_TAX";
    final static String DISPLAY_HOME_FURNITURE_PRICE_VALUE_ADDED_TAX_INCLUDED = "DISPLAY_HOME_FURNITURE_PRICE_VALUE_ADDED_TAX_INCLUDED";
    final static String EXPORT_TO_CSV = "EXPORT_TO_CSV";
    final static String SELECT = "SELECT";
    final static String PAN = "PAN";
    final static String CREATE_WALLS = "CREATE_WALLS";
    final static String CREATE_ROOMS = "CREATE_ROOMS";
    final static String CREATE_DIMENSION_LINES = "CREATE_DIMENSION_LINES";
    final static String CREATE_LABELS = "CREATE_LABELS";
    final static String DELETE_SELECTION = "DELETE_SELECTION";
    final static String LOCK_BASE_PLAN = "LOCK_BASE_PLAN";
    final static String UNLOCK_BASE_PLAN = "UNLOCK_BASE_PLAN";
    final static String MODIFY_COMPASS = "MODIFY_COMPASS";
    final static String MODIFY_WALL = "MODIFY_WALL";
    final static String REVERSE_WALL_DIRECTION = "REVERSE_WALL_DIRECTION";
    final static String SPLIT_WALL = "SPLIT_WALL";
    final static String MODIFY_ROOM = "MODIFY_ROOM";
    final static String MODIFY_LABEL = "MODIFY_LABEL";
    final static String INCREASE_TEXT_SIZE = "INCREASE_TEXT_SIZE";
    final static String DECREASE_TEXT_SIZE = "DECREASE_TEXT_SIZE";
    final static String TOGGLE_BOLD_STYLE = "TOGGLE_BOLD_STYLE";
    final static String TOGGLE_ITALIC_STYLE = "TOGGLE_ITALIC_STYLE";
    final static String IMPORT_BACKGROUND_IMAGE = "IMPORT_BACKGROUND_IMAGE";
    final static String MODIFY_BACKGROUND_IMAGE = "MODIFY_BACKGROUND_IMAGE";
    final static String HIDE_BACKGROUND_IMAGE = "HIDE_BACKGROUND_IMAGE";
    final static String SHOW_BACKGROUND_IMAGE = "SHOW_BACKGROUND_IMAGE";
    final static String DELETE_BACKGROUND_IMAGE = "DELETE_BACKGROUND_IMAGE";
    final static String ADD_LEVEL = "ADD_LEVEL";
    final static String DELETE_LEVEL = "DELETE_LEVEL";
    final static String MODIFY_LEVEL = "MODIFY_LEVEL";
    final static String ZOOM_OUT = "ZOOM_OUT";
    final static String ZOOM_IN = "ZOOM_IN";
    final static String EXPORT_TO_SVG = "EXPORT_TO_SVG";
    final static String VIEW_FROM_TOP = "VIEW_FROM_TOP";
    final static String VIEW_FROM_OBSERVER = "VIEW_FROM_OBSERVER";
    final static String MODIFY_OBSERVER = "MODIFY_OBSERVER";
    final static String STORE_POINT_OF_VIEW = "STORE_POINT_OF_VIEW";
    final static String DELETE_POINTS_OF_VIEW = "DELETE_POINTS_OF_VIEW";
    final static String CREATE_PHOTOS_AT_POINTS_OF_VIEW = "CREATE_PHOTOS_AT_POINTS_OF_VIEW";
    final static String DETACH_3D_VIEW = "DETACH_3D_VIEW";
    final static String ATTACH_3D_VIEW = "ATTACH_3D_VIEW";
    final static String DISPLAY_ALL_LEVELS = "DISPLAY_ALL_LEVELS";
    final static String DISPLAY_SELECTED_LEVEL = "DISPLAY_SELECTED_LEVEL";
    final static String MODIFY_3D_ATTRIBUTES = "MODIFY_3D_ATTRIBUTES";
    final static String CREATE_PHOTO = "CREATE_PHOTO";
    final static String CREATE_VIDEO = "CREATE_VIDEO";
    final static String EXPORT_TO_OBJ = "EXPORT_TO_OBJ";
    final static String HELP = "HELP";
    final static String ABOUT = "ABOUT";

    final static String PLAN_MENU                            = "PLAN_MENU";
    final static String VIEW_3D_MENU                         = "VIEW_3D_MENU";
    final static String HELP_MENU                            = "HELP_MENU";
    final static String DISPLAY_HOME_FURNITURE_PROPERTY_MENU = "DISPLAY_HOME_FURNITURE_PROPERTY_MENU";
    final static String MODIFY_TEXT_STYLE                    = "MODIFY_TEXT_STYLE";
    final static String GO_TO_POINT_OF_VIEW                  = "GO_TO_POINT_OF_VIEW";
    final static String SELECT_OBJECT_MENU                   = "SELECT_OBJECT_MENU";
  };


  private Menu createMenuBar(Shell shell, UserPreferences preferences) {
    AbstractMenuItem[] menuInfo = {
      new AbstractMenuItem(Constants.FILE_MENU, new AbstractMenuItem[]{
        new AbstractMenuItem(Constants.NEW_HOME),
        new AbstractMenuItem(Constants.OPEN),
        new AbstractMenuItem(Constants.OPEN_RECENT_HOME_MENU,
            new AbstractMenuItem[] {
                new AbstractMenuItem(Constants.DELETE_RECENT_HOMES)
        }),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.CLOSE),
        new AbstractMenuItem(Constants.SAVE),
        new AbstractMenuItem(Constants.SAVE_AS),
        new AbstractMenuItem(Constants.SAVE_AND_COMPRESS),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.PAGE_SETUP),
        new AbstractMenuItem(Constants.PRINT_PREVIEW),
        new AbstractMenuItem(Constants.PRINT),
        new AbstractMenuItem(Constants.PRINT_TO_PDF),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.PREFERENCES),
        new AbstractMenuItem(Constants.EXIT)
      }),
      new AbstractMenuItem(Constants.EDIT_MENU, new AbstractMenuItem[] {
        new AbstractMenuItem(Constants.UNDO),
        new AbstractMenuItem(Constants.REDO),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.CUT),
        new AbstractMenuItem(Constants.COPY),
        new AbstractMenuItem(Constants.PASTE),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.DELETE),
        new AbstractMenuItem(Constants.SELECT_ALL)
      }),
      new AbstractMenuItem(Constants.FURNITURE_MENU, new AbstractMenuItem[] {
        new AbstractMenuItem(Constants.ADD_HOME_FURNITURE),
        new AbstractMenuItem(Constants.MODIFY_FURNITURE),
        new AbstractMenuItem(Constants.GROUP_FURNITURE),
        new AbstractMenuItem(Constants.UNGROUP_FURNITURE),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.ALIGN_FURNITURE_ON_TOP),
        new AbstractMenuItem(Constants.ALIGN_FURNITURE_ON_BOTTOM),
        new AbstractMenuItem(Constants.ALIGN_FURNITURE_ON_LEFT),
        new AbstractMenuItem(Constants.ALIGN_FURNITURE_ON_RIGHT),
        new AbstractMenuItem(Constants.ALIGN_FURNITURE_ON_FRONT_SIDE),
        new AbstractMenuItem(Constants.ALIGN_FURNITURE_ON_BACK_SIDE),
        new AbstractMenuItem(Constants.ALIGN_FURNITURE_ON_LEFT_SIDE),
        new AbstractMenuItem(Constants.ALIGN_FURNITURE_ON_RIGHT_SIDE),
        new AbstractMenuItem(Constants.ALIGN_FURNITURE_SIDE_BY_SIDE),
        new AbstractMenuItem(Constants.DISTRIBUTE_FURNITURE_HORIZONTALLY),
        new AbstractMenuItem(Constants.DISTRIBUTE_FURNITURE_VERTICALLY),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.IMPORT_FURNITURE),
        new AbstractMenuItem(Constants.IMPORT_FURNITURE_LIBRARY),
        new AbstractMenuItem(Constants.IMPORT_TEXTURE),
        new AbstractMenuItem(Constants.IMPORT_TEXTURES_LIBRARY),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_MENU, new AbstractMenuItem[] {
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_CATALOG_ID),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_NAME),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_WIDTH),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_DEPTH),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_HEIGHT),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_X),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_Y),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_ELEVATION),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_ANGLE),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_LEVEL),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_COLOR),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_TEXTURE),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_MOVABILITY),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_TYPE),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_VISIBILITY),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_PRICE),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_VALUE_ADDED_TAX_PERCENTAGE),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_VALUE_ADDED_TAX),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_PRICE_VALUE_ADDED_TAX_INCLUDED),
          new AbstractMenuItem(Constants.SORT_HOME_FURNITURE_BY_DESCENDING_ORDER),
        }),
        new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_PROPERTY_MENU, new AbstractMenuItem[] {
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_CATALOG_ID),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_NAME),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_WIDTH),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_DEPTH),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_HEIGHT),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_X),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_Y),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_ELEVATION),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_ANGLE),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_COLOR),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_LEVEL),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_TEXTURE),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_MOVABLE),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_DOOR_OR_WINDOW),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_VISIBLE),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_PRICE),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_VALUE_ADDED_TAX_PERCENTAGE),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_VALUE_ADDED_TAX),
          new AbstractMenuItem(Constants.DISPLAY_HOME_FURNITURE_PRICE_VALUE_ADDED_TAX_INCLUDED),
       }),
        new AbstractMenuItem(Constants.EXPORT_TO_CSV),
      }),
      new AbstractMenuItem(Constants.PLAN_MENU, new AbstractMenuItem[] {
        new AbstractMenuItem(Constants.SELECT),
        new AbstractMenuItem(Constants.PAN),
        new AbstractMenuItem(Constants.CREATE_WALLS),
        new AbstractMenuItem(Constants.CREATE_ROOMS),
        new AbstractMenuItem(Constants.CREATE_DIMENSION_LINES),
        new AbstractMenuItem(Constants.CREATE_LABELS),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.LOCK_BASE_PLAN),
        new AbstractMenuItem(Constants.UNLOCK_BASE_PLAN),
        new AbstractMenuItem(Constants.MODIFY_COMPASS),
        new AbstractMenuItem(Constants.MODIFY_WALL),
        new AbstractMenuItem(Constants.REVERSE_WALL_DIRECTION),
        new AbstractMenuItem(Constants.SPLIT_WALL),
        new AbstractMenuItem(Constants.MODIFY_ROOM),
        new AbstractMenuItem(Constants.MODIFY_LABEL),
        new AbstractMenuItem(Constants.MODIFY_TEXT_STYLE, new AbstractMenuItem [] {
          new AbstractMenuItem(Constants.INCREASE_TEXT_SIZE),
          new AbstractMenuItem(Constants.DECREASE_TEXT_SIZE),
          new AbstractMenuItem(),
          new AbstractMenuItem(Constants.TOGGLE_BOLD_STYLE),
          new AbstractMenuItem(Constants.TOGGLE_ITALIC_STYLE),
        }),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.IMPORT_BACKGROUND_IMAGE),
        new AbstractMenuItem(Constants.MODIFY_BACKGROUND_IMAGE),
        new AbstractMenuItem(Constants.DELETE_BACKGROUND_IMAGE),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.ADD_LEVEL),
        new AbstractMenuItem(Constants.MODIFY_LEVEL),
        new AbstractMenuItem(Constants.DELETE_LEVEL),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.ZOOM_OUT),
        new AbstractMenuItem(Constants.ZOOM_IN),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.EXPORT_TO_SVG),
      }),
      new AbstractMenuItem(Constants.VIEW_3D_MENU, new AbstractMenuItem[] {
        new AbstractMenuItem(Constants.VIEW_FROM_TOP),
        new AbstractMenuItem(Constants.VIEW_FROM_OBSERVER),
        new AbstractMenuItem(Constants.MODIFY_OBSERVER),
        new AbstractMenuItem(Constants.STORE_POINT_OF_VIEW),
        new AbstractMenuItem(Constants.GO_TO_POINT_OF_VIEW),
        new AbstractMenuItem(Constants.DELETE_POINTS_OF_VIEW),
        new AbstractMenuItem(Constants.DETACH_3D_VIEW),
        new AbstractMenuItem(Constants.ATTACH_3D_VIEW),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.DISPLAY_ALL_LEVELS),
        new AbstractMenuItem(Constants.DISPLAY_SELECTED_LEVEL),
        new AbstractMenuItem(Constants.MODIFY_3D_ATTRIBUTES),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.CREATE_PHOTO),
        new AbstractMenuItem(Constants.CREATE_PHOTOS_AT_POINTS_OF_VIEW),
        new AbstractMenuItem(Constants.CREATE_VIDEO),
        new AbstractMenuItem(),
        new AbstractMenuItem(Constants.EXPORT_TO_OBJ),
      }),
      new AbstractMenuItem(Constants.HELP_MENU, new AbstractMenuItem[] {
        new AbstractMenuItem(Constants.HELP),
        new AbstractMenuItem(Constants.ABOUT),
      }),
    };
    Menu menuBar = new Menu(shell, SWT.BAR);
    for (AbstractMenuItem menuItem : menuInfo) {
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
