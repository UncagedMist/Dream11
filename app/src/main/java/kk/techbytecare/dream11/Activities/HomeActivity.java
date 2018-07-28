package kk.techbytecare.dream11.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import kk.techbytecare.dream11.Fragments.HomeFragment;
import kk.techbytecare.dream11.Fragments.MoreFragment;
import kk.techbytecare.dream11.Fragments.MyContestFragment;
import kk.techbytecare.dream11.Fragments.ProfileFragment;
import kk.techbytecare.dream11.Helper.BottomNavigationViewHelper;
import kk.techbytecare.dream11.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigationView);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId())   {

                    case R.id.action_home :
                        selectedFragment = HomeFragment.getInstance();
                        break;

                    case  R.id.action_contests :
                        selectedFragment = MyContestFragment.getInstance();
                        break;

                    case R.id.action_me :
                        selectedFragment = ProfileFragment.getInstance();
                        break;

                    case R.id.action_more :
                        selectedFragment = MoreFragment.getInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame, selectedFragment);
                transaction.commit();
                return false;
            }
        });
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, HomeFragment.getInstance());
        transaction.commit();
    }
}
