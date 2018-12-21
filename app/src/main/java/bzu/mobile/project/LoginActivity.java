package bzu.mobile.project;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bzu.mobile.project.dbHandler.MyDBHandler;
import bzu.mobile.project.dbModels.User;

public class LoginActivity extends AppCompatActivity {

    EditText usernameBox;
    EditText passwordBox;

    public static String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameBox = (EditText) findViewById(R.id.LoginEmail);
        passwordBox = (EditText) findViewById(R.id.LoginPassword);
    }

    public void Login(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        String res = dbHandler.loadHandler();
        Toast.makeText(this, res, Toast.LENGTH_LONG).show();

        username = usernameBox.getText().toString();
        String password = passwordBox.getText().toString();

        User user = dbHandler.findUser(username);

        if(user != null) {
            if(!user.getUserPassword().equals(password)) {
                Toast.makeText(this, "Incorrect Password", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
        else {
            Toast.makeText(this, "Incorrect Username", Toast.LENGTH_LONG).show();
        }

    }

    public void CreateNewAccount(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

}
