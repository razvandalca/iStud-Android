package ro.horiacalin.istud.PresentationLayer.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ro.horiacalin.istud.PresentationLayer.Controller.FragmentCalendar;
import ro.horiacalin.istud.PresentationLayer.Controller.FragmentNote;
import ro.horiacalin.istud.PresentationLayer.Controller.FragmentSetari;

/**
 * Created by horiaacalin on 26/02/2017.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {

    int numarFragmente = 3;

    public ViewPageAdapter(FragmentManager fm, int numarFragmente) {
        super(fm);
        this.numarFragmente = numarFragmente;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return FragmentCalendar.newInstance("Fragment 1");

            case 1:
                return FragmentNote.newInstance("Fragment 2");

            case 2:
                return FragmentSetari.newInstance("Fragment 3");

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return numarFragmente;
    }
}
