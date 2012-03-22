package habrahabr.android.example;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

/**
 * Base application image working
 * 
 * @author VMaximenko
 * 
 */
public class ImageUtil {

	private final String TAG = "ImageUtil";

	protected String mObjectPathName = Constants.APP_CACHE_PATH;

	private boolean mExternalStorageAvailable;
	private boolean mExternalStorageWriteable;

	private Context mContext;
	
	private String image;

	public ImageUtil(Context context) {
		super();

		mContext = context;
	}

	private void checkExternalStorage() throws LocalizedException {
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// We can read and write the media
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// We can only read the media
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			// Something else is wrong. It may be one of many other states, but
			// all we need
			// to know is we can neither read nor write
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}

		if (!mExternalStorageAvailable || !mExternalStorageWriteable)
			throw new LocalizedException(R.string.error_external_storage);
	}

	/**
	 * Check exist path to stored object
	 */
	private void checkObjectPath() {
		File file = new File(mObjectPathName);
		file.mkdirs();
	}

	// Check for existence of source images, download the missing, if necessary
	public void checkIsContentExists() throws LocalizedException {
		Log.i(TAG, "Check source images existence");

		checkExternalStorage();
		checkObjectPath();

		for (String imageName : Constants.IMAGE_NAMES) {
			if (!isImageExists(imageName))
				downloadImage(imageName);
		}
	}

	// Check existence of image in application store
	private boolean isImageExists(String imageName) {
		Log.d(TAG, "Check image " + imageName);

		File file = new File(Constants.APP_CACHE_PATH + imageName);
		if (!file.exists())
			Log.w(TAG, "Image " + imageName + " not exists");

		return file.exists();
	}

	// download source image and save it to the application resources
	private void downloadImage(String imageName) throws LocalizedException {
		Log.d(TAG, "Download image " + imageName);

		if (!checkInternetConnection()) {
			Log.e(TAG, "Internet connection not present");
			throw new LocalizedException(R.string.error_connection_not_present);
		}

		File file = new File(Constants.APP_CACHE_PATH + imageName);

		HttpURLConnection conn = null;

		try {
			URL url = new URL(Constants.APP_SOURCE_IMAGES_URL + imageName);
			Log.d(TAG, "Url " + url.toString());

			conn = (HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK)
				throw new LocalizedException(R.string.error_connection_status);

			InputStream input = new BufferedInputStream(conn.getInputStream());
			OutputStream output = new FileOutputStream(file);

			byte data[] = new byte[1024];
			int count = 0;

			while ((count = input.read(data)) != -1)
				output.write(data, 0, count);

			output.flush();
			output.close();

			input.close();

		} catch (IOException e) {
			Log.e(TAG, "Connection error : " + e.getMessage(), e);
			throw new LocalizedException(R.string.error_connection,
					e.getMessage());
		} finally {
			if (conn != null)
				conn.disconnect();
		}
	}

	private boolean checkInternetConnection() {
		Log.d(TAG, "Test for connection ");

		ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getActiveNetworkInfo() != null && 
				cm.getActiveNetworkInfo().isAvailable() && 
				cm.getActiveNetworkInfo().isConnected());
	}

	// get picture bitmap
	public Bitmap getImageBitmap() {
		Log.d(TAG, "Get picture bitmap " + this.image);

		try {
			FileInputStream fis = new FileInputStream(Constants.APP_CACHE_PATH + this.image);
			BufferedInputStream bis = new BufferedInputStream(fis);

			Bitmap img = BitmapFactory.decodeStream(bis);

			bis.close();
			fis.close();

			return img;

		} catch (IOException e) {
			Log.e(TAG, "Picture load problem : " + e.getMessage(), e);
		}

		return null;
	}

	// get picture URI
	public Uri getImageURI() {
		Log.d(TAG, "Get picture URI " + this.image);

		return Uri.fromFile( new File( Constants.APP_CACHE_PATH + this.image ) );
	}

// get random image from predefined list	
	private String getRandomImage() {
		return Constants.IMAGE_NAMES[ new Random().nextInt( Constants.IMAGE_NAMES.length ) ];
	}

// get image file	
	public String getImageFileLink() {
		return "file:///" + Constants.APP_CACHE_PATH + this.image;
	}

// get image content HTML document 	
	public String getImageHtml(int width, int height) {
		Log.d(TAG, "Get picture HTML " + this.image);
		
		StringBuffer html = new StringBuffer();
		
		html.append("<html><body style='margin:0;padding:0'>");
		html.append("<img src='");
		html.append("file:///");
		html.append(Constants.APP_CACHE_PATH);
		html.append(this.image);
		html.append("' width='");
		html.append(width);
		html.append("' height='");
		html.append(height);
		html.append("'></body></html>");
		
		return html.toString();
	}

	public void selectRandomImage() {
		this.image = getRandomImage();
	}
	
}
