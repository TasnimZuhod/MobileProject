package bzu.mobile.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bzu.mobile.project.dbHandler.MyDBHandler;
import bzu.mobile.project.dbModels.User;

public class RegistrationActivity extends AppCompatActivity {

    EditText emailBox;
    EditText passwordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        emailBox = (EditText) findViewById(R.id.Email);
        passwordBox = (EditText) findViewById(R.id.Password);
    }

    public void LoginPage(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void Register(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        String email = emailBox.getText().toString();
        String password = passwordBox.getText().toString();
        User user = new User(email, password);
        dbHandler.addUser(user);
        emailBox.setText("");
        passwordBox.setText("");

//        String res = dbHandler.loadHandler();
//        Toast.makeText(this, res, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
