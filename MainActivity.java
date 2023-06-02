package com.susulan202102276.Susulan202102276;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nim,nama,jeniskelamin,alamat,email;
    String isikode,isijudul,isipengarang,isipenerbit,isiisbn;
    Button simpan;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nim = findViewById(R.id.edtnim);
        nama = findViewById(R.id.edtnama);
        jeniskelamin = findViewById(R.id.edtjk);
        alamat = findViewById(R.id.edtalamat);
        email = findViewById(R.id.edtemail);
        simpan = findViewById(R.id.btnsimpan);
        db = new DBHelper(this);


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  isikode = nim.getText().toString();
                String isijudul = nama.getText().toString();
                String isipengarang = jeniskelamin.getText().toString();
                String isipenerbit = alamat.getText().toString();
                String isiisbn = email.getText().toString();

                if (TextUtils.isEmpty(isikode) || TextUtils.isEmpty(isijudul) || TextUtils.isEmpty(isipengarang)
                        || TextUtils.isEmpty(isipenerbit) || TextUtils.isEmpty(isiisbn)){
                    Toast.makeText(MainActivity.this,"Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                }else {
                    Boolean checkkode = db.checkkodebuku(isikode);
                    if (checkkode == false){
                        Boolean insert = db.insertData(isikode,isijudul,isipengarang,isipenerbit,isiisbn);
                        if (insert == true){
                            Toast.makeText(MainActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),BiodataActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this,"Data gagal disimpan", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(MainActivity.this,"Data Mahasiswa Sudah Ada", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });


    }
}