package room.gaming.egamingroom;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import room.gaming.egamingroom.fragments.PaymentsFragment;
import room.gaming.egamingroom.fragments.ProfileFragment;
import room.gaming.egamingroom.fragments.TransfersFragment;
import room.gaming.egamingroom.helper.MyFragmentUtilities;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyFragmentUtilities.OpenAsReplace(this,TransfersFragment.newInstance());
    }


}
