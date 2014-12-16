package com.seeq.eclipse;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.*;

public class Activator extends Plugin {
	
    // The plug-in ID
    public static final String PLUGIN_ID = "com.seeq.eclipse.importprojects"; //$NON-NLS-1$

    // The shared instance
    private static Activator plugin;
	
	private static BundleContext context;

	public static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		plugin = null;
	}

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }
	
}
