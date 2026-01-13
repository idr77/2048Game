## 2026-01-13 - Accessible Menus in Legacy Swing
**Learning:** Legacy Swing apps often miss basic a11y like mnemonics and accelerators, forcing mouse usage. Adding these is a high-impact, low-risk improvement.
**Action:** Always check `JMenu` and `JMenuItem` for `setMnemonic` and `setAccelerator` calls.
