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
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Bundle extras = getIntent().getExtras();
        videoURL = extras.getString("URL");

        if (videoURL.equals("playback"))
        {
            filename = extras.getString("filename");

            // If we are streaming a playback video on the pi...
            Log.d("MAIN", "We're in playback mode baybee");

            streamView = (VideoView)findViewById(R.id.video_viewer);

            mediaController = new MediaController(this);

            mediaController.setAnchorView(streamView);

            streamView.setMediaController(mediaController);

            streamView.setVideoURI(Uri.parse("http://192.168.1.20/video.mp4"));

            streamView.start();
            //
            Toast.makeText(VideoActivity.this, filename, Toast.LENGTH_LONG).show();
            //
        }
        else {
            Log.d("MAIN", "our URL value is " + videoURL);
            streamView = (VideoView) findViewById(R.id.video_viewer);

            Uri UriSrc = Uri.parse(videoURL);
            if (UriSrc == null) {
                Toast.makeText(VideoActivity.this,
                        "UriSrc == null", Toast.LENGTH_LONG).show();
            } else {
                streamView.setVideoURI(UriSrc);
                mediaController = new MediaController(this);
                streamView.setMediaController(mediaController);
                streamView.start();

                Toast.makeText(VideoActivity.this,
                        "Connect: " + src,
                        Toast.LENGTH_LONG).show();
            }
        }

    }

    protected void onDestroy()
    {
        Log.d("MAIN", "Uhh we're destroying loll");

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
        else if (videoURL.equals("playback"))
            address = "http://192.168.1.20/Delete_Video.php";

        new AsyncNetworkHandler().execute(address);
    }



}
