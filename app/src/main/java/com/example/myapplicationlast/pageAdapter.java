package com.example.myapplicationlast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pageAdapter extends FragmentPagerAdapter {

    private int tabnambers;

    public pageAdapter(@NonNull FragmentManager fm, int behavior,int tabs) {
        super(fm, behavior);
        this .tabnambers =tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:

                return new BlankFragment1();
            case 1:
                return new BlankFragment2();
            case 2:
                return new BlankFragment3();
                default: return null;


        }


    }

    @Override
    public int getCount() {
        return tabnambers;
    }
}
