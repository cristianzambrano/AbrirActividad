package uteq.solutions.abriractividad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class WSWithHeaders extends AppCompatActivity implements Asynchtask
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wswith_headers);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<String> lstBancos = new ArrayList<String> ();
        JSONArray JSONlista =  new JSONArray(result);

        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco=  JSONlista.getJSONObject(i);
            lstBancos.add(banco.getString("name").toString());
        }
        TextView txtver = findViewById(R.id.txtlista);
        txtver.setText(lstBancos.toString());
    }

    public void btnwebservice (View view){
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://api-uat.kushkipagos.com/transfer/v1/bankList",
        datos, WSWithHeaders.this, WSWithHeaders.this);
        ws.execute("GET", "Public-Merchant-Id", "84e1d0de1fbf437e9779fd6a52a9ca18");

    }
    public void  btngooglevolley(View view){
        // Instantiate the RequestQueue.

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api-uat.kushkipagos.com/transfer/v1/bankList";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<String> lstBancos = new ArrayList<String> ();
                        JSONArray JSONlista = null;
                        try {
                            JSONlista = new JSONArray(response);
                            for(int i=0; i< JSONlista.length();i++){
                                JSONObject banco=  JSONlista.getJSONObject(i);
                                lstBancos.add(banco.getString("name").toString());
                             }
                             TextView txtvolley = findViewById(R.id.txtlista);
                            txtvolley.setText(lstBancos.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Toast.makeText(getApplicationContext(),  error.getMessage(), Toast.LENGTH_LONG).show();
                    TextView txtvolley = findViewById(R.id.txtlista);
                    txtvolley.setText(error.networkResponse.statusCode +"  Error");
                }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headerMap = new HashMap<String, String>();
                headerMap.put("Public-Merchant-Id", "84e1d0de1fbf437e9779fd6a52a9ca18");
                return headerMap;
            }
        };


        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}