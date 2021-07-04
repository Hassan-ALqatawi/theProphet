package com.al_qatawi.theprophet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.work.Constraints;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Context;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.al_qatawi.theprophet.modle.MyTab;
import com.al_qatawi.theprophet.modle.MyWorker;
import com.al_qatawi.theprophet.modle.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);



       pRequest();


        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        adapter.AddTab(new MyTab(getResources().getString(R.string.A),CategaryFragment.newInstance(1)));
        adapter.AddTab(new MyTab(getResources().getString(R.string.B),CategaryFragment.newInstance(2)));
        adapter.AddTab(new MyTab(getResources().getString(R.string.C),CategaryFragment.newInstance(3)));
        adapter.AddTab(new MyTab(getResources().getString(R.string.D),CategaryFragment.newInstance(4)));
        adapter.AddTab(new MyTab(getResources().getString(R.string.E),CategaryFragment.newInstance(5)));
        adapter.AddTab(new MyTab(getResources().getString(R.string.F),CategaryFragment.newInstance(6)));
        adapter.AddTab(new MyTab(getResources().getString(R.string.G),CategaryFragment.newInstance(7)));
        adapter.AddTab(new MyTab(getResources().getString(R.string.H),CategaryFragment.newInstance(8)));
        adapter.AddTab(new MyTab(getResources().getString(R.string.M),CategaryFragment.newInstance(9)));


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    public static Constraints setCons(){

        Constraints constraints = new Constraints.Builder()
                .build();

        return constraints;

    }

    public  void pRequest() {


        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorker.class,
                15, TimeUnit.MINUTES)

                //.setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(setCons())
                .addTag("صلي علي النبي و تبسم")
                .build();
        WorkManager.getInstance().enqueue(periodicWorkRequest);


    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        mediaPlayer = MediaPlayer.create(this,R.raw.sand);
        mediaPlayer.start();

        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mediaPlayer.stop();
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}