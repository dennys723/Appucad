package sv.edu.ucad.appucad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.PortUnreachableException;

public class DBhelper extends SQLiteOpenHelper{
    //el constructor necesita 2 parametros
    //informacion de la base de datos
    public static final String DB_NOMBRE = "dbdocentesucad.sqlite";
    public static final int DB_SCHEME_VERSION = 1;

    //informacion de la tabla USUARIOS
    public static final String USUARIOS_TABLA = "usuarios";
    public static final String CN_ID = "_id";
    public static final String CN_NAME = "nomusuario";
    public static final String CN_LASTNAME = "apeusuario";
    public static final String CN_EMAIL = "correousuario";
    public static final String CN_USER = "usuario";
    public static final String CN_PASS = "claveusuario";
    public static final String CN_AGE = "edadusuario";

    //Creo la tabla
    public static final String USUARIO_CREATETABLE = "create table " +USUARIOS_TABLA+ " ("
            + CN_ID + " integer not null primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_LASTNAME + " text not null,"
            + CN_EMAIL + " text,"
            + CN_USER + " text,"
            + CN_PASS + " text not null,"
            + CN_AGE + " text);";
    //INFORMACION DE LA TABLA
    public static final String TABLA_NOTAS = "notas";
    public static final String CNN_ID ="_id";
    public static final String CNN_NOTA = "tiponota";
    public static final String CNN_FECHA = "fechanota";
    public static final String CNN_COMENT = "comentnota";
    public static final String CNN_AUTORIDAD = "autoridad";
    public static final String CNN_USER = "usuario";
    //CREO LA TABLA
    public static final String CREATETABLE_NOTAS = "create table " +TABLA_NOTAS+ " ("
            + CNN_ID + " integer not null primary key autoincrement,"
            + CNN_NOTA + " text not null,"
            + CNN_FECHA + " text,"
            + CNN_COMENT + " text,"
            + CNN_AUTORIDAD + " text,"
            + CNN_USER + " text);";
    //Informacion de la tabla
    public static final String TABLA_SOLICITUDES = "solicitudes";
    public static final String CNS_ID = "_id";
    public static final String CNS_COMENT ="comentsolicitud";
    public static final String CNS_FECHA ="fechasolicitud";
    public static final String CNS_ESTADO ="estadosolucitud";
    public static final String CNS_AUTORIDAD ="autoridad";
    public static final String CNS_USER ="usuario";
    public static final String CNS_RESPUESTA ="respuestasolicitud";
    //CREO LA TABLA SOLICITUDES
    public static final String CREATETABLE_SOLICITUDES = "create table " +TABLA_SOLICITUDES+ " ("
            + CNS_ID + " integer not null primary key autoincrement,"
            + CNS_COMENT + " text,"
            + CNS_FECHA + " text,"
            + CNS_ESTADO + " text,"
            + CNS_AUTORIDAD + " text,"
            + CNS_USER + " text,"
            + CNS_RESPUESTA + " text);";
    //Informacion de tabla actividad agenda
    public static final String TABLA_AGENDA ="actividadagenda";
    public static final String CNA_ID ="_id";
    public static final String CNA_NOMACTI ="nomactividad";
    public static final String CNA_FECHACTI ="fechaactividad";
    public static final String CNA_COMENT ="comentactividad";
    public static final String CNA_USER = "usuario";
    //CREO LA TABLA
    public static final String CREATETABLE_AGENDA = "create table " +TABLA_AGENDA+ " ("
            + CNA_ID + " integer not null primary key autoincrement,"
            + CNA_NOMACTI + " text,"
            + CNA_FECHACTI + " text,"
            + CNA_COMENT + " text,"
            + CNA_USER + " text);";

    SQLiteDatabase db;
    //Constructor de la clase
    public DBhelper(Context context) {
        super(context, DB_NOMBRE, null, DB_SCHEME_VERSION);

    } //Fin constructor de la clase

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USUARIO_CREATETABLE);
        db.execSQL(CREATETABLE_NOTAS);
        db.execSQL(CREATETABLE_SOLICITUDES);
        db.execSQL(CREATETABLE_AGENDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + USUARIOS_TABLA);
        db.execSQL("DROP TABLE IF EXISTS" + CREATETABLE_NOTAS);
        db.execSQL("DROP TABLE IF EXISTS" + CREATETABLE_SOLICITUDES);
        db.execSQL("DROP TABLE IF EXISTS" + CREATETABLE_AGENDA);
        onCreate(db);
    }
}
