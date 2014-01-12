/*
 * SwingViewFactory.java 28 oct. 2008
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
package com.eteks.sweethome3d.swt;

import java.security.AccessControlException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.tools.DiagnosticCollector;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.widgets.Display;

import com.eteks.sweethome3d.HomeFrameController;
import com.eteks.sweethome3d.model.BackgroundImage;
import com.eteks.sweethome3d.model.Camera;
import com.eteks.sweethome3d.model.CatalogPieceOfFurniture;
import com.eteks.sweethome3d.model.CatalogTexture;
import com.eteks.sweethome3d.model.Content;
import com.eteks.sweethome3d.model.DimensionLine;
import com.eteks.sweethome3d.model.FurnitureCatalog;
import com.eteks.sweethome3d.model.Home;
import com.eteks.sweethome3d.model.HomeApplication;
import com.eteks.sweethome3d.model.RecorderException;
import com.eteks.sweethome3d.model.Selectable;
import com.eteks.sweethome3d.model.TextStyle;
import com.eteks.sweethome3d.model.UserPreferences;
import com.eteks.sweethome3d.viewcontroller.BackgroundImageWizardController;
import com.eteks.sweethome3d.viewcontroller.CompassController;
import com.eteks.sweethome3d.viewcontroller.ContentManager;
import com.eteks.sweethome3d.viewcontroller.DialogView;
import com.eteks.sweethome3d.viewcontroller.FurnitureCatalogController;
import com.eteks.sweethome3d.viewcontroller.FurnitureController;
import com.eteks.sweethome3d.viewcontroller.HelpController;
import com.eteks.sweethome3d.viewcontroller.HelpView;
import com.eteks.sweethome3d.viewcontroller.Home3DAttributesController;
import com.eteks.sweethome3d.viewcontroller.HomeController;
import com.eteks.sweethome3d.viewcontroller.HomeController3D;
import com.eteks.sweethome3d.viewcontroller.HomeFrameView;
import com.eteks.sweethome3d.viewcontroller.HomeFurnitureController;
import com.eteks.sweethome3d.viewcontroller.HomeView;
import com.eteks.sweethome3d.viewcontroller.ImportedFurnitureWizardController;
import com.eteks.sweethome3d.viewcontroller.ImportedFurnitureWizardStepsView;
import com.eteks.sweethome3d.viewcontroller.ImportedTextureWizardController;
import com.eteks.sweethome3d.viewcontroller.LabelController;
import com.eteks.sweethome3d.viewcontroller.LevelController;
import com.eteks.sweethome3d.viewcontroller.ModelMaterialsController;
import com.eteks.sweethome3d.viewcontroller.ObserverCameraController;
import com.eteks.sweethome3d.viewcontroller.PageSetupController;
import com.eteks.sweethome3d.viewcontroller.PhotoController;
import com.eteks.sweethome3d.viewcontroller.PhotosController;
import com.eteks.sweethome3d.viewcontroller.PlanController;
import com.eteks.sweethome3d.viewcontroller.PlanView;
import com.eteks.sweethome3d.viewcontroller.PrintPreviewController;
import com.eteks.sweethome3d.viewcontroller.RoomController;
import com.eteks.sweethome3d.viewcontroller.TextureChoiceController;
import com.eteks.sweethome3d.viewcontroller.TextureChoiceView;
import com.eteks.sweethome3d.viewcontroller.ThreadedTaskController;
import com.eteks.sweethome3d.viewcontroller.ThreadedTaskView;
import com.eteks.sweethome3d.viewcontroller.UserPreferencesController;
import com.eteks.sweethome3d.viewcontroller.VideoController;
import com.eteks.sweethome3d.viewcontroller.View;
import com.eteks.sweethome3d.viewcontroller.ViewFactory;
import com.eteks.sweethome3d.viewcontroller.WallController;
import com.eteks.sweethome3d.viewcontroller.WizardController;
import com.eteks.sweethome3d.viewcontroller.HomeView.SaveAnswer;
import com.eteks.sweethome3d.viewcontroller.PlanController.EditableProperty;
import com.eteks.sweethome3d.viewcontroller.PlanView.CursorType;

/**
 * View factory that instantiates the Swing components of this package.
 * @author Emmanuel Puybaret
 */
