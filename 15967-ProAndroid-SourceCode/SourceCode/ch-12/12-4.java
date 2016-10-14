import android.app.Activity;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MediaScannerActivity extends Activity implements
		MediaScannerConnectionClient {
	private static final String SCAN_PATH = "/sdcard/";
	private static final String FILE_TYPE = "image/jpeg";
	private MediaScannerConnection conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan);
		Button scanBtn = (Button) findViewById(R.id.scanBtn);

		scanBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				startScan();
			}
		});
	}

	private void startScan() {
		if (conn != null) {
			conn.disconnect();
		}
		conn = new MediaScannerConnection(this, this);
		conn.connect();
	}

	@Override
	public void onMediaScannerConnected() {
		conn.scanFile(SCAN_PATH, FILE_TYPE);
	}

	@Override
	public void onScanCompleted(String path, Uri uri) {
		try {
			if (uri != null) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(uri);
				startActivity(intent);
			}
		} finally {
			conn.disconnect();
			conn = null;
		}
	}
}