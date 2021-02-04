package dev.th0m4s.labelshackathon;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import dev.th0m4s.labelshackathon.adapters.HistoryAdapter;
import dev.th0m4s.labelshackathon.db.ResultsDatabase;

public class MainActivity extends AppCompatActivity {

    public static RequestQueue netRequestQueue;
    public static MainActivity Instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Instance = this;

        netRequestQueue = Volley.newRequestQueue(this);
        ResultsDatabase.Prepare(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scanIntent = new Intent(toolbar.getContext(), ScanActivity.class);
                scanIntent.setFlags(scanIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(scanIntent);
            }
        });

        new Thread(() -> {
            ResultsDatabase.UpdateCachedCount();
            ((RecyclerView)findViewById(R.id.recycleViewHistory)).setAdapter(new HistoryAdapter(getResources()));
        }).start();
    }

    public void UpdateHistory() {
        ResultsDatabase.UpdateCachedCount();

        runOnUiThread(() -> {
            RecyclerView.Adapter adapter = ((RecyclerView)findViewById(R.id.recycleViewHistory)).getAdapter();
            if(adapter != null)
                adapter.notifyDataSetChanged();
        });
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
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}