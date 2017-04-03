package seniordesign.vehiclesecurity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private String web_server_protocol = "http://";
    private String web_server_address = "192.168.1.20/";
    //System.setProperty("http.keepAlive", "false");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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


    public void dashcam_mode(View view)
    {
        Log.d("MAIN", "Button 3 clicked");
        new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "Dashcam_Mode.php");
    }

    public void alert_mode(View view)
    {
        Log.d("MAIN", "Button 3 clicked");
        new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "Alert_Mode.php");
    }

    /*
    public void onButton1(View view) throws IOException {
        Log.d("MAIN", "Button 1 clicked");
        new AsyncNetworkHandler().execute(web_server_protocol + web_server_address +
                "Test_Program_1.php");
    }

    public void onButton2(View view)
    {
        Log.d("MAIN", "Button 2 clicked");
        new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "Test_Program_2.php");
    }

    public void onButton3(View view)
    {
        Log.d("MAIN", "Button 3 clicked");
        new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "Test_Program_3.php");
        //enableBluetooth();
    }

    public void onButton4(View view)
    {
        Log.d("MAIN", "Button 4 clicked");
        new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "Test_Program_4.php");
        //Intent startNewActivity = new Intent(this,configActivity.class);
        //startActivity(startNewActivity);
    }

    public void onButton5(View view)
    {
        Log.d("MAIN", "Button 5 clicked");
        new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "Test_Program_5.php");
    }
    //*/

    // XML
    /*
    <Button
    android:id="@+id/button_1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="top|center_horizontal"
    android:layout_marginTop="56dp"
    android:text="Test Program 1"
    app:layout_anchor="@+id/include"
    app:layout_anchorGravity="center_vertical|center_horizontal"
    android:onClick="onButton1"
    android:layout_alignParentTop="true"
    android:layout_alignParentLeft="true"
    android:layout_alignParentStart="true" />

        <Button
    android:id="@+id/button_2"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Test Program 2"
    android:onClick="onButton2"
    android:layout_below="@+id/button_1"
    android:layout_toLeftOf="@+id/button_5"
    android:layout_toStartOf="@+id/button_5" />

        <Button
    android:id="@+id/button_3"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="42dp"
    android:text="Test Program 3"
    android:onClick="onButton3"
    android:layout_below="@+id/button_2"
    android:layout_alignLeft="@+id/button_2"
    android:layout_alignStart="@+id/button_2" />

        <Button
    android:id="@+id/button_4"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="40dp"
    android:text="Test Program 4"
    android:onClick="onButton4"
    android:layout_below="@+id/button_3"
    android:layout_alignLeft="@+id/button_3"
    android:layout_alignStart="@+id/button_3" />

        <Button
    android:id="@+id/button_5"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Test Program 5"
    android:onClick="onButton5"
    android:layout_below="@+id/button_4"
    android:layout_alignParentLeft="true"
    android:layout_alignParentStart="true" />

    //*/
    public void view_video_streams(View view)
    {
        Intent startNewActivity = new Intent(this, Select_Stream.class);

        startActivity(startNewActivity);
    }

    public void debug_commands(View view)
    {
        Intent startNewActivity = new Intent(this, DebugCommands.class);

        startActivity(startNewActivity);
    }

    public void setWebServerIP(String url)
    {
        web_server_address = url;
    }
}

class AsyncNetworkHandler extends AsyncTask<String, Integer, Double>
{
    @Override
    protected Double doInBackground(String... params)
    {
        //if (params.length>1){
           // postData(params[0]);
        //}
       // else{
        sendRequest(params[0]);
        //}

        return null;
    }


    private void sendRequest(String address)
    {
        //Log.d("MAIN", "sendRequest beginning...");
        try
        {
            //URL mycoo = new URL("http://192.168.1.20/Test_Program_1.php");
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5000);
            conn.getInputStream();
            conn.disconnect();

            //if(conn.getResponseCode() == HttpURLConnection.HTTP_OK)
            //    Log.d("MAIN", "we got our HTTP OK");

            //conn.setRequestMethod("POST");

            // Process for connection --
            /*
            1. Set Request Properties
            2. (Optional) getOutputStream(), write to the stream, close the stream.
            3. getInputStream(), read from the stream, close the stream.
            http://stackoverflow.com/questions/4844535/why-do-you-have-to-call-urlconnectiongetinputstream-to-be-able-to-write-out-to
            //*/

            /*
            conn.setDoOutput(true);
            conn.setDoInput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes("mydata=hello");
            wr.flush();
            wr.close();
            //*/

            /*
            InputStream is = conn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            Log.d("MAIN", response.toString());
            conn.disconnect();
            //*/
        }
        catch(Exception e)
        {
            Log.d("MAIN", e.toString());
        }
    }


   /* public void postData(String progress) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://192.168.1.20/Test_Program_4.php");

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("myHttpData", progress));
            //nameValuePairs.add(new BasicNameValuePair("clipLength", clipLength));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }*/

    // On completion
    protected void onPostExecute(Double result)
    {

    }

    // On progress
    protected void onProgressUpdate(Integer... progress)
    {

    }
}