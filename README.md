# SWT 2 Projekt

## Ordner Struktur

### `Application.java`

Start Datei fuer das Projekt. Von hier aus werden Verbindungen hergestellt und das start Menue verwaltet

### `src`

Hauptordner fuer alle Java-Dateien

#### `src/main`

Programmablauf relevantes

##### `src/main/admin`

Admin verwaltungs-Ansicht des Restaurants

##### `src/main/table`

Tischansicht (Tischauswahl & Bestellung & Bezahlung)

#### `src/api`

Alle Module zur weiterverwendung

##### `src/api/database.java`

MariaDB verwaltungs api

## Git Befehle

`git branch` zeigt alle verfuegbaren Branches an

`git checkout` wechsel den aktiven Branch

`git checkout -b <name>` erstellt einen neuen Branch und wechselt dorthin

`git fetch` holt alle updates (branches, commits)

`git status` zeigt derzeitigen commit an

`git add <file or .>` adds changes to commit

`git commit -m "<message>"` packt alle dateien in einen commit

`git push` lädt alle commits hoch

`git pull` lädt den jetzigen branch in dem man gerade ist
