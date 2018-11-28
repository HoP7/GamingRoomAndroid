package room.gaming.egamingroom.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import room.gaming.egamingroom.R;
import room.gaming.egamingroom.models.Transfer;

public class TransfersFragment extends Fragment {
   ListView listTransfers;
    private View addTransferButton;

    public static TransfersFragment newInstance() {
      return  new TransfersFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
  View view = inflater.inflate(R.layout.transfers_layout, container, false);

         listTransfers = view.findViewById(R.id.list_in);
         addTransferButton = view.findViewById(R.id.transfer_button_id);
         
         addTransferButton.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v) {
                 openAddTransferDialog();
             }
         });
  fillListData();
  return  view;
    }

    private void openAddTransferDialog() {
        TransfersAddFragment dlg = TransfersAddFragment.newInstance();
        FragmentManager fm = getActivity().getFragmentManager();
                dlg.show(fm, "add_transfer");
    }

    private void fillListData() {
         final List<Transfer> data = new ArrayList<Transfer>();
         Transfer t = new Transfer();
         t.Coins = 100;
         t.Ime ="Test";
        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);

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
                TextView nameParam = view.findViewById(R.id.transfer_name_id);
                TextView coinsParam = view.findViewById(R.id.transfer_coins_id);

                Transfer x = data.get(position);

                nameParam.setText("Kenan Gutic");
                coinsParam.setText("100");
                dateParam.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));

                return view;
            }
        });
    }
}
