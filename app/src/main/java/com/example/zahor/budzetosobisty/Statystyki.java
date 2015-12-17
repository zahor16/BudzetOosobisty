package com.example.zahor.budzetosobisty;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Statystyki extends ActionBarActivity {

    TextView tvWpMax, tvWpMin, tvWpSr, tvWypMax, tvWypMin, tvWypSr;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statystyki);

        tvWpMax=(TextView)findViewById(R.id.tvWpMax);
        tvWpMin=(TextView)findViewById(R.id.tvWpMin);
        tvWpSr=(TextView)findViewById(R.id.tvWpSr);
        tvWypMax=(TextView)findViewById(R.id.tvWypMax);
        tvWypMin=(TextView)findViewById(R.id.tvWypMin);
        tvWypSr=(TextView)findViewById(R.id.tvWypSr);
        myDb=new DatabaseHelper(this);
        ViewStats();
    }

    public void ViewStats(){
        Cursor srWp = myDb.SrWplata();
        Cursor srWyp = myDb.SrWyplata();
        Cursor minWyp = myDb.MinWyplata();
        Cursor maxWyp = myDb.MaxWyplata();
        Cursor minWp = myDb.MinWplata();
        Cursor maxWp = myDb.MaxWplata();

        while (maxWp.moveToNext()) {
            tvWpMax.setText(maxWp.getString(0));
        }
        while (minWp.moveToNext()) {
            tvWpMin.setText(minWp.getString(0));
        }
        while (maxWyp.moveToNext()) {
            tvWypMax.setText(maxWyp.getString(0));
        }
        while (minWyp.moveToNext()) {
            tvWypMin.setText(minWyp.getString(0));
        }
        while (srWyp.moveToNext()) {
            tvWypSr.setText(srWyp.getString(0));
        }
        while (srWp.moveToNext()) {
            tvWpSr.setText(srWp.getString(0));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statystyki, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
