package net.farnetwork.latihanespresso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText textUsername;
    EditText textPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadView();
        init();
    }

    private void loadView() {
        textUsername = findViewById(R.id.textUsername);
        textPassword = findViewById(R.id.textPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void init() {
        btnLogin.setOnClickListener(v -> {
            String username = textUsername.getText().toString();
            String password = textPassword.getText().toString();

            if (!validateLogin(username, password))
                return;

            doLogin(username, password);
        });
    }

    private void doLogin(String username, String password) {
        if (username.equals("user") && password.equals("password")) {
            Toast.makeText(
                    this,
                    getString(R.string.welcomeMessage, username),
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            Toast.makeText(this, R.string.wrongUsernamePassword, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateLogin(String username, String password) {
        boolean isValid = true;

        if (username.isEmpty()) {
            isValid = false;
            textUsername.setError(getString(R.string.emptyUsername));
            textUsername.setFocusable(true);
        }
        if (password.isEmpty()) {
            isValid = false;
            textPassword.setError(getString(R.string.emptyPassword));
        }

        return isValid;
    }
}
