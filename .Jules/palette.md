## 2024-05-22 - Menu Accessibility Improvements
**Learning:** Adding standard mnemonics and accelerators to Swing menus significantly improves keyboard accessibility without visual clutter. It also reveals existing keyboard shortcuts to users (like 'Space' for Hint).
**Action:** Always check `JMenuBar` implementations for missing `setMnemonic` and `setAccelerator` calls. Match accelerators to existing `InputMap` bindings.
