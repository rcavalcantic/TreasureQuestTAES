package com.example.tesouro.marcos.testetesouro;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.location.*;
import android.widget.TextView;
import android.widget.Toast;

import jogo.Jogo;
import com.example.tesouro.marcos.testetesouro.R;

import java.text.SimpleDateFormat;

import javax.net.ssl.SSLEngineResult;


public class MainActivity extends AppCompatActivity {
    Location location;
    LocationManager locationManager;
    Jogo jogo = new Jogo(1, "jogador");
    Button resposta[] = new Button[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resposta[0] = (Button) findViewById(R.id.button);
        resposta[1] = (Button) findViewById(R.id.button2);
        resposta[2] = (Button) findViewById(R.id.button3);
        resposta[3] = (Button) findViewById(R.id.button4);
        resposta[4] = (Button) findViewById(R.id.button5);
        jogar();
    }


    public void jogar() {
        resposta[0].setText(jogo.getResposta(0));
        resposta[1].setText(jogo.getResposta(1));
        resposta[2].setText(jogo.getResposta(2));
        resposta[3].setText(jogo.getResposta(3));
        resposta[4].setText(jogo.getResposta(4));

    }

    public void resposta1(View view) {
        // Toast.makeText(MainActivity.this, "Entrou no resposta1", Toast.LENGTH_SHORT).show();
        if (jogo.acertouResposta(1)) {
            jogo.proximaFase();
            jogar();
        } else {
            jogo.recarregaFase();
        }
    }

    public void resposta2(View view) {
        if (jogo.acertouResposta(2)) {
            jogo.proximaFase();
            jogar();
        } else {
            jogo.recarregaFase();
        }
    }

    public void resposta3(View view) {
        if (jogo.acertouResposta(3)) {
            jogo.proximaFase();
            jogar();
        } else {
            jogo.recarregaFase();
        }
    }

    public void resposta4(View view) {
        if (jogo.acertouResposta(4)) {
            jogo.proximaFase();
            jogar();
        } else {
            jogo.recarregaFase();
        }
    }

    public void resposta5(View view) {
        if(jogo.acertouResposta(5)) {
            jogo.proximaFase();
            jogar();
        }else{
            jogo.recarregaFase();
        }
    }

    public void getlocal() {
        //for(String provider : locationManager.getAllProviders()){

        //Toast.makeText(getApplicationContext(), (int) location.getLatitude(), Toast.LENGTH_LONG).show();
        //}
    }

    public double calDistancia(double latitude, double longitude, double latitudePto, double longitudePto) {
        double lat1, long1, lat2, long2;
        lat1 = Math.toRadians(latitude);
        long1 = Math.toRadians(longitude);
        lat2 = Math.toRadians(latitudePto);
        long2 = Math.toRadians(longitudePto);

        double dlon, dlat, a, distancia;
        dlon = long2 - long1;
        dlat = lat2 - long1;

        a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(latitude) * Math.cos(latitudePto) * Math.pow(Math.sin(dlon / 2), 2);
        distancia = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6378140 * distancia; // 6378140 is the radius of the Earth in meters
    }

    public Boolean VerificaDistancia(Location locat) {
        Double Latitude = locat.getLatitude();
        Double Longitude = locat.getLongitude();
        Double LatitudePto = jogo.getFaseAtual().locX;
        Double LongitudePto = jogo.getFaseAtual().locY;

        if (calDistancia(Latitude, Longitude, LatitudePto, LongitudePto) < 20) {
            return true;
        }
        return false;
    }

    public void StartGPS() {
        LocationManager lmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener lListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                VerificaDistancia(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lListener);
    }}

}
