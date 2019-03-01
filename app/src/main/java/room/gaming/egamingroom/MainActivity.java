package room.gaming.egamingroom;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import room.gaming.egamingroom.fragments.PaymentsFragment;
import room.gaming.egamingroom.fragments.ProfileFragment;
import room.gaming.egamingroom.fragments.TransfersFragment;
import room.gaming.egamingroom.helper.MyFragmentUtilities;
import room.gaming.egamingroom.helper.MySession;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private Activity currentActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentActivity = this;
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch(id)
                {
                    case R.id.navmenu_profil_id:{
                        MyFragmentUtilities.OpenAsReplace(currentActivity,ProfileFragment.newInstance());
break;
                    }
                    case R.id.navmenu_transfers_id: {
                        MyFragmentUtilities.OpenAsReplace(currentActivity, TransfersFragment.newInstance());
                    break;
                    }
                    case R.id.navmenu_transactions_id: {
                        MyFragmentUtilities.OpenAsReplace(currentActivity, PaymentsFragment.newInstance());
                    break;}
                    case R.id.navmenu_logout_id: {
                        MySession.setUser(currentActivity, null);
                        startActivity(new Intent(currentActivity, LoginActivity.class));
                    }
                }
return  true;
            }

        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}