# Palette's Journal

## 2024-10-25 - Standard Keyboard Navigation
**Learning:** Adding mnemonics and accelerators to Swing menus significantly improves accessibility for keyboard-only users, providing a standard alternative to game-specific hotkeys.
**Action:** Always verify that `JMenu` and `JMenuItem` components have `setMnemonic` and `setAccelerator` configured with standard keys (Ctrl+Z for Undo, Ctrl+Q for Quit).

## 2024-10-26 - Semantic Label Association
**Learning:** Swing components do not automatically associate labels with fields. `JLabel.setLabelFor()` is critical for screen reader accessibility, even for read-only fields.
**Action:** Whenever a `JLabel` describes another component, explicitly link them using `setLabelFor()`.
