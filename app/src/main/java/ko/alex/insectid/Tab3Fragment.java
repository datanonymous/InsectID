package ko.alex.insectid;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kyanogen.signatureview.SignatureView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3Fragment extends Fragment {



    public Tab3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        TextView serviceCommentID = view.findViewById(R.id.serviceCommentID);

        SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yyyy");
        String date = sdf.format(new Date());
        serviceCommentID.setText("Current date is: " + date + "\nService Comment:\n"); //serviceCommentID.setHint("Current date is: " + date);

        FloatingActionButton fabSignature = view.findViewById(R.id.fabSignature);
        FloatingActionButton fabPDF = view.findViewById(R.id.fabPDF);
        FloatingActionButton fabSaveComment = view.findViewById(R.id.fabSaveComment);
        SignatureView signatureView = view.findViewById(R.id.signature_view);

        fabSignature.setOnClickListener((View v) ->{
            //https://stackoverflow.com/questions/30752547/listener-can-be-replaced-with-lambda
            //https://github.com/zahid-ali-shah/SignatureView
            signatureView.clearCanvas();
            Toast.makeText(getActivity(), "Clear signature button pressed", Toast.LENGTH_SHORT).show();
        });
        fabPDF.setOnClickListener((View v) ->{
            //https://stackoverflow.com/questions/30752547/listener-can-be-replaced-with-lambda
            Toast.makeText(getActivity(), "PDF button pressed", Toast.LENGTH_SHORT).show();
        });
        fabSaveComment.setOnClickListener((View v) ->{
            //https://stackoverflow.com/questions/30752547/listener-can-be-replaced-with-lambda
            String serviceComment = serviceCommentID.getText().toString();
            Toast.makeText(getActivity(), "Saved comment: \n" + serviceComment, Toast.LENGTH_SHORT).show();
        });
        //Other way of using onClickListener
        //https://stackoverflow.com/questions/30752547/listener-can-be-replaced-with-lambda
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "asdfasdf", Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }



}
