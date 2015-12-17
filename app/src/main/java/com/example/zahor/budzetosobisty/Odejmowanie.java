package com.example.zahor.budzetosobisty;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Odejmowanie extends ActionBarActivity {

    DatabaseHelper myDb;
    EditText editNazwa, editKwota, editData;
    Button btnZapisz;
    Button btnPokaz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odejmowanie);

        myDb= new DatabaseHelper(this);
        editNazwa = (EditText)findViewById(R.id.wydatkiTxt);
        editKwota= (EditText)findViewById(R.id.kwota2Txt);
        editData= (EditText)findViewById(R.id.data2Txt);
        btnZapisz= (Button)findViewById(R.id.odejmijBtn);
        btnPokaz = (Button)findViewById(R.id.button);
        AddData1();
        ViewAll1();
    }

    public void AddData1(){
        btnZapisz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted= myDb.insertDataWyplaty(editNazwa.getText().toString(),
                                editKwota.getText().toString(),
                                editData.getText().toString());
                        if(isInserted=true)
                            Toast.makeText(Odejmowanie.this, "Zapisano", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Odejmowanie.this,"Nie zapisano",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void ViewAll1(){
        btnPokaz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res1 = myDb.getAllDataWyplaty();
                        if (res1.getCount() == 0) {
                            showMessage("Błąd", "Brak danych");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res1.moveToNext()) {
                            buffer.append("Id :" + res1.getString(0) + "\n");
                            buffer.append("Nazwa :" + res1.getString(1) + "\n");
                            buffer.append("Kwota :" + res1.getString(2) + "\n");
                            buffer.append("Data :" + res1.getString(3) + "\n\n");
                        }
                        showMessage("Dane", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_odejmowanie, menu);
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
