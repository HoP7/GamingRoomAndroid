package room.gaming.egamingroom;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import room.gaming.egamingroom.helper.MyApiRequest;
import room.gaming.egamingroom.helper.MyApp;
import room.gaming.egamingroom.helper.MyCallback;
import room.gaming.egamingroom.helper.MySession;
import room.gaming.egamingroom.models.LoginDto;
import room.gaming.egamingroom.models.User;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUserName;
    private  EditText txtPassword;
    Activity currentActivity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
currentActivity = this;
        User user = MySession.getUser();
        if(user!=null) {
            startActivity(new Intent(this, MainActivity.class));
        }

        txtUserName = findViewById(R.id.login_username_id);
        txtPassword = findViewById(R.id.login_password_id);

        findViewById(R.id.login_button_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                    MyCallback<User> myCallback = new MyCallback<User>(User.class) {
                        @Override
                        public void Run(User x) {
                            login(x);
                        }
                    };
                LoginDto login = new LoginDto();
                login.Username = txtUserName.getText().toString();
                login.Password = txtPassword.getText().toString();

                MyApiRequest.post(currentActivity, "api/Account/login", login, myCallback, false);
            }
        });
        findViewById(R.id.login_creating_new_account).setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                openRegisterForm();
            }
        });
    }

    private void openRegisterForm() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void login(User user) {
            MySession.setUser(user);
            startActivity(new Intent(this, MainActivity.class));
    }
}
