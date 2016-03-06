package com.quanglh.englishtest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    final String EMAIL_VALIDATE_ERROR = "Email không hợp lệ";
    final String PASSWORD_VALIDATE_ERROR = "Mật khẩu phải có ít nhất 4 ký tự";

    EditText emailText;
    EditText passwordText;
    Button loginButton;
    TextView signupLink;
    TextView forgetpassLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText) findViewById(R.id.input_email);
        passwordText = (EditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        signupLink = (TextView) findViewById(R.id.link_signup);
        forgetpassLink = (TextView) findViewById(R.id.link_forgetpassword);

        //--Button Login click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    //Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                    loginButton.setEnabled(true);
                    return;
                }

                loginButton.setEnabled(false);
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Đang xác thực...");
                progressDialog.show();

                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                //onLoginSuccess();
                                // onLoginFailed();
                                loginButton.setEnabled(true);
                                progressDialog.dismiss();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(getBaseContext(), "Xin chào, abc!", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }, 1000);

            }
        });
        //--Link đăng ký click
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError(EMAIL_VALIDATE_ERROR);
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 ) {
            passwordText.setError(PASSWORD_VALIDATE_ERROR);
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
