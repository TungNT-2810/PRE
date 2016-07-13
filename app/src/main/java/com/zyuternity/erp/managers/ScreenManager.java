package com.zyuternity.erp.managers;

//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by ZYuTernity on 7/13/2016.
 */
public class ScreenManager {

    private FragmentManager fragmentManager;

    private int fragmentContainerId;

    public ScreenManager(FragmentManager fragmentManager, int fragmentContainerId) {
        this.fragmentManager = fragmentManager;
        this.fragmentContainerId = fragmentContainerId;
    }

    public void openFragment(Fragment fragment, boolean addToBackStack){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (addToBackStack){
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }

    public void openFragment(Fragment fragment){
        openFragment(fragment, true);
    }

    public boolean back() {
        if (fragmentManager.getBackStackEntryCount() > 0){
            fragmentManager.popBackStack();
            return true;
        }
        return false;
    }
}
