package room.gaming.egamingroom.fragments;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import room.gaming.egamingroom.MainActivity;
import room.gaming.egamingroom.R;
import room.gaming.egamingroom.helper.MyApiRequest;
import room.gaming.egamingroom.helper.MyApp;
import room.gaming.egamingroom.helper.MyCallback;
import room.gaming.egamingroom.helper.MyRunniable;
import room.gaming.egamingroom.helper.MySession;
import room.gaming.egamingroom.models.AddCoinsDto;
import room.gaming.egamingroom.models.Payment;
import room.gaming.egamingroom.models.Transaction;
import room.gaming.egamingroom.models.TransactionDto;

@SuppressLint("ValidFragment")
public class PaymentStartPlayFragment extends DialogFragment {
    private View txtUserCode;
    private View txtCoins;
    private View completeButton;
public  MyRunniable myRunniable;
    public PaymentStartPlayFragment(MyRunniable myRunniable) {
        this.myRunniable = myRunniable;
    }

    public static PaymentStartPlayFragment newInstance(MyRunniable myRunniable) {
      return  new PaymentStartPlayFragment(myRunniable);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
View view = inflater.inflate(R.layout.payment_play_layout, container,false);
        TextView hours = view.findViewById(R.id.payment_hour_number);
        Button play = view.findViewById(R.id.payment_play_button);
        TextView code = view.findViewById(R.id.payment_play_code);

        play.setOnClickListener(v -> {
            MyCallback<String> myCallback = new MyCallback<String>(String.class) {
                @Override
                public void Run(String x) {
                    MainActivity a = (MainActivity)MyApp.getCurrentActivity();
                    a.fillProfilInfo();
                    myRunniable.Run();
                  code.setText("Generated code: " + x);
                }
            };
            TransactionDto transaction = new TransactionDto();
            transaction.Hours = Integer.parseInt(hours.getText().toString());
            transaction.UserId = MySession.getUser().id;
            MyApiRequest.post(getActivity(), "api/Transaction",transaction, myCallback, false);
        });

return  view;
    }

}
