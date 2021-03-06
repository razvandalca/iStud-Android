package ro.horiacalin.istud.PresentationLayer.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;
import ro.horiacalin.istud.PresentationLayer.Adapters.ViewPageAdapter;
import ro.horiacalin.istud.R;

/**
 * Created by horiaacalin on 26/02/2017.
 */

public class EcranPrincipalActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;
    private List<Fragment>  fragmentList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        setContentView(R.layout.ecran_principal);


        viewPager = (ViewPager) findViewById(R.id.pager);
        fragmentList.clear();
        fragmentList.add(FragmentCalendar.newInstance());
        fragmentList.add(FragmentNote.newInstance());
        fragmentList.add(FragmentNotificari.newInstance());
        fragmentList.add(FragmentSetari.newInstance());
        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPageAdapter);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_calendar),
                        Color.TRANSPARENT
                ).title("Calendar")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_note),
                        Color.TRANSPARENT
                ).title("Note")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_notification_tab_bar),
                        Color.TRANSPARENT
                ).title("Notificari")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_settings),
                        Color.TRANSPARENT
                ).title("Setari")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);

        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
        navigationTabBar.setBadgeGravity(NavigationTabBar.BadgeGravity.BOTTOM);
        navigationTabBar.setBadgePosition(NavigationTabBar.BadgePosition.CENTER);
        navigationTabBar.setIsBadged(true);
        navigationTabBar.setIsTitled(true);
        navigationTabBar.setIsTinted(true);
        navigationTabBar.setIsBadgeUseTypeface(true);
        navigationTabBar.setBadgeTitleColor(Color.WHITE);
        navigationTabBar.setIsSwiped(true);
        navigationTabBar.setBgColor(getResources().getColor(R.color.colorPrimary));
        navigationTabBar.setBadgeSize(5);
        navigationTabBar.setTitleSize(30);
    }
}
