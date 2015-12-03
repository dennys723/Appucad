package sv.edu.ucad.appucad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

public class NuevoUsuario extends AppCompatActivity implements TextWatcher{

        //atributos
        EditText txtnombres;
        EditText txtapellidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylayout_nuevousuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtnombres = (EditText)findViewById(R.id.nombreusu);
        txtapellidos = (EditText)findViewById(R.id.apellidouso);
        //validando los campos del formulario
        txtnombres.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus){
                    if (txtnombres.getText().length()<3)
                    {
                        txtnombres.setError("La longitud del Nombre debe ser mayor a 3 caracteres");
                    }
                }
            }
         });
        txtapellidos.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    if (txtapellidos.getText().length()<3)
                    {
                        txtapellidos.setError("la longotud del Apellido debe ser mayur a 3 cacatreres");
                    }
                }
            }
        });

        dbconection = new SQLcontrolador(this);
        dbconection.abrirBaseDeDatos();

    } //Fin del onCreate

        SQLcontrolador dbconection;

    public void onNuevoUsoClick(View v){
        if (v.getId()==(R.id.guardarusu))
        {
            EditText nombre = (EditText)findViewById(R.id.nombreusu);
            EditText apellido = (EditText)findViewById(R.id.apellidouso);
            EditText edad = (EditText)findViewById(R.id.edad);
            EditText email = (EditText)findViewById(R.id.emailusu);
            EditText usuario = (EditText)findViewById(R.id.nickname);
            EditText contrasenia = (EditText)findViewById(R.id.contrasenia);
            EditText contrasenia2 = (EditText)findViewById(R.id.contrasenia2);


            String contrastr = contrasenia.getText().toString();
            String contrastr2 = contrasenia2.getText().toString();
            if (!contrastr.equals(contrastr2)){
                Snackbar.make(v, "LAS CONTRASEÑAS NO COINCIDEN!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else
            {
                //insertar los datos en la Bd
                String nombrestr = nombre.getText().toString();
                String apellstr = apellido.getText().toString();
                String edadstr = edad.getText().toString();
                String emailstr = email.getText().toString();
                String usostr = usuario.getText().toString();
                String contrastrin = contrasenia.getText().toString();
                dbconection.insertUsuario(nombrestr, apellstr,emailstr,edadstr,usostr,contrastrin);
                dbconection.cerrar();
                Toast.makeText(this,"Usuario creado correctamente",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getBaseContext(),PpalActivity.class);
                //.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        }else if (v.getId()==(R.id.cancelar)){
            txtnombres.setText("");
            txtapellidos.setText("");
            Intent i = new Intent(getBaseContext(),PpalActivity.class);
                    //.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
            startActivity(i);
            finish();
            dbconection.cerrar();
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
