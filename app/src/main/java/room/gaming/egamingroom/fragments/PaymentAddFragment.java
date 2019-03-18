package room.gaming.egamingroom.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.DialogFragment;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import room.gaming.egamingroom.MainActivity;
import room.gaming.egamingroom.R;
import room.gaming.egamingroom.helper.MyApiRequest;
import room.gaming.egamingroom.helper.MyApp;
import room.gaming.egamingroom.helper.MyCallback;
import room.gaming.egamingroom.helper.MyRunniable;
import room.gaming.egamingroom.helper.MySession;
import room.gaming.egamingroom.models.AddCoinsDto;
import room.gaming.egamingroom.models.Payment;
import room.gaming.egamingroom.models.PaymentPlayGame;

@SuppressLint("ValidFragment")
public class PaymentAddFragment extends DialogFragment {
    private View txtUserCode;
    private View txtCoins;
    private View completeButton;
    MyRunniable myRunniable;
    @SuppressLint("ValidFragment")
    public PaymentAddFragment(MyRunniable myRunniable) {
        this.myRunniable = myRunniable;
    }

    public static PaymentAddFragment newInstance(MyRunniable myRunniable) {
      return  new PaymentAddFragment(myRunniable);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
View view = inflater.inflate(R.layout.payment_add_layout, container,false);

        TextView paymentCoins  = view.findViewById(R.id.payment_add_coins);
        Button complete = view.findViewById(R.id.payment_add_payment);

        complete.setOnClickListener(v -> {
            MyCallback<Payment> myCallback = new MyCallback<Payment>(new TypeToken<Payment>(){}.getType()) {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void Run(Payment x) {
                    MainActivity a = (MainActivity)MyApp.getCurrentActivity();
                    a.fillProfilInfo();
                    myRunniable.Run();
                    dismiss();
                }
            };
            AddCoinsDto payment = new AddCoinsDto();
            payment.Coins = Integer.parseInt(paymentCoins.getText().toString());
            payment.UserId = MySession.getUser().id;
            MyApiRequest.post(getActivity(), "api/Transaction/addcoins",payment, myCallback, false);
        });
return  view;
    }

}
