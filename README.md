# Eclipse Import Projects Plug-in

This plug-in allows import of projects an preferences using Eclipse command-line parameters. It is useful
in situations where Maven is producing the .project files and Eclipse would ideally import/refresh
them when launched or for Eclipse provisioning.

## Installation

Place the com.seeq.eclipse.importprojects JAR file in the eclipse/plugins folder.

## Usage

When launching Eclipse, add the `-importProject <root project folder>` command line parameter. This will
cause Eclipse to recursively search the supplied folder for .project files and import them into
the workspace. If they are already present in the workspace, they will be refreshed. The path supplied
must be an absolute path.

You can also supply `-importPreferences exported.epf` to import Eclipse preferences exported earlier.

You can supply multiple `-importProjet` an `-importPreferences` directives to import multiple folders. E.g.
`-importProject projectFolder1 -importProject projectFolder2`, `-importPreferences prefs1.epf -importPreferences
prefs2.epf` or even `-importProject projectFolder -importPreferences prefs.epf`.

The plugin will log activity and any errors to the Eclipse *Error Log* view (`Window > Show View > Other > Error Log`).

### Headless

The aforementioned usage will import the projects on eclipse startup and will utimately place you in the IDE.
To perform a headless-import that does not actually start the IDE, run with `-application com.seeq.eclipse.importprojects.headlessimport`.  
On Windows use `eclipsec` instead of `eclipse` launcher.
(CMD also does not know about `\` for breaking long lines, replace with `^`. For PowerShell use `` ` ``)

```
eclipsec.exe -nosplash -application com.seeq.eclipse.importprojects.headlessimport \
-data <workspace-folder> -import <import-folder>
```

Alternatively, use the Equinox launcher directly:

```
java -jar eclipse-jee-mars-1.0.2/plugins/org.eclipse.equinox.launcher_$VERSION.jar -nosplash -data $WORKSPACE \
-application com.seeq.eclipse.importprojects.headlessimport \
-importProject $PROJECT -importPreferences $PREFERENCES
```

## Supported Configurations

This plugin has been tested with Eclipse Helios (4.2), Kepler (4.3), Luna (4.4) and Mars (4.5) against Java 7 and 8.
It will probably work with other configurations but they haven't been tested.

## Building

This plugin builds using Maven. It has been tested with Maven 3.2.3 and 3.5.2.

With Maven on your path, execute `mvn package` from the root of the repository. The target
folder will contain the resulting jar file.

## Debugging

You can debug this plugin from Eclipse using the Plugin Development Environment. Take the following steps:

1. Create the Eclipse project files from Maven by executing `mvn eclipse:eclipse` at the root of the repo.
2. Install the "Eclipse for RCP and RAP Developers" edition of Eclipse and launch it.
3. Import the plugin project via `File > Import... > General > Existing Projects into Workspace`.
4. Go to `Preferences > Java > Build Path > Classpath Variables` and add an `M2_REPO` variable if it
   doesn't already exist. It should point to your local Maven repository, which by default is `~/.m2/repository`. 
5. Open up `ImportProjects.java` and put a breakpoint in `earlyStartup()`.
6. Select `Run > Debug Configurations...` and click `Eclipse Application`.
7. Press the `New` button to create a new debug configuration. Call it whatever you like.
8. Click on the `Arguments` tab and add an `-import <dir>` directive to the `Program arguments` section.
9. Click on `Apply` and then `Debug` and you should hit your breakpoint.
