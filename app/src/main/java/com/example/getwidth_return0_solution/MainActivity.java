package com.example.getwidth_return0_solution;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public int state;
    public Bitmap sourcePhoto;
    public Fragment now;
    public Rumor_Fragment rumor;
    public Food_Fragment_Main food_main;
    public FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_viewRumor:
                    showFrag(R.id.navigation_viewRumor);
                    return true;

                case R.id.navigation_searchDatabase:
                    showFrag(R.id.navigation_searchDatabase);
                    return true;

            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /*建立Fragment*/
        try {
            fragmentManager = getSupportFragmentManager();
            rumor = new Rumor_Fragment();

            food_main = new Food_Fragment_Main();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, rumor, "rumor");

            fragmentTransaction.show(rumor);
            fragmentTransaction.commit();
            now = rumor;
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void showFrag(int id) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (id) {
            case R.id.navigation_viewRumor:
                this.state = R.id.navigation_viewRumor;
                fragmentTransaction.hide(now);
                fragmentTransaction.show(rumor);
                fragmentTransaction.commit();
                now = rumor;
                break;

            case R.id.navigation_searchDatabase:
                this.state = R.id.navigation_searchDatabase;
                Fragment fragmentA = fragmentManager.findFragmentByTag("food_main");
                if (fragmentA == null) {
                    fragmentTransaction.add(R.id.container, food_main, "food_main");
                }
                fragmentTransaction.hide(now);
                fragmentTransaction.show(food_main);
                fragmentTransaction.commit();
                now = food_main;
                break;

        }
    }


}
