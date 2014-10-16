package jp.kanagawa.kawasaki.lunchplaces.test;

import jp.kanagawa.kawasaki.lunchplaces.MainActivity;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	
	public MainActivityTest() {
		super("jp.kanagawa.kawasaki.lunchplaces", MainActivity.class);
	}

	private TextView view;
	private String resourceString;
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        Activity activity = getActivity();
        view = (TextView) activity.findViewById(jp.kanagawa.kawasaki.lunchplaces.R.id.textView1);
        resourceString = activity.getString(jp.kanagawa.kawasaki.lunchplaces.R.string.hello_world);
	}
	
	public void testText() throws Exception {
        assertNotNull(resourceString, view.getText().toString());
    }
}
