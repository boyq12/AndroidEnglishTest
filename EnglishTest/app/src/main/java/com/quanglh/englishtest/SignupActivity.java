package com.quanglh.englishtest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {
    final String NAME_VALIDATE_ERROR = "Vui lòng nhập họ tên";
    final String EMAIL_VALIDATE_ERROR = "Email không hợp lệ";
    final String PASSWORD_VALIDATE_ERROR = "Mật khẩu phải có ít nhất 4 ký tự";
    final String REPASSWORD_VALIDATE_ERROR = "Mật khẩu nhập lại không khớp";

    EditText nameText;
    EditText emailText;
    EditText passwordText;
    EditText repasswordText;
    Button signupButton;
    TextView returnloginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameText = (EditText) findViewById(R.id.input_name);
        emailText = (EditText) findViewById(R.id.input_email);
        passwordText = (EditText) findViewById(R.id.input_password);
        repasswordText = (EditText) findViewById(R.id.input_repassword);
        signupButton = (Button) findViewById(R.id.btn_signup);
        returnloginLink = (TextView) findViewById(R.id.link_login);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    //Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                    signupButton.setEnabled(true);
                    return;
                }

                signupButton.setEnabled(false);
                final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Đang xử lý...");
                progressDialog.show();

                String namestr = nameText.getText().toString();
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                String repassword = repasswordText.getText().toString();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                //onLoginSuccess();
                                // onLoginFailed();
                                signupButton.setEnabled(true);
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }
        });

        returnloginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        String namestr = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String repassword = repasswordText.getText().toString();

        if (namestr.isEmpty()) {
            nameText.setError(NAME_VALIDATE_ERROR);
            valid = false;
        } else {
            nameText.setError(null);
        }

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

        if (!repassword.equals(password)) {
            repasswordText.setError(REPASSWORD_VALIDATE_ERROR);
            valid = false;
        } else {
            repasswordText.setError(null);
        }

        return valid;
    }
}
