package com.scure.bluetooth;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.util.Log;

public class BluetoothAcceptThread extends Thread {
	static private final String TAG = "Bluetooth Accept Thread";
	
	private final BluetoothServerSocket mmServerSocket;
	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	
	// BT members and constants
    private static final UUID BT_SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); // SPP UID
    private static final String NAME_INSECURE = "PhoneInfoAcc"; // Name for the SDP record when creating server socket
	
	public BluetoothAcceptThread(){
		// Use a temporary object that is later assigned to mmServerSocket,
		// because mmServerSocket is final
		BluetoothServerSocket tmp = null;
		try{
			// MY_UUID is the app's UUID string, also used by the client code
			tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME_INSECURE, BT_SPP_UUID);
		} catch (IOException e){}
		mmServerSocket = tmp;
	}
	
	public void run(){
		BluetoothSocket socket = null;
		// Keep listening until exception occurs
		while(true){
			try{
				Log.d(TAG, "Looking for a connection");
				socket = mmServerSocket.accept();
			} catch(IOException e){
				break;
			}
			// If a connection was accepted
			if(socket != null){
				// Do work to manage the connection (in a separate thread)
//				manageConnectedSocket(socket);
				Log.d(TAG, "CONNECTED");
				try {
					mmServerSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	//Will cancel the listening socket, and cause the thread to finish
	public void cancel(){
		try{
			mmServerSocket.close();
		} catch(IOException e){}
	}

}
