package com.programacaobrasil.smsaplicativo;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtNumero;
    private EditText edtMensagem;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validatePermissions();
        initializeComponents();
        initializeEventsComponents();
    }

    private void validatePermissions()
    {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.SEND_SMS
        }, 1);
    }

    private void initializeEventsComponents()
    {
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeroCelular = edtNumero.getText().toString();
                String mensagem = edtMensagem.getText().toString();

                sendMessage(numeroCelular, mensagem);
            }
        });
    }

    private void sendMessage(String numero, String mensagem)
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(numero, null, mensagem, null, null);

        Toast.makeText(getApplicationContext(), "SMS enviado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void initializeComponents()
    {
        edtNumero = (EditText)findViewById(R.id.edtNumero);
        edtMensagem = (EditText)findViewById(R.id.edtMensagem);
        btnEnviar = (Button)findViewById(R.id.btnEnviar);
    }
}
