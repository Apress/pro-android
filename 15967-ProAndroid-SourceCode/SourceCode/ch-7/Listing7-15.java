import java.util.ArrayList;
import java.util.List;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MappingOverlayActivity extends MapActivity {
	private MapView mapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		mapView = (MapView) findViewById(R.id.mapview);
		LinearLayout layout = (LinearLayout) findViewById(R.id.zoomCtrls);
		layout.addView(mapView.getZoomControls());
		mapView.setClickable(true);
		Drawable marker = getResources().getDrawable(R.drawable.mapmarker);
		marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker
				.getIntrinsicHeight());
		mapView.getOverlays().add(new InterestingLocations(marker));
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	class InterestingLocations extends ItemizedOverlay {
		private List<OverlayItem> locations = new ArrayList<OverlayItem>();
		private Drawable marker;

		public InterestingLocations(Drawable marker) {
			super(marker);
			this.marker = marker;
			// create locations of interest
			GeoPoint disneyMagicKingdom = new GeoPoint(
					(int) (28.418971 * 1000000), (int) (-81.581436 * 1000000));
			GeoPoint disneySevenLagoon = new GeoPoint(
					(int) (28.410067 * 1000000), (int) (-81.583699 * 1000000));
			locations.add(new OverlayItem(disneyMagicKingdom, "Magic Kingdom",
					"Magic Kingdom"));
			locations.add(new OverlayItem(disneySevenLagoon, "Seven Lagoon",
					"Seven Lagoon"));
			populate();
		}

		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) {
			super.draw(canvas, mapView, shadow);
			boundCenterBottom(marker);
		}

		@Override
		protected OverlayItem createItem(int i) {
			return locations.get(i);
		}

		@Override
		public int size() {
			return locations.size();
		}
	}
}