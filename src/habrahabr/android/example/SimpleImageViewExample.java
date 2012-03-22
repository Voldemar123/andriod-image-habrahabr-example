package habrahabr.android.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class SimpleImageViewExample extends Activity {

	private ImageView mImageView;
	protected ImageUtil imageUtil;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		loadImage();
	}

	private void loadImage() {
		try {
			imageUtil = new ImageUtil( getApplicationContext() );
			imageUtil.checkIsContentExists();
			imageUtil.selectRandomImage();
			
			setUpUI();
			setContent();
			
		} catch (LocalizedException e) {
			
			Toast.makeText( getApplicationContext(),
       			 e.getString( getApplicationContext() ), 
       			 Toast.LENGTH_LONG)
       			 .show();
		}
	}
	
	protected void setUpUI() {
		setContentView(R.layout.example1);
		
		mImageView = (ImageView) findViewById(R.id.imageView1);
	}
	
	protected void setContent() {
		// How-to initialize image view content, 3 ways
		// https://developer.android.com/reference/android/widget/ImageView.html		
		// 1. from drawable
		// 2. from bitmap		
		// 3. from specified Uri		
		
		mImageView.setImageResource(R.drawable.img3);
		
// Fill image from picture stored on SD card	

		mImageView.setImageBitmap( imageUtil.getImageBitmap() );
		mImageView.setImageURI( imageUtil.getImageURI() );
	}
	
}