public class SWTViewFactory implements ViewFactory {
  /**
   * Returns a new view that displays furniture <code>catalog</code>.
   */
  public View createFurnitureCatalogView(FurnitureCatalog catalog,
                                         UserPreferences preferences,
                                         FurnitureCatalogController furnitureCatalogController) {
    if (preferences == null || preferences.isFurnitureCatalogViewedInTree()) {
      //return new FurnitureCatalogTree(catalog, preferences, furnitureCatalogController);
      return new View() {
      };
    } else {
      //return new FurnitureCatalogListPanel(catalog, preferences, furnitureCatalogController);
      return new View() {
      };
    }
  }
  
  /**
   * Returns a new table that displays <code>home</code> furniture.
   */
  public View createFurnitureView(Home home, UserPreferences preferences,
                                  FurnitureController furnitureController) {
    //return new FurnitureTable(home, preferences, furnitureController);
    return new View() {
    };
  }

  /**
   * Returns a new view that displays <code>home</code> plan.
   */
  public PlanView createPlanView(Home home, UserPreferences preferences,
                                 PlanController planController) {
    //return new MultipleLevelsPlanPanel(home, preferences, planController);
    return new PlanView() {
      
      public void setToolTipFeedback(String toolTipFeedback, float x, float y) {
        // TODO Auto-generated method stub
        
      }
      
      public void setToolTipEditedProperties(EditableProperty [] toolTipEditedProperties, Object [] toolTipPropertyValues,
                                             float x, float y) {
        // TODO Auto-generated method stub
        
      }
      
      public void setScale(float scale) {
        // TODO Auto-generated method stub
        
      }
      
      public void setResizeIndicatorVisible(boolean resizeIndicatorVisible) {
        // TODO Auto-generated method stub
        
      }
      
      public void setRectangleFeedback(float x0, float y0, float x1, float y1) {
        // TODO Auto-generated method stub
        
      }
      
      public void setDraggedItemsFeedback(List<Selectable> draggedItems) {
        // TODO Auto-generated method stub
        
      }
      
      public void setDimensionLinesFeedback(List<DimensionLine> dimensionLines) {
        // TODO Auto-generated method stub
        
      }
      
      public void setCursor(CursorType cursorType) {
        // TODO Auto-generated method stub
        
      }
      
      public void setAngleFeedback(float xCenter, float yCenter, float x1, float y1, float x2, float y2) {
        // TODO Auto-generated method stub
        
      }
      
      public void setAlignmentFeedback(Class<? extends Selectable> alignedObjectClass, Selectable alignedObject, float x,
                                       float y, boolean showPoint) {
        // TODO Auto-generated method stub
        
      }
      
      public void moveView(float dx, float dy) {
        // TODO Auto-generated method stub
        
      }
      
      public void makeSelectionVisible() {
        // TODO Auto-generated method stub
        
      }
      
      public void makePointVisible(float x, float y) {
        // TODO Auto-generated method stub
        
      }
      
      public View getVerticalRuler() {
        // TODO Auto-generated method stub
        return null;
      }
      
      public float [][] getTextBounds(String text, TextStyle style, float x, float y, float angle) {
        // TODO Auto-generated method stub
        return null;
      }
      
      public float getScale() {
        // TODO Auto-generated method stub
        return 0;
      }
      
      public float getPixelLength() {
        // TODO Auto-generated method stub
        return 0;
      }
      
      public View getHorizontalRuler() {
        // TODO Auto-generated method stub
        return null;
      }
      
      public void deleteToolTipFeedback() {
        // TODO Auto-generated method stub
        
      }
      
      public void deleteFeedback() {
        // TODO Auto-generated method stub
        
      }
      
      public float convertYPixelToModel(int y) {
        // TODO Auto-generated method stub
        return 0;
      }
      
      public int convertYModelToScreen(float y) {
        // TODO Auto-generated method stub
        return 0;
      }
      
      public float convertXPixelToModel(int x) {
        // TODO Auto-generated method stub
        return 0;
      }
      
      public int convertXModelToScreen(float x) {
        // TODO Auto-generated method stub
        return 0;
      }
      
      public boolean canImportDraggedItems(List<Selectable> items, int x, int y) {
        // TODO Auto-generated method stub
        return false;
      }
    };
  }

