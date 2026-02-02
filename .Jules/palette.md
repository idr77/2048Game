# Palette's Journal

## 2024-10-25 - Standard Keyboard Navigation
**Learning:** Adding mnemonics and accelerators to Swing menus significantly improves accessibility for keyboard-only users, providing a standard alternative to game-specific hotkeys.
**Action:** Always verify that `JMenu` and `JMenuItem` components have `setMnemonic` and `setAccelerator` configured with standard keys (Ctrl+Z for Undo, Ctrl+Q for Quit).

## 2024-10-26 - Label Associations and HTML Text
**Learning:** Swing's `JLabel` can parse HTML, which is perfect for rich text formatting in instructions. Also, `setLabelFor()` is crucial for read-only fields to ensure screen readers announce the label, even if the field is not editable.
**Action:** Use `<html>` tags in `JLabel` text for emphasis and always pair `JLabel` with its component using `setLabelFor()`.
