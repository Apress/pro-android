import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MapViewDemoActivity extends MapActivity {
	private MapView mapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		mapView = (MapView) findViewById(R.id.mapview);
		Button zoominBtn = (Button) findViewById(R.id.zoomin);
		Button zoomoutBtn = (Button) findViewById(R.id.zoomout);
		Button satBtn = (Button) findViewById(R.id.sat);
		Button streetBtn = (Button) findViewById(R.id.street);
		Button trafficBtn = (Button) findViewById(R.id.traffic);
		// zoomin
		zoominBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mapView.getController().zoomIn();
			}
		});
		// zoom out
		zoomoutBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mapView.getController().zoomOut();
			}
		});
		// satellite
		satBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mapView.setStreetView(false);
				mapView.setTraffic(false);
				mapView.setSatellite(true);
			}
		});
		// street
		streetBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mapView.setTraffic(false);
				mapView.setSatellite(false);
				mapView.setStreetView(true);
			}
		});
		// traffic
		trafficBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mapView.setSatellite(false);
				mapView.setStreetView(false);
				mapView.setTraffic(true);
			}
		});
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}