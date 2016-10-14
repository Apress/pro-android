Spinner s2 = (Spinner) findViewById(R.id.spinner2);
adapter = ArrayAdapter.createFromResource(this,R.array.planets,android.R.layout.simple_spinner_item);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
s2.setAdapter(adapter);