package room.gaming.egamingroom.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.app.FragmentManager;

import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import room.gaming.egamingroom.R;
import room.gaming.egamingroom.helper.MyApiRequest;
import room.gaming.egamingroom.helper.MyCallback;
import room.gaming.egamingroom.helper.MyRunniable;
import room.gaming.egamingroom.models.Payment;
import room.gaming.egamingroom.models.PaymentPlayGame;
import room.gaming.egamingroom.models.TopPlayerDto;
import room.gaming.egamingroom.models.Transfer;

public class PaymentsFragment extends Fragment {
   ListView listPayments;
    private View addCoinsButton;
    private View startPlayButton;
     ListView listPaymentsPlayGame;
    private TabLayout tabLayout;
  int currentTab = 0;
    public static PaymentsFragment newInstance() {
      return  new PaymentsFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
  View view = inflater.inflate(R.layout.payments_layout, container, false);
        listPayments = view.findViewById(R.id.list_payment_in);
        addCoinsButton = view.findViewById(R.id.payment_add_coins);
        startPlayButton = view.findViewById(R.id.payment_start_play);

        tabLayout = view.findViewById(R.id.tab_payment_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        currentTab = 0;
                        callPayments();
                        break;
                    case 1:
                        currentTab  = 1;
                     callPlays();
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        listPaymentsPlayGame = view.findViewById(R.id.payment_start_play_list_id);

        addCoinsButton.setOnClickListener(v -> {
            PaymentAddFragment dlg = PaymentAddFragment.newInstance(new MyRunniable(){
                @Override
                public void Run() {
                    callPayments();
                }
            });
            FragmentManager fm = getActivity().getFragmentManager();
            dlg.show(fm, "add_coins");
        });

        startPlayButton.setOnClickListener(v -> {
            PaymentStartPlayFragment dlg = PaymentStartPlayFragment.newInstance(new MyRunniable(){
                @Override
                public void Run() {
                    callPlays();
                }
            });
            FragmentManager fm = getActivity().getFragmentManager();
            dlg.show(fm, "start_play");
        });

 callPayments();
  return  view;
    }
    public void callPayments() {
        if(this.currentTab == 1)
            return;
        listPaymentsPlayGame.setAdapter(null);
        MyCallback<ArrayList<Payment>> myCallbackPayments = new MyCallback<ArrayList<Payment>>(new TypeToken<ArrayList<Payment>>(){}.getType()) {
            @Override
            public void Run(ArrayList<Payment> x) {
                fillListData(x);
            }
        };
        MyApiRequest.get(getActivity(), "api/Transaction/payments", myCallbackPayments, true);
    }
    public void callPlays(){
        if(this.currentTab == 0)
            return;
        listPayments.setAdapter(null);
        MyCallback<ArrayList<PaymentPlayGame>> myCallback = new MyCallback<ArrayList<PaymentPlayGame>>(new TypeToken<ArrayList<PaymentPlayGame>>(){}.getType()) {
            @Override
            public void Run(ArrayList<PaymentPlayGame> x) {
                fillPlayGameData(x);
            }
        };
        MyApiRequest.get(getActivity(), "api/Transaction", myCallback, true);
    }
    private void fillPlayGameData(List<PaymentPlayGame> data) {
        listPaymentsPlayGame.setAdapter(new BaseAdapter() {
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
                    view = inflater.inflate(R.layout.payment_item_start_play, parent, false);
                }
                TextView dateParam = view.findViewById(R.id.payment_start_play_date_id);
                TextView coinsParam = view.findViewById(R.id.payment_start_play_coins_id);
                TextView hoursParam = view.findViewById(R.id.payment_start_play_hours_id);
                TextView codeParam = view.findViewById(R.id.payment_start_play_code_id);

                PaymentPlayGame x = data.get(position);

                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(x.transactionDate);
                coinsParam.setText(String.valueOf(x.coins));
                dateParam.setText(date);
                codeParam.setText(String.valueOf(x.code));
                hoursParam.setText(String.valueOf(x.hours));
                return view;
            }
        });
    }
    private void fillListData(List<Payment> data) {
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

                coinsParam.setText(String.valueOf(x.coins));
                dateParam.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(x.date));

                return view;
            }
        });
    }
}
