package com.example.banyantask;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;



import androidx.annotation.RequiresApi;


@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class Bluetooth extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    TextView deviceName1,deviceMACAddress1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        deviceName1 = findViewById(R.id.deviceName);
        deviceMACAddress1 = findViewById(R.id.deviceMACAddress);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)) {
            Toast.makeText(this, "Bluetooth not supported on this device", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth not supported on this device", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if (!bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth is not enabled", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String deviceName = bluetoothAdapter.getName();
        String deviceAddress = bluetoothAdapter.getAddress();

        deviceName1.setText("Device Name: " + deviceName);
        deviceMACAddress1.setText("Device MAC Address: " + deviceAddress);

        Toast.makeText(this, "Device Name: " + deviceName + "\nDevice MAC Address: " + deviceAddress, Toast.LENGTH_LONG).show();
    }
}

