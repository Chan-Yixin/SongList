package sg.edu.rp.c346.id22038283.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Song> alSong;
    Button star5;

    ArrayAdapter aaSong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        alSong = new ArrayList<Song>();


        lv = findViewById(R.id.lv);

        star5 = findViewById(R.id.btn5Star);


        ArrayAdapter adapter = new ArrayAdapter<>(SecondActivity.this, android.R.layout.simple_list_item_1, alSong);


        lv.setAdapter(adapter);

        DBHelper db = new DBHelper(SecondActivity.this);
        alSong.clear();
        alSong.addAll(db.getSong());
        adapter.notifyDataSetChanged();


        ArrayList<String> data = db.getSongContent();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Song data = alSong.get(position);


                // Create an Intent to launch the third activity
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);

            }
        });
        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(SecondActivity.this);

                ArrayList<Song> fiveStarSongs = new ArrayList<>();

                for (Song song : alSong) {
                    if (song.getStar() == 5) {
                        fiveStarSongs.add(song);
                    }

                    ArrayAdapter<Song> adapter = new ArrayAdapter<>(SecondActivity.this, android.R.layout.simple_list_item_1, fiveStarSongs);
                    lv.setAdapter(adapter);
                }



            }
        });









    }




}