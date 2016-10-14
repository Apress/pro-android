	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		ImageView img = new ImageView(this);
		imgsetImageResource(R.drawable.myimage);
		AbsoluteLayout al = new AbsoluteLayout(this);
		mContentView.addView(img, new AbsoluteLayout.LayoutParams(50, // width
				50, // height
				0, // left
				0)); // top
		setContentView(al);
	}