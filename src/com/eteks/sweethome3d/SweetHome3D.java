/*
 * SweetHome3D.java 1 sept. 2006
 * 
 * Sweet Home 3D, Copyright (c) 2006 Emmanuel PUYBARET / eTeks <info@eteks.com>
 *  
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 */
package com.eteks.sweethome3d;

import java.awt.EventQueue;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.jnlp.BasicService;
import javax.jnlp.ServiceManager;
import javax.jnlp.SingleInstanceListener;
import javax.jnlp.SingleInstanceService;
import javax.jnlp.UnavailableServiceException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;


import com.eteks.sweethome3d.io.AutoRecoveryManager;
import com.eteks.sweethome3d.io.FileUserPreferences;
import com.eteks.sweethome3d.io.HomeFileRecorder;
import com.eteks.sweethome3d.j3d.Component3DManager;
import com.eteks.sweethome3d.model.CollectionEvent;
import com.eteks.sweethome3d.model.CollectionListener;
import com.eteks.sweethome3d.model.Home;
import com.eteks.sweethome3d.model.HomeApplication;
import com.eteks.sweethome3d.model.HomeRecorder;
import com.eteks.sweethome3d.model.Library;
import com.eteks.sweethome3d.model.RecorderException;
import com.eteks.sweethome3d.model.UserPreferences;
import com.eteks.sweethome3d.plugin.HomePluginController;
import com.eteks.sweethome3d.plugin.PluginManager;
import com.eteks.sweethome3d.swing.FileContentManager;
import com.eteks.sweethome3d.swing.SwingTools;
import com.eteks.sweethome3d.swing.SwingViewFactory;
import com.eteks.sweethome3d.tools.OperatingSystem;
import com.eteks.sweethome3d.viewcontroller.ContentManager;
import com.eteks.sweethome3d.viewcontroller.HomeController;
import com.eteks.sweethome3d.viewcontroller.HomeFrameView;
import com.eteks.sweethome3d.viewcontroller.View;
import com.eteks.sweethome3d.viewcontroller.ViewFactory;

/**
 * Sweet Home 3D main class. Sweet Home 3D accepts the parameter
 * <code>-open</code> followed by the path of a Sweet Home 3D file.<br> 
 * Users may also define the optional following System properties to alter program behavior:
 * 
 * <ul><li><code>com.eteks.sweethome3d.applicationFolders</code> defines the folder(s) where private files 
 * of Sweet Home 3D are stored. Private files include SH3F furniture library files stored in a subfolder named
 * <code>furniture</code>, SH3T textures library files stored in a subfolder named <code>textures</code>, 
 * SH3L language library files stored in a subfolder named <code>languages</code>, SH3P application plug-in 
 * files stored in a subfolder named <code>plugins</code> and SH3D files automatically created for recovery 
 * purpose stored in a subfolder named <code>recovery</code>.<br>
 * If this property describes more than one folder, they should be separated by a semicolon (;) under Windows
 * or by a colon (:) under Mac OS X and Unix systems. The first folder listed in this property is used
 * as the folder where will be stored recovered SH3D files and SH3F, SH3T, SH3L, SH3P files imported by the user.
 * Thus the user should have write access rights on this first folder otherwise he won't be able to import 
 * SH3F, SH3T, SH3L or SH3P files, and auto recovered SH3D files won't be managed. If this folder or
 * one of its <code>furniture</code>, <code>textures</code>, <code>languages</code>, <code>plugins</code>,
 * <code>recovery</code> subfolders don't exist, Sweet Home 3D will create it when needed.<br>
 * The other folders are used as resources where SH3F, SH3T, SH3L, SH3P files will be searched 
 * in their respective <code>furniture</code>, <code>textures</code>, <code>languages</code>, <code>plugins</code> 
 * subfolders. Any of the cited folders may be an absolute path or a relative path to the folder
 * from which the program was launched.</li>
 *       
 * <li><code>com.eteks.sweethome3d.preferencesFolder</code> defines the folder where preferences
 * files (<code>preferences.xml</code> and the files depending on it) are stored. The user should have
 * write access rights on this folder otherwise the program won't be able to save his preferences
 * and the files he imported in furniture and textures catalogs. This folder may be the same as the
 * folder cited in <code>com.eteks.sweethome3d.applicationFolders</code> property.</li>
 * 
 * <li><code>com.eteks.sweethome3d.no3D</code> should be set to <code>true</code> 
 * if 3D capabilities (including 3D view and importing furniture 3D models) shouldn't be used in Sweet Home 3D. 
 * 
 * <li><code>com.eteks.sweethome3d.j3d.checkOffScreenSupport</code> should be set to <code>false</code> 
 * when editing preferences, printing, creating a photo or creating a video always lead to a crash of Sweet Home 3D. 
 * This means offscreen 3D images isn't supported by your video driver and Sweet Home 3D doesn't even succeed
 * to test this support. Setting this System property to <code>false</code> disables this test.</li>
 * 
 * <li><code>com.eteks.sweethome3d.j3d.additionalLoaderClasses</code> defines additional Java 3D 
 * {@linkplain com.sun.j3d.loaders.Loader loader} classes that Sweet Home 3D will use to read 3D models content
 * at formats not supported by default in Sweet Home 3D.<br>
 * The classes cited in this property must be available in the classpath and if more than one class is
 * cited, they should be separated by a colon or a space.</li></ul>
 * 
 * <p>The value of a System property can be set with the -D 
 * <a href="http://download.oracle.com/javase/6/docs/technotes/tools/windows/java.html">java</a> option.
 * @author Emmanuel Puybaret
 */
public class SweetHome3D extends HomeApplication {
  private static final String     PREFERENCES_FOLDER             = "com.eteks.sweethome3d.preferencesFolder";
  private static final String     APPLICATION_FOLDERS            = "com.eteks.sweethome3d.applicationFolders";
  private static final String     APPLICATION_PLUGINS_SUB_FOLDER = "plugins";

  private HomeRecorder            homeRecorder;
  private HomeRecorder            compressedHomeRecorder;
  private UserPreferences         userPreferences;
  private ContentManager          contentManager;
  private ViewFactory             viewFactory;
  private PluginManager           pluginManager;
  private boolean                 pluginManagerInitialized;
  private boolean                 checkUpdatesNeeded;
  private AutoRecoveryManager     autoRecoveryManager;
  private final Map<Home, JFrame> homeFrames;

  /**
   * Creates a home application instance. Recorders, user preferences, content
   * manager, view factory and plug-in manager handled by this application are
   * lazily instantiated to let subclasses override their creation.
   */
  protected SweetHome3D() {
    this.homeFrames = new HashMap<Home, JFrame>();
  }

  /**
   * Returns a recorder able to write and read homes in files.
   */
  @Override
  public HomeRecorder getHomeRecorder() {
    // Initialize homeRecorder lazily
    if (this.homeRecorder == null) {
      this.homeRecorder = new HomeFileRecorder();
    }
    return this.homeRecorder;
  }

  @Override
  public HomeRecorder getHomeRecorder(HomeRecorder.Type type) {
    if (type == HomeRecorder.Type.COMPRESSED) {
      // Initialize compressedHomeRecorder lazily
      if (this.compressedHomeRecorder == null) {
        this.compressedHomeRecorder = new HomeFileRecorder(9);
      }
      return this.compressedHomeRecorder;
    } else {
      return super.getHomeRecorder(type);
    }
  }

