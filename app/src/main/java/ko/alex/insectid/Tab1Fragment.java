package ko.alex.insectid;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment {

    //Working with RecyclerView
    //https://www.androidhive.info/2016/01/android-working-with-recycler-view/
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private List<Device> deviceList;

    public Tab1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//Tab1Fragment.this inside LinearLayoutManager

        deviceList = new ArrayList<>();
        for(int index = 0; index < 11; index++){
            Device myDevice = new Device("Device #: " + index, "Location: " + index, "Date: " + index);
            deviceList.add(myDevice);
        }
        recyclerViewAdapter = new MyRecyclerViewAdapter(deviceList, getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);

        FloatingActionButton addDeviceFab = view.findViewById(R.id.addDeviceFab);
        addDeviceFab.setOnClickListener((View v) ->{
            //https://stackoverflow.com/questions/30752547/listener-can-be-replaced-with-lambda
            Toast.makeText(getActivity(), "Add Device FAB clicked", Toast.LENGTH_SHORT).show();
            startCustomAlertDialog();
        });




        // figuring out how to click on individual elements in recyclerview
        // https://medium.com/@harivigneshjayapalan/android-recyclerview-implementing-single-item-click-and-long-press-part-ii-b43ef8cb6ad8
        // https://github.com/Hariofspades/CustomRecyclerView/blob/master/app/src/main/java/com/hariofspades/customrecyclerview/MainActivity.java
        recyclerView.addOnItemTouchListener(new MainActivity.RecyclerTouchListener(getActivity(),
                recyclerView, new MainActivity.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                Toast.makeText(getActivity(), "Single Click on position: "+position,
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position: "+position,
                        Toast.LENGTH_LONG).show();
                deviceList.remove(position);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        }));




        return view;
    }

    public void startCustomAlertDialog(){
        // https://www.youtube.com/watch?v=plnLs6aST1M
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // android.R.style.Theme_Black_NoTitleBar_Fullscreen or android.R.style.Theme_Light

        builder.setTitle("Enter device information below:");

        // Need subView because you are referencing the view in custom_alert_dialog.xml, not the main view
        View subView = getLayoutInflater().inflate(R.layout.custom_alert_dialog,null);
        final EditText deviceEdit = subView.findViewById(R.id.deviceEditText);
        final EditText locationEdit = subView.findViewById(R.id.locationEditText);

        builder.setPositiveButton("Sounds good!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                String deviceEditAlertDialog = deviceEdit.getText().toString();
                String locationEditAlertDialog = locationEdit.getText().toString();

                Toast.makeText(getActivity(), "Device number is: "+deviceEditAlertDialog+ "\nLocation is: "+locationEditAlertDialog, Toast.LENGTH_LONG).show();

                SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yyyy");
                String date = sdf.format(new Date());

                Device myDevice = new Device("Device #: " + deviceEditAlertDialog, "Location: " + locationEditAlertDialog, "Date: " + date);
                deviceList.add(myDevice);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss(); // dialog.cancel() can also work
            }
        });
        // For custom alert dialog
        builder.setView(subView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }







}



