## 2024-05-22 - [Swing Menu Accessibility]
**Learning:** Swing menus are inaccessible by default (no keyboard nav). Adding `setMnemonic` and `setAccelerator` makes them keyboard-friendly, which is critical for a game where users already rely on keyboard controls.
**Action:** Always add mnemonics to JMenus and accelerators to JMenuItems, mirroring existing game key bindings where possible.
