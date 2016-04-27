package ufam.edu.br.treasurequest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
public class AdicionarJogadorActivity extends AppCompatActivity {
    IPServer ip = new IPServer();
    String cores = "";
    Button bt1, bt2, bt3, bt4;
    String cor = "", Tnome = "", mensagem = "Toque para confirmar";
    int idcor = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_jogador);
        bt1 = (Button) findViewById(R.id.btnVermelho);
        bt2 = (Button) findViewById(R.id.btnAzul);
        bt3 = (Button) findViewById(R.id.btnVerde);
        bt4 = (Button) findViewById(R.id.btnAmarelo);

        Intent intent = getIntent();
        Tnome = intent.getStringExtra("nomeTime");
        verificaCoresDisponiveis();

        Button btnIniciar = (Button)findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDadosjogador();
                Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_SHORT).show();
                if (mensagem.equals("Jogador cadastrado com sucesso"))
                    mostraAguardandoConexao();
            }
        });
    }

    public void mostraAguardandoConexao() {
        Intent intent = new Intent(AdicionarJogadorActivity.this, AguardandoConexaoActivity.class);
        intent.putExtra("nomeTime", Tnome);
        startActivity(intent);
    }

    public void verificaCoresDisponiveis() {
        new Thread() {
            public void run() {
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
        HttpPost httpPost = new HttpPost("http://"+ip.getIP()+"/studio/coresUsadas.php?nomeTime='"+Tnome+"'");

        final HttpResponse resposta = httpClient.execute(httpPost);

        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    //Toast.makeText(getBaseContext(), EntityUtils.toString(resposta.getEntity()), Toast.LENGTH_SHORT).show();
                    cores = EntityUtils.toString(resposta.getEntity());
                    if(cores.contains("1")){
                        bt1.setVisibility(View.INVISIBLE);
                    }
                    if(cores.contains("2")){
                        bt2.setVisibility(View.INVISIBLE);
                    }
                    if(cores.contains("3")){
                        bt3.setVisibility(View.INVISIBLE);
                    }
                    if(cores.contains("4")){
                        bt4.setVisibility(View.INVISIBLE);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void enviarDadosjogador(){
        new Thread(){
            public void run(){
                EditText nEt = (EditText) findViewById(R.id.edtTxtNomeJogador);
                try {
                    postHttpJogador(nEt.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void postHttpJogador(String nome) throws IOException {
        String entrada = nome;
        if (nome.contains(" "))
        {
            entrada = entrada.replaceAll(" ", "_");
        }
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://"+ip.getIP()+"/studio/cadastraJogador.php?nome="+nome+"&nomeTime="+Tnome+"&cor="+idcor);

        final HttpResponse resposta1 = httpClient.execute(httpPost);

        runOnUiThread(new Runnable(){
            public void run(){
                try {
                    mensagem = EntityUtils.toString(resposta1.getEntity());
                    //Toast.makeText(getBaseContext(), EntityUtils.toString(resposta1.getEntity()), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void seleciona_cor1(View view){
        idcor = 1;
        bt2.setVisibility(View.INVISIBLE);
        bt3.setVisibility(View.INVISIBLE);
        bt4.setVisibility(View.INVISIBLE);
    }
    public void seleciona_cor2(View view){
        idcor = 2;
        bt1.setVisibility(View.INVISIBLE);
        bt3.setVisibility(View.INVISIBLE);
        bt4.setVisibility(View.INVISIBLE);
    }
    public void seleciona_cor3(View view){
        idcor = 3;
        bt1.setVisibility(View.INVISIBLE);
        bt2.setVisibility(View.INVISIBLE);
        bt4.setVisibility(View.INVISIBLE);
    }
    public void seleciona_cor4(View view){
        idcor = 4;
        bt1.setVisibility(View.INVISIBLE);
        bt2.setVisibility(View.INVISIBLE);
        bt3.setVisibility(View.INVISIBLE);
    }
}