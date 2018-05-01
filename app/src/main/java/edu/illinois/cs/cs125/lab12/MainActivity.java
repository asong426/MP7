package edu.illinois.cs.cs125.lab12;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


/**
 * Main class for our UI design lab.
 */
public final class MainActivity extends AppCompatActivity implements LocationListener {


    private LocationManager locationManager;
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Lab12:Main";

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;

    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the queue for our API requests
        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_main);

        startAPICall();

        final Button sinx = findViewById((R.id.sinx));
        sinx.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Calendar calendar = Calendar.getInstance();
                String currentDate = "Today's Date: " + DateFormat.getDateInstance().format(calendar.getTime());

                TextView textView = findViewById(R.id.text_view_date);
                textView.setText(currentDate);
            }
        });
        final Button cosx = findViewById((R.id.cosx));
        cosx.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                String currentTime = "Current Time: " + format.format(calendar.getTime());

                TextView textView = findViewById(R.id.text_view_date);
                textView.setText(currentTime);
            }
        });

        final Button tanx = findViewById((R.id.tanx));
        tanx.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION }, 7);
                }
                else {
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    onLocationChanged(location);
                }

            }
        });
    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Make a call to the weather API.
     */
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?zip=61820,us&appid="
                            + BuildConfig.API_KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, response.toString(2));
                            } catch (JSONException ignored) { }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.e(TAG, error.toString());
                        }
                    });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        TextView textView = findViewById(R.id.text_view_date);
        Log.i("MainActivity", "Longitude: " + longitude + ", Latitude: " + latitude);
        textView.setText("Longitude: " + longitude + "\n" + "Latitude: " + latitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
