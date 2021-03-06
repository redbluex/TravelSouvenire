package pl.redblue.travelsouvenire;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import static pl.redblue.travelsouvenire.RegisterActivity.myIds;
import static pl.redblue.travelsouvenire.SearchFragment.searchId;

public class MainActivity extends FragmentActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
        NewPostsFrag newPostsFrag =new NewPostsFrag();
        fragmentTransaction.replace(R.id.fragment2, newPostsFrag).commit();


        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){

                    case R.id.action_home:
                        NewPostsFrag newPostsFrag =new NewPostsFrag();
                        fragmentTransaction.replace(R.id.fragment2, newPostsFrag);
                        break;
                    case R.id.action_search:
                        SearchMain searchMain = new SearchMain();
                        fragmentTransaction.replace(R.id.fragment2, searchMain);
                        break;
                    case R.id.action_add:
                        Intent intent = new Intent(MainActivity.this, AddActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_profile:
                        searchId = null;
                        ProfileActivity profileActivity = new ProfileActivity();
                        fragmentTransaction.replace(R.id.fragment2, profileActivity);
                        break;
                }
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            }
        });


    }
}
