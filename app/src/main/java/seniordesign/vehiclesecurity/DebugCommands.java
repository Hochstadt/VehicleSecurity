package seniordesign.vehiclesecurity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class DebugCommands extends AppCompatActivity {
    private String web_server_protocol = "http://";
    private String web_server_address = "192.168.1.20/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_commands);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void enableBluetooth() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Log.d("MAIN", "bluetooth not supported");

        } else {
            Log.d("MAIN", "bluetooth supported");

            //discoverable mode
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);
            startActivity(discoverableIntent);

        }
    }
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
        /*new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "Test_Program_4.php");*/
        Intent startNewActivity = new Intent(this,configActivity.class);
        startActivity(startNewActivity);
    }

    public void onButton5(View view)
    {
        Log.d("MAIN", "Button 5 clicked");
        new AsyncNetworkHandler().execute(web_server_protocol + web_server_address
                + "Test_Program_5.php");
    }

}
