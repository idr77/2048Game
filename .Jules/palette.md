## 2024-05-22 - Swing Menu Accessibility
**Learning:** Swing's `JMenu` and `JMenuItem` do not have default keyboard accessibility features. Mnemonics (e.g., `Alt+F`) and Accelerators (e.g., `Ctrl+Z`) must be manually configured using `.setMnemonic()` and `.setAccelerator()`.
**Action:** Always check `JMenuBar` implementations in Swing apps for missing mnemonics and accelerators. Use `InputEvent.CTRL_DOWN_MASK` for modern, cross-platform modifier key handling instead of the deprecated `ActionEvent.CTRL_MASK`.
