## 2024-05-22 - Keyboard Accessibility in Swing Menus
**Learning:** Swing's `JMenuBar` benefits greatly from `setMnemonic` and `setAccelerator` for keyboard users. Mnemonics allow navigation via Alt+Key, while accelerators provide direct shortcuts. Matching accelerators to existing `InputMap` bindings enhances discoverability.
**Action:** Always verify `InputMap` bindings in the main window and mirror them in the menu bar accelerators for consistency and discoverability.
