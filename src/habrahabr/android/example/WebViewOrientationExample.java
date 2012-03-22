package habrahabr.android.example;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class WebViewOrientationExample extends WebViewExample {

	private static final String TAG = "WebViewOrientationExample";
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Log.i(TAG, "Configuration changed " + newConfig.orientation);
		
	    super.onConfigurationChanged(newConfig);

	    setContent();
	}	

	@Override
	protected void setContent() {
    	Log.d(TAG, "Scale picture depend device orientation");
    	
    	Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    	
    	int width = display.getWidth(); 
        int height = display.getHeight(); 

        Log.d(TAG, "Display width " + width);
        Log.d(TAG, "Display height " + height);

    	String imgName = imageUtil.getRandomImage();
    	Bitmap img = imageUtil.getImageBitmap(imgName);
    	
        int picWidth = img.getWidth();
        int picHeight = img.getHeight();
        
        Log.d(TAG, "Picture width " + picWidth);
        Log.d(TAG, "Picture height " + picHeight);

// scale web view depend of rotate device        
        Double val = 1d;

		if (picHeight > height)
			val = new Double(height) / new Double(picHeight);
        
    	val = val * 100d;
    	Log.i(TAG, "Scale to " + val);
    
    	webView.setInitialScale( val.intValue() );
		webView.loadDataWithBaseURL("/", 
				imageUtil.getImageHtml(imgName, picWidth, picHeight), 
				"text/html", 
				"UTF-8", 
				null);
    }
	

}
