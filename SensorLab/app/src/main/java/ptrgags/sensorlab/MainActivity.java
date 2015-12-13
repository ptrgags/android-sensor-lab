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
    private Sensor ambientTemp;
    private Sensor steps;
    private Sensor proximity;
    private Sensor ambientLight;
    private Sensor ambientPressure;
    private Sensor ambientRelHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Motion Sensors
        //acceleration = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        //gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        steps = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        //Position sensors
        //magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        //Ambient environment sensors
        ambientTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        ambientLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        ambientPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        ambientRelHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        registerListeners();
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
        registerListeners();
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
        else if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            ((TextView) findViewById(R.id.ambientTemp)).setText(getString(R.string.ambientTemp, event.values[0]));
        }
        else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            ((TextView) findViewById(R.id.ambientLight)).setText(getString(R.string.ambientLight, event.values[0]));
        }
        else if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            ((TextView) findViewById(R.id.ambientPressure)).setText(getString(R.string.ambientPressure, event.values[0]));
        }
        else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            ((TextView) findViewById(R.id.proximity)).setText(getString(R.string.proximity, event.values[0]));
        }
        else if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            ((TextView) findViewById(R.id.steps)).setText(getString(R.string.steps, event.values[0]));
        }
        else if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            ((TextView) findViewById(R.id.ambientRelHumidity)).setText(getString(R.string.ambientRelHumidity, event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Do nothing
    }


    public void registerListeners() {
        sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, ambientTemp, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, ambientLight, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, ambientPressure, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, ambientRelHumidity, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, steps, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_FASTEST);
    }
}
