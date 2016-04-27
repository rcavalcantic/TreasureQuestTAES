package ufam.edu.br.treasurequest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.text.ParseException;
import java.util.HashMap;

public class CriarTimeActivity extends AppCompatActivity {
    String mensagem = "Toque para confirmar!", Tnome = "";
    IPServer ip = new IPServer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_time);

        Button btnAvancar = (Button)findViewById(R.id.btnAvancar);
        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDados();
                Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_SHORT).show();
                if (mensagem.equals("Time cadastrado com sucesso")) {
                    mostraAdicionarJogador();
                }
            }
        });
    }

    public void mostraAdicionarJogador() {
        Intent intent = new Intent(this, AdicionarJogadorActivity.class);
        intent.putExtra("nomeTime", Tnome);
        startActivity(intent);
    }

    public void enviarDados(){
        new Thread(){
            public void run(){
                EditText edtTxtNomeTime = (EditText)findViewById(R.id.edtTxtNomeTime);
                try {
                    postHttp(edtTxtNomeTime.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void postHttp(String nome) throws IOException {
        String entrada = nome;
        if (nome.contains(" ")) {
            entrada = entrada.replaceAll(" ", "_");
        }
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://"+ip.getIP()+"/studio/inseretime.php?nome=" + entrada);
        Tnome = entrada;
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
