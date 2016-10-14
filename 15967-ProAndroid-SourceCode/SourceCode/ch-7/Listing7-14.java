public class MapViewDemoActivity extends MapActivity {
	private MapView mapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		mapView = (MapView) findViewById(R.id.mapview);
		LinearLayout layout = (LinearLayout) findViewById(R.id.zoomCtrls);
		layout.addView(mapView.getZoomControls());
		mapView.setClickable(true);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}