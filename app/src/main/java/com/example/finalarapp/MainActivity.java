package com.example.finalarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.Window;




import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.task.core.BaseOptions;
import org.tensorflow.lite.task.vision.detector.Detection;
import org.tensorflow.lite.task.vision.detector.ObjectDetector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hides the Title Bar from android studio theme
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        //Object Detector options
        ObjectDetector.ObjectDetectorOptions options =
                ObjectDetector.ObjectDetectorOptions.builder()
                        .setBaseOptions(BaseOptions.builder().useGpu().build())
                        .setMaxResults(1)
                        .build();


        //Open file from asset folder and read/write it to new file thats accessible on emulator
        File model1 = Environment.getExternalStorageDirectory();
        File dir = new File(model1.getAbsolutePath() + "/Documents/ARApp");
        dir.mkdirs();

        File model4 = new File(dir, "newmodel.tflite");
        try {
            model4.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream mngr = getAssets().open("app_model_v2.tflite");
            FileOutputStream outputStream = new FileOutputStream(model4, false);
            int read;
            byte[] bytes = new byte[mngr.available()];
            while ((read = mngr.read(bytes)) != -1){
                outputStream.write(bytes, 0, read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create Object Detector and Run Test Image
        try {
            ObjectDetector objectDetector = ObjectDetector.createFromFileAndOptions(model4, options);
            Bitmap input = BitmapFactory.decodeResource(getResources(), R.mipmap.modeltest1); //Converts manual image to bitmmap for input
            TensorImage image = TensorImage.fromBitmap(input);
            List<Detection> results = objectDetector.detect(image);


        } catch (IOException e) {
            e.printStackTrace();
        }






        //Creates Button to leave homepage
        Button homeButton = (Button) findViewById((R.id.home_page_button));
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TopicsPage.class));
            }
        });
    }





    }
