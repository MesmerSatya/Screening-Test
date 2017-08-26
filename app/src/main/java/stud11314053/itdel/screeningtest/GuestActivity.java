package stud11314053.itdel.screeningtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GuestActivity extends Activity {
    private GridView listGuest;
    private ArrayList<DaftarGuest> namaBirthdateGuest;
    private Context context;
    private int positionSelected;
    private GuestAdapter adapter;

    private class requestListGuest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... endpoint_url) {
            // Membuat objek http dan url
            URL endpoint = null;
            try {
                endpoint = new URL(endpoint_url[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection askEndpoint = null;
            try {
                assert endpoint != null;
                askEndpoint = (HttpURLConnection) endpoint.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Membaca response dari request endpoint dalam bentuk buffer stream
            BufferedReader reader = null;
            try {
                assert askEndpoint != null;
                reader = new BufferedReader(new InputStreamReader(askEndpoint.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Ubah stream buffer dari response ke string
            // String response doInBackground
            String response = "";
            String inputLineReader;
            try {
                assert reader != null;
                while ((inputLineReader = reader.readLine()) != null) {
                    response += inputLineReader;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Tutup koneksi
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        private void getListGuestJSON (String response) {
            // Melakukan parsing string response dalam format json, dan dimasukkan ke list vector listGuest
            JSONArray guest = null;
            try {
                guest = new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            assert guest != null;
            for (int i=0; i<guest.length(); i++) {
                JSONObject itemGuest = null;
                try {
                    itemGuest = guest.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String nama = "";
                try {
                    assert itemGuest != null;
                    nama = itemGuest.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String birthday = "";
                try {
                    birthday = itemGuest.getString("birthdate");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                namaBirthdateGuest.add(new DaftarGuest(nama, birthday));
            }
        }

        @Override
        protected void onPostExecute(String result) {
            getListGuestJSON(result);
            adapter = new GuestAdapter(context, namaBirthdateGuest);
            listGuest.setAdapter(adapter);
            listGuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    positionSelected = position;
                    finish();
                }
            });

            listGuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    positionSelected = position;
                    finish();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        listGuest = (GridView) findViewById(R.id.listGuest);
        namaBirthdateGuest = new ArrayList<>();
        context = GuestActivity.this;

        // Merequest data guest dari endpoint kemudian isi list guest
        requestListGuest requestList = new requestListGuest();
        requestList.execute("http://dry-sierra-6832.herokuapp.com/api/people");
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("nama guest", namaBirthdateGuest.get(positionSelected).getNama());
        intent.putExtra("birthday guest", namaBirthdateGuest.get(positionSelected).getBirthday());
        setResult(RESULT_OK, intent);
        super.finish();
    }
}
