package ro.horiacalin.istud;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by horiaacalin on 26/02/2017.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {

    int numarFragmente = 2;

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

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return numarFragmente;
    }
}
