package iestrassierra.pmdm.preferencias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    private EditText etDificultad, etBD;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //Editar preferencias desde código
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("bd_externa", true);
        editor.putString("nombre", "Por determinar");
        editor.putString("ip", "0.0.0.0");
        editor.apply();

        //de forma anónima
        sharedPreferences.edit().putString("dificultad", "Nula").apply();

        etDificultad = findViewById(R.id.etDificultad);
        etBD = findViewById(R.id.etBD);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Seteamos los EditText en onResume para cuando esta actividad pasa de estar en segundo plano
        //a primer plano. Así cuando se editan las preferencias siempre aparecerán los valores actualizados.
        String dificultad = sharedPreferences.getString("dificultad", "Desconocida");
        etDificultad.setText(dificultad);

        String bd = "Interna SQLite";
        if (sharedPreferences.getBoolean("bd_externa", true)) {
            bd = "Externa: " + sharedPreferences.getString("nombre", "Desconocida") + "@" +
                    sharedPreferences.getString("ip", "x.x.x.x");
        }
        etBD.setText(bd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        menu.setGroupVisible(R.id.it_group_preferencias,true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id == R.id.preferencias)
            startActivity(new Intent(this, PreferencesActivity.class));
        else if (id == R.id.it_salir)
            finishAffinity();
        return super.onOptionsItemSelected(item);
    }

}
