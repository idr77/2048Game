## 2024-10-18 - Swing Menu Accessibility
**Learning:** Java Swing menus do not have default keyboard accessibility. Adding mnemonics and accelerators manually is required for standard desktop behavior.
**Action:** Always check `JMenu` and `JMenuItem` for `setMnemonic` and `setAccelerator` configurations.
