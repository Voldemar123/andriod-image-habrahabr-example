package habrahabr.android.example;

import android.R.color;
import android.view.View;
import android.webkit.WebView;

public class WebViewExample extends SimpleImageViewExample {

	protected WebView webView;

	protected void setUpUI() {
		setContentView(R.layout.example2);
		
		webView = (WebView) findViewById(R.id.webView1);

		webView.setBackgroundColor(color.black);
		
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);
    	
		webView.setPadding(0, 0, 0, 0);
		
		webView.setScrollbarFadingEnabled(true);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
	}
	
	protected void setContent() {
		String imgFileLink = imageUtil.getImageFileLink();

		webView.loadUrl(imgFileLink);
	}
}
