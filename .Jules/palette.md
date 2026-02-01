# Palette's Journal

## 2024-10-25 - Standard Keyboard Navigation
**Learning:** Adding mnemonics and accelerators to Swing menus significantly improves accessibility for keyboard-only users, providing a standard alternative to game-specific hotkeys.
**Action:** Always verify that `JMenu` and `JMenuItem` components have `setMnemonic` and `setAccelerator` configured with standard keys (Ctrl+Z for Undo, Ctrl+Q for Quit).

## 2024-10-26 - Semantic Field Association
**Learning:** In Swing, purely visual labels for text fields (even read-only ones) are invisible to screen readers unless programmatically associated.
**Action:** Use `label.setLabelFor(field)` for every `JLabel` that serves as a caption for a component, ensuring accessible relationships.
