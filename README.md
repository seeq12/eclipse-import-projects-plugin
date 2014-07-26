# Eclipse Import Projects Plug-in

This plug-in allows import of projects using an Eclipse command-line parameter. It is useful
in situations where Maven is producing the .project files and Eclipse would ideally import/refresh
them when launched.

## Installation

Place the com.seeq.eclipse.importprojects JAR file in the eclipse/plugins folder.

## Usage

When launching Eclipse, add the `-import <root project folder>` command line parameter. This will
cause Eclipse to recursively search the supplied folder for .project files and import them into
the workspace. If they are already present in the workspace, they will be refreshed.

## License

This plug-in is usable under the MIT 2.0 open source license.
