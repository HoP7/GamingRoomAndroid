package room.gaming.egamingroom;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import room.gaming.egamingroom.fragments.PaymentsFragment;
import room.gaming.egamingroom.fragments.ProfileFragment;
import room.gaming.egamingroom.fragments.TopPlayersFragment;
import room.gaming.egamingroom.fragments.TransfersFragment;
import room.gaming.egamingroom.helper.MyApiRequest;
import room.gaming.egamingroom.helper.MyApp;
import room.gaming.egamingroom.helper.MyCallback;
import room.gaming.egamingroom.helper.MyFragmentUtilities;
import room.gaming.egamingroom.helper.MySession;
import room.gaming.egamingroom.models.LoginDto;
import room.gaming.egamingroom.models.User;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView progressBarText;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private Activity currentActivity;
    TextView fullName;
    TextView coins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.setCurrentActivity(this);
        setContentView(R.layout.activity_main);
        currentActivity = this;

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
         dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        View header = nv.getHeaderView(0);
         fullName = header.findViewById(R.id.navmenu_name_id);
         coins = header.findViewById(R.id.navmenu_coins_id);
        progressBar = (ProgressBar) header.findViewById(R.id.progressBar);
        progressBarText = (TextView)header.findViewById(R.id.textView);
   fillProfilInfo();
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
                    case R.id.navmenu_top_players_id: {
                        MyFragmentUtilities.OpenAsReplace(currentActivity, TopPlayersFragment.newInstance());
                        break;
                    }
                    case R.id.navmenu_logout_id: {
                        MySession.setUser(null);
                        startActivity(new Intent(currentActivity, LoginActivity.class));
                    }
                }
return  true;
            }

        });


    }
    public void fillProfilInfo(){
        MyCallback<User> m = new MyCallback<User>(User.class) {
            @Override
            public void Run(User x) {
                fullName.setText(x.fullName);
                coins.setText(String.valueOf(x.coins) + " coins");
                progressBar.setProgress(x.addedFromLastBonus);
                progressBarText.setText(x.addedFromLastBonus + "/" + 500);
            }
        };

        MyApiRequest.get(currentActivity, "api/User/" + MySession.getUser().id, m, false);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}