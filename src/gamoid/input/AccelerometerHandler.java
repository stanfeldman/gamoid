package gamoid.input;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerHandler implements SensorEventListener 
{
	public AccelerometerHandler(Context context)
	{
		SensorManager manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		Sensor accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		if(accelerometer != null)
			manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
	}
	
	public void onSensorChanged(SensorEvent event) 
	{
		accelX = event.values[0];
		accelY = event.values[1];
		accelZ = event.values[2];
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) 
	{
	}
	
	public float getAccelX() {
		return accelX;
	}

	public float getAccelY() {
		return accelY;
	}

	public float getAccelZ() {
		return accelZ;
	}

	private float accelX;
	private float accelY;
	private float accelZ;
}
