package stud11314053.itdel.screeningtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Button btnGuest;
    private static final int REQUEST_CODE_GUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnGuest = (Button) findViewById(R.id.btnGuest);

        TextView txtName = (TextView) findViewById(R.id.txtName);
        Button button = (Button) findViewById(R.id.btnEvent);
        Intent intent = getIntent();

        // mengambil data dari intent
        String username = intent.getStringExtra("username");
        button.setText(intent.getStringExtra("btnEvent") == null ? "Pilih Event" : intent.getStringExtra("btnEvent"));
        Log.e("Second Screen", username);
        // menampilkan data yang diambil
        txtName.setText(username);

        // mengecek username apakah palindrom atau bukan
        if (isNamaPalindrom(username)) {
            Toast.makeText(getBaseContext(),"is palindrome",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(),"not palindrome",Toast.LENGTH_SHORT).show();
        }
    }

    public void openEvent( View view){
        Intent menuIntent = new Intent(getApplicationContext(),EventActivity.class);
        Intent intent = getIntent();

        // mengambil data dari intent
        String username = intent.getStringExtra("username");
        menuIntent.putExtra("username",username);
        startActivity(menuIntent);
        finish();
    }

    public void openGuest(View view) {
        Intent intent = new Intent(this, GuestActivity.class);
        startActivityForResult(intent, REQUEST_CODE_GUEST);
    }
    private boolean isNamaPalindrom (String nama) {
        int indexDepan = 0;
        int indexBelakang = nama.length() - 1;
        boolean palindrom = true;

        do {
            if (nama.charAt(indexDepan) != nama.charAt(indexBelakang)) {
                palindrom = false;
            }
            indexDepan++;
            indexBelakang--;
        } while (indexDepan <= indexBelakang);

        return palindrom;
    }

    private boolean isMonthPrime(int month) {
        boolean Prime;
        switch (month) {
            case 2 :
            case 3 :
            case 5 :
            case 7 :
            case 11 :
                Prime = true; break;
            default :
                Prime = false; break;
        }
        return Prime;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_GUEST) {                                     // Guest Activity, request code 2
                if (data.hasExtra("nama guest")) {
                    String namaGuest = data.getExtras().getString("nama guest");
                    btnGuest.setText(namaGuest);
                    if (data.hasExtra("birthday guest")) {
                        String birthdayGuest = data.getExtras().getString("birthday guest");
                        String[] parseBirthdayGuest = birthdayGuest.split("[-]");
                        int dayBirthdayGuest = Integer.parseInt(parseBirthdayGuest[2]);         // Dapat hari dari birthday guest
                        int monthBirthdayGuest = Integer.parseInt(parseBirthdayGuest[1]);       // Dapat bulan dari birthday guest

                        String kategoriDay;
                        if ((dayBirthdayGuest % 2 == 0) && (dayBirthdayGuest % 3 == 0)) {
                            kategoriDay = "iOS";
                        } else {
                            if (dayBirthdayGuest % 2 == 0) {
                                kategoriDay = "blackberry";
                            } else if (dayBirthdayGuest % 3 == 0) {
                                kategoriDay = "android";
                            } else {
                                kategoriDay = "feature phone";
                            }
                        }

                        String kategoriMonth;
                        if (isMonthPrime(monthBirthdayGuest)) {
                            kategoriMonth = "prime";
                        } else {
                            kategoriMonth = "not prime";
                        }

                        String toastMessage = kategoriDay + " and " + kategoriMonth;
                        Toast.makeText(getBaseContext(), toastMessage, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
