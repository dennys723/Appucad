package sv.edu.ucad.appucad;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity{

    protected static final int TIMER_RUMTIME = 10000;
    protected boolean mbActive;
    protected ProgressBar mProgressBar;
    public static int delay = 2;
    public static final int segundos = 5;
    public static final int milisegundos = segundos * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylayout_splash);

        Thread segundoplano = new Thread(){
            public void run(){
                try {
                    sleep(5000);
                    Intent i = new Intent(getBaseContext(),PpalActivity.class);
                    startActivity(i);
                    //Se elimina la actividad del splash
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            } //fin del metodo run del hilo
        };
        segundoplano.start();

        //se instancia el objeto progressBar
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        //se instancia el valor maximo que podra obtener el progressBar
        mProgressBar.setMax(progress_maximo());
        empezarAnimacion();
    }

    public void empezarAnimacion() {
        new CountDownTimer(milisegundos,1000){
            @Override
            public void onTick(long millisUntilFinished){
            }
            @Override
            public void onFinish(){
                Intent next = new Intent(SplashActivity.this,PpalActivity.class);
                startActivity(next);
                finish();
            }
        }.start();
    }
    public int progreso(long milliseconds){
        return (int)((milisegundos-milliseconds)/1000);
    }
    public int progress_maximo() {
        return segundos-delay;
    }
}
