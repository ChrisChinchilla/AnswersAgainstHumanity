package aah.gregariousmammal.com.answersagainsthumanity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = null;

        try {

            AssetManager am = getApplication().getAssets();
            InputStream is = am.open("cards.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //TODO: This needs to show a loading screen until finished.
        //TODO: Turn into a common function shared between this and button click

        try {
            JSONObject obj = new JSONObject(json);

            JSONArray m_jArry = obj.getJSONArray("masterCards");
            String[] answers = new String[m_jArry.length()];


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String text_value = jo_inside.getString("text");
                String card_type = jo_inside.getString("cardType");
                //Log.v("log_tag", "tc " + card_type);

                if (card_type.equalsIgnoreCase("A")) {
                    answers[i] = text_value;
                }
            }

            String item = answers[new Random().nextInt(answers.length)];

            ((TextView) findViewById(R.id.text)).setText(item);
        } catch (Exception ex) {
            Log.e("log_tag", "Error getJSONfromURL " + ex.toString());
        }
    }

    public void randomNumber(View view) {
        setContentView(R.layout.activity_main);

        String json = null;

        try {

            AssetManager am = getApplication().getAssets();
            InputStream is = am.open("cards.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        try {
            JSONObject obj = new JSONObject(json);

            JSONArray m_jArry = obj.getJSONArray("masterCards");
            String[] answers = new String[m_jArry.length()];


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String text_value = jo_inside.getString("text");

                String card_type = jo_inside.getString("cardType");
                //Log.v("log_tag", "tc " + card_type);

                if (card_type.equalsIgnoreCase("A")) {
                    answers[i] = text_value;
                }
            }

            String item = answers[new Random().nextInt(answers.length)];

            ((TextView) findViewById(R.id.text)).setText(item);
        } catch (Exception ex) {
            Log.e("log_tag", "Error getJSONfromURL " + ex.toString());
        }
    }


}

