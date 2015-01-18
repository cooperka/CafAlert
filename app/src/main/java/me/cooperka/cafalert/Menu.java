package me.cooperka.cafalert;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu extends Activity {

    private ProgressDialog m_ProgressDialog = null;
    private String name = "", code = "", theDate = "", theMenu = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            name = extras.getString("name");
            code = extras.getString("code");
            TextView title = (TextView) findViewById(R.id.title);
            title.setText(name);
        }

        display();
    }

    private void display() {
        m_ProgressDialog = ProgressDialog.show(getParent(), "Please wait...", "Retrieving menu...", true, true,
                new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) { FirstGroup.group.back(); }
                });

        Runnable getMenu = new Runnable() {
            @Override
            public void run() { getMenu(); }
        };
        Thread thread = new Thread(null, getMenu, "thread");
        thread.start();
    }

    private void getMenu() {
        try
        {
            Pattern p = Pattern.compile("Menu<span id=\"theDate\" style=\"font-weight:normal; font-size:12px; padding-left:10px;\">for (.+)</span></h2></div>");

            URL url = new URL("http://www.housing.umich.edu/files/helper_files/js/menu2xml.php?html=webmenu&location=" + code + "&date=today");

            BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) url.getContent()));
            String line;

            while((line = in.readLine()) != null)
            {
                Matcher m = p.matcher(line);
                if (m.find()) {
                    theDate = m.group(1);
                    runOnUiThread(changeDate);
                    theMenu = line;
                    runOnUiThread(changeMenu);
                }
            }
        }
        catch(IOException e) { e.printStackTrace(); }

        if (m_ProgressDialog != null) m_ProgressDialog.dismiss();
    }

    private Runnable changeDate = new Runnable() {
        public void run() { ((TextView) findViewById(R.id.date)).setText("Menu for " + theDate); }
    };

    private Runnable changeMenu = new Runnable() {
        public void run() {
            String split[] = theMenu.split("(<td>|<h3>|<li>)");
            theMenu = "";
            int meal = 1; // 1 = breakfast, 2 = lunch, 3 = dinner
            boolean justDid = false; // true means a time (break, lunch, dn) was just printed

            for (int i = 1; i < split.length; i++)
            {
                int loc = split[i].indexOf("<", 1);

                if (split[i].charAt(0) == '<')
                {
                    theMenu += (meal == 1 ? "\nBREAKFAST" : meal == 2 ? "\n\nLUNCH" : "\n\nDINNER") + "\n\n";
                    meal++;
                    justDid = true;
                    continue;
                }

                if (split[i].charAt(loc+2) == 'h' && !justDid)
                    theMenu += "\n";
                else if (!justDid)
                    theMenu += "- ";

                split[i] = split[i].substring(0, loc).trim().replace("&amp;", "&");
                theMenu += split[i] + "\n";

                justDid = false;
            }

            ((TextView) findViewById(R.id.menu)).setText((theMenu.length() <= 5 ? "\nMeals are not being served today." : theMenu));
        }
    };

}
