package com.example.tesouro.marcos.agoravai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

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

public class MainActivity extends AppCompatActivity {
    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enviarDados(View view){
        new Thread(){
            public void run(){
                EditText nEt = (EditText) findViewById(R.id.nome);
                try {
                    postHttp(nEt.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void postHttp(String nome) throws IOException {
        String entrada = nome;
        if (nome.contains(" "))
        {
            entrada = entrada.replaceAll(" ", "_");
        }
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://10.206.7.16/studio/inseretime.php?nome="+entrada);

        final HttpResponse resposta = httpClient.execute(httpPost);

        runOnUiThread(new Runnable(){
            public void run(){
                try {
                    Toast.makeText(getBaseContext(), EntityUtils.toString(resposta.getEntity()), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}





















   /* public void getJson(final String url) {

        // Toda chamada externa necessita rodar em background, então utilizamos thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Criamos nosso objeto de retorno que poderia ser uma entidade (Exemplo: Aluno, Usuário, etc.), nesse caso utilizamos algo genérico.
                Object retorno = null;
                // Há a necessidade de tratarmos excessão tendo em vista que estamos realizando requisições em nossa aplicação
                try {
                    // Setamos o cliente http e o nosso request, que será do tipo GET (O POST veremos em outros artigos)
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    // Setamos nossa URI
                    request.setURI(new URI(url));
                    // Executamos nossa transação HTTP
                    HttpResponse response = httpclient.execute(request);
                    // Pegamos o conteúdo advindo como resposta e inserimos em um InputStream
                    Log.i("Script", response.toString());
                    InputStream content = response.getEntity().getContent();
                    // Instanciamos o nosso Reader com o InputStream
                    Reader reader = new InputStreamReader(content);
                    // Aqui vamos utilizar a Biblioteca Gson para transformar o Json recebido em Objeto JAVA
                    /* Instanciamos o objeto Gson e em seguida utilizamos o método fromJson() passando como parâmetro o Reader instanciado e o tipo do Objeto que será retornado.
                    Gson gson = new Gson();
                    retorno = gson.fromJson(reader, HashMap.class);
                    Log.i("Script", retorno.toString());
                    content.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    } */
