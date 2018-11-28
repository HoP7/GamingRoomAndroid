package room.gaming.egamingroom.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import room.gaming.egamingroom.R;

public class PaymentStartPlayFragment extends DialogFragment {
    private View txtUserCode;
    private View txtCoins;
    private View completeButton;

    public static PaymentStartPlayFragment newInstance() {
      return  new PaymentStartPlayFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
View view = inflater.inflate(R.layout.payment_play_layout, container,false);
return  view;
    }

}
