package com.example.banyantask;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WifiActivity extends AppCompatActivity {

    private EditText ssidEditText;
    private EditText passwordEditText;
    private Button connectButton;

    private static final long TIMEOUT_DURATION = 20000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        ssidEditText = findViewById(R.id.ssidEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        connectButton = findViewById(R.id.connectButton);



        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  connectToWiFi();
                connectToWifi(ssidEditText.getText().toString(), passwordEditText.getText().toString());
            //    connectToWifi(ssidEditText.getText().toString(), passwordEditText.getText().toString());

                // Start countdown timer
                new CountDownTimer(TIMEOUT_DURATION, 1000) {

                    public void onTick(long millisUntilFinished) {
                        // Timer is ticking
                    }

                    public void onFinish() {
                        // Timeout reached, stop WiFi functionalities
                        stopWifi();
                    }
                }.start();

            }
        });


    }



    private void connectToWifi(String SSID, String PASSWORD) {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        // Create a WifiConfiguration
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = "\"" + SSID + "\"";
        wifiConfig.preSharedKey = "\"" + PASSWORD + "\"";
        int networkId = wifiManager.addNetwork(wifiConfig);
        Toast.makeText(this, " " +  networkId, Toast.LENGTH_SHORT).show();
        wifiManager.enableNetwork(networkId, true);
        wifiManager.reconnect();

        if (isConnectedToWifi(SSID)) {
            Toast.makeText(this, "Connected to WiFi", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to connect to WiFi", Toast.LENGTH_SHORT).show();
        }
    }


    private void stopWifi() {
        // Forget the connected WiFi network
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.disconnect();
        wifiManager.removeNetwork(wifiManager.getConnectionInfo().getNetworkId());

        // Disable WiFi
        wifiManager.setWifiEnabled(false);

        // Display a toast message indicating the timeout
        Toast.makeText(this, "WiFi connection timeout", Toast.LENGTH_SHORT).show();
    }

    private boolean isConnectedToWifi(String ssid) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                && networkInfo.getExtraInfo() != null && networkInfo.getExtraInfo().replace("\"", "").equals(ssid);
    }


}
