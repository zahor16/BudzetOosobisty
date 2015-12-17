package com.example.zahor.budzetosobisty;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class EkranGlowny extends ActionBarActivity {

    DatabaseHelper myDb;
    Button menuBtn, refBtn;
    TextView TVwplaty, TVwyplaty, TVsaldo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_glowny);

        myDb =new DatabaseHelper(this);
        TVwplaty=(TextView)findViewById(R.id.textView5);
        TVwyplaty=(TextView)findViewById(R.id.textView7);
        TVsaldo=(TextView)findViewById(R.id.textView3);
        menuBtn=(Button)findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),Akcja.class);
                startActivity(intent);
            }
        });
        refBtn=(Button)findViewById(R.id.refBtn);
        refBtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          ViewWplaty();
                                          ViewWyplaty();
                                          ViewSaldo();
                                      }
                                  }

        );
        ViewWplaty();
        ViewWyplaty();
        ViewSaldo();
    }

    public void ViewWplaty(){
        Cursor sumWplat= myDb.sumaWplat();
        // String wplaty = Double.toString(sumWplat.getColumnNames().length);
        while (sumWplat.moveToNext()) {
            TVwplaty.setText(sumWplat.getString(0));
        }

    }
    public void ViewWyplaty() {
        Cursor sumWyplat = myDb.sumaWyplat();
        //  String wyplaty = Double.toString(sumWyplat.getColumnNames().length);
        while (sumWyplat.moveToNext()) {
            TVwyplaty.setText(sumWyplat.getString(0));
        }
    }
    public void ViewSaldo(){
        String wp, wyp, wynik;
        double wplaty, wyplaty, saldo;
        wp = TVwplaty.getText().toString();
        wyp = TVwyplaty.getText().toString();
        try {
            wplaty = Double.valueOf(wp).doubleValue();
            wyplaty = Double.valueOf(wyp).doubleValue();
        }
        catch (NumberFormatException e){
            wplaty=0;
            wyplaty=0;
        }
        saldo=wplaty-wyplaty;
        wynik=String.valueOf(saldo).toString();
        TVsaldo.setText(wynik);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ekran_glowny, menu);
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
