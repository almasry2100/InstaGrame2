package com.example.instagrameclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class SocialMediaActivity extends AppCompatActivity {
    private androidx.appcompat.widget.Toolbar toolbar;
    private ViewPager viewpager;
    private TabLayout tablayout;
    private TabAdapter tabadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        setTitle( "Social Media App !!!" );
        toolbar=findViewById( R.id.myToolbar );
        setSupportActionBar( toolbar );
        viewpager=findViewById( R.id.viewPager );
        tabadapter=new TabAdapter( getSupportFragmentManager() );
        viewpager.setAdapter( tabadapter );
        tablayout=findViewById( R.id.tabLayout );
        tablayout.setupWithViewPager( viewpager,false );
    }
}