package sv.edu.ucad.appucad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLcontrolador {
    private DBhelper dbhelper;
    private Context micontext;
    private SQLiteDatabase database;

    public SQLcontrolador(Context c){
        micontext = c;
    }

    public SQLcontrolador abrirBaseDeDatos() throws SQLException{
     dbhelper = new DBhelper(micontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar(){
        dbhelper.close();
    }

    public void insertUsuario(String nombre, String apellidos,String email, String edad, String user, String pass)
    {
        //dbhelper = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBhelper.CN_NAME,nombre);
        values.put(DBhelper.CN_LASTNAME,apellidos);
        values.put(DBhelper.CN_EMAIL,email);
        values.put(DBhelper.CN_USER,user);
        values.put(DBhelper.CN_PASS,pass);
        values.put(DBhelper.CN_AGE,edad);
        database.insert(DBhelper.USUARIOS_TABLA, null, values);

        /*values.put(CN_NAME, d.getNombres());
        values.put(CN_LASTNAME, d.getApellidos());
        values.put(CN_EMAIL, d.getEmail());t
        values.put(CN_USER, d.getUsuario());
        values.put(CN_PASS, d.getContasenia());
        values.put(CN_AGE, d.getEdad());*/

    }
    //Cursor para leer los registros de la tabla usuarios
    public Cursor leerUsuarios() {
        String[] todasLasColumnas = new String[]{
                DBhelper.CN_ID,DBhelper.CN_NAME,DBhelper.CN_LASTNAME,DBhelper.CN_EMAIL,
                DBhelper.CN_USER,DBhelper.CN_PASS,DBhelper.CN_AGE
        };
        Cursor c = database.query(DBhelper.USUARIOS_TABLA, todasLasColumnas, null, null, null, null, null);
        if (c != null){
            c.moveToFirst();
        }
            return c;
    }

}
