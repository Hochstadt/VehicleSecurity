package seniordesign.vehiclesecurity;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import static android.R.attr.src;

@TargetApi(21)
public class VideoActivity extends AppCompatActivity {

    VideoView streamView;
    MediaController mediaController;
    String videoURL = "http://192.168.1.20:8090"; // Default

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        videoURL = extras.getString("URL");
        Log.d("MAIN", "our URL value is " + videoURL);
        streamView = (VideoView) findViewById(R.id.video_viewer);

        Uri UriSrc = Uri.parse(videoURL);
        if(UriSrc == null){
            Toast.makeText(VideoActivity.this,
                    "UriSrc == null", Toast.LENGTH_LONG).show();
        }else{
            streamView.setVideoURI(UriSrc);
            mediaController = new MediaController(this);
            streamView.setMediaController(mediaController);
            streamView.start();

            Toast.makeText(VideoActivity.this,
                    "Connect: " + src,
                    Toast.LENGTH_LONG).show();
        }

    }

    protected void onDestroy()
    {
        super.onDestroy();
        streamView.stopPlayback();
        String address = "http://192.168.1.20/Terminate_Stream_Front.php";

        if(videoURL.equals("http://192.168.1.20:8090"))
            address = "http://192.168.1.20/Terminate_Stream_Front.php";
        else if(videoURL.equals("http://192.168.1.21:8090"))
            address = "http://192.168.1.20/Terminate_Stream_Right.php";
        else if(videoURL.equals("http://192.168.1.22:8090"))
            address = "http://192.168.1.20/Terminate_Stream_Rear.php";
        else if(videoURL.equals("http://192.168.1.23:8090"))
            address = "http://192.168.1.20/Terminate_Stream_Left.php";

        new AsyncNetworkHandler().execute(address);
    }



}
