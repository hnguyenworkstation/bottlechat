package com.onlineteammakers.bottlechat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.onlineteammakers.bottlechat.Intent.UserInformation;

public class FirstTimeUserRegisterActivity extends Activity {

    private static final String TAG = "Register!" ;
    private static UserInformation user;
    private static EditText _userName;
    private static EditText _userNickName;
    private static EditText _userEmail;
    private static EditText _userPhonenum;
    private static Button _signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_user_register);

        user = new UserInformation();

        _userName = (EditText) findViewById(R.id.input_name);
        _userNickName = (EditText) findViewById(R.id.input_nickname);
        _userPhonenum = (EditText) findViewById(R.id.input_phonenumber);
        _userEmail = (EditText) findViewById(R.id.input_email);
        _signUpBtn = (Button) findViewById(R.id.btn_signup);

        // Set input text color
        _userName.setTextColor(Color.BLACK);
        _userNickName.setTextColor(Color.BLACK);
        _userPhonenum.setTextColor(Color.BLACK);
        _userEmail.setTextColor(Color.BLACK);
        _userNickName.setTextColor(Color.BLACK);

        _signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Todo: Added SQL Link/Functions here to start storing user's information
                createUserProfile();
            }
        });
    }

    public void setAvailableInfor() {
        if (user.getUserName() != null)
            _userName.setText(user.getUserName());

        if (user.getUserEmail() != null)
            _userEmail.setText(user.getUserEmail());
    }

    public void createUserProfile() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onCreateFailed();
            return;
        }

        _signUpBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(FirstTimeUserRegisterActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _userName.getText().toString();
        String email = _userEmail.getText().toString();
        String phonenum = _userPhonenum.getText().toString();
        String nickname = _userNickName.getText().toString();

        // TODO: Implement Server Logic here

        final Handler handler = new android.os.Handler();
            handler.postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onCreateSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

        // Sending to MainActivity if User account is created successfully
        startActivity(new Intent(FirstTimeUserRegisterActivity.this, MainActivity.class));
    }

    public void onCreateSuccess() {
        _signUpBtn.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onCreateFailed() {
        Toast.makeText(getBaseContext(), "Failed to create user's profile", Toast.LENGTH_LONG).show();
        _signUpBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _userName.getText().toString();
        String email = _userEmail.getText().toString();
        String nickname = _userNickName.getText().toString();
        String phonenum = _userPhonenum.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _userName.setError("at least 3 characters");
            valid = false;
        } else {
            _userName.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _userEmail.setError("enter a valid email address");
            valid = false;
        } else {
            _userEmail.setError(null);
        }

        if (phonenum.isEmpty() || !Patterns.PHONE.matcher(phonenum).matches()) {
            _userPhonenum.setError("enter a valid email address");
            valid = false;
        } else {
            _userPhonenum.setError(null);
        }

        if (nickname.isEmpty() || nickname.length() < 4 || nickname.length() > 10) {
            _userNickName.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _userNickName.setError(null);
        }

        return valid;
    }
}
