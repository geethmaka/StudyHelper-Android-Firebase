package com.example.madd_project.ui.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.madd_project.TM_Main;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }


}