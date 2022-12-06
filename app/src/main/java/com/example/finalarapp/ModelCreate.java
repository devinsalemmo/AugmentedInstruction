package com.example.finalarapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.os.Environment;
import android.content.res.AssetManager;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.task.core.BaseOptions;
import org.tensorflow.lite.task.vision.detector.Detection;
import org.tensorflow.lite.task.vision.detector.ObjectDetector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class ModelCreate extends AppCompatActivity {
    public File model4;


    public List<String> feedInput(ObjectDetector model, Bitmap input) {
        TensorImage image = TensorImage.fromBitmap(input); //Creates tensorImage from bitmap
        List<Detection> results = model.detect(image); //Detects image and gets results from model
        Detection example = results.get(0);
        RectF bounding_box = example.getBoundingBox(); //Parses results for bounding box
        List<Category> label = example.getCategories(); // Parses results for label
        Category real_label = label.get(0);
        String real_labell = real_label.getLabel();
        float x = bounding_box.centerX();
        float y = bounding_box.centerY();
        List<String> answers = new ArrayList<>();
        answers.add(Float.toString(x));
        answers.add(Float.toString(y));
        answers.add(real_labell);
        return answers;
    }
}


