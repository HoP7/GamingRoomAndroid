package room.gaming.egamingroom.fragments;

import android.app.Fragment;
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
import room.gaming.egamingroom.models.Transfer;

public class PaymentsFragment extends Fragment {
   ListView listPayments;
    public static PaymentsFragment newInstance() {
      return  new PaymentsFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
  View view = inflater.inflate(R.layout.payments_layout, container, false);

        listPayments = view.findViewById(R.id.list_payment_in);
  fillListData();
  return  view;
    }

    private void fillListData() {
         final List<Payment> data = new ArrayList<Payment>();
        Payment payment = new Payment();
         payment.Coins = 100;
        data.add(payment);
        data.add(payment);
        data.add(payment);
        data.add(payment);
        data.add(payment);
        data.add(payment);
        data.add(payment);
        data.add(payment);
        data.add(payment);
        data.add(payment);

        listPayments.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
              if(view == null) {
                  LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                  view = inflater.inflate(R.layout.payment_item, parent, false);
              }
                TextView dateParam = view.findViewById(R.id.transfer_payment_date_id);
                TextView coinsParam = view.findViewById(R.id.transfer_payment_coins_id);

                Payment x = data.get(position);

                coinsParam.setText("100");
                dateParam.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));

                return view;
            }
        });
    }
}
