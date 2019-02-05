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

public class IdentificationActivity extends AppCompatActivity  {

    ImageView imageView;
    TextView textView;

    static{
        System.loadLibrary("tensorflow_inference");
    }

    private static final String MODEL_FILE = "file:///android_asset/Bug_Detector.pb";
    private static final String INPUT_NODE = "reshape_1_input";
    private static final long[] INPUT_SHAPE = {1,3072};
    private static final String OUTPUT_NODE = "dense_2/Softmax";
    public TensorFlowInferenceInterface inferenceInterface; //this is private in udemy code

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identification);

        imageView = findViewById(R.id.image_view);
        textView = findViewById(R.id.results_text_view);


        //udemy code
        //inferenceInterface = new TensorFlowInferenceInterface(getAssets(), MODEL_FILE);


        //Cannot call buttons any other way in fragment class; must use onClickListeners
        //https://stackoverflow.com/questions/38942843/android-method-is-never-used-warning-for-onclick-method
        Button nextImageButton = findViewById(R.id.next_image_button);
        Button guessImageButton = findViewById(R.id.guess_image_button);
        nextImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //https://stackoverflow.com/questions/43364776/java-lang-illegalstateexception-could-not-find-method-in-a-parent-or-ancestor-c?rq=1
                imageIDsIndex = (imageIDsIndex >= 9)?0:imageIDsIndex+1;
                Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), imageIDs[imageIDsIndex]);
                displayImageBitmap = Bitmap.createScaledBitmap(imageBitmap, 32, 32, true);
                imageView.setImageBitmap(displayImageBitmap);
            }
        });
        guessImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                float[] pixelBuffer = convertImageToFloatArray();
//                float[] results = performInference(pixelBuffer);
//                displayResults(results);
            }
        });



        //https://stackoverflow.com/questions/47429389/i-cant-use-getassets-method-without-mainactivity?noredirect=1&lq=1
//        inferenceInterface = new TensorFlowInferenceInterface(getAssets(), MODEL_FILE);
        InitSession();



        // Inflate the layout for this fragment
        //https://www.youtube.com/watch?v=fF8f3BDDudo     How to Use findViewById in Fragment in Android - Navigation Drawer
        //return view;
    }//end onCreate

    TensorFlowInferenceInterface InitSession(){
        //https://www.programcreek.com/java-api-examples/?class=org.tensorflow.contrib.android.TensorFlowInferenceInterface&method=initializeTensorFlow
        inferenceInterface = new TensorFlowInferenceInterface();
        inferenceInterface.initializeTensorFlow(getAssets(),MODEL_FILE);
        return inferenceInterface;
    }

    private float[] convertImageToFloatArray(){
        int[] intArray = new int[1024];
        displayImageBitmap.getPixels(intArray, 0, 28, 0, 0, 32, 32);
        float[] floatArray = new float[3072];
        for(int i = 0; i<1024; i++){
            floatArray[i] = ((intArray[i] >> 16) & 0xff) / 255.0f;
            floatArray[i+1] = ((intArray[i] >> 8) & 0xff) / 255.0f;
            floatArray[i+2] = (intArray[i] & 0xff) / 255.0f;
        }
        return floatArray;
    }

//    public float[] performInference(float[] pixelBuffer){
//
////        /// play with, can delete
////        AssetManager assetManager = getActivity().getAssets();
////        inferenceInterface = new TensorFlowInferenceInterface(assetManager, MODEL_FILE);
////        inferenceInterface = new TensorFlowInferenceInterface();
////        inferenceInterface.initializeTensorFlow(getActivity().getAssets(),MODEL_FILE);
////        InitSession();
////        ///
//
//        inferenceInterface.feed(INPUT_NODE, pixelBuffer, INPUT_SHAPE);
//        inferenceInterface.run(new String[] {OUTPUT_NODE});
//        float[] results = new float[2];
//        inferenceInterface.fetch(OUTPUT_NODE, results);
//        return results;
//    }

    private void displayResults(float[] results){
        if(results[0] > results[1]){
            textView.setText("Model predicts: Bed bug");
        } else if(results[0] < results[1]){
            textView.setText("Model predicts: Cockroach");
        } else{
            textView.setText("Model predicts: Neither");
        }
    }


}

