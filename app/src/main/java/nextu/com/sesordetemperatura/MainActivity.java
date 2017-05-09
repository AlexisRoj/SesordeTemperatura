package nextu.com.sesordetemperatura;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView temperatura;
    private ImageView imagen;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = (ImageView) findViewById(R.id.imagen);
        temperatura = (TextView) findViewById(R.id.temperatura);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        String valor="";
        NumberFormat numberFormat = new DecimalFormat("#0.00");
        valor += "\n"+numberFormat.format(sensorEvent.values[0]) + " °C";
        valor += "\n"+numberFormat.format((9*(sensorEvent.values[0])/5)+32) +" °F";
        valor += "\n"+numberFormat.format((sensorEvent.values[0])+273.15) +" °K";

        if(sensorEvent.values[0]>25){
            imagen.setBackgroundResource(R.drawable.sol);
        }
        else{
            imagen.setBackgroundResource(R.drawable.nieve);
        }
        temperatura.setText(valor);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
