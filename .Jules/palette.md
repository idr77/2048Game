# Palette's Journal

## 2024-10-25 - Standard Keyboard Navigation
**Learning:** Adding mnemonics and accelerators to Swing menus significantly improves accessibility for keyboard-only users, providing a standard alternative to game-specific hotkeys.
**Action:** Always verify that `JMenu` and `JMenuItem` components have `setMnemonic` and `setAccelerator` configured with standard keys (Ctrl+Z for Undo, Ctrl+Q for Quit).

## 2024-05-22 - Label Association in Swing
**Learning:** Swing `JLabel` components accompanying input fields (editable or read-only) must use `setLabelFor()` to ensure association for screen readers.
**Action:** When auditing `JPanel` layouts, check for `JLabel` followed by `JTextField` and verify `setLabelFor` is called.
