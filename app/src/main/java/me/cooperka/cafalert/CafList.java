package me.cooperka.cafalert;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CafList extends ListActivity {

    private Caf c[] = new Caf[9];
    private ArrayList<Caf> m_cafs = null; // list of caf items
    private CafAdapter m_adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_caflist);

        m_cafs = new ArrayList<Caf>();
        this.m_adapter = new CafAdapter(this, R.layout.row_caf, m_cafs);
        setListAdapter(this.m_adapter);

        // listener for clicking on a caf
        getListView().setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (c != null)
                {
                    // open the menu
                    Intent intent = new Intent(CafList.this, Menu.class);
                    intent.putExtra("code", c[position].getCode());
                    intent.putExtra("name", "" + c[position].getName());

                    // Create the view using FirstGroup's LocalActivityManager
                    View newView = FirstGroup.group.getLocalActivityManager()
                            .startActivity("menu", intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                            .getDecorView();

                    FirstGroup.group.replaceView(newView); // replace the view
                }
                else // ERROR!
                    Log.e("CafList", "CAF IS NULL");
            }
        });

        addCafs();
    }

    private void addCafs() {
        for (int i = 0; i < 9; i++)
            c[i] = new Caf();

        c[0].setStuff("Mojo", "MARKETPLACE", "7am - 9pm", Color.rgb(0xAA, 0x00, 0x11)); // red
        c[2].setStuff("Markley", "MARKLEY%20DINING%20HALL", "7am - 9pm", Color.rgb(0xDD, 0x33, 0x00)); // orange
        c[1].setStuff("Bursley", "BURSLEY%20DINING%20HALL", "7am - 9pm", Color.rgb(0x00, 0x88, 0x00)); // green
        c[3].setStuff("North Quad", "North%20Quad%20Dining%20Hall", "7am - 9pm", Color.rgb(0x66, 0x00, 0x66)); // purple
        c[4].setStuff("South Quad", "SOUTH%20QUAD%20DINING%20HALL", "7am - 9pm", Color.rgb(0x00, 0x00, 0xCC)); // blue
        c[5].setStuff("East Quad", "EAST%20QUAD%20DINING%20HALL", "7am - 9pm", Color.rgb(0x33, 0x66, 0x00)); // green
        c[6].setStuff("West Quad", "WEST%20QUAD%20DINING%20HALL", "7am - 9pm", Color.rgb(0x66, 0x33, 0x00)); // brown
        c[7].setStuff("Barbour", "BARBOUR%20DINING%20HALL", "7am - 9pm", Color.rgb(0xCC, 0x00, 0x99)); // pink
        c[8].setStuff("Twigs", "Twigs%20at%20Oxford", "7am - 9pm", Color.rgb(0x00, 0x33, 0x66)); // cyan

        for (int i = 0; i < 9; i++)
            m_cafs.add(c[i]);

        m_adapter.notifyDataSetChanged();
    }

    // adapter for list of cafs
    public class CafAdapter extends ArrayAdapter<Caf> {
        private ArrayList<Caf> items;

        public CafAdapter(Context context, int textViewResourceId, ArrayList<Caf> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_caf, null);

            Caf c = items.get(position);

            if (c != null) {
                TextView name = (TextView) v.findViewById(R.id.name);
                TextView hours = (TextView) v.findViewById(R.id.hours);
                LinearLayout bkgd = (LinearLayout) v.findViewById(R.id.bkgd);

                if (name != null)
                    name.setText(c.getName());
                if (hours != null)
                    hours.setText("(" + c.getHours() + ")");

                if (bkgd != null)
                    bkgd.setBackgroundColor(c.getColor());
            }
            return v;
        }
    }

}
