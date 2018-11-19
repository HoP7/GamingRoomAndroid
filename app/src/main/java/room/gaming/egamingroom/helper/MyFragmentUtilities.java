package room.gaming.egamingroom.helper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import room.gaming.egamingroom.R;
import room.gaming.egamingroom.fragments.TransfersFragment;

public class MyFragmentUtilities {
    public static void  OpenAsReplace(Activity activity, Fragment transfersFragment) {
        FragmentManager fragmentManager =activity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_place_id, transfersFragment);
        fragmentTransaction.commit();
    }
}
