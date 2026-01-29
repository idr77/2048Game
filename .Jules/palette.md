# Palette's Journal

## 2024-10-25 - Standard Keyboard Navigation
**Learning:** Adding mnemonics and accelerators to Swing menus significantly improves accessibility for keyboard-only users, providing a standard alternative to game-specific hotkeys.
**Action:** Always verify that `JMenu` and `JMenuItem` components have `setMnemonic` and `setAccelerator` configured with standard keys (Ctrl+Z for Undo, Ctrl+Q for Quit).

## 2024-10-25 - Reliable Key Bindings in Swing
**Learning:** Default `InputMap` requires component focus, which causes "broken" controls in games when focus is lost (e.g. to menus).
**Action:** Use `WHEN_IN_FOCUSED_WINDOW` for global game controls to ensure they work regardless of focus state.
