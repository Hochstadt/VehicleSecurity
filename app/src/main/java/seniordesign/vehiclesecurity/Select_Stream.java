package seniordesign.vehiclesecurity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class Select_Stream extends AppCompatActivity {

    private String web_server_address = "http://192.168.1.20/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__stream);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    public void onFront(View view)
    {
        Log.d("SEL_STREAM", "onFront button clicked");
        new AsyncNetworkHandler().execute(web_server_address + "View_Front_Stream.php");
        view_video(0);
    }

    public void onRight(View view)
    {
        Log.d("SEL_STREAM", "onRight button clicked");
        new AsyncNetworkHandler().execute(web_server_address + "View_Right_Stream.php");
        view_video(1);
    }

    public void onRear(View view)
    {
        Log.d("SEL_STREAM", "onRear button clicked");
        new AsyncNetworkHandler().execute(web_server_address + "View_Rear_Stream.php");
        view_video(2);
    }

    public void onLeft(View view)
    {
        Log.d("SEL_STREAM", "onLeft button clicked");
        new AsyncNetworkHandler().execute(web_server_address + "View_Left_Stream.php");
        view_video(3);
    }



    public void view_video(int orientation)
    {
        Intent startNewActivity = new Intent(this, VideoActivity.class);
        String address = "http://192.168.1.20:8090";

        // Front
        if(orientation == 0)
            address = "http://192.168.1.20:8090";
        // Right
        else if (orientation == 1)
            address = "http://192.168.1.21:8090";
        // Rear
        else if (orientation == 2)
            address = "http://192.168.1.22:8090";
        // Left
        else if (orientation == 3)
            address = "http://192.168.1.23:8090";

        startNewActivity.putExtra("URL", address);
        startActivity(startNewActivity);
    }
}
