package seniordesign.vehiclesecurity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ViewDirectories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_directories);
    }

    public void on_front(View view)
    {
        new AsyncNetworkHandler(getApplicationContext()).execute("doesn't actually matter", "directories", "FRONT");

        //Intent startNewActivity = new Intent(this, DirectoryList.class);

        //startActivity(startNewActivity);

    }

    public void on_right(View view)
    {
        //new AsyncNetworkHandler().execute("http://192.168.1.20/Copy_Right_Directories.php");
        //SystemClock.sleep(1000);
        //new AsyncNetworkHandler().execute("doesn't actually matter", "directories", "RIGHT");

    }

    public void on_rear(View view)
    {
        //new AsyncNetworkHandler().execute("doesn't actually matter", "directories", "REAR");

    }

    public void on_left(View view)
    {
        //new AsyncNetworkHandler().execute("doesn't actually matter", "directories", "LEFT");

        // Testing Video playing
        //Intent startNewActivity = new Intent(this, VideoActivity.class);
        //startNewActivity.putExtra("URL", "playback");
        //startActivity(startNewActivity);
    }

}
