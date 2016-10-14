package com.syh;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;

public class MainActivity extends Activity {
	private MediaRecorder recorder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	private void recordVideo() throws Exception {
		if (recorder != null) {
			recorder.stop();
			recorder.release();
		}
		recorder = new MediaRecorder();
		recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setVideoSize(176, 144);
		recorder.setVideoFrameRate(30);
		recorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setOutputFile("/sdcard/output.3gpp");
		recorder.prepare();
		recorder.start();
	}
}
