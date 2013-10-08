package com.scure.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Bluetooth extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth);
		
		Intent BTDiscoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		startActivity(BTDiscoverableIntent);
		
		//Thread acceptThread = new BluetoothAcceptThread();
		//acceptThread.start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				Log.d("practice", "thread running");
			}
		}).start();
	}

}