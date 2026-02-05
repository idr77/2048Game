# Palette's Journal

## 2024-10-25 - Standard Keyboard Navigation
**Learning:** Adding mnemonics and accelerators to Swing menus significantly improves accessibility for keyboard-only users, providing a standard alternative to game-specific hotkeys.
**Action:** Always verify that `JMenu` and `JMenuItem` components have `setMnemonic` and `setAccelerator` configured with standard keys (Ctrl+Z for Undo, Ctrl+Q for Quit).

## 2026-02-05 - Read-only Text Fields Accessibility
**Learning:** Read-only `JTextField` components (used for scores) are focusable by default, making `setLabelFor` mandatory for screen reader context.
**Action:** Ensure every `JTextField` (even `editable(false)`) has an associated `JLabel` with `setLabelFor()`.
