package ro.horiacalin.istud.PresentationLayer.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;
import ro.horiacalin.istud.R;
import ro.horiacalin.istud.PresentationLayer.Adapters.ViewPageAdapter;

/**
 * Created by horiaacalin on 26/02/2017.
 */

public class EcranPrincipalActivity extends AppCompatActivity {

    public ViewPager viewPager;
    public ViewPageAdapter viewPageAdapter;
    public final int numarFragmente = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecran_principal);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), numarFragmente);
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

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);

        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
        navigationTabBar.setBadgeGravity(NavigationTabBar.BadgeGravity.BOTTOM);
        navigationTabBar.setBadgePosition(NavigationTabBar.BadgePosition.CENTER);
        navigationTabBar.setIsBadged(true);
        navigationTabBar.setIsTitled(true);
        navigationTabBar.setIsTinted(true);
        navigationTabBar.setIsBadgeUseTypeface(true);
        navigationTabBar.setBadgeBgColor(Color.RED);
        navigationTabBar.setBadgeTitleColor(Color.WHITE);
        navigationTabBar.setIsSwiped(true);
        navigationTabBar.setBgColor(Color.RED);
        navigationTabBar.setBadgeSize(5);
        navigationTabBar.setTitleSize(15);
    }
}
