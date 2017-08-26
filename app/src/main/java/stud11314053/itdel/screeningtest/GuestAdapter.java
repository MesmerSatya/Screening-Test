package stud11314053.itdel.screeningtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Satya on 26-Aug-17.
 */

public class GuestAdapter extends ArrayAdapter<DaftarGuest> {
    private final Context context;
    private final ArrayList<DaftarGuest> listGuest;

    public GuestAdapter(Context context, ArrayList<DaftarGuest> listGuest) {
        super(context, R.layout.daftar_guest, listGuest);
        this.context = context;
        this.listGuest = listGuest;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Bind row guest view dengan inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View GuestView = inflater.inflate(R.layout.daftar_guest, parent, false);

        // Bind komponen widget row view layout activity
        ImageView imageGuestView = (ImageView) GuestView.findViewById(R.id.guest_view);
        TextView nameGuestView = (TextView) GuestView.findViewById(R.id.nama_guest);

        // Setting komponen widget pembentuk row guest
        imageGuestView.setImageResource(R.drawable.suit);
        nameGuestView.setText(listGuest.get(position).getNama());

        return GuestView;
    }
}

