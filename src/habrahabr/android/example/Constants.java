package habrahabr.android.example;

import android.os.Environment;

public class Constants {

	public static final String APP_PREFS_NAME = Constants.class.getPackage().getName();
	public static final String APP_CACHE_PATH = 
		Environment.getExternalStorageDirectory().getAbsolutePath() + 
		"/Android/data/" + APP_PREFS_NAME + "/cache/";
	
// localhost 	
//	public static final String APP_SOURCE_IMAGES_URL = "http://10.0.2.2/images/";
// GitHub project storage 	
	public static final String APP_SOURCE_IMAGES_URL = "https://github.com/Voldemar123/andriod-image-habrahabr-example/raw/master/res/drawable/";
	
// pictures names list	
	protected static final String IMAGE_NAMES[] = {
			"img1.jpg",
			"img2.jpg",
			"img3.jpg"
	}; 

}
