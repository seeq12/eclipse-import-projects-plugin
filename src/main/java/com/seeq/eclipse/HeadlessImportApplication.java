package com.seeq.eclipse;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class HeadlessImportApplication implements IApplication {

    @Override
    public Object start(IApplicationContext iac) throws Exception {
        new ImportProjects().earlyStartup();
        return null;
    }

    @Override
    public void stop() {
    }
}
