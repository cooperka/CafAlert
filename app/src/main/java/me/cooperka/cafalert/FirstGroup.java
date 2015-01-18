package me.cooperka.cafalert;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;

// from http://web.archive.org/web/20100816175634/http://blog.henriklarsentoft.com/2010/07/android-tabactivity-nested-activities/

public class FirstGroup extends ActivityGroup {

    // Keep this in a static variable to make it accessible for all the nested activities, lets them manipulate the view
    public static FirstGroup group;

    // Need to keep track of the history if you want the back-button to work properly, don't use this if your activities requires a lot of memory.
    private ArrayList<View> history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.history = new ArrayList<View>();
        group = this;

        // Start the root activity within the group and get its view
        View view = getLocalActivityManager()
                .startActivity("caflist", new Intent(this, CafList.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                .getDecorView();

        // Replace the view of this ActivityGroup
        replaceView(view);
    }

    public void replaceView(View v) {
        history.add(v); // Add the old one to history
        setContentView(v); // Change this Groups View to the new View.
    }

    public void back() {
        if (history.size() >= 2)
        {
            history.remove(history.size()-1);
            setContentView(history.get(history.size()-1));
        }
        else
            finish();
    }

    @Override
    public void onBackPressed() {
        FirstGroup.group.back();
        return;
    }

    // from another poster, idk if it's required?
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            FirstGroup.group.back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