  /**
   * Returns user preferences stored in resources and local file system.
   */
  @Override
  public UserPreferences getUserPreferences() {
    // Initialize userPreferences lazily
    if (this.userPreferences == null) {
      // Retrieve preferences and application folders
      String preferencesFolderProperty = System.getProperty(PREFERENCES_FOLDER, null);
      String applicationFoldersProperty = System.getProperty(APPLICATION_FOLDERS, null);
      File preferencesFolder = preferencesFolderProperty != null
          ? new File(preferencesFolderProperty)
          : null;
      File [] applicationFolders;
      if (applicationFoldersProperty != null) {
        String [] applicationFoldersProperties = applicationFoldersProperty.split(File.pathSeparator);
        applicationFolders = new File [applicationFoldersProperties.length];
        for (int i = 0; i < applicationFolders.length; i++) {
          applicationFolders [i] = new File(applicationFoldersProperties [i]);
        }
      } else {
        applicationFolders = null;
      }
      Executor eventQueueExecutor = new Executor() {
          public void execute(Runnable command) {
            EventQueue.invokeLater(command);
          }
        };
      this.userPreferences = new FileUserPreferences(preferencesFolder, applicationFolders, eventQueueExecutor) {
          @Override
          public List<Library> getLibraries() {
            if (pluginManager != null) {
              List<Library> pluginLibraries = pluginManager.getPluginLibraries();
              if (!pluginLibraries.isEmpty()) {
                // Add plug-ins to the list returned by user preferences
                ArrayList<Library> libraries = new ArrayList<Library>(super.getLibraries());
                libraries.addAll(pluginLibraries);
                return Collections.unmodifiableList(libraries);
              }
            }
            return super.getLibraries();
          }
          
          @Override
          public void deleteLibraries(List<Library> libraries) throws RecorderException {
            super.deleteLibraries(libraries);
            List<Library> plugins = new ArrayList<Library>();
            for (Library library : libraries) {
              if (PluginManager.PLUGIN_LIBRARY_TYPE.equals(library.getType())) {
                plugins.add(library);
              }
            }
            pluginManager.deletePlugins(plugins);            
          }
        };
      this.checkUpdatesNeeded = this.userPreferences.isCheckUpdatesEnabled();
    }
    return this.userPreferences;
  }

  /**
   * Returns a content manager able to handle files.
   */
  public ContentManager getContentManager() {
    if (this.contentManager == null) {
      this.contentManager = new FileContentManagerWithRecordedLastDirectories(getUserPreferences(), getClass());
    }
    return this.contentManager;
  }

  /**
   * Returns a Swing view factory.
   */
  public ViewFactory getViewFactory() {
    if (this.viewFactory == null) {
      this.viewFactory = new SwingViewFactory();
    }
    return this.viewFactory;
  }

  /**
   * Returns the plugin manager of this application.
   */
  public PluginManager getPluginManager() {
    if (!this.pluginManagerInitialized) {
      try {
        UserPreferences userPreferences = getUserPreferences();
        if (userPreferences instanceof FileUserPreferences) {
          File [] applicationPluginsFolders = ((FileUserPreferences) userPreferences)
              .getApplicationSubfolders(APPLICATION_PLUGINS_SUB_FOLDER);
          // Create the plug-in manager that will search plug-in files in plugins folders
          this.pluginManager = new PluginManager(applicationPluginsFolders);
        }
      } catch (IOException ex) {
      }
      this.pluginManagerInitialized = true;
    }
    return this.pluginManager;
  }

  /**
   * Returns Sweet Home 3D application read from resources.
   */
  @Override
  public String getId() {
    String applicationId = System.getProperty("com.eteks.sweethome3d.applicationId");
    if (applicationId != null && applicationId.length() > 0) {
      return applicationId;
    } else {
      try {
        return getUserPreferences().getLocalizedString(SweetHome3D.class, "applicationId");
      } catch (IllegalArgumentException ex) {
        return super.getId();
      }
    }
  }
  
  /**
   * Returns the name of this application read from resources.
   */
  @Override
  public String getName() {
    return getUserPreferences().getLocalizedString(SweetHome3D.class, "applicationName");
  }

  /**
   * Returns information about the version of this application.
   */
  public String getVersion() {
    String applicationVersion = System.getProperty("com.eteks.sweethome3d.applicationVersion");
    if (applicationVersion != null) {
      return applicationVersion;
    } else {
      return getUserPreferences().getLocalizedString(SweetHome3D.class, "applicationVersion");
    }
  }

  /**
   * Returns the frame that displays a given <code>home</code>.
   */
  public JFrame getHomeFrame(Home home) {
    return this.homeFrames.get(home);
  }

  /**
   * Shows and brings to front <code>home</code> frame.
   */
  protected void showHomeFrame(Home home) {
    final JFrame homeFrame = (JFrame)getHomeFrame(home);
    homeFrame.setVisible(true);
    homeFrame.setState(JFrame.NORMAL);
    homeFrame.toFront();
  }

