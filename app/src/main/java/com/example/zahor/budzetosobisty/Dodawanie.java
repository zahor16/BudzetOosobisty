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


public class Dodawanie extends ActionBarActivity {

    DatabaseHelper myDb;
    EditText editNazwa, editKwota, editData;
    Button btnZapisz;
    Button btnPokaz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodawanie);


        myDb= new DatabaseHelper(this);
        editNazwa = (EditText)findViewById(R.id.wplataTxt);
        editKwota= (EditText)findViewById(R.id.kwotaTxt);
        editData= (EditText)findViewById(R.id.dataTxt);
        btnZapisz= (Button)findViewById(R.id.dodajBtn);
        btnPokaz = (Button)findViewById(R.id.pokazBtn);
        AddData();
        ViewAll();
    }

    public void AddData(){
        btnZapisz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted= myDb.insertDataWplaty(editNazwa.getText().toString(),
                                editKwota.getText().toString(),
                                editData.getText().toString());
                        if(isInserted=true)
                            Toast.makeText(Dodawanie.this, "Zapisano", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Dodawanie.this,"Nie zapisano",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void ViewAll(){
        btnPokaz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllDataWplaty();
                        if (res.getCount() == 0) {
                            showMessage("Błąd", "Brak danych");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Nazwa :" + res.getString(1) + "\n");
                            buffer.append("Kwota :" + res.getString(2) + "\n");
                            buffer.append("Data :" + res.getString(3) + "\n\n");
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
        getMenuInflater().inflate(R.menu.menu_dodawanie, menu);
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
