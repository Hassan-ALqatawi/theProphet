package com.al_qatawi.theprophet.modle;

import androidx.fragment.app.Fragment;

public class MyTab {
    private String tabName;
    Fragment fragment;

    public MyTab(String tabName, Fragment fragment) {
        this.tabName = tabName;
        this.fragment = fragment;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
