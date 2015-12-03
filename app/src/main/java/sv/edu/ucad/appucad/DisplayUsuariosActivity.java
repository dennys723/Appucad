package sv.edu.ucad.appucad;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.Button;

public class DisplayUsuariosActivity extends AppCompatActivity {
    private SQLcontrolador dbconection;
    private ListView ListUsuarios;
    SimpleCursorAdapter adapter;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_usuarios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbconection = new SQLcontrolador(this);
        dbconection.abrirBaseDeDatos();
        ListUsuarios = (ListView)findViewById(R.id.listViewUsuarios);

        //Tomar los valores de la base de datos, se ponen en un Cursor y despues en el adapter
        Cursor cursor = dbconection.leerUsuarios();

        String [] from = new String[]{
               DBhelper.CN_ID,DBhelper.CN_NAME,DBhelper.CN_LASTNAME,
               DBhelper.CN_EMAIL,DBhelper.CN_USER,DBhelper.CN_PASS,
               DBhelper.CN_AGE
        };
        int[] to = new int[]{
                R.id.usu_id,R.id.usu_nombre,R.id.usu_ape,
                R.id.usu_email,R.id.usu_nickname,R.id.usu_pass,
                R.id.uso_edad
        };
        adapter = new SimpleCursorAdapter(
                DisplayUsuariosActivity.this,R.layout.formato_fila_usuarios,cursor,from,to,0);
        //adapter.notifyDataSetChanged();
        ListUsuarios.setAdapter(adapter);
        //dbconection.cerrar();
    } //Fin del onCreate
} //Fin de la clase
