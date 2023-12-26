package com.example.banyantask;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;
    BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this, WifiActivity.class));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this, workmanage.class));
            }
        });






        btn2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                            REQUEST_BLUETOOTH_PERMISSION);
                    mBluetoothAdapter.isEnabled();
                    mBluetoothAdapter.enable();
                }else{
                    mBluetoothAdapter.isEnabled();
                    mBluetoothAdapter.enable();
                    startActivity(new Intent(MainActivity.this, Bluetooth.class));
                }

            }
        });


    }


    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_BLUETOOTH_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mBluetoothAdapter.isEnabled();
                mBluetoothAdapter.enable();
                startActivity(new Intent(MainActivity.this, Bluetooth.class));
            } else {
                mBluetoothAdapter.isEnabled();
                mBluetoothAdapter.enable();
                startActivity(new Intent(MainActivity.this, Bluetooth.class));
            }
        }
    }


}