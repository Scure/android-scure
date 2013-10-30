package com.scure.login;

import com.scure.bluetooth.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

public class RegisterActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        
        findViewById(R.id.button_yes).setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View view) {
        		Intent i = new Intent(getApplicationContext(), RegisterDeviceActivity.class);
            	// Close all views before launching
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                // close registration screen
                finish();
        	}
        });
        
        findViewById(R.id.button_no).setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View view) {
        		Intent i = new Intent(getApplicationContext(), RegisterInfoActivity.class);
            	// Close all views before launching
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                // close registration screen
                finish();
        	}
        });
        
        findViewById(R.id.button_go_to_login).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            	// Close all views before launching
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                // close registration screen
                finish();
			}
        });
	}

}
