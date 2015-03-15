package com.bright.JSONParser;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import org.json.JSONObject;

public class HomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        showFragment(JSONFeedFragment.class, R.id.fragment_main);
    }


    public void showFragment(Class<? extends Fragment> fragmentClass, int viewID) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(viewID, createOrRetainFragment(fragmentClass));
        ft.commit();
    }

    private Fragment createOrRetainFragment(Class<? extends Fragment> fragmentClass) {
        Fragment newFragment;

        try {
            newFragment = fragmentClass.newInstance();
            return newFragment;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
