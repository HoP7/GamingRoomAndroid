package room.gaming.egamingroom.fragments;

import android.app.DialogFragment;
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

import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import room.gaming.egamingroom.R;
import room.gaming.egamingroom.helper.MyApiRequest;
import room.gaming.egamingroom.helper.MyCallback;
import room.gaming.egamingroom.helper.MySession;
import room.gaming.egamingroom.models.AddCoinsDto;
import room.gaming.egamingroom.models.Payment;
import room.gaming.egamingroom.models.Transfer;
import room.gaming.egamingroom.models.TransferDto;

public class TransfersAddFragment extends DialogFragment {
    private TextView txtUserCode;
    private TextView txtCoins;
    private View completeButton;

    public static TransfersAddFragment newInstance() {
      return  new TransfersAddFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
View view = inflater.inflate(R.layout.transfer_add_layout, container,false);

txtUserCode = view.findViewById(R.id.transfer_add_user_code);
txtCoins = view.findViewById(R.id.transfer_add_coins);

completeButton = view.findViewById(R.id.transfer_add_user_code);
completeButton.setOnClickListener(v -> do_completeTransferClick());

return  view;
    }

    private void do_completeTransferClick() {
        MyCallback<Transfer> myCallback = new MyCallback<Transfer>(new TypeToken<Transfer>(){}.getType()) {
            @Override
            public void Run(Transfer x) {
                dismiss();
            }
        };
        TransferDto transfer = new TransferDto();
        transfer.Coins = Integer.parseInt(txtCoins.getText().toString());
        transfer.UserCode = txtUserCode.getText().toString();
        MyApiRequest.post(getActivity(), "api/Transfer",transfer, myCallback, false);

    }
}
