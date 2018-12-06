package room.gaming.egamingroom.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import room.gaming.egamingroom.R;
import room.gaming.egamingroom.models.Payment;
import room.gaming.egamingroom.models.PaymentPlayGame;

public class RegisterFragment extends Fragment {

    public static RegisterFragment newInstance() {
      return  new RegisterFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
  View view = inflater.inflate(R.layout.register_layout, container, false);

  return  view;
    }
}
