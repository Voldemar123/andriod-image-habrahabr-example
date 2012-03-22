package habrahabr.android.example;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

	private static Context context;

	public void onCreate() {
		MainApplication.context = getApplicationContext();
	}

	public static Context getContext() {
		return context;
	}

}