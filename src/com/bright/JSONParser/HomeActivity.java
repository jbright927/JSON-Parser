package com.bright.JSONParser;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import org.json.JSONObject;

public class HomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private Menu optionsMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        showFragment(JSONFeedFragment.class, R.id.fragment_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.optionsMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                showFragment(JSONFeedFragment.class, R.id.fragment_main);
                setRefreshActionButtonState(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setRefreshActionButtonState(final boolean refreshing) {
        if (optionsMenu != null) {
            final MenuItem refreshItem = optionsMenu
                    .findItem(R.id.action_search);
            if (refreshItem != null) {
                if (refreshing) {
                    refreshItem.setActionView(R.layout.action_bar_progress_spinner);
                } else {
                    refreshItem.setActionView(null);
                }
            }
        }
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
