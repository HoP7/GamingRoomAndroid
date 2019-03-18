package room.gaming.egamingroom.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import room.gaming.egamingroom.R;
import room.gaming.egamingroom.helper.MyApiRequest;
import room.gaming.egamingroom.helper.MyCallback;
import room.gaming.egamingroom.models.PaymentPlayGame;
import room.gaming.egamingroom.models.Transfer;
import room.gaming.egamingroom.models.User;

public class TransfersFragment extends Fragment {
   ListView listInTransfers;
    private FloatingActionButton addTransferButton;
    private ListView listTransfers;
    private TabLayout tabLayout;

    public static TransfersFragment newInstance() {
      return  new TransfersFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
  View view = inflater.inflate(R.layout.transfers_layout, container, false);

         listTransfers = view.findViewById(R.id.transfer_list_id);
         addTransferButton = view.findViewById(R.id.transfer_button_id);
         tabLayout = view.findViewById(R.id.tab_layout);

         addTransferButton.setOnClickListener(v -> openAddTransferDialog());
        MyCallback<ArrayList<Transfer>> myCallback = new MyCallback<ArrayList<Transfer>>(new TypeToken<ArrayList<Transfer>>(){}.getType()) {
            @Override
            public void Run(ArrayList<Transfer> x) {
                fillListInData(x);
            }
        };
        MyApiRequest.get(getActivity(), "api/Transfer/In", myCallback, true);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        listTransfers.setAdapter(null);
                        MyCallback<ArrayList<Transfer>> myCallback = new MyCallback<ArrayList<Transfer>>(new TypeToken<ArrayList<Transfer>>(){}.getType()) {
                            @Override
                            public void Run(ArrayList<Transfer> x) {
                                fillListInData(x);
                            }
                        };
                        MyApiRequest.get(getActivity(), "api/Transfer/In", myCallback, true);
                        break;
                    case 1:
                        listTransfers.setAdapter(null);
                        MyCallback<ArrayList<Transfer>> callback = new MyCallback<ArrayList<Transfer>>(new TypeToken<ArrayList<Transfer>>(){}.getType()) {
                            @Override
                            public void Run(ArrayList<Transfer> x) {
                                fillListInData(x);
                            }
                        };
                        MyApiRequest.get(getActivity(), "api/Transfer/Out", callback, true);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
  return  view;
    }

    private void openAddTransferDialog() {
        TransfersAddFragment dlg = TransfersAddFragment.newInstance();
        FragmentManager fm = getActivity().getFragmentManager();
                dlg.show(fm, "add_transfer");
    }

    private void fillListInData(List<Transfer> data) {
        listTransfers.setAdapter(new BaseAdapter() {
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
                  view = inflater.inflate(R.layout.transfer_item, parent, false);
              }
                TextView dateParam = view.findViewById(R.id.transfer_date_id);
                TextView senderName = view.findViewById(R.id.transfer_sender_name_id);
                TextView receiverName = view.findViewById(R.id.transfer_receiver_name_id);
                TextView coinsParam = view.findViewById(R.id.transfer_coins_id);

                Transfer x = data.get(position);

                coinsParam.setText(String.valueOf(x.coins));
                dateParam.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(x.transferDate));
               senderName.setText(x.sender.fullName);
               receiverName.setText(x.receiver.fullName);
                return view;
            }
        });
    }
}
