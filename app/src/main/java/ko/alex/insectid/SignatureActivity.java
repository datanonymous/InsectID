package ko.alex.insectid;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kyanogen.signatureview.SignatureView;

public class SignatureActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signature);

        SignatureView signatureView = findViewById(R.id.signature_view);
        FloatingActionButton fabSignature = findViewById(R.id.fabSignature);
        fabSignature.setOnClickListener((View v) ->{
            //https://stackoverflow.com/questions/30752547/listener-can-be-replaced-with-lambda
            //https://github.com/zahid-ali-shah/SignatureView
            signatureView.clearCanvas();
            Toast.makeText(getApplicationContext(), "Clear signature button pressed", Toast.LENGTH_SHORT).show();
        });

    }

}
