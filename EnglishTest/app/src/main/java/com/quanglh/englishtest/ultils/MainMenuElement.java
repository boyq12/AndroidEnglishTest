package com.quanglh.englishtest.ultils;

import com.quanglh.englishtest.MainActivity;

/**
 * Created by Dell7548 on 3/3/2016.
 */
public class MainMenuElement {
    private String menuName;
    private String iconName;
    private String note;
    
    public MainMenuElement(String menuName, String iconName, String note) {
        this.menuName = menuName;
        this.iconName = iconName;
        this.note = note;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return this.menuName+" (Note: "+ this.note+")";
    }
}
