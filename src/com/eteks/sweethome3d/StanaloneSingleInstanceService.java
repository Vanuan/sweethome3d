package com.eteks.sweethome3d;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.jnlp.SingleInstanceListener;
import javax.jnlp.SingleInstanceService;

import com.eteks.sweethome3d.model.HomeApplication;
import com.eteks.sweethome3d.tools.OperatingSystem;

/**
 * A single instance service server that waits for further Sweet Home 3D
 * launches.
 */
class StandaloneSingleInstanceService implements SingleInstanceService {
  private static final String                SINGLE_INSTANCE_PORT    = "singleInstancePort";

  private final Class<? extends HomeApplication> mainClass;
  private final List<SingleInstanceListener> singleInstanceListeners = new ArrayList<SingleInstanceListener>();

  public StandaloneSingleInstanceService(Class<? extends HomeApplication> mainClass) {
    this.mainClass = mainClass;
  }

  public void addSingleInstanceListener(SingleInstanceListener l) {
    if (this.singleInstanceListeners.isEmpty()) {
      if (!OperatingSystem.isMacOSX()) {
        // Launching a server is useless under Mac OS X because further launches will be notified 
        // by com.apple.eawt.ApplicationListener added to application in MacOSXConfiguration class
        launchSingleInstanceServer();
      }
    }
    this.singleInstanceListeners.add(l);
  }

  /**
   * Launches single instance server.
   */
  private void launchSingleInstanceServer() {
    final ServerSocket serverSocket;
    try {
      // Launch a server that waits for other Sweet Home 3D launches
      serverSocket = new ServerSocket(0, 0, InetAddress.getByName("127.0.0.1"));
      // Share server port in preferences
      Preferences preferences = Preferences.userNodeForPackage(this.mainClass);
      preferences.putInt(SINGLE_INSTANCE_PORT, serverSocket.getLocalPort());
      preferences.flush();
    } catch (IOException ex) {
      // Ignore exception, Sweet Home 3D will work with multiple instances
      return;
    } catch (BackingStoreException ex) {
      // Ignore exception, Sweet Home 3D will work with multiple instances
      return;
    }

    Executors.newSingleThreadExecutor().execute(new Runnable() {
      public void run() {
        try {
          while (true) {
            // Wait client calls
            Socket socket = serverSocket.accept();
            // Read client params
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            String [] params = reader.readLine().split("\t");
            reader.close();
            socket.close();

            // Work on a copy of singleInstanceListeners to ensure a listener
            // can modify safely listeners list
            SingleInstanceListener [] listeners = singleInstanceListeners
                .toArray(new SingleInstanceListener [singleInstanceListeners.size()]);
            // Call listeners with received params
            for (SingleInstanceListener listener : listeners) {
              listener.newActivation(params);
            }
          }
        } catch (IOException ex) {
          // In case of problem, relaunch server
          launchSingleInstanceServer();
        }
      }
    });
  }

  public void removeSingleInstanceListener(SingleInstanceListener l) {
    this.singleInstanceListeners.remove(l);
    if (this.singleInstanceListeners.isEmpty()) {
      Preferences preferences = Preferences.userNodeForPackage(this.mainClass);
      preferences.remove(SINGLE_INSTANCE_PORT);
      try {
        preferences.flush();
      } catch (BackingStoreException ex) {
        throw new RuntimeException(ex);
      }
    }
  }

  /**
   * Returns <code>true</code> if single instance server was successfully
   * called.
   */
  public static boolean callSingleInstanceServer(String [] mainArgs, Class<? extends HomeApplication> mainClass) {
    if (!OperatingSystem.isMacOSX()) {
      // No server under Mac OS X, multiple application launches are managed
      // by com.apple.eawt.ApplicationListener in MacOSXConfiguration class
      Preferences preferences = Preferences.userNodeForPackage(mainClass);
      int singleInstancePort = preferences.getInt(SINGLE_INSTANCE_PORT, -1);
      if (singleInstancePort != -1) {
        try {
          // Try to connect to single instance server
          Socket socket = new Socket("127.0.0.1", singleInstancePort);
          // Write main args
          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
          for (String arg : mainArgs) {
            writer.write(arg);
            writer.write("\t");
          }
          writer.write("\n");
          writer.close();
          socket.close();
          return true;
        } catch (IOException ex) {
          // Return false
        }
      }
    }
    return false;
  }
}