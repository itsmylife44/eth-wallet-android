package com.example.crimson.crimson;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.kenai.jffi.Main;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class ScanToSendActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {
    private BarcodeReader barcodeReader;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_to_send);
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString(MainActivity.SCANNED_ADDRESS, null);
        editor.commit();

        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);
    }



    @Override
    public void onScanned(Barcode barcode) {
        // play beep sound
        //Toast.makeText(getApplicationContext(), barcode.displayValue, Toast.LENGTH_LONG).show();
        Log.e("SCAN", "onScanned: " + barcode.displayValue);

        if(barcode.displayValue.length() > 5) {
            editor.putString(MainActivity.SCANNED_ADDRESS, barcode.displayValue);
            editor.commit();
            finish();
        }

        barcodeReader.playBeep();
    }

    @Override
    public void onScannedMultiple(List<Barcode> list) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String s) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getApplicationContext(), "Camera permission denied!", Toast.LENGTH_LONG).show();
    }
}
