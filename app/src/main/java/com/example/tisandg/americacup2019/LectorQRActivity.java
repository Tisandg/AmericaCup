package com.example.tisandg.americacup2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class LectorQRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    //Escaner codigo QR
    private ZXingScannerView scannerview;
    private String TAG = "LectorQRFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_qr);

        Log.d(TAG,"Creando LectorQrActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        scannerview = new ZXingScannerView(this);
        scannerview= new ZXingScannerView(this);
        setContentView(scannerview);
        scannerview.setResultHandler(this);
        scannerview.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.d(TAG, "Resultado recibido: "+result.getText());
        activityTerminada(result.getText());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        retrocedio();
        return false;
    }

    public void retrocedio(){
        Intent regresoMain = new Intent(this, MainActivity.class);
        setResult(RESULT_CANCELED, regresoMain);
        finish();
    }

    public void activityTerminada(String codigo){
        Intent regresoMain = new Intent(this, MainActivity.class);
        regresoMain.putExtra("Data", codigo);
        setResult(RESULT_OK, regresoMain);
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        scannerview.setResultHandler(this);
        scannerview.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerview.stopCamera();
    }

}
