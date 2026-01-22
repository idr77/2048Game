## 2024-05-22 - Swing Accessibility
**Learning:** Swing components like JMenu and JMenuItem require manual configuration of mnemonics and accelerators to be keyboard accessible. Unlike some modern frameworks, this is not automatic.
**Action:** Always check Swing menus for `setMnemonic` and `setAccelerator` calls.
