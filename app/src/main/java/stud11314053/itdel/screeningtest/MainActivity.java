package stud11314053.itdel.screeningtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText inputUsername = null;
    private Button btnNext;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private final String KEY_NAME = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputUsername=(EditText) findViewById(R.id.username);
        btnNext = (Button) findViewById(R.id.btnNext);

        pref = getSharedPreferences("mypreferences", MODE_PRIVATE);
        final EditText Name = (EditText) findViewById(R.id.username);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String name = Name.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    editor = pref.edit();
                    editor.putString(KEY_NAME, name);
                    editor.commit();
                    Intent next = new Intent(getApplicationContext(),MenuActivity.class);
                    next.putExtra("username",inputUsername.getText().toString());
                    startActivity(next);
                }
            }
        });
    }


}
