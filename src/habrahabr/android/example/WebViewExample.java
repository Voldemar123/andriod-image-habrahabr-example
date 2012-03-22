package habrahabr.android.example;

import android.R.color;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewExample extends Activity {

	protected WebView webView;
	protected ImageUtil imageUtil;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example2);
	
		webView = (WebView) findViewById(R.id.webView1);

		webView.setBackgroundColor(color.black);
		
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);
    	
		webView.setPadding(0, 0, 0, 0);
		
		webView.setScrollbarFadingEnabled(true);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    	
		loadImage();
	}

	private void loadImage() {
		try {
			imageUtil = new ImageUtil( getApplicationContext() );
			imageUtil.checkIsContentExists();
			
			setContent();
			
		} catch (LocalizedException e) {
			
			Toast.makeText( getApplicationContext(),
       			 e.getString( getApplicationContext() ), 
       			 Toast.LENGTH_LONG)
       			 .show();
		}
	}
	
	protected void setContent() {
		String imgFile = imageUtil.getRandomImageFile();

		webView.loadUrl(imgFile);
	}
}
