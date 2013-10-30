package com.scure.login;

import org.json.JSONException;
import org.json.JSONObject;

import com.scure.bluetooth.R;
import com.scure.library.DatabaseHandler;
import com.scure.library.UserFunctions;
import com.scure.login.LoginActivity.UserLoginTask;
import com.scure.main.HomeActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterInfoActivity extends Activity{
	/**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello",
            "bar@example.com:world"
    };
	
	/**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserRegTask mAuthTask = null;
	
	// Values for mEmail, mPassword, and mConfirmPassword at the time of the login attempt.
    private String mEmail, mPassword, mConfirmPassword;
	
	// UI references
	private EditText mEmailView, mPasswordView, mConfirmPasswordView;
	private View mRegFormView, mRegStatusView;
	private TextView mRegStatusMessageView;
	
	// JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "mEmail";
    private static String KEY_CREATED_AT = "created_at";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register_info);
		
        // Set up the registration form.
		mEmailView = (EditText) findViewById(R.id.reg_email);
		mPasswordView = (EditText) findViewById(R.id.reg_password);
		mConfirmPasswordView = (EditText) findViewById(R.id.reg_confirm_password);
		
		mRegFormView = findViewById(R.id.reg_info_form);
		mRegStatusView = findViewById(R.id.reg_status);
		mRegStatusMessageView = (TextView) findViewById(R.id.reg_status_message);
		
		findViewById(R.id.button_register).setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View view) {
        		attemptReg();
        	}
        });
	}
		
	public void attemptReg() {
		if (mAuthTask != null) {
			return;
		}
		
		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        mEmail = mEmailView.getText().toString();
        mPassword = mPasswordView.getText().toString();
        mConfirmPassword = mConfirmPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

      //Check for a valid mConfirmPassword
        if (TextUtils.isEmpty(mConfirmPassword)) {
        	mConfirmPasswordView.setError(getString(R.string.error_field_required));
        	focusView = mConfirmPasswordView;
        	cancel = true;
        }else if (!mPassword.equals(mConfirmPassword)) {
        	mConfirmPasswordView.setError(getString(R.string.error_password_mismatch));
        	focusView = mConfirmPasswordView;
        	cancel = true;
        }

        //Check for a valid mPassword.
        if (TextUtils.isEmpty(mPassword)) {
        	mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (mPassword.length() < 4) {
        	mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid mEmail address.
        if (TextUtils.isEmpty(mEmail)) {
        mEmailView.setError(getString(R.string.error_field_required));
        focusView = mEmailView;
        cancel = true;
        } else if (!mEmail.contains("@")) {
        	mEmailView.setError(getString(R.string.error_invalid_email));
        	focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
        	// There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
        	// Show a progress spinner, and kick off a background task to
        	// perform the user login attempt.
        	mRegStatusMessageView.setText(R.string.login_progress_logging_in);
        	showProgress(true);
        	mAuthTask = new UserRegTask();
        	mAuthTask.execute((Void) null);
        }
	}
	
	/**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegStatusView.setVisibility(View.VISIBLE);
            mRegStatusView.animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mRegStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
                        }
                    });

            mRegFormView.setVisibility(View.VISIBLE);
            mRegFormView.animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 0 : 1)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mRegFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                        }
                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mRegStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    
    /**
     * Represents an asynchronous registration task used to authenticate
     * the user.
     */
    public class UserRegTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
/*
        	String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();
            UserFunctions userFunction = new UserFunctions();
            JSONObject json = userFunction.loginUser(email, password);

            // check for reg response
            try {
            	if (json.getString(KEY_SUCCESS) != null) {
                   	String res = json.getString(KEY_SUCCESS); 
                    if(Integer.parseInt(res) == 1){
                      	// user successfully registered
                        // Store user details in SQLite Database
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        JSONObject json_user = json.getJSONObject("user");
                                 
                        // Clear all previous data in database
                        userFunction.logoutUser(getApplicationContext());
                        db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));                        
                        // Launch Home Screen
                        Intent dashboard = new Intent(getApplicationContext(), HomeActivity.class);
                        // Close all views before launching Home
                        dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(dashboard);
                        // Close Registration Screen
                        finish();
                    }else{
                       	// Error in registration
                    }
                }
            } catch (JSONException e) {
               	e.printStackTrace();
            }*/
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
            	Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            	// Close all views before launching
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            	// close login screen
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
