package com.seeq.eclipse;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.equinox.app.*;
import org.osgi.framework.*;

public class ImportProjects implements org.eclipse.ui.IStartup {
	
	private String getImportPath() {
		BundleContext context = Activator.getContext();
        ServiceReference<?> ser = context.getServiceReference(IApplicationContext.class);
        IApplicationContext iac = (IApplicationContext)context.getService(ser);
        String[] args = (String[]) iac.getArguments().get(IApplicationContext.APPLICATION_ARGS);
        String importPath = null;
        for (int i = 0; i < args.length; i++) {
        	String arg = args[i];
        	if (arg.compareToIgnoreCase("-import") == 0) {
        		i++;
        		if (i < args.length) {
	        		importPath = args[i];
	        		break;
        		}
        	}
        }
        
        return importPath;
	}

    private List<File> findFilesRecursively(String path, String pattern) {
    	List<File> returnedList = new ArrayList<File>();
    	return this.findFilesRecursively(path, pattern, returnedList);
    }

    private List<File> findFilesRecursively(String path, String pattern, List<File> returnedList) {
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null)
        	return returnedList;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                this.findFilesRecursively(f.getAbsolutePath(), pattern, returnedList);
            }
            else {
            	if (Pattern.matches(pattern, f.getName()) == true) {
            		returnedList.add(f);
            	}
            }
        }
        
        return returnedList;
    }

	@Override
	public void earlyStartup() {
		
		String importPath = this.getImportPath();
		
		System.out.println(String.format("Searching for projects in %s", importPath));
		List<File> projectFiles = this.findFilesRecursively(importPath, "\\.project");
        
		for (File projectFile : projectFiles) {
			try {
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IProjectDescription description = workspace.loadProjectDescription(
						new Path(projectFile.toString()));
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
				if (project.isOpen() == false) {
					System.out.println(String.format("Importing project %s", description.getName()));
					project.create(description, null);
					project.open(null);
				} else {
					System.out.println(String.format("Refreshing project %s", description.getName()));
					project.refreshLocal(IResource.DEPTH_INFINITE, null);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
}
