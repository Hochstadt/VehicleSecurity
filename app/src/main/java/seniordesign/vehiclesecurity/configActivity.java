package seniordesign.vehiclesecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class configActivity extends AppCompatActivity {

    //all widgets
    private SeekBar seekBar;
    private TextView textView;
    private Button submitConfig;
    private EditText lengthOfClip;

    private String web_server_protocol = "http://";
    private String web_server_address = "192.168.1.20/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        initializeVariables();


        textView.setText(seekBar.getProgress() + " Seconds");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText(seekBar.getProgress() + " Seconds");
            }

        });
    }

    private void initializeVariables() {
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        textView = (TextView) findViewById(R.id.textView1);
        submitConfig = (Button) findViewById(R.id.button1);
        lengthOfClip = (EditText) findViewById(R.id.clipLength);

    }

    public void onSubmit(View view){
        //lengthOfClip.setEnabled(false);
        textView.setText(seekBar.getProgress() + " this is " + lengthOfClip.getText());
        //adds progress
        new AsyncNetworkHandler().execute("http://192.168.1.20/Modify_Field_Value.php/?field=DASHCAM_" +
                "CLIP_LENGTH&value="+seekBar.getProgress());
       //adds length of clip to config
        new AsyncNetworkHandler().execute("http://192.168.1.20/Modify_Field_Value.php/?field=DASHCAM_" +
                "CLIP_COUNT&value="+lengthOfClip.getText().toString());


    }

}
