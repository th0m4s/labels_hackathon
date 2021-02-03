package dev.th0m4s.labelshackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentContainerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.util.SparseArray;
import android.view.HapticFeedbackConstants;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.IOException;

public class ScanActivity extends AppCompatActivity {

    SurfaceView cameraView;
    CameraSource cameraSource;

    SlidingUpPanelLayout panelLayout;

    FragmentContainerView resultContainer;
    ResultFragment resultFragment;

    String lastDetected = "";
    int defaultHeight = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        cameraView = findViewById(R.id.scanCameraView);
        resultFragment = (ResultFragment) getSupportFragmentManager().findFragmentById(R.id.resultFragment);
        resultContainer = findViewById(R.id.resultFragment);

        panelLayout = findViewById(R.id.scanLayoutPanel);
        defaultHeight = panelLayout.getPanelHeight();
        setPanelHeight(0);

        BarcodeDetector barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.EAN_8 | Barcode.EAN_13)
                        .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .setAutoFocusEnabled(true)
                .setRequestedFps(24f)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                startCamera();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(BarcodeDetector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    String code = barcodes.valueAt(0).displayValue.trim();
                    if(!code.equals(lastDetected)) {
                        lastDetected = code;
                        setPanelHeight(defaultHeight);
                        resultFragment.loadResult(code, true);
                    }
                }
            }
        });
    }

    private void setPanelState(SlidingUpPanelLayout.PanelState panelState) {
        runOnUiThread(() -> {
            panelLayout.setPanelState(panelState);
        });
    }

    private void setPanelHeight(int height) {
        runOnUiThread(() -> {
            panelLayout.setPanelHeight(height);
        });
    }

    private void startCamera() {
        try {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                cameraSource.start(cameraView.getHolder());
            } else if(android.os.Build.VERSION.SDK_INT >= 23) {
                requestPermissions( new String[] { Manifest.permission.CAMERA},4292);
            } else finish();
        } catch (IOException ie) {
            Log.e("CAMERA SOURCE", ie.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 4292:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCamera();
                } else finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(panelLayout.getPanelState() != SlidingUpPanelLayout.PanelState.COLLAPSED)
            panelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        else if(panelLayout.getPanelHeight() > 0) {
            lastDetected = "";
            panelLayout.setPanelHeight(0);
        } else super.onBackPressed();
    }
}