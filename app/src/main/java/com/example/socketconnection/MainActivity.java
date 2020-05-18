package com.example.socketconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private EditText IPUsuario;
    private Button CONECTAR, DESCONECTAR;
    private static final int PORT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IPUsuario = findViewById(R.id.IPUsuario);
        CONECTAR = findViewById(R.id.conexionaconectar);
        DESCONECTAR =findViewById(R.id.conexiondesconectar);

        CONECTAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conexion conectar = new conexion();
                conectar.execute();

            }
        });


        DESCONECTAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conexiondesc desconectar = new conexiondesc();
                desconectar.execute();
            }
        });
    }

    class conexion extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String resultado;
            try {
                Socket socket = new Socket(strings[0], PORT);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class conexiondesc extends  AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            return null;
        }
    }

    /*@Override
    protected void onPostExecute(Void mensaje) {
        super.onPostExecute(mensaje);
    }*/
}
