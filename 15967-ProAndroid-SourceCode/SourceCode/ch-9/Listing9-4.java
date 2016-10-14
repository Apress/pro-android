@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.video);
		videoView = (VideoView) this.findViewById(R.id.videoView);
		MediaController mc = new MediaController(this);
		videoView.setMediaController(mc);
		videoView.setVideoURI(Uri
				.parse("http://sayedhashimi.com/downloads/android/movie.mp4"));
		// videoView.setVideoURI(Uri.parse("file:///sdcard/movie.mp4"));
		videoView.requestFocus();
	}