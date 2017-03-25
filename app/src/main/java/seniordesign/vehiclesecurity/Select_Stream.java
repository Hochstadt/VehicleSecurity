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

    private String web_server_protocol = "http://";
    private String web_server_address = "192.168.1.20/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__stream);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    public void onFront(View view)
    {
        Log.d("SEL_STREAM", "onFront button clicked");
        new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "View_Front_Stream.php");
        view_video();
    }

    public void onRear(View view)
    {
        Log.d("SEL_STREAM", "onRear button clicked");
        new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "View_Rear_Stream.php");
        view_video();
    }

    public void onLeft(View view)
    {
        Log.d("SEL_STREAM", "onLeft button clicked");
        new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "View_Left_Stream.php");
        view_video();
    }

    public void onRight(View view)
    {
        Log.d("SEL_STREAM", "onRight button clicked");
        new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "View_Right_Stream.php");
        view_video();
    }

    public void view_video()
    {
        Intent startNewActivity = new Intent(this, VideoActivity.class);

        startNewActivity.putExtra("URL", "http://192.168.1.20:8090");
        startActivity(startNewActivity);
    }
}
