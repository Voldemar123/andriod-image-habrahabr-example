package habrahabr.android.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class SimpleImageViewExample extends Activity {

	ImageView mImageView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example1);
	
		mImageView = (ImageView) findViewById(R.id.imageView1);
		
// How-to initialize image view content, 3 ways
// https://developer.android.com/reference/android/widget/ImageView.html		
// 1. from drawable
// 2. from bitmap		
// 3. from specified Uri		
		
		drawFromRecource();
		drawFromSDCard();
	}

	/**
	 * Fill image from resources
	 */
	private void drawFromRecource() {
		mImageView.setImageResource(R.drawable.img3);
	}
	
/**
 * Fill image from picture stored on SD card	
 */
	private void drawFromSDCard() {
		try {
			ImageUtil util = new ImageUtil( getApplicationContext() );
			util.checkIsContentExists();
			
			String imgName = util.getRandomImage();

			mImageView.setImageBitmap( util.getImageBitmap(imgName) );
			mImageView.setImageURI( util.getImageURI(imgName) );
			
		} catch (LocalizedException e) {
			
			Toast.makeText( getApplicationContext(),
       			 e.getString( getApplicationContext() ), 
       			 Toast.LENGTH_LONG)
       			 .show();
		}
		
	}

}
