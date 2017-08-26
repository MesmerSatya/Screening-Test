package stud11314053.itdel.screeningtest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {
    ListView listEvent;

    private static final class Holder{
        public ImageView gambar;
        public TextView nama;
        public TextView tanggal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        listEvent = (ListView)findViewById(R.id.listEvent);

        final List<DaftarEvent> daftarEvents = new ArrayList<>();
        DaftarEvent daftarEvent1 = new DaftarEvent("","abc event","15 sept 2014");
        daftarEvents.add(daftarEvent1);
        DaftarEvent daftarEvent2 = new DaftarEvent("","Mesmer","17 sept 2017");
        daftarEvents.add(daftarEvent2);
        DaftarEvent daftarEvent3 = new DaftarEvent("","Satya","17 sept 2017");
        daftarEvents.add(daftarEvent3);
        DaftarEvent daftarEvent4 = new DaftarEvent("","Sinaga","17 sept 2017");
        daftarEvents.add(daftarEvent4);

        DaftarEventAdapter adapter = new DaftarEventAdapter(this,android.R.layout.simple_list_item_1, daftarEvents);
        listEvent.setAdapter(adapter);

        final Intent intentMenu = getIntent();

        listEvent.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DaftarEvent daftarEvent = daftarEvents.get(position);
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.putExtra("username",intentMenu.getStringExtra("username"));
                intent.putExtra("btnEvent",daftarEvent.getNama().toString());
                startActivity(intent);
            }
        });
    }

    private class DaftarEventAdapter extends ArrayAdapter<DaftarEvent>{
        private LayoutInflater mInfalter;

        public DaftarEventAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<DaftarEvent> objects) {
            super(context, resource, objects);

            mInfalter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View view = convertView;
            Holder holder;

            if(view == null){
                view = mInfalter.inflate(R.layout.daftar_event, parent, false);
                holder = new Holder();
                holder.gambar = (ImageView)view.findViewById(R.id.image);
                holder.nama = (TextView)view.findViewById(R.id.namaUser);
                holder.tanggal = (TextView)view.findViewById(R.id.txtTanggal);

                view.setTag(holder);
            }else{
                holder = (Holder) view.getTag();
            }

            DaftarEvent stream = getItem(position);

            holder.nama.setText(stream.getNama());
            holder.tanggal.setText(stream.getTanggal());

            return view;
        }
    }
}


