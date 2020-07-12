# REPLNote

Write your notes with a built in REPL!

## Installation

Download from GitHub releases, or build it yourself using `leiningen`.

## Usage

The editor works much like a normal text editor, but exposes a Clojure
REPL. You can evaluate expressions by adding a `#` preceeding a Clojure
expression wrapped in parenthesis. Expressions are evaluated when the
ENTER key is pressed.

```text
This is some example text.
Blah blah #(str "This will be evaluated!")
```

You can run the application by either double clicking the jar file or
running the following command:

    $ java -jar replnote-X.Y.Z-standalone.jar

## Demo

[REPLNote Demo - YouTube](https://www.youtube.com/watch?v=ZgzvGNp4C0w)

## License

Copyright © 2020 Gabriel Simmer

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
