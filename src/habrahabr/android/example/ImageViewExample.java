package habrahabr.android.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ImageViewExample extends Activity {

	ListView mListView;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		String[] exampleItems = getResources().getStringArray(R.array.example_items);

		mListView = (ListView) findViewById(R.id.menu);
		
		mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exampleItems));
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
				switch (position) {
				case 0:
					startActivity(new Intent(ImageViewExample.this, SimpleImageViewExample.class));
					break;
				case 1:
					startActivity(new Intent(ImageViewExample.this, WebViewExample.class));
					break;
				case 2:
					startActivity(new Intent(ImageViewExample.this, WebViewOrientationExample.class));
					break;
				}
			}
		});
    }
}