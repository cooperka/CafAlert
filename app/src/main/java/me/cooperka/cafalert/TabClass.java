package me.cooperka.cafalert;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class TabClass extends TabActivity
{
	public TabHost tabHost;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tabclass);

		Resources res = getResources(); // Resource object to get Drawables
		this.tabHost = getTabHost(); // Get the tabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch the first Activity for the tab (to be reused)
		intent = new Intent().setClass(this, FirstGroup.class);
		// Initialize a TabSpec for the first tab and add it to the TabHost
		spec = tabHost.newTabSpec("caf").setIndicator(res.getString(R.string.tab_caf), res.getDrawable(R.drawable.search)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, Alerts.class);
		spec = tabHost.newTabSpec("alert").setIndicator(res.getString(R.string.tab_alert), res.getDrawable(R.drawable.search)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}
}
