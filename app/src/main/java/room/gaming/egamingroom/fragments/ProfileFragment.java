package room.gaming.egamingroom.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import room.gaming.egamingroom.models.Payment;
import room.gaming.egamingroom.models.User;

public class ProfileFragment extends Fragment {
   ListView listPayments;
    public static ProfileFragment newInstance() {
      return  new ProfileFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
  View view = inflater.inflate(R.layout.profile_layout, container, false);

        TextView firstName = view.findViewById(R.id.profile_firstname_id);
        TextView lastName = view.findViewById(R.id.profile_lastname_id);
        TextView email = view.findViewById(R.id.profile_email_id);
        TextView password = view.findViewById(R.id.profile_password_id);
        TextView code = view.findViewById(R.id.generated_code_text);
        Button save = view.findViewById(R.id.profile_button_id);
        Button btnGenerateCode = view.findViewById(R.id.profile_generate_code_button);

       User currentUser = MySession.getUser();
        firstName.setText(currentUser.firstName);
        lastName.setText(currentUser.lastName);
        email.setText(currentUser.email);
        password.setText("");

        btnGenerateCode.setOnClickListener(v -> {
    MyCallback<String> myCallback = new MyCallback<String>(new TypeToken<String>(){}.getType()) {
        @Override
        public void Run(String  x) {
            code.setText("Generated code: " + x);
        }
    };
    MyApiRequest.get(getActivity(), "api/Code/generateCode", myCallback, false);
});

save.setOnClickListener(v -> {
    MyCallback<User> myCallback = new MyCallback<User>(new TypeToken<User>(){}.getType()) {
        @Override
        public void Run(User  x) {
            Snackbar.make(view, "User is updated", Snackbar.LENGTH_SHORT).show();
        }
    };
    User user = new User();
    user.id  = MySession.getUser().id;
    user.firstName = firstName.getText().toString();
    user.lastName = lastName.getText().toString();
    user.email = email.getText().toString();
    user.password = password.getText().toString();

    MyApiRequest.put(getActivity(), "api/User", user, myCallback, false);
});
  return  view;
    }
}
