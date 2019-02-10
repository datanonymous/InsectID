package ko.alex.insectid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3Fragment extends Fragment {

    TextView serviceCommentID;

    public Tab3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        //
        //
        //
        TextView serviceCommentID = view.findViewById(R.id.serviceCommentID);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yyyy");
        String date = sdf.format(new Date());
        serviceCommentID.setText("Current date is: " + date + "\nService Comment:\n"); //serviceCommentID.setHint("Current date is: " + date);

        return view;
    }



}
