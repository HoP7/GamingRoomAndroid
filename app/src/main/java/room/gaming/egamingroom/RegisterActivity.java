package room.gaming.egamingroom;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import room.gaming.egamingroom.helper.MyApiRequest;
import room.gaming.egamingroom.helper.MyCallback;
import room.gaming.egamingroom.models.User;

public class RegisterActivity extends AppCompatActivity {

    Activity currentActivity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        View view = findViewById(R.id.register_layout_id);
         currentActivity = this;
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
                        startActivity(new Intent(currentActivity, LoginActivity.class));
                    }
                };
                User user = new User();
                user.firstName = firstName.getText().toString();
                user.lastName = lastName.getText().toString();
                user.email = email.getText().toString();
                user.username = username.getText().toString();
                user.password = password.getText().toString();
                MyApiRequest.post(currentActivity, "api/User",user, myCallback, false);
            }
        });
    }
}
