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

Folder path mast be absolute.

You can supply multiple `-import` directives to import multiple folders. E.g. `-import <folder 1> -import <folder 2>`.

## License

MIT License

Copyright (C) 2014 Seeq Corporation


Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
