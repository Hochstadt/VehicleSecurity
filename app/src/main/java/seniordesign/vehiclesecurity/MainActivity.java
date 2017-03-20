package seniordesign.vehiclesecurity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private String web_server_ip = "192.168.1.124";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButton1(View view) throws IOException {
        Log.d(TAG, "Button 1 clicked");
        //URL url = new URL("192.168.1.124/Test_Program_1.php");
        //HttpURLConnection client = (HttpURLConnection) url.openConnection();
        //client.setRequestMethod("GET");
    }

    public void onButton2(View view)
    {
        Log.d(TAG, "Button 2 clicked");
    }

    public void onButton3(View view)
    {
        Log.d(TAG, "Button 3 clicked");
    }

    public void onButton4(View view)
    {
        Log.d(TAG, "Button 4 clicked");
    }

    public void setWebServerIP(String url)
    {
        web_server_ip = url;
    }
}
