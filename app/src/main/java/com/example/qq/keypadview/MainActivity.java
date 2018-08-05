package com.example.qq.keypadview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PinLockView";
    private String passCode = "123456";
    private PinLockView pinLockView;
    private IndicatorDots indicatorDots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pinLockView = findViewById(R.id.pin_lock_view);
        indicatorDots = findViewById(R.id.indicator_dots);

        pinLockView.attachIndicatorDots(indicatorDots);
        pinLockView.setPinLength(6);
        pinLockView.setTextColor(ContextCompat.getColor(this, R.color.white));
        indicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);

        pinLockView.setPinLockListener(new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                if(pin.equals(passCode)){
                    Toast.makeText(getApplicationContext(),"Login complete",Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Pin Login complete: " + pin);
                }else {
                    Log.d(TAG, "Pin Login false: " + pin);
                }

            }

            @Override
            public void onEmpty() {
                Log.d(TAG, "Pin empty");
            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {
                Log.d(TAG, "Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
            }
        });
    }
}
