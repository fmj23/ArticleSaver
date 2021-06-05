package com.group15.articlesaver;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Manager {
    public static void hideKeyboard(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        // Find the currently focused view
        View view = activity.getCurrentFocus();
        // If no view
        if (view == null){
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
