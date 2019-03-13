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
import android.widget.Button;
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
import room.gaming.egamingroom.models.LoginDto;
import room.gaming.egamingroom.models.Payment;
import room.gaming.egamingroom.models.PaymentPlayGame;
import room.gaming.egamingroom.models.User;

public class RegisterFragment extends Fragment {

    public static RegisterFragment newInstance() {
      return  new RegisterFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
  View view = inflater.inflate(R.layout.register_layout, container, false);


        TextView firstName = view.findViewById(R.id.register_firstname_id);
        TextView lastName = view.findViewById(R.id.register_lastname_id);
        TextView email = view.findViewById(R.id.register_email_id);
        TextView password = view.findViewById(R.id.register_password_id);
        TextView username = view.findViewById(R.id.register_username_id);
        Button register = view.findViewById(R.id.register_button_id);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                MyCallback<User> myCallback = new MyCallback<User>(User.class) {
                    @Override
                    public void Run(User x) {
                    }
                };
                User user = new User();
                user.firstName = firstName.getText().toString();
                user.lastName = lastName.getText().toString();
                user.email = email.getText().toString();
                user.username = username.getText().toString();
                user.password = password.getText().toString();
                MyApiRequest.post(getActivity(), "api/Account/register",user, myCallback, false);
            }
        });
  return  view;
    }
}
