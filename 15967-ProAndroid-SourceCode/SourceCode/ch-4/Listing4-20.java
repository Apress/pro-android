protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		GridView gv = (GridView) this.findViewById(R.id.dataGrid);
		Cursor c = getContentResolver().query(People.CONTENT_URI, null, null,
				null, null);
		startManagingCursor(c);
		String[] cols = new String[] { People.NAME };
		int[] names = new int[] { R.id.grid_entry };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.grid_item, c, cols, names);
		gv.setAdapter(adapter);
	}