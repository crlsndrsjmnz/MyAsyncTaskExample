package co.carlosandresjimenez.example.android.myasynctaskexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    TextView tvExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvExample = (TextView) findViewById(R.id.tv_example);
        FloatingActionButton btExecute = (FloatingActionButton) findViewById(R.id.fab);

        btExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ExampleAsyncTask().execute(getString(R.string.async_message));
            }
        });
    }

    class ExampleAsyncTask extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... message) {

            try {
                Thread.sleep(5000); // Sleep for 10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArrayList<String> values = new ArrayList<>();
            values.add(message[0]);

            return values;
        }

        @Override
        protected void onPostExecute(ArrayList<String> values) {

            if (values.size() > 0) {
                tvExample.setText(values.get(0));
            } else {
                tvExample.setText(R.string.error);
            }
        }
    }
}
