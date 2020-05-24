package com.example.socketconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private EditText IPUsuario, editText, editText2;
    private Button CONECTAR, button, DESCONECTAR;
    private static final int PORT = 5000;
    private Context context = this;

    /**
     * HOST
     * */
    private static final String ADDRESS = "52.87.207.123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IPUsuario = findViewById(R.id.IPUsuario);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        CONECTAR = findViewById(R.id.conexionaconectar);
        button = findViewById(R.id.button);
        DESCONECTAR =findViewById(R.id.conexiondesconectar);

        CONECTAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IPUsuario.getText().length() > 0) {
                    conexion conectar = new conexion();
                    conectar.execute(IPUsuario.getText().toString());
                } else {
                    conexion conectar = new conexion();
                    conectar.execute(ADDRESS);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() > 0) {
                    conexion conectar = new conexion();
                    conectar.execute(editText.getText().toString());
                } else {
                    Toast.makeText(context, "Escriba \"Mr. Worderful\" o \"libro\" ", Toast.LENGTH_LONG).show();
                }
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

    class conexion extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String resultado = "";
            try {

                Socket socket = new Socket(ADDRESS, PORT);
                //socket.isConnected();
                //PrintStream output = new PrintStream(socket.getOutputStream());
                //output.println("Mr. Wonderful");

                BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
                resultado = input.readLine();
                socket.close();

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return resultado;
        }

        @Override
        protected void onPostExecute(String mensaje) {
            //super.onPostExecute(mensaje);
            editText2.setText(mensaje);
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
