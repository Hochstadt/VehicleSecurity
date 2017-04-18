package seniordesign.vehiclesecurity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryList extends ListActivity {

    String file_list;
    String direction;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        file_list = extras.getString("file_list");
        direction = extras.getString("direction");

        listview = getListView();

        TextView textview = new TextView(getApplicationContext());
        textview.setText(direction);
        listview.addHeaderView(textview);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Gather selected string
                String selected_file_name = listview.getItemAtPosition(position).toString();
                Log.d("MAIN", "The filename you've selected is" + selected_file_name + "!");

                // Tell the pi to copy from FRONT with string (video name)
                //        new AsyncNetworkHandler().execute("http://192.168.1.20/Modify_Field_Value.php/?field=DASHCAM_CLIP_COUNT&value="+lengthOfClip.getText().toString());
                new AsyncNetworkHandler().execute("http://192.168.1.20/Copy_Video.php/?direction=" + direction  + "&filename=" + selected_file_name);

                // play video!

                Intent startNewActivity = new Intent(getApplicationContext(), VideoActivity.class);
                startNewActivity.putExtra("URL", "playback");
                startNewActivity.putExtra("filename", selected_file_name);
                startActivity(startNewActivity);

            }
        });

        Log.d("MAIN", "Our new file list from the " + direction + " directory is " + file_list);

        String[] new_name = file_list.split("[^a-z0-9._]");
        Arrays.sort(new_name);

        for(String temp : new_name)
        {
            if (!temp.equals(""))
            {
                Log.d("MAIN", "Our FANCY file name is " + temp + ".");
                listItems.add(temp);

                adapter.notifyDataSetChanged();
            }
        }

    }




}
