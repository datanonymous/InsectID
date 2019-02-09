package ko.alex.insectid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerViewAdapter extends
        RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Device> devices;
    private Context context;

    public MyRecyclerViewAdapter(List<Device> devices, Context context){
        this.devices = devices;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Device device = devices.get(position);
        viewHolder.deviceID.setText(device.getDeviceID());
        viewHolder.descriptionID.setText(device.getDescriptionID());
        viewHolder.lastServicedID.setText(device.getLastServicedID());
    }

    @Override
    public int getItemCount() {
        return devices.size();//returns elements in persons list
    }

    //inner class
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView deviceID;
        public TextView descriptionID;
        public TextView lastServicedID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            deviceID = itemView.findViewById(R.id.deviceID); //deviceID from device_list_item.xml
            descriptionID = itemView.findViewById(R.id.descriptionID); //descriptionID from device_list_item.xml
            lastServicedID = itemView.findViewById(R.id.lastServicedID); //lastServicedID from device_list_item.xml

        }
    }

}

