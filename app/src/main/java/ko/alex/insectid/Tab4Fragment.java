package ko.alex.insectid;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class Tab4Fragment extends Fragment {

    ImageView imageView;
    TextView textView;

    static{
        System.loadLibrary("tensorflow_inference");
    }

    private static final String MODEL_FILE = "file:///android_asset/Bug_Detector.pb";
    private static final String INPUT_NODE = "reshape_1_input";
    private static final long[] INPUT_SHAPE = {1,3072};
    private static final String OUTPUT_NODE = "dense_2/Softmax";
    private TensorFlowInferenceInterface inferenceInterface;

    int imageIDsIndex = 9;
    int[] imageIDs = {
            R.drawable.bedbug0,
            R.drawable.bedbug1,
            R.drawable.bedbug2,
            R.drawable.bedbug6,
            R.drawable.bedbug7,
            R.drawable.cockroach3,
            R.drawable.cockroach5,
            R.drawable.cockroach8,
            R.drawable.cockroach19,
            R.drawable.cockroach20
    };
    Bitmap displayImageBitmap;

    public Tab4Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab4, container, false);

        imageView = view.findViewById(R.id.image_view);
        textView = view.findViewById(R.id.results_text_view);



        Button nextImageButton = view.findViewById(R.id.next_image_button);
        Button guessImageButton = view.findViewById(R.id.guess_image_button);
        nextImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "nextImageButton", Toast.LENGTH_LONG).show();
                //https://stackoverflow.com/questions/43364776/java-lang-illegalstateexception-could-not-find-method-in-a-parent-or-ancestor-c?rq=1
                Toast.makeText(getActivity(), "String", Toast.LENGTH_LONG).show();
                imageIDsIndex = (imageIDsIndex >= 9)?0:imageIDsIndex+1;
                Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), imageIDs[imageIDsIndex]);
                displayImageBitmap = Bitmap.createScaledBitmap(imageBitmap, 32, 32, true);
                imageView.setImageBitmap(displayImageBitmap);
            }
        });
        guessImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "guessImageButton", Toast.LENGTH_LONG).show();
            }
        });

        

        //https://stackoverflow.com/questions/47429389/i-cant-use-getassets-method-without-mainactivity?noredirect=1&lq=1
        //inferenceInterface = new TensorFlowInferenceInterface(getActivity().getAssets(), MODEL_FILE);
        InitSession();

        // Inflate the layout for this fragment
        //https://www.youtube.com/watch?v=fF8f3BDDudo     How to Use findViewById in Fragment in Android - Navigation Drawer
        return view;
    }

    TensorFlowInferenceInterface InitSession(){
        //https://www.programcreek.com/java-api-examples/?class=org.tensorflow.contrib.android.TensorFlowInferenceInterface&method=initializeTensorFlow
        inferenceInterface = new TensorFlowInferenceInterface();
        inferenceInterface.initializeTensorFlow(getActivity().getAssets(),MODEL_FILE);
        return inferenceInterface;
    }



}
