package seniordesign.vehiclesecurity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//implements AsyncLoadImage.AsynchResponse
public class MainActivity extends Activity implements AsyncLoadImage.AsynchResponse{

    private static final String TAG = MainActivity.class.getSimpleName();
    private String web_server_protocol = "http://";
    private String web_server_address = "192.168.1.20/";
    //private String imageAddress = "http://10.1.3.123/image.jpg";

    private Bitmap pic;
    //final Context context = this;

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
       /* new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "Alert_Mode.php");*/


       //Async task for image alert
        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
        AsyncLoadImage imageLoad = new AsyncLoadImage(this);
        imageLoad.response =this;
        //change to ip address
        imageLoad.execute("http://192.168.1.20/image.jpg");

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


    public void displayDialog(){
        //could change to context
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_main);



       ImageView image = (ImageView) dialog.findViewById(R.id.DialogImage);
       Picasso.with(this).load("http://192.168.1.20/image.jpg")
               .memoryPolicy(MemoryPolicy.NO_CACHE)
               .networkPolicy(NetworkPolicy.NO_CACHE)
               .resize(400,300).into(image);

        Button ignore = (Button) dialog.findViewById(R.id.Disregard);
        ignore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
               /*new AsyncNetworkHandler().execute(web_server_protocol + web_server_address +
                       "delete_Picture.php");*/
                alert_mode(findViewById(R.id.alert_mode));

            }
        });

        Button save = (Button) dialog.findViewById(R.id.SaveImage);
        // if button is clicked, close the custom dialog
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                imageDownload("http://192.168.1.20/image.jpg");
                dialog.dismiss();
                alert_mode(findViewById(R.id.alert_mode));

               /*new AsyncNetworkHandler().execute(web_server_protocol + web_server_address +
                       "delete_Picture.php");*/
                //alert_mode(findViewById(R.id.alert_mode));
            }
        });

        Button alert = (Button) dialog.findViewById(R.id.SendAlert);
        alert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
              /* new AsyncNetworkHandler().execute(web_server_protocol + web_server_address +
                       "soundAlarm.php");*/
                //alert_mode(findViewById(R.id.alert_mode));
            }
        });



        dialog.show();

        //look at codealert.txt in case


    }




   public void imageDownload(String url){
       Picasso.with(this)
               .load(url)
               .memoryPolicy(MemoryPolicy.NO_CACHE)
               .networkPolicy(NetworkPolicy.NO_CACHE)
               .resize(400,300)
               .into(target);
   }

   //saves into local storage Directory Pictures
   private Target target = new Target() {
        @Override
        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    File file = new File(Environment.getExternalStorageDirectory().getPath() +"/Pictures/security.jpg");
                    try
                    {
                        file.createNewFile();
                        FileOutputStream ostream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, ostream);
                        ostream.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            if (placeHolderDrawable != null) {
            }
        }
    };



    //methods for loading image
    public void processFinish(Bitmap bitmap,String message) {
       if(bitmap==null){
           //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

           //constant checking
           AsyncLoadImage imageLoad = new AsyncLoadImage(this);
           imageLoad.response =this;
           //change to ip address
           imageLoad.execute("http://192.168.1.20/image.jpg");

       }
       else {
           //pic = bitmap;
           displayDialog();
          // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

       }
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


    // On completion
    protected void onPostExecute(Double result)
    {

    }

    // On progress
    protected void onProgressUpdate(Integer... progress)
    {

    }
}


//Currently working on

class AsyncLoadImage extends AsyncTask<String, Void,Bitmap>{


    public AsynchResponse response;

    public AsyncLoadImage(AsynchResponse listener) {

        response = listener;
    }

    public interface AsynchResponse{

        void processFinish(Bitmap bitmap,String message);
    }


    @Override
    protected Bitmap doInBackground(String... args) {
        Bitmap picture;
        //BitmapFactory.Options options;
        try {
            URL url = new URL(args[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.connect();

            picture = BitmapFactory.decodeStream(conn.getInputStream());

            /*while( picture == null) {
                picture = BitmapFactory.decodeStream(conn.getInputStream());
            }*/

            conn.disconnect();
            //return picture;

            return picture;


        } catch (IOException e) {
            e.printStackTrace();
            //return null;
        }
        return null;

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        //Toast.makeText(this, "Executing", Toast.LENGTH_SHORT).show();

        //response.processFinish(bitmap);
        if (bitmap != null) {

            //execute when image successfully loaded
            response.processFinish(bitmap,"found pic");

        } else {
            //display error
            response.processFinish(bitmap,"no alert");
        }

    }


}
