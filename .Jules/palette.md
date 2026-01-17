# Palette's Journal - Critical Learnings

## 2024-10-25 - Swing Menu Accessibility
**Learning:** Swing menus (JMenu/JMenuItem) do not support mnemonics or accelerators by default. Users must manually add them using `setMnemonic` and `setAccelerator`.
**Action:** Always check `JMenu` and `JMenuItem` implementations for missing mnemonics and accelerators to improve keyboard accessibility.