  /**
   * Sweet Home 3D entry point.
   * @param args may contain one .sh3d, .sh3f or .sh3p file to open or install,
   *          following a <code>-open</code> option.
   */
  public static void main(final String [] args) {
    new SweetHome3D().init(args);
  }

  /**
   * Inits application instance.
   */
  protected void init(final String [] args) {
    initSystemProperties();

    // If Sweet Home 3D is launched from outside of Java Web Start
    if (ServiceManager.getServiceNames() == null) {
      // Try to call single instance server
      if (StandaloneSingleInstanceService.callSingleInstanceServer(args, getClass())) {
        // If single instance server was successfully called, exit application
        System.exit(0);
      } else {
        // Display splash screen
        SwingTools.showSplashScreenWindow(SweetHome3D.class.getResource("resources/splashScreen.jpg"));
        // Create JNLP services required by Sweet Home 3D
        ServiceManager.setServiceManagerStub(new StandaloneServiceManager(getClass(), StandaloneBasicServiceAWT.class));
      }
    }

    SingleInstanceService service = null;
    final SingleInstanceListener singleInstanceListener = new SingleInstanceListener() {
      public void newActivation(final String [] args) {
        // Call run with the arguments it should have received
        EventQueue.invokeLater(new Runnable() {
          public void run() {
            SweetHome3D.this.start(args);
          }
        });
      }
    };
    try {
      // Retrieve Java Web Start SingleInstanceService
      service = (SingleInstanceService) ServiceManager.lookup("javax.jnlp.SingleInstanceService");
      service.addSingleInstanceListener(singleInstanceListener);
    } catch (UnavailableServiceException ex) {
      // Just ignore SingleInstanceService if it's not available
      // to let application work outside of Java Web Start
    }

    // Make a final copy of service
    final SingleInstanceService singleInstanceService = service;

    // Add a listener that opens a frame when a home is added to application
    addHomesListener(new CollectionListener<Home>() {
      private boolean firstApplicationHomeAdded;

      public void collectionChanged(CollectionEvent<Home> ev) {
        switch (ev.getType()) {
          case ADD:
            Home home = ev.getItem();
            try {
              HomeFrameController controller = createHomeFrameController(home);
              controller.displayView();
              if (!this.firstApplicationHomeAdded) {
                this.firstApplicationHomeAdded = true;
                addNewHomeCloseListener(home, controller.getHomeController());
              }

              JFrame homeFrame = (JFrame)((HomeFrameView)controller.getView()).getObject();
              homeFrames.put(home, homeFrame);
            } catch (IllegalStateException ex) {
              // Check exception by class name to avoid a mandatory bind to Java 3D
              if ("javax.media.j3d.IllegalRenderingStateException".equals(ex.getClass().getName())) {
                ex.printStackTrace();
                // In case of a problem in Java 3D, simply exit with a message.
                exitAfter3DError();
              } else {
                throw ex;
              }
            }
            break;
          case DELETE:
            homeFrames.remove(ev.getItem());

            // If application has no more home
            if (getHomes().isEmpty() && !OperatingSystem.isMacOSX()) {
              // If SingleInstanceService is available, remove the listener that was added on it
              if (singleInstanceService != null) {
                singleInstanceService.removeSingleInstanceListener(singleInstanceListener);
              }
              // Exit once current events are managed (under Mac OS X, exit is managed by MacOSXConfiguration)
              EventQueue.invokeLater(new Runnable() {
                  public void run() {
                    System.exit(0);
                  }
                });
            }
            break;
        }
      };
    });

    addComponent3DRenderingErrorObserver();

    getUserPreferences();
    try {
      // Set User Agent to follow statistics on used operating systems 
      System.setProperty("http.agent", getId() + "/" + getVersion()  
           + " (" + System.getProperty("os.name") + " " + System.getProperty("os.version") + "; " + System.getProperty("os.arch") + "; " + Locale.getDefault() + ")");
    } catch (AccessControlException ex) {
      // Ignore User Agent change
    }
    // Init look and feel afterwards to ensure that Swing takes into account
    // default locale change
    initLookAndFeel();
    try {
      this.autoRecoveryManager = new AutoRecoveryManager(this);
    } catch (RecorderException ex) {
      // Too bad we can't retrieve homes to recover
      ex.printStackTrace();
    }
    if (OperatingSystem.isMacOSX()) {
      // Bind to application menu at last
      MacOSXConfiguration.bindToApplicationMenu(this);
    }

    // Run everything else in Event Dispatch Thread
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        SweetHome3D.this.start(args);
      }
    });
  }

  /**
   * Sets various <code>System</code> properties.
   */
  private void initSystemProperties() {
    if (OperatingSystem.isMacOSX()) {
      // Change Mac OS X application menu name
      String classPackage = SweetHome3D.class.getName();
      classPackage = classPackage.substring(0, classPackage.lastIndexOf("."));
      ResourceBundle resource = ResourceBundle.getBundle(classPackage + "." + "package");
      String applicationName = resource.getString("SweetHome3D.applicationName");
      System.setProperty("com.apple.mrj.application.apple.menu.about.name", applicationName);
      System.setProperty("apple.awt.application.name", applicationName);
      // Use Mac OS X screen menu bar for frames menu bar
      System.setProperty("apple.laf.useScreenMenuBar", "true");
      // Force the use of Quartz under Mac OS X for better Java 2D rendering performance
      System.setProperty("apple.awt.graphics.UseQuartz", "true");
      // Request to use system proxies to access to the Internet
      if (System.getProperty("java.net.useSystemProxies") == null) {
        System.setProperty("java.net.useSystemProxies", "true");
      }
    }
  }

  /**
   * Sets application look and feel.
   */
  private void initLookAndFeel() {
    try {
      // Apply current system look and feel if swing.defaultlaf isn't defined
      UIManager.setLookAndFeel(System.getProperty("swing.defaultlaf", UIManager.getSystemLookAndFeelClassName()));
      // Change default titled borders under Mac OS X 10.5
      if (OperatingSystem.isMacOSXLeopardOrSuperior()) {
        UIManager.put("TitledBorder.border", UIManager.getBorder("TitledBorder.aquaVariant"));
      }
      SwingTools.updateSwingResourceLanguage(getUserPreferences());
    } catch (Exception ex) {
      // Too bad keep current look and feel
    }
  }

  /**
   * Adds a listener to new home to close it if an other one is opened.
   */
  private void addNewHomeCloseListener(final Home home, final HomeController controller) {
    if (home.getName() == null) {
      final CollectionListener<Home> newHomeListener = new CollectionListener<Home>() {
        public void collectionChanged(CollectionEvent<Home> ev) {
          // Close new home for any named home added to application
          if (ev.getType() == CollectionEvent.Type.ADD) {
            if (ev.getItem().getName() != null 
                && home.getName() == null
                && !home.isRecovered()) {
              controller.close();
            }
            removeHomesListener(this);
          } else if (ev.getItem() == home && ev.getType() == CollectionEvent.Type.DELETE) {
            removeHomesListener(this);
          }
        }
      };
      addHomesListener(newHomeListener);
      // Disable this listener at first home change
      home.addPropertyChangeListener(Home.Property.MODIFIED, new PropertyChangeListener() {
        public void propertyChange(PropertyChangeEvent ev) {
          removeHomesListener(newHomeListener);
          home.removePropertyChangeListener(Home.Property.MODIFIED, this);
        }
      });
    }
  }

  /**
   * Sets the rendering error listener bound to Java 3D to avoid default System
   * exit in case of error during 3D rendering.
   */
  private void addComponent3DRenderingErrorObserver() {
    if (!Boolean.getBoolean("com.eteks.sweethome3d.no3D")) { 
      // Add a RenderingErrorObserver to Component3DManager, because offscreen
      // rendering needs to check rendering errors with its own RenderingErrorListener
      Component3DManager.getInstance().setRenderingErrorObserver(new Component3DManager.RenderingErrorObserver() {
          public void errorOccured(int errorCode, String errorMessage) {
            System.err.print("Error in Java 3D : " + errorCode + " " + errorMessage);
            EventQueue.invokeLater(new Runnable() {
              public void run() {
                exitAfter3DError();
              }
            });
          }
        });
    }
  }

  /**
   * Displays a message to user about a 3D error, saves modified homes and
   * forces exit.
   */
  private void exitAfter3DError() {
    // Check if there are modified homes
    boolean modifiedHomes = false;
    for (Home home : getHomes()) {
      if (home.isModified()) {
        modifiedHomes = true;
        break;
      }
    }

    if (!modifiedHomes) {
      // Show 3D error message
      show3DError();
    } else if (confirmSaveAfter3DError()) {
      // Delete all homes after saving modified ones
      for (Home home : getHomes()) {
        if (home.isModified()) {
          String homeName = home.getName();
          if (homeName == null) {
            JFrame homeFrame = (JFrame) getHomeFrame(home);
            homeFrame.toFront();
            homeName = contentManager.showSaveDialog((View) homeFrame.getRootPane(), null,
                ContentManager.ContentType.SWEET_HOME_3D, null);
          }
          if (homeName != null) {
            try {
              // Write home with application recorder
              getHomeRecorder().writeHome(home, homeName);
            } catch (RecorderException ex) {
              // As this is an emergency exit, don't report error
              ex.printStackTrace();
            }
          }
          deleteHome(home);
        }
      }
    }
    // Close homes
    for (Home home : getHomes()) {
      deleteHome(home);
    }
    // Force exit if program didn't exit by itself
    System.exit(0);
  }

  /**
   * Displays in a 3D error message.
   */
  private void show3DError() {
    UserPreferences userPreferences = getUserPreferences();
    String message = userPreferences.getLocalizedString(SweetHome3D.class, "3DError.message");
    String title = userPreferences.getLocalizedString(SweetHome3D.class, "3DError.title");
    JOptionPane.showMessageDialog(KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(), message,
        title, JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Displays a dialog that let user choose whether he wants to save modified
   * homes after an error in 3D rendering system.
   * @return <code>true</code> if user confirmed to save.
   */
  private boolean confirmSaveAfter3DError() {
    UserPreferences userPreferences = getUserPreferences();
    String message = userPreferences.getLocalizedString(SweetHome3D.class, "confirmSaveAfter3DError.message");
    String title = userPreferences.getLocalizedString(SweetHome3D.class, "confirmSaveAfter3DError.title");
    String save = userPreferences.getLocalizedString(SweetHome3D.class, "confirmSaveAfter3DError.save");
    String doNotSave = userPreferences.getLocalizedString(SweetHome3D.class, "confirmSaveAfter3DError.doNotSave");

    return JOptionPane.showOptionDialog(KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),
        message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object [] {save, doNotSave},
        save) == JOptionPane.YES_OPTION;
  }

  /**
   * If the home with homeName is already opened, show it.
   * 
   * @param homeName
   * @return true if the home is already opened
   */
  protected boolean showOpenedFrame(String homeName) {
    for (Home home : getHomes()) {
      if (homeName.equals(home.getName())) {
        showHomeFrame(home);
        return true;
      }
    }
    return false;
  }
  
  /**
   * Starts application once initialized and opens home passed in arguments. 
   * This method is executed from Event Dispatch Thread.
   */
  public void start(String [] args) {
    if (args.length == 2 && args [0].equals("-open") && args [1].length() > 0) {
      // If requested home is already opened, show it
      if (!showOpenedFrame(args [1])) {
        openFile(args [1]);
      }
    } else { 
      showDefaultHomeFrame();
      checkUpdates();
    }
  }

  private void openFile(String filename) {
    HomeController homeController = createHomeFrameController(createHome()).getHomeController();
    if (getContentManager().isAcceptable(filename, ContentManager.ContentType.SWEET_HOME_3D)) {
      // Add a listener to application to recover homes once the one in parameter is open
      addHomesListener(new CollectionListener<Home>() {
          public void collectionChanged(CollectionEvent<Home> ev) {
            if (ev.getType() == CollectionEvent.Type.ADD) {
              removeHomesListener(this);
              if (autoRecoveryManager != null) {
                autoRecoveryManager.openRecoveredHomes();
              }
            }
          }
        });
      // Read home file with a dummy controller
      homeController.open(filename);
    } else if (getContentManager().isAcceptable(filename, ContentManager.ContentType.LANGUAGE_LIBRARY)) {
      showDefaultHomeFrame();
      final String languageLibraryName = filename;
      List<String> supportedLanguages = Arrays.asList(getUserPreferences().getSupportedLanguages());
      // Import language library with a dummy controller
      homeController.importLanguageLibrary(languageLibraryName);
      // Switch to the first language added to supported languages
      for (String language : getUserPreferences().getSupportedLanguages()) {
        if (!supportedLanguages.contains(language)) {
          getUserPreferences().setLanguage(language);
          break;
        }
      }
    } else if (getContentManager().isAcceptable(filename, ContentManager.ContentType.FURNITURE_LIBRARY)) {
      showDefaultHomeFrame();
      final String furnitureLibraryName = filename;
      // Import furniture library with a dummy controller
      homeController.importFurnitureLibrary(furnitureLibraryName);
    } else if (getContentManager().isAcceptable(filename, ContentManager.ContentType.TEXTURES_LIBRARY)) {
      showDefaultHomeFrame();
      final String texturesLibraryName = filename;
      // Import textures library with a dummy controller
      createHomeFrameController(createHome()).getHomeController().importTexturesLibrary(texturesLibraryName);
    } else if (getContentManager().isAcceptable(filename, ContentManager.ContentType.PLUGIN)) {
      showDefaultHomeFrame();
      final String pluginName = filename;
      // Import plug-in with a dummy controller
      if (homeController instanceof HomePluginController) {
        ((HomePluginController)homeController).importPlugin(pluginName);
      }
    }
    checkUpdates();
  }

  /**
   * Shows a home frame, either a new one when no home is opened, or the last created home frame.  
   */
  public void showDefaultHomeFrame() {
    if (getHomes().isEmpty()) {
      if (this.autoRecoveryManager != null) {
        this.autoRecoveryManager.openRecoveredHomes();
      }
      if (getHomes().isEmpty()) {
        // Add a new home to application
        addHome(createHome());
      }
    } else {
      // If no Sweet Home 3D frame has focus, bring last created viewed frame to front
      final List<Home> homes = getHomes();
      Home home = null;
      for (int i = homes.size() - 1; i >= 0; i--) {
        JFrame homeFrame = (JFrame) getHomeFrame(homes.get(i));
        if (homeFrame.isActive() || homeFrame.getState() != JFrame.ICONIFIED) {
          home = homes.get(i);
          break;
        }
      }
      // If no frame is visible and not iconified, take any displayable frame
      if (home == null) {
        for (int i = homes.size() - 1; i >= 0; i--) {
          JFrame homeFrame = (JFrame) getHomeFrame(homes.get(i));
          if (homeFrame.isDisplayable()) {
            home = homes.get(i);
            break;
          }
        }
      }
 
      showHomeFrame(home);
    }
  }

  /**
   * Check updates if needed.
   */
  private void checkUpdates() {
    if (this.checkUpdatesNeeded) {
      this.checkUpdatesNeeded = false;
      // Delay updates checking to let program launch finish
      new Timer(500, new ActionListener() {
          public void actionPerformed(ActionEvent ev) {
            ((Timer)ev.getSource()).stop();
            // Check updates with a dummy controller
            createHomeFrameController(createHome()).getHomeController().checkUpdates(true);
          }
        }).start();
    }
  }
  
  /**
   * A file content manager that records the last directories for each content
   * in Java preferences.
   */
  private static class FileContentManagerWithRecordedLastDirectories extends FileContentManager {
    private static final String LAST_DIRECTORY         = "lastDirectory#";
    private static final String LAST_DEFAULT_DIRECTORY = "lastDefaultDirectory";
    
    private final Class<? extends SweetHome3D> mainClass;

    public FileContentManagerWithRecordedLastDirectories(UserPreferences preferences, 
                                                         Class<? extends SweetHome3D> mainClass) {
      super(preferences);
      this.mainClass = mainClass;
    }

    @Override
    protected File getLastDirectory(ContentType contentType) {
      Preferences preferences = Preferences.userNodeForPackage(this.mainClass);
      String directoryPath = null;
      if (contentType != null) {
        directoryPath = preferences.get(LAST_DIRECTORY + contentType, null);
      }
      if (directoryPath == null) {
        directoryPath = preferences.get(LAST_DEFAULT_DIRECTORY, null);
      }
      if (directoryPath != null) {
        File directory = new File(directoryPath);
        if (directory.isDirectory()) {
          return directory;
        } 
      }
      return null;
    }
    
    @Override
    protected void setLastDirectory(ContentType contentType, File directory) {
      // Last directories are not recorded in user preferences since there's no need of portability 
      // from a computer to an other
      Preferences preferences = Preferences.userNodeForPackage(this.mainClass);
      if (directory == null) {
        preferences.remove(LAST_DIRECTORY + contentType);
      } else {
        String directoryPath = directory.getAbsolutePath();
        if (contentType != null) {
          preferences.put(LAST_DIRECTORY + contentType, directoryPath);
        }
        if (directoryPath != null) {
          preferences.put(LAST_DEFAULT_DIRECTORY, directoryPath);
        }
      }
      try {
        preferences.flush();
      } catch (BackingStoreException ex) {
        // Ignore exception, Sweet Home 3D will work without recorded directories
      }
    }
  }
  
  /**
   * <code>BasicService</code> that launches web browser either with Java SE 6
   * <code>java.awt.Desktop</code> class, or with the <code>open</code> command
   * under Mac OS X.
   */
  private static class StandaloneBasicServiceAWT implements BasicService {
    public boolean showDocument(URL url) {
      try {
        if (OperatingSystem.isJavaVersionGreaterOrEqual("1.6")) {
          // Call Java SE 6 java.awt.Desktop browse method by reflection to
          // ensure Java SE 5 compatibility
          Class<?> desktopClass = Class.forName("java.awt.Desktop");
          Object desktopInstance = desktopClass.getMethod("getDesktop").invoke(null);
          desktopClass.getMethod("browse", URI.class).invoke(desktopInstance, url.toURI());
          return true;
        }
      } catch (Exception ex) {
        try {
          if (OperatingSystem.isMacOSX()) {
            Runtime.getRuntime().exec(new String [] {"open", url.toString()});
            return true;
          } else if (OperatingSystem.isLinux()) {
            Runtime.getRuntime().exec(new String [] {"xdg-open", url.toString()});
            return true;
          }  
        } catch (IOException ex2) {
        }
        // For other cases, let's consider simply the showDocument method failed
      }
      return false;
    }

    public URL getCodeBase() {
      // Return a default URL matching the <code>resources</code> sub directory.
      return StandaloneServiceManager.class.getResource("resources");
    }

    public boolean isOffline() {
      return false;
    }

    public boolean isWebBrowserSupported() {
      if (OperatingSystem.isJavaVersionGreaterOrEqual("1.6")) {
        try {
          // Call Java SE 6 java.awt.Desktop isSupported(Desktop.Action.BROWSE)
          // method by reflection to ensure Java SE 5 compatibility
          Class<?> desktopClass = Class.forName("java.awt.Desktop");
          Object desktopInstance = desktopClass.getMethod("getDesktop").invoke(null);
          Class<?> desktopActionClass = Class.forName("java.awt.Desktop$Action");
          Object desktopBrowseAction = desktopActionClass.getMethod("valueOf", String.class).invoke(null, "BROWSE");
          if ((Boolean)desktopClass.getMethod("isSupported", desktopActionClass).invoke(desktopInstance,
              desktopBrowseAction)) {
            return true;
          }
        } catch (Exception ex) {
          // For any exception, let's consider simply the isSupported method failed
        }
      }
      // For other Java versions, let's support Mac OS X and Linux
      return OperatingSystem.isMacOSX() || OperatingSystem.isLinux();
    }
  }

  @Override
  public void syncExec(Runnable runnable) {
    try{
      EventQueue.invokeAndWait(runnable);
    } catch (InvocationTargetException ex) {
      throw new RuntimeException(ex);
    } catch (InterruptedException ex) {
      // Ignore in case of interruption
    }
  }

  @Override
  public void asyncExec(Runnable runnable) {
    EventQueue.invokeLater(runnable);
  }

}
