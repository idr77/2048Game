# Palette's Journal

## 2024-10-25 - Standard Keyboard Navigation
**Learning:** Adding mnemonics and accelerators to Swing menus significantly improves accessibility for keyboard-only users, providing a standard alternative to game-specific hotkeys.
**Action:** Always verify that `JMenu` and `JMenuItem` components have `setMnemonic` and `setAccelerator` configured with standard keys (Ctrl+Z for Undo, Ctrl+Q for Quit).

## 2026-01-31 - HTML Labels for Key Instructions
**Learning:** Swing JLabels support HTML formatting, which is perfect for visually emphasizing key names (e.g., `<b>[Key]</b>`) in instruction text without complex custom rendering.
**Action:** Use `<html>` tags in instructional labels to bold key names for better scanability.
