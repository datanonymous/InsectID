package ko.alex.insectid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment {

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
        for(int index = 0; index < 20; index++){
            Device myDevice = new Device("Device #: " + index, "Location #: " + index, "Date: " + index);
            deviceList.add(myDevice);
        }
        recyclerViewAdapter = new MyRecyclerViewAdapter(deviceList, getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

}



