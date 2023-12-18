package iestrassierra.pmdm.preferencias;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Esta actividad no utiliza setContentView()!!

        //BARRA DE ACCIÓN//
        //Mostramos el botón 'home' (flecha atrás)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Mostamos título
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //Establecemos el contenido del título
        getSupportActionBar().setTitle("Preferencias de usuario");

        //Cargamos el fragmento de preferencias
        getSupportFragmentManager().beginTransaction()
                //El recurso 'android.R.id.content' es la ventana activa de la aplicación
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //El recurso 'android.R.id.home' es el botón 'home' (flecha atrás) en la barra de acción
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    /////////// Clase del fragmento de preferencias ///////////
    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.xml_preferences, rootKey);
        }
    }

}