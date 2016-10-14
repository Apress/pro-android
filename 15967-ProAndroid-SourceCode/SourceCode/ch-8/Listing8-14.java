package com.sayed;

import com.syh.IStockQuoteService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private IStockQuoteService stockService = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button bindBtn = (Button) findViewById(R.id.bindBtn);
		bindBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				bindService(new Intent(IStockQuoteService.class.getName()),
						serConn, Context.BIND_AUTO_CREATE);
			}
		});
		Button unbindBtn = (Button) findViewById(R.id.unbindBtn);
		unbindBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				unbindService(serConn);
			}
		});
	}

	private ServiceConnection serConn = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			stockService = IStockQuoteService.Stub.asInterface(service);
			double val;
			try {
				val = stockService.getQuote("syh");
				Toast
						.makeText(MainActivity.this,
								"Value from service is " + val + "",
								Toast.LENGTH_SHORT).show();
			} catch (RemoteException ee) {
				Log.e("MainActivity", ee.getMessage(), ee);
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
		}
	};
}