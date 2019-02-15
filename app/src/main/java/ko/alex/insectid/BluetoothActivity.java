package ko.alex.insectid;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    BluetoothAdapter BA = BluetoothAdapter.getDefaultAdapter();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);

        if(BA.isEnabled()){ //error because need to enable bluetooth in manifest
            Toast.makeText(getApplicationContext(),"Bluetooth is on", Toast.LENGTH_LONG).show();
        } else{
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);
            if(BA.isEnabled()){
                Toast.makeText(getApplicationContext(),"Bluetooth is now on!", Toast.LENGTH_LONG).show();
            }
        }

        Button buttonOff = findViewById(R.id.buttonOff);
        Button findDiscoverableDevices = findViewById(R.id.findDiscoverableDevices);
        Button viewPairedDevices = findViewById(R.id.viewPairedDevices);
        buttonOff.setOnClickListener((View v) ->{
            BA.disable(); //add permission BLUETOOTH_ADMIN
            if(BA.isEnabled()){ //error because need to enable bluetooth in manifest
                Toast.makeText(getApplicationContext(),"Bluetooth could not be disabled!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),"Bluetooth is turned off.", Toast.LENGTH_LONG).show();
            }
        });
        findDiscoverableDevices.setOnClickListener((View v) ->{
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivity(i);
        });
        viewPairedDevices.setOnClickListener((View v) ->{
            Set <BluetoothDevice> pairedDevices = BA.getBondedDevices();
            //Working with RecyclerView
            //https://www.androidhive.info/2016/01/android-working-with-recycler-view/
            ListView pairedDevicesListView = findViewById(R.id.pairedDevicesListView);

            ArrayList pairedDevicesArrayList = new ArrayList<>();

            for(BluetoothDevice bluetoothDevice : pairedDevices){
                pairedDevicesArrayList.add(bluetoothDevice.getName());
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_selectable_list_item, pairedDevicesArrayList);
            pairedDevicesListView.setAdapter(arrayAdapter);
        });

    }

}
