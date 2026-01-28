# Palette's Journal

## 2024-10-25 - Standard Keyboard Navigation
**Learning:** Adding mnemonics and accelerators to Swing menus significantly improves accessibility for keyboard-only users, providing a standard alternative to game-specific hotkeys.
**Action:** Always verify that `JMenu` and `JMenuItem` components have `setMnemonic` and `setAccelerator` configured with standard keys (Ctrl+Z for Undo, Ctrl+Q for Quit).

## 2024-10-25 - Explicit Label Association
**Learning:** Screen readers often struggle with unassociated labels in Swing layouts (like GridBagLayout). Using `label.setLabelFor(component)` creates a programmatic link that ensures the label is read when the field is focused.
**Action:** Whenever placing a `JLabel` next to an input or display field, explicitly call `setLabelFor` to ensure accessibility.
