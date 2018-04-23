package edu.illinois.cs.cs125.lab12;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main class for our UI design lab.
 */
public final class MainActivity extends AppCompatActivity {
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
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "sinx", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button cosx = findViewById((R.id.cosx));
        cosx.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "cosx", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button tanx = findViewById((R.id.tanx));
        tanx.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "tanx", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button one = findViewById((R.id.one));
        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "one", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button two = findViewById((R.id.two));
        two.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "two", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button three = findViewById((R.id.three));
        three.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "three", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button four = findViewById((R.id.four));
        four.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "four", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button five = findViewById((R.id.five));
        five.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "five", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button six = findViewById((R.id.six));
        six.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "six", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button seven = findViewById((R.id.seven));
        seven.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "seven", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button eight = findViewById((R.id.eight));
        eight.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "eight", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button nine = findViewById((R.id.nine));
        nine.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "nine", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button zero = findViewById((R.id.zero));
        zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "zero", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button plus = findViewById((R.id.plus));
        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "plus", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button minus = findViewById((R.id.minus));
        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "minus", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        final Button ac = findViewById((R.id.AC));
        ac.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "AC", Toast.LENGTH_SHORT);
                toast.show();
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
}