  /**
   * Returns a new view that displays <code>home</code> in 3D.
   */
  public View createView3D(Home home, UserPreferences preferences,
                           HomeController3D homeController3D) {
    try {
      if (!Boolean.getBoolean("com.eteks.sweethome3d.no3D")) {
        //return new HomeComponent3D(home, preferences, homeController3D);
        return new View() {
        };
      }
    } catch (AccessControlException ex) {
      // If com.eteks.sweethome3d.no3D property can't be read, 
      // security manager won't allow to access to Java 3D DLLs required by HomeComponent3D class too
    }
    return null;
  }

  /**
   * Returns a new view that displays <code>home</code> and its sub views.
   */
  public HomeView createHomeView(Home home, UserPreferences preferences,
                                 HomeController homeController) {
    //return new HomePane(home, preferences, homeController);
    return new SwtHomeView(home, preferences, homeController);
  }

  /**
   * Returns a new view that displays a wizard. 
   */
  public DialogView createWizardView(UserPreferences preferences,
                                     WizardController wizardController) {
    //return new WizardPane(preferences, wizardController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }

  /**
   * Returns a new view that displays the different steps that helps user to choose a background image. 
   */
  public View createBackgroundImageWizardStepsView(BackgroundImage backgroundImage,
                      UserPreferences preferences, 
                      BackgroundImageWizardController backgroundImageWizardController) {
    //return new BackgroundImageWizardStepsPanel(backgroundImage, preferences,  
    //    backgroundImageWizardController);
    return new View() {
    };
  }

  /**
   * Returns a new view that displays the different steps that helps user to import furniture. 
   */
  public ImportedFurnitureWizardStepsView createImportedFurnitureWizardStepsView(
                      CatalogPieceOfFurniture piece,
                      String modelName, boolean importHomePiece,
                      UserPreferences preferences, 
                      ImportedFurnitureWizardController importedFurnitureWizardController) {
    //return new ImportedFurnitureWizardStepsPanel(piece, modelName, importHomePiece,
    //    preferences, importedFurnitureWizardController);
    return new ImportedFurnitureWizardStepsView() {
      
      public Content getIcon() {
        // TODO Auto-generated method stub
        return null;
      }
    };
  }

  /**
   * Returns a new view that displays the different steps that helps the user to import a texture. 
   */
  public View createImportedTextureWizardStepsView(
                      CatalogTexture texture, String textureName,
                      UserPreferences preferences,
                      ImportedTextureWizardController importedTextureWizardController) {
    //return new ImportedTextureWizardStepsPanel(texture, textureName, preferences,
    //    importedTextureWizardController);
    return new View() {
    };
  }

  /**
   * Returns a new view that displays message for a threaded task.
   */
  public ThreadedTaskView createThreadedTaskView(String taskMessage,
                                                 UserPreferences preferences,
                                                 ThreadedTaskController threadedTaskController) {
    //return new ThreadedTaskPanel(taskMessage, preferences, threadedTaskController);
    return new ThreadedTaskView() {
      
      public void setTaskRunning(boolean taskRunning, View executingView) {
        // TODO Auto-generated method stub
        
      }
      
      public void invokeLater(Runnable runnable) {
        // TODO Auto-generated method stub
        
      }
    };
  }

  /**
   * Returns a new view that edits user preferences.
   */
  public DialogView createUserPreferencesView(UserPreferences preferences,
                                          UserPreferencesController userPreferencesController) {
    //return new UserPreferencesPanel(preferences, userPreferencesController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }

  /**
   * Returns a new view that edits level values.
   */
  public DialogView createLevelView(UserPreferences preferences, LevelController levelController) {
    //return new LevelPanel(preferences, levelController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }

  /**
   * Returns a new view that edits furniture values.
   */
  public DialogView createHomeFurnitureView(UserPreferences preferences,
                               HomeFurnitureController homeFurnitureController) {
    //return new HomeFurniturePanel(preferences, homeFurnitureController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }

  /**
   * Returns a new view that edits wall values.
   */
  public DialogView createWallView(UserPreferences preferences,
                                 WallController wallController) {
    //return new WallPanel(preferences, wallController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }
  
  /**
   * Returns a new view that edits room values.
   */
  public DialogView createRoomView(UserPreferences preferences,
                                   RoomController roomController) {
    //return new RoomPanel(preferences, roomController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }
  
  /**
   * Returns a new view that edits label values.
   */
  public DialogView createLabelView(boolean modification,
                                    UserPreferences preferences,
                                    LabelController labelController) {
    //return new LabelPanel(modification, preferences, labelController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }
  
  /**
   * Returns a new view that edits compass values.
   */
  public DialogView createCompassView(UserPreferences preferences,
                                    CompassController compassController) {
    //return new CompassPanel(preferences, compassController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }
  
  /**
   * Returns a new view that edits 3D attributes.
   */
  public DialogView createHome3DAttributesView(UserPreferences preferences,
                                  Home3DAttributesController home3DAttributesController) {
    //return new Home3DAttributesPanel(preferences, home3DAttributesController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }
  
  /**
   * Returns a new view that edits observer camera values.
   */
  public DialogView createObserverCameraView(UserPreferences preferences,
                                             ObserverCameraController observerCameraController) {
    //return new ObserverCameraPanel(preferences, observerCameraController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }
  
  /**
   * Returns a new view that edits the texture of the given controller.  
   */
  public TextureChoiceView createTextureChoiceView(UserPreferences preferences,
                                            TextureChoiceController textureChoiceController) {
    //return new TextureChoiceComponent(preferences, textureChoiceController);
    return new TextureChoiceView() {
      
      public boolean confirmDeleteSelectedCatalogTexture() {
        // TODO Auto-generated method stub
        return false;
      }
    };
  }

  /**
   * Returns a new view that edits the materials of its controller.  
   */
  public View createModelMaterialsView(UserPreferences preferences,
                                        ModelMaterialsController controller) {
    //return new ModelMaterialsComponent(preferences, controller);
    return new View() {
    };
  }

  /**
   * Creates a new view that edits page setup.
   */
  public DialogView createPageSetupView(UserPreferences preferences,
                                        PageSetupController pageSetupController) {
    //return new PageSetupPanel(preferences, pageSetupController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }

  /**
   * Returns a new view that displays <code>home</code> print preview. 
   */
  public DialogView createPrintPreviewView(Home home,
                                           UserPreferences preferences,
                                           HomeController homeController,
                                           PrintPreviewController printPreviewController) {
    //return new PrintPreviewPanel(home, preferences, homeController, printPreviewController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }
  
  /**
   * Returns a new view able to compute a photos of a home from its stored points of view. 
   */
  public DialogView createPhotosView(Home home, UserPreferences preferences, 
                                     PhotosController photosController) {
    //return new PhotosPanel(home, preferences, photosController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }
  
  /**
   * Returns a new view able to create photo realistic images of the given home. 
   */
  public DialogView createPhotoView(Home home, 
                                    UserPreferences preferences, 
                                    PhotoController photoController) {
    //return new PhotoPanel(home, preferences, photoController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }
  
  /**
   * Returns a new view able to create 3D videos of the given home. 
   */
  public DialogView createVideoView(Home home, 
                                    UserPreferences preferences, 
                                    VideoController videoController) {
    //return new VideoPanel(home, preferences, videoController);
    return new DialogView() {
      
      public void displayView(View parentView) {
        // TODO Auto-generated method stub
        
      }
    };
  }
  
  /**
   * Returns a new view that displays Sweet Home 3D help.
   */
  public HelpView createHelpView(UserPreferences preferences,
                                 HelpController helpController) {
    return new HelpView() {
    // HelpPane(preferences, helpController);
      public void displayView() {
        // TODO Auto-generated method stub
        
      }
    };
  }

  public HomeFrameView createHomeFrameView(Home home, HomeApplication application, ContentManager contentManager,
                                  HomeFrameController homeFrameController) {
    // TODO Auto-generated method stub
    //return new HomeFramePane(this.home, this.application, this.contentManager, this);
    return new HomeFrameSWT(home, application, contentManager, homeFrameController);
    
  }
}
