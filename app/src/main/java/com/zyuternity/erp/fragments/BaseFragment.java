package com.zyuternity.erp.fragments;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.zyuternity.erp.managers.ScreenManager;

/**
 * Created by ZYuTernity on 7/13/2016.
 */
public class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.toString();

    public ScreenManager getScreenManager() {
        ScreenManagerHolder screenManagerHolder = (ScreenManagerHolder) this.getActivity();
        if(screenManagerHolder == null) {
            Log.e(TAG, "Parent activity must implement ScreenManagerHolder");
            return null;
        }
        return screenManagerHolder.getScreenManager();
    }
}
