package com.scure.login;

import com.scure.bluetooth.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;

public class RegisterDeviceActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_device);
        
        findViewById(R.id.button_next).setOnClickListener(new OnClickListener(){
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
	}

}
