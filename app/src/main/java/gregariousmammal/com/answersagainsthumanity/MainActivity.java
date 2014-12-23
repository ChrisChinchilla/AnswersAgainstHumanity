package gregariousmammal.com.answersagainsthumanity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends Activity {

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

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("masterCards");
            String[] answersArray = new String[m_jArry.length()];

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String text_value = jo_inside.getString("text");

                String card_type = jo_inside.getString("cardType");
                //Log.v("log_tag", "tc " + card_type);

                if (card_type.equalsIgnoreCase("A")) {
                    answersArray[i] = text_value;
                }
            }
            Globals g = Globals.getInstance();
            g.setData(answersArray);

        } catch (Exception ex) {
            Log.e("log_tag", "Error getJSONfromURL " + ex.toString());
        }

        randomNumber();

        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                randomNumber();
            }
        });
    }


    public void randomNumber() {
        Globals g = Globals.getInstance();
        String[] answersArray = g.getData();
        String item = answersArray[new Random().nextInt(answersArray.length)];
        //Log.v("log_tag", "st " + new Random().nextInt(answersArray.length));
        ((TextView) findViewById(R.id.text)).setText(item);
    }
}