package ptrgags.sensorlab;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Get the accelerometer
        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        sensorManager.unregisterListener(this);
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_FASTEST);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            float [] gravity = event.values;
            ((TextView) findViewById(R.id.gravityX)).setText(getString(R.string.gravityX, gravity[0]));
            ((TextView) findViewById(R.id.gravityY)).setText(getString(R.string.gravityY, gravity[1]));
            ((TextView) findViewById(R.id.gravityZ)).setText(getString(R.string.gravityZ, gravity[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Do nothing
    }
}
