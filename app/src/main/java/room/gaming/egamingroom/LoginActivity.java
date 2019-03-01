package room.gaming.egamingroom;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import room.gaming.egamingroom.helper.MySession;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUserName;
    private  EditText txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        String user = MySession.getUser(this);
        if(user!=null) {
            startActivity(new Intent(this, MainActivity.class));
        }

        txtUserName = findViewById(R.id.login_username_id);
        txtPassword = findViewById(R.id.login_password_id);

        findViewById(R.id.login_button_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                login();
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

    private void login() {
        String strUserName = txtUserName.getText().toString();
        String strPassword = txtPassword.getText().toString();

        if(!strUserName.equals("admin")) {
            View parentLayout = findViewById(R.id.content_id);
            Snackbar.make(parentLayout, "Pogrešan username/password", Snackbar.LENGTH_LONG).show();
        } else {
            MySession.setUser(this,"admin");
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
