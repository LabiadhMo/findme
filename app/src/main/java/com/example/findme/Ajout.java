package com.example.findme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ajout extends AppCompatActivity {

    EditText ednum , edlat, edlong ;
    Button btnvald , btnqte ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        ednum = findViewById(R.id.ednum_ajout);
        edlat = findViewById(R.id.edlat_ajout);
        edlong = findViewById(R.id.edlong_ajouter);

        btnvald = findViewById(R.id.btnvalid_ajouter);
        btnqte = findViewById(R.id.btnste_ajouter);

        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ajout.this.finish();

            }
        });

        btnvald.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(ednum.getText().toString()) ;
                String lat = edlat.getText().toString() ;
                String log = edlong.getText().toString();

                PositionManager pm = new PositionManager(Ajout.this,"positions.db",1);

                long a = pm.inserer(num,log,lat);


            }
        });
    }
}