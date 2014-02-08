/*
 * HomeView.java 28 oct 2008
 *
 * Sweet Home 3D, Copyright (c) 2008 Emmanuel PUYBARET / eTeks <info@eteks.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.eteks.sweethome3d.viewcontroller;

import java.util.List;
import java.util.concurrent.Callable;

import com.eteks.sweethome3d.model.Camera;
import com.eteks.sweethome3d.model.InterruptedRecorderException;
import com.eteks.sweethome3d.model.RecorderException;

/**
 * The main view that displays a home.
 * @author Emmanuel Puybaret
 */
public interface HomeView extends View {
  class Constants {
    public final static String FILE_MENU                            = "FILE_MENU";
    public final static String NEW_HOME = "NEW_HOME";
    public final static String OPEN = "OPEN";
    public final static String OPEN_RECENT_HOME_MENU                = "OPEN_RECENT_HOME_MENU";
    public final static String DELETE_RECENT_HOMES                = "DELETE_RECENT_HOMES";

    public final static String CLOSE                = "CLOSE";
    public final static String SAVE                = "SAVE";
    public final static String SAVE_AS                = "SAVE_AS";
    public final static String SAVE_AND_COMPRESS                = "SAVE_AND_COMPRESS";

    public final static String PAGE_SETUP                = "PAGE_SETUP";
    public final static String PRINT_PREVIEW                = "PRINT_PREVIEW";
    public final static String PRINT                = "PRINT";
    public final static String PRINT_TO_PDF                = "PRINT_TO_PDF";

    public final static String PREFERENCES                = "PREFERENCES";
    public final static String EXIT                = "EXIT";

    public final static String EDIT_MENU                            = "EDIT_MENU";
    public final static String UNDO                            = "UNDO";
    public final static String REDO                            = "REDO";
    public final static String CUT                            = "CUT";
    public final static String COPY                            = "COPY";
    public final static String PASTE                            = "PASTE";
    public final static String DELETE                            = "DELETE";
    public final static String SELECT_ALL                            = "SELECT_ALL";

    public final static String FURNITURE_MENU                       = "FURNITURE_MENU";
    public final static String ADD_HOME_FURNITURE                            = "ADD_HOME_FURNITURE";
    public final static String DELETE_HOME_FURNITURE                            = "DELETE_HOME_FURNITURE";
    public final static String MODIFY_FURNITURE                            = "MODIFY_FURNITURE";
    public final static String GROUP_FURNITURE = "GROUP_FURNITURE";
    public final static String UNGROUP_FURNITURE = "UNGROUP_FURNITURE";

    public final static String ALIGN_OR_DISTRIBUTE_MENU             = "ALIGN_OR_DISTRIBUTE_MENU";
    public final static String ALIGN_FURNITURE_ON_TOP = "ALIGN_FURNITURE_ON_TOP";
    public final static String ALIGN_FURNITURE_ON_BOTTOM = "ALIGN_FURNITURE_ON_BOTTOM";
    public final static String ALIGN_FURNITURE_ON_LEFT = "ALIGN_FURNITURE_ON_LEFT";
    public final static String ALIGN_FURNITURE_ON_RIGHT = "ALIGN_FURNITURE_ON_RIGHT";
    public final static String ALIGN_FURNITURE_ON_FRONT_SIDE = "ALIGN_FURNITURE_ON_FRONT_SIDE";
    public final static String ALIGN_FURNITURE_ON_BACK_SIDE = "ALIGN_FURNITURE_ON_BACK_SIDE";
    public final static String ALIGN_FURNITURE_ON_LEFT_SIDE = "ALIGN_FURNITURE_ON_LEFT_SIDE";
    public final static String ALIGN_FURNITURE_ON_RIGHT_SIDE = "ALIGN_FURNITURE_ON_RIGHT_SIDE";
    public final static String ALIGN_FURNITURE_SIDE_BY_SIDE = "ALIGN_FURNITURE_SIDE_BY_SIDE";
    public final static String DISTRIBUTE_FURNITURE_HORIZONTALLY = "DISTRIBUTE_FURNITURE_HORIZONTALLY";
    public final static String DISTRIBUTE_FURNITURE_VERTICALLY = "DISTRIBUTE_FURNITURE_VERTICALLY";

