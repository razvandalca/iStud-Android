package ro.horiacalin.istud.PresentationLayer.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import ro.horiacalin.istud.PresentationLayer.Controller.FragmentCalendar;
import ro.horiacalin.istud.PresentationLayer.Controller.FragmentNote;
import ro.horiacalin.istud.PresentationLayer.Controller.FragmentSetari;

/**
 * Created by horiaacalin on 26/02/2017.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;

    public ViewPageAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentManager=fm;
        this.fragments=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
