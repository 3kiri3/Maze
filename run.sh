#!/bin/bash

# ------------------------------
# Script to compile and run the Maze game (Linux/macOS)
# Usage:
#   ./run.sh
# Make executable first:
#   chmod +x run.sh
# Notes:
#   If errors appear, check installed libraries and install JavaFX if needed.
# ------------------------------

# Detect OS
OS="$(uname -s)"

# Set JavaFX SDK path
JAVAFX_PATH="$HOME/javafx-sdk-25/lib"

echo "Detected OS: $OS"
echo "Using JavaFX path: $JAVAFX_PATH"

# Compile (optional: only if code changed)
javac --module-path "$JAVAFX_PATH" \
      --add-modules javafx.controls,javafx.fxml \
      -d bin src/application/*.java

# Run the game
if [[ "$OS" == "Linux" ]]; then
    # Force X11 backend
    GDK_BACKEND=x11 java --enable-native-access=javafx.graphics \
         --module-path "$JAVAFX_PATH" \
         --add-modules javafx.controls,javafx.fxml \
         -cp bin application.Main
else
    # macOS or others
    java --enable-native-access=javafx.graphics \
         --module-path "$JAVAFX_PATH" \
         --add-modules javafx.controls,javafx.fxml \
         -cp bin application.Main
fi
