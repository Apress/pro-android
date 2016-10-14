import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				captureImage();
			}
		});
	}

	private void captureImage() {
		Intent intt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intt, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
			Intent inn = new Intent(Intent.ACTION_VIEW);
			inn.setData(data.getData());
			startActivity(inn);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}