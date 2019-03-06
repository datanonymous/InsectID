package ko.alex.insectid;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
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
            Device myDevice = new Device("Device #: " + index, "Location #: " + index, "Date: " + index);
            deviceList.add(myDevice);
        }
        recyclerViewAdapter = new MyRecyclerViewAdapter(deviceList, getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);

        FloatingActionButton addDeviceFab = view.findViewById(R.id.addDeviceFab);
        addDeviceFab.setOnClickListener((View v) ->{
            //https://stackoverflow.com/questions/30752547/listener-can-be-replaced-with-lambda
            Toast.makeText(getActivity(), "Add Device FAB clicked", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

}



