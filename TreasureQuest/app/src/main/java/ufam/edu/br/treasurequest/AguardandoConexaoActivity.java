package ufam.edu.br.treasurequest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class AguardandoConexaoActivity extends AppCompatActivity {
    IPServer ip = new IPServer();
    String mensagem = "", Tnome = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aguardando_conexao);


        Intent intent = getIntent();
        Tnome = intent.getStringExtra("nomeTime");

        Button btnCor = (Button)findViewById(R.id.btnCor);
        btnCor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aguardaConexao();
                Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_SHORT).show();
                if(mensagem.contains("true"))
                    mostraTreasureQuestBarra();
            }
        });
        btnCor.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                mostraTreasureQuestPergunta();
                return false;
            }
        });
        final Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                aguardaConexao();
                //Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_SHORT).show();
                if(mensagem.contains("true")) {
                    mostraTreasureQuestBarra();
                    timer.cancel();
                }
            }
        }, 500, 500);
    }

    public void mostraTreasureQuestPergunta() {
        Intent intent = new Intent(AguardandoConexaoActivity.this, TreasureQuestPerguntaActivity.class);
        startActivity(intent);
    }

    public void mostraTreasureQuestBarra() {
        Intent intent = new Intent(AguardandoConexaoActivity.this, TreasureQuestBarraActivity.class);
        startActivity(intent);
    }

    public void aguardaConexao(){
        new Thread(){
            public void run(){
                try {
                    postHttp();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void postHttp() throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://"+ip.getIP()+"/studio/timeCheio.php?nomeTime=" + Tnome);
        final HttpResponse resposta = httpClient.execute(httpPost);

        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    mensagem = EntityUtils.toString(resposta.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
