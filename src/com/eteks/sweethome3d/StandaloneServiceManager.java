package com.eteks.sweethome3d;

import javax.jnlp.BasicService;
import javax.jnlp.ServiceManagerStub;
import javax.jnlp.UnavailableServiceException;

import com.eteks.sweethome3d.model.HomeApplication;

/**
 * JNLP <code>ServiceManagerStub</code> implementation for standalone
 * applications run out of Java Web Start. This service manager supports
 * <code>BasicService</code> and <code>javax.jnlp.SingleInstanceService</code>.
 * .
 */
class StandaloneServiceManager implements ServiceManagerStub {
  private final Class<? extends HomeApplication> mainClass;
  private final Class<? extends BasicService>    basicServiceClass;

  public StandaloneServiceManager(Class<? extends HomeApplication> mainClass,
                                  Class<? extends BasicService> basicServiceClass) {
    this.mainClass = mainClass;
    this.basicServiceClass = basicServiceClass;
  }

  public Object lookup(final String name) throws UnavailableServiceException {
    if (name.equals("javax.jnlp.BasicService")) {
      // Create a basic service
      try {
        return this.basicServiceClass.newInstance();
      } catch (InstantiationException ex) {
        throw new UnavailableServiceException(name);
      } catch (IllegalAccessException ex) {
        throw new UnavailableServiceException(name);
      }
    } else if (name.equals("javax.jnlp.SingleInstanceService")) {
      // Create a server that waits for further Sweet Home 3D launches
      return new StandaloneSingleInstanceService(this.mainClass);
    } else {
      throw new UnavailableServiceException(name);
    }
  }

  public String [] getServiceNames() {
    return new String [] {"javax.jnlp.BasicService", "javax.jnlp.SingleInstanceService"};
  }
}