    public final static String IMPORT_FURNITURE                            = "IMPORT_FURNITURE";
    public final static String IMPORT_FURNITURE_LIBRARY                            = "IMPORT_FURNITURE_LIBRARY";
    public final static String IMPORT_TEXTURE                            = "IMPORT_TEXTURE";
    public final static String IMPORT_TEXTURES_LIBRARY                            = "IMPORT_TEXTURES_LIBRARY";

    public final static String SORT_HOME_FURNITURE_MENU             = "SORT_HOME_FURNITURE_MENU";
    public final static String SORT_HOME_FURNITURE_BY_CATALOG_ID                            = "SORT_HOME_FURNITURE_BY_CATALOG_ID";
    public final static String SORT_HOME_FURNITURE_BY_NAME                            = "SORT_HOME_FURNITURE_BY_NAME";
    public final static String SORT_HOME_FURNITURE_BY_WIDTH                            = "SORT_HOME_FURNITURE_BY_WIDTH";
    public final static String SORT_HOME_FURNITURE_BY_DEPTH                            = "SORT_HOME_FURNITURE_BY_DEPTH";
    public final static String SORT_HOME_FURNITURE_BY_HEIGHT                            = "SORT_HOME_FURNITURE_BY_HEIGHT";
    public final static String SORT_HOME_FURNITURE_BY_X                            = "SORT_HOME_FURNITURE_BY_X";
    public final static String SORT_HOME_FURNITURE_BY_Y                            = "SORT_HOME_FURNITURE_BY_Y";
    public final static String SORT_HOME_FURNITURE_BY_ELEVATION                            = "SORT_HOME_FURNITURE_BY_ELEVATION";
    public final static String SORT_HOME_FURNITURE_BY_ANGLE                            = "SORT_HOME_FURNITURE_BY_ANGLE";
    public final static String SORT_HOME_FURNITURE_BY_LEVEL                            = "SORT_HOME_FURNITURE_BY_LEVEL";
    public final static String SORT_HOME_FURNITURE_BY_COLOR                            = "SORT_HOME_FURNITURE_BY_COLOR";
    public final static String SORT_HOME_FURNITURE_BY_TEXTURE                            = "SORT_HOME_FURNITURE_BY_TEXTURE";
    public final static String SORT_HOME_FURNITURE_BY_MOVABILITY                            = "SORT_HOME_FURNITURE_BY_MOVABILITY";
    public final static String SORT_HOME_FURNITURE_BY_TYPE                            = "SORT_HOME_FURNITURE_BY_TYPE";
    public final static String SORT_HOME_FURNITURE_BY_VISIBILITY                            = "SORT_HOME_FURNITURE_BY_VISIBILITY";
    public final static String SORT_HOME_FURNITURE_BY_PRICE                            = "SORT_HOME_FURNITURE_BY_PRICE";
    public final static String SORT_HOME_FURNITURE_BY_VALUE_ADDED_TAX_PERCENTAGE                            = "SORT_HOME_FURNITURE_BY_VALUE_ADDED_TAX_PERCENTAGE";
    public final static String SORT_HOME_FURNITURE_BY_VALUE_ADDED_TAX                            = "SORT_HOME_FURNITURE_BY_VALUE_ADDED_TAX";
    public final static String SORT_HOME_FURNITURE_BY_PRICE_VALUE_ADDED_TAX_INCLUDED                            = "SORT_HOME_FURNITURE_BY_PRICE_VALUE_ADDED_TAX_INCLUDED";
    public final static String SORT_HOME_FURNITURE_BY_DESCENDING_ORDER                            = "SORT_HOME_FURNITURE_BY_DESCENDING_ORDER";
    public final static String DISPLAY_HOME_FURNITURE_CATALOG_ID                            = "DISPLAY_HOME_FURNITURE_CATALOG_ID";
    public final static String DISPLAY_HOME_FURNITURE_NAME                            = "DISPLAY_HOME_FURNITURE_NAME";
    public final static String DISPLAY_HOME_FURNITURE_WIDTH                            = "DISPLAY_HOME_FURNITURE_WIDTH";
    public final static String DISPLAY_HOME_FURNITURE_DEPTH                            = "DISPLAY_HOME_FURNITURE_DEPTH";
    public final static String DISPLAY_HOME_FURNITURE_HEIGHT = "DISPLAY_HOME_FURNITURE_HEIGHT";
    public final static String DISPLAY_HOME_FURNITURE_X = "DISPLAY_HOME_FURNITURE_X";
    public final static String DISPLAY_HOME_FURNITURE_Y = "DISPLAY_HOME_FURNITURE_Y";
    public final static String DISPLAY_HOME_FURNITURE_ELEVATION = "DISPLAY_HOME_FURNITURE_ELEVATION";
    public final static String DISPLAY_HOME_FURNITURE_ANGLE = "DISPLAY_HOME_FURNITURE_ANGLE";
    public final static String DISPLAY_HOME_FURNITURE_COLOR = "DISPLAY_HOME_FURNITURE_COLOR";
    public final static String DISPLAY_HOME_FURNITURE_LEVEL = "DISPLAY_HOME_FURNITURE_LEVEL";
    public final static String DISPLAY_HOME_FURNITURE_TEXTURE = "DISPLAY_HOME_FURNITURE_TEXTURE";
    public final static String DISPLAY_HOME_FURNITURE_MOVABLE = "DISPLAY_HOME_FURNITURE_MOVABLE";
    public final static String DISPLAY_HOME_FURNITURE_DOOR_OR_WINDOW = "DISPLAY_HOME_FURNITURE_DOOR_OR_WINDOW";
    public final static String DISPLAY_HOME_FURNITURE_VISIBLE = "DISPLAY_HOME_FURNITURE_VISIBLE";
    public final static String DISPLAY_HOME_FURNITURE_PRICE = "DISPLAY_HOME_FURNITURE_PRICE";
    public final static String DISPLAY_HOME_FURNITURE_VALUE_ADDED_TAX_PERCENTAGE = "DISPLAY_HOME_FURNITURE_VALUE_ADDED_TAX_PERCENTAGE";
    public final static String DISPLAY_HOME_FURNITURE_VALUE_ADDED_TAX = "DISPLAY_HOME_FURNITURE_VALUE_ADDED_TAX";
    public final static String DISPLAY_HOME_FURNITURE_PRICE_VALUE_ADDED_TAX_INCLUDED = "DISPLAY_HOME_FURNITURE_PRICE_VALUE_ADDED_TAX_INCLUDED";
    public final static String EXPORT_TO_CSV = "EXPORT_TO_CSV";
    public final static String SELECT = "SELECT";
    public final static String PAN = "PAN";
    public final static String CREATE_WALLS = "CREATE_WALLS";
    public final static String CREATE_ROOMS = "CREATE_ROOMS";
    public final static String CREATE_DIMENSION_LINES = "CREATE_DIMENSION_LINES";
    public final static String CREATE_LABELS = "CREATE_LABELS";
    public final static String DELETE_SELECTION = "DELETE_SELECTION";
    public final static String LOCK_BASE_PLAN = "LOCK_BASE_PLAN";
    public final static String UNLOCK_BASE_PLAN = "UNLOCK_BASE_PLAN";
    public final static String MODIFY_COMPASS = "MODIFY_COMPASS";
    public final static String MODIFY_WALL = "MODIFY_WALL";
    public final static String REVERSE_WALL_DIRECTION = "REVERSE_WALL_DIRECTION";
    public final static String SPLIT_WALL = "SPLIT_WALL";
    public final static String MODIFY_ROOM = "MODIFY_ROOM";
    public final static String MODIFY_LABEL = "MODIFY_LABEL";
    public final static String INCREASE_TEXT_SIZE = "INCREASE_TEXT_SIZE";
    public final static String DECREASE_TEXT_SIZE = "DECREASE_TEXT_SIZE";
    public final static String TOGGLE_BOLD_STYLE = "TOGGLE_BOLD_STYLE";
    public final static String TOGGLE_ITALIC_STYLE = "TOGGLE_ITALIC_STYLE";
    public final static String IMPORT_BACKGROUND_IMAGE = "IMPORT_BACKGROUND_IMAGE";
    public final static String MODIFY_BACKGROUND_IMAGE = "MODIFY_BACKGROUND_IMAGE";
    public final static String HIDE_BACKGROUND_IMAGE = "HIDE_BACKGROUND_IMAGE";
    public final static String SHOW_BACKGROUND_IMAGE = "SHOW_BACKGROUND_IMAGE";
    public final static String DELETE_BACKGROUND_IMAGE = "DELETE_BACKGROUND_IMAGE";
    public final static String ADD_LEVEL = "ADD_LEVEL";
    public final static String DELETE_LEVEL = "DELETE_LEVEL";
    public final static String MODIFY_LEVEL = "MODIFY_LEVEL";
    public final static String ZOOM_OUT = "ZOOM_OUT";
    public final static String ZOOM_IN = "ZOOM_IN";
    public final static String EXPORT_TO_SVG = "EXPORT_TO_SVG";
    public final static String VIEW_FROM_TOP = "VIEW_FROM_TOP";
    public final static String VIEW_FROM_OBSERVER = "VIEW_FROM_OBSERVER";
    public final static String MODIFY_OBSERVER = "MODIFY_OBSERVER";
    public final static String STORE_POINT_OF_VIEW = "STORE_POINT_OF_VIEW";
    public final static String DELETE_POINTS_OF_VIEW = "DELETE_POINTS_OF_VIEW";
    public final static String CREATE_PHOTOS_AT_POINTS_OF_VIEW = "CREATE_PHOTOS_AT_POINTS_OF_VIEW";
    public final static String DETACH_3D_VIEW = "DETACH_3D_VIEW";
    public final static String ATTACH_3D_VIEW = "ATTACH_3D_VIEW";
    public final static String DISPLAY_ALL_LEVELS = "DISPLAY_ALL_LEVELS";
    public final static String DISPLAY_SELECTED_LEVEL = "DISPLAY_SELECTED_LEVEL";
    public final static String MODIFY_3D_ATTRIBUTES = "MODIFY_3D_ATTRIBUTES";
    public final static String CREATE_PHOTO = "CREATE_PHOTO";
    public final static String CREATE_VIDEO = "CREATE_VIDEO";
    public final static String EXPORT_TO_OBJ = "EXPORT_TO_OBJ";
    public final static String HELP = "HELP";
    public final static String ABOUT = "ABOUT";

    public final static String PLAN_MENU                            = "PLAN_MENU";
    public final static String VIEW_3D_MENU                         = "VIEW_3D_MENU";
    public final static String HELP_MENU                            = "HELP_MENU";
    public final static String DISPLAY_HOME_FURNITURE_PROPERTY_MENU = "DISPLAY_HOME_FURNITURE_PROPERTY_MENU";
    public final static String MODIFY_TEXT_STYLE                    = "MODIFY_TEXT_STYLE";
    public final static String GO_TO_POINT_OF_VIEW                  = "GO_TO_POINT_OF_VIEW";
    public final static String SELECT_OBJECT_MENU                   = "SELECT_OBJECT_MENU";
  };

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

  AbstractMenuItem[] mMenuInfo = {
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

  /**
   * The actions proposed by the view to user.
   */
  public enum ActionType {
      NEW_HOME, CLOSE, OPEN, DELETE_RECENT_HOMES, SAVE, SAVE_AS, SAVE_AND_COMPRESS,
      PAGE_SETUP, PRINT_PREVIEW, PRINT, PRINT_TO_PDF, PREFERENCES, EXIT, 
      UNDO, REDO, CUT, COPY, PASTE, DELETE, SELECT_ALL,
      ADD_HOME_FURNITURE, DELETE_HOME_FURNITURE, MODIFY_FURNITURE, 
      IMPORT_FURNITURE, IMPORT_FURNITURE_LIBRARY, IMPORT_TEXTURE, IMPORT_TEXTURES_LIBRARY,
      SORT_HOME_FURNITURE_BY_CATALOG_ID, SORT_HOME_FURNITURE_BY_NAME, 
      SORT_HOME_FURNITURE_BY_WIDTH, SORT_HOME_FURNITURE_BY_DEPTH, SORT_HOME_FURNITURE_BY_HEIGHT, 
      SORT_HOME_FURNITURE_BY_X, SORT_HOME_FURNITURE_BY_Y, SORT_HOME_FURNITURE_BY_ELEVATION, 
      SORT_HOME_FURNITURE_BY_ANGLE, SORT_HOME_FURNITURE_BY_LEVEL, SORT_HOME_FURNITURE_BY_COLOR, SORT_HOME_FURNITURE_BY_TEXTURE, 
      SORT_HOME_FURNITURE_BY_MOVABILITY, SORT_HOME_FURNITURE_BY_TYPE, SORT_HOME_FURNITURE_BY_VISIBILITY, 
      SORT_HOME_FURNITURE_BY_PRICE, SORT_HOME_FURNITURE_BY_VALUE_ADDED_TAX_PERCENTAGE, 
      SORT_HOME_FURNITURE_BY_VALUE_ADDED_TAX, SORT_HOME_FURNITURE_BY_PRICE_VALUE_ADDED_TAX_INCLUDED,
      SORT_HOME_FURNITURE_BY_DESCENDING_ORDER,
      DISPLAY_HOME_FURNITURE_CATALOG_ID, DISPLAY_HOME_FURNITURE_NAME, 
      DISPLAY_HOME_FURNITURE_WIDTH, DISPLAY_HOME_FURNITURE_DEPTH, DISPLAY_HOME_FURNITURE_HEIGHT, 
      DISPLAY_HOME_FURNITURE_X, DISPLAY_HOME_FURNITURE_Y, DISPLAY_HOME_FURNITURE_ELEVATION, 
      DISPLAY_HOME_FURNITURE_ANGLE, DISPLAY_HOME_FURNITURE_COLOR, DISPLAY_HOME_FURNITURE_LEVEL, DISPLAY_HOME_FURNITURE_TEXTURE, 
      DISPLAY_HOME_FURNITURE_MOVABLE, DISPLAY_HOME_FURNITURE_DOOR_OR_WINDOW, DISPLAY_HOME_FURNITURE_VISIBLE,
      DISPLAY_HOME_FURNITURE_PRICE, DISPLAY_HOME_FURNITURE_VALUE_ADDED_TAX_PERCENTAGE,
      DISPLAY_HOME_FURNITURE_VALUE_ADDED_TAX, DISPLAY_HOME_FURNITURE_PRICE_VALUE_ADDED_TAX_INCLUDED,
      ALIGN_FURNITURE_ON_TOP, ALIGN_FURNITURE_ON_BOTTOM, ALIGN_FURNITURE_ON_LEFT, ALIGN_FURNITURE_ON_RIGHT, 
      ALIGN_FURNITURE_ON_FRONT_SIDE, ALIGN_FURNITURE_ON_BACK_SIDE, ALIGN_FURNITURE_ON_LEFT_SIDE, ALIGN_FURNITURE_ON_RIGHT_SIDE, ALIGN_FURNITURE_SIDE_BY_SIDE,
      DISTRIBUTE_FURNITURE_HORIZONTALLY, DISTRIBUTE_FURNITURE_VERTICALLY,
      GROUP_FURNITURE, UNGROUP_FURNITURE, EXPORT_TO_CSV, 
      SELECT, PAN, CREATE_WALLS, CREATE_ROOMS, CREATE_DIMENSION_LINES, CREATE_LABELS, DELETE_SELECTION,
      LOCK_BASE_PLAN, UNLOCK_BASE_PLAN, MODIFY_COMPASS, MODIFY_WALL, REVERSE_WALL_DIRECTION, SPLIT_WALL, MODIFY_ROOM, MODIFY_LABEL,
      INCREASE_TEXT_SIZE, DECREASE_TEXT_SIZE, TOGGLE_BOLD_STYLE, TOGGLE_ITALIC_STYLE,
      IMPORT_BACKGROUND_IMAGE, MODIFY_BACKGROUND_IMAGE, HIDE_BACKGROUND_IMAGE, SHOW_BACKGROUND_IMAGE, DELETE_BACKGROUND_IMAGE, 
      ADD_LEVEL, DELETE_LEVEL, MODIFY_LEVEL,
      ZOOM_OUT, ZOOM_IN, EXPORT_TO_SVG,
      VIEW_FROM_TOP, VIEW_FROM_OBSERVER, MODIFY_OBSERVER, STORE_POINT_OF_VIEW, DELETE_POINTS_OF_VIEW, CREATE_PHOTOS_AT_POINTS_OF_VIEW, DETACH_3D_VIEW, ATTACH_3D_VIEW,  
      DISPLAY_ALL_LEVELS, DISPLAY_SELECTED_LEVEL, MODIFY_3D_ATTRIBUTES, CREATE_PHOTO, CREATE_VIDEO, EXPORT_TO_OBJ,
      HELP, ABOUT}
  public enum SaveAnswer {SAVE, CANCEL, DO_NOT_SAVE}

  /**
   * Enables or disables the action matching <code>actionType</code>.
   */
  public abstract void setEnabled(ActionType actionType,
                                  boolean enabled);

  /**
   * Sets the name and tool tip of undo and redo actions. If a parameter is <code>null</code>,
   * the properties will be reset to their initial values.
   */
  public abstract void setUndoRedoName(String undoText,
                                       String redoText);

  /**
   * Enables or disables transfer between components.  
   */
  public abstract void setTransferEnabled(boolean enabled);


  /**
   * Detaches the given <code>view</code> from home view.
   */
  public abstract void detachView(View view);
                
  /**
   * Attaches the given <code>view</code> to home view.
   */
  public abstract void attachView(View view);
  
  /**
   * Displays a content chooser open dialog to choose the name of a home.
   */
  public abstract String showOpenDialog();

  /**
   * Displays a content chooser open dialog to choose a language library.
   */
  public abstract String showImportLanguageLibraryDialog();

  /**
   * Displays a dialog that lets user choose whether he wants to overwrite
   * an existing language library or not. 
   */
  public abstract boolean confirmReplaceLanguageLibrary(String languageLibraryName);

  /**
   * Displays a content chooser open dialog to choose a furniture library.
   */
  public abstract String showImportFurnitureLibraryDialog();

  /**
   * Displays a dialog that lets user choose whether he wants to overwrite
   * an existing furniture library or not. 
   */
  public abstract boolean confirmReplaceFurnitureLibrary(String furnitureLibraryName);

  /**
   * Displays a content chooser open dialog to choose a textures library.
   */
  public abstract String showImportTexturesLibraryDialog();

  /**
   * Displays a dialog that lets user choose whether he wants to overwrite
   * an existing textures library or not. 
   */
  public abstract boolean confirmReplaceTexturesLibrary(String texturesLibraryName);

  /**
   * Displays a dialog that lets user choose whether he wants to overwrite
   * an existing plug-in or not. 
   */
  public abstract boolean confirmReplacePlugin(String pluginName);

  /**
   * Displays a content chooser save dialog to choose the name of a home.
   */
  public abstract String showSaveDialog(String homeName);

  /**
   * Displays a dialog that lets user choose whether he wants to save
   * the current home or not.
   * @return {@link SaveAnswer#SAVE} if user chose to save home,
   * {@link SaveAnswer#DO_NOT_SAVE} if user don't want to save home,
   * or {@link SaveAnswer#CANCEL} if doesn't want to continue current operation.
   */
  public abstract SaveAnswer confirmSave(String homeName);

  /**
   * Displays a dialog that let user choose whether he wants to save
   * a home that was created with a newer version of Sweet Home 3D.
   * @return <code>true</code> if user confirmed to save.
   */
  public abstract boolean confirmSaveNewerHome(String homeName);

  /**
   * Displays a dialog that let user choose whether he wants to delete 
   * the selected furniture from catalog or not.
   * @return <code>true</code> if user confirmed to delete.
   */
  public abstract boolean confirmDeleteCatalogSelection();
  
  /**
   * Displays a dialog that let user choose whether he wants to exit 
   * application or not.
   * @return <code>true</code> if user confirmed to exit.
   */
  public abstract boolean confirmExit();
  
  /**
   * Displays <code>message</code> in an error message box.
   */
  public abstract void showError(String message);

  /**
   * Displays <code>message</code> in a message box.
   */
  public abstract void showMessage(String message);

  /**
   * Displays the tip matching <code>actionTipKey</code> and 
   * returns <code>true</code> if the user chose not to display again the tip.
   */
  public abstract boolean showActionTipMessage(String actionTipKey);
  
  /**
   * Displays an about dialog.
   */
  public abstract void showAboutDialog();

  /**
   * Shows a print dialog to print the home displayed by this pane.  
   * @return a print task to execute or <code>null</code> if the user canceled print.
   *    The <code>call<code> method of the returned task may throw a 
   *    {@link RecorderException RecorderException} exception if print failed 
   *    or an {@link InterruptedRecorderException InterruptedRecorderException}
   *    exception if it was interrupted.
   */
  public abstract Callable<Void> showPrintDialog();

  /**
   * Shows a content chooser save dialog to print a home in a PDF file.
   */
  public abstract String showPrintToPDFDialog(String homeName);

  /**
   * Prints a home to a given PDF file. This method may be overridden
   * to write to another kind of output stream.
   * Caution !!! This method may be called from a threaded task.  
   */
  public abstract void printToPDF(String pdfFile) throws RecorderException;

  /**
   * Shows a content chooser save dialog to export furniture list in a CSV file.
   */
  public abstract String showExportToCSVDialog(String name);

  /**
   * Exports furniture list to a given SVG file.
   * Caution !!! This method may be called from a threaded task.  
   */
  public abstract void exportToCSV(String csvName) throws RecorderException;

  /**
   * Shows a content chooser save dialog to export a home plan in a SVG file.
   */
  public abstract String showExportToSVGDialog(String name);

  /**
   * Exports the plan objects to a given SVG file.
   * Caution !!! This method may be called from a threaded task.  
   */
  public abstract void exportToSVG(String svgName) throws RecorderException;

  /**
   * Shows a content chooser save dialog to export a 3D home in a OBJ file.
   */
  public abstract String showExportToOBJDialog(String homeName);
  
  /**
   * Exports the 3D home objects to a given OBJ file.
   * Caution !!! This method may be called from a threaded task.  
   */
  public abstract void exportToOBJ(String objFile) throws RecorderException;

  /**
   * Displays a dialog that lets the user choose a name for the current camera.
   */
  public abstract String showStoreCameraDialog(String cameraName);

  /**
   * Displays a dialog showing the list of cameras stored in home 
   * and returns the ones selected by the user to be deleted.  
   */
  public abstract List<Camera> showDeletedCamerasDialog();
  
  /**
   * Returns <code>true</code> if clipboard contains data that
   * components are able to handle.
   */
  public abstract boolean isClipboardEmpty();

  /**
   * Displays the given message and returns <code>false</code> if the user 
   * doesn't want to be informed of the displayed updates and <code>showOnlyMessage</code> is <code>false</code>. 
   */
  public abstract boolean showUpdatesMessage(String updatesMessage, boolean showOnlyMessage);
  
  /**
   * Execute <code>runnable</code> asynchronously in the thread 
   * that manages toolkit events.  
   */
  public abstract void invokeLater(Runnable runnable);

  /**
   * Returns underlying GUI component
   * @return
   */
  public Object getObject();
}