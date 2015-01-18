package me.cooperka.cafalert;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class Alerts extends ListActivity
{
	private ArrayList<Alert> m_alerts = null; // list of message items
	private AlertAdapter m_adapter;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_caflist);

		m_alerts = new ArrayList<Alert>();
		this.m_adapter = new AlertAdapter(this, R.layout.row_alert, m_alerts);
		setListAdapter(this.m_adapter);

		addAlerts();
	}

	private void addAlerts()
	{
		Alert a[] = new Alert[4];

		for (int i = 0; i < 4; i++)
			a[i] = new Alert();

		a[0].setStuff("These don't work... (yet)", false);
		a[1].setStuff("Mashed potatoes", false);
		a[2].setStuff("Quesedillas", false);
		a[3].setStuff("Mac and cheese", false);

		for (int i = 0; i < 4; i++)
			m_alerts.add(a[i]);

		m_adapter.notifyDataSetChanged();
	}

	// adapter for list of events
	public class AlertAdapter extends ArrayAdapter<Alert>
	{
		private ArrayList<Alert> items;

		public AlertAdapter(Context context, int textViewResourceId, ArrayList<Alert> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.row_alert, null);

			Alert a = items.get(position);

			if (a != null) {
				TextView name = (TextView) v.findViewById(R.id.name);
				CheckBox active = (CheckBox) v.findViewById(R.id.check);

				if (name != null)
					name.setText(a.getName());
				if (active != null)
					active.setChecked(a.getActive());
			}
			return v;
		}
	}
}
