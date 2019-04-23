package com.firebase.whatsappcode.codehans.whatsappcode;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private EditText edt_phone, edt_code;
    private Button btn_send;

    // TODO variables usadas por Firebase
    // TODO en este caso la creacion de un callbk para el manejo de respuesta del metodo
    // TODO verifyPhoneNumber()
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcllbck;
    private String verificationID;
    // TODO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO inicializando la aplicacion firebase
        FirebaseApp.initializeApp(this);
        // TODO

        // TODO verifica si el usuario ya existe y no necesitamos realizar la verificacion del numero
        userIsLoggedIn();
        // TODO

        // TODO referencia de las variables a los elementos del xml
        edt_phone = findViewById(R.id.edt_phone);
        edt_code = findViewById(R.id.edt_code);
        btn_send = findViewById(R.id.btn_send);
        // TODO

        // TODO creando un listener para la accion del button
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO en caso verificationID tenga valor es decir que mcllbck se instancio
                // TODO pasaremos a verificar el telefono con el codigo y el verificationID
                if (verificationID != null) {
                    verificarPhoneWithCode();
                } else {
                    // TODO en caso no cumpla con la condicion se procedera a enviar el codigo
                    // TODO para la verificacion del telefono
                    startPhoneVerification();
                }
            }
        });
        // TODO

        // TODO Manejo de Callbks, ya sea onVerificationCompleted es decir con exito
        // TODO o onVerificationFailed
        mcllbck = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                // TODO funcion para el ingreso con las credenciales
                IngresarConCredencial(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.d("onVerificationFailed", "onVerificationFailed: " + e);
            }

            // TODO Metodo para comprobar si se realizo el envio del code
            @Override
            public void onCodeSent(String firebaseVerificationID, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(firebaseVerificationID, forceResendingToken);
                verificationID = firebaseVerificationID;
                // TODO modificamos el texto del button en el xml con la variable
                btn_send.setText("VERIFICAR CODIGO");
            }
        };


    }

    // TODO este metodo verifica las credenciales del telefono con el codigo
    // TODO generalmente firebase verifica el telefono de forma automatica no necesita del codigo
    // TODO no necesariamente pasa asique este metodo verifica nuestras credenciales
    private void verificarPhoneWithCode() {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, edt_code.getText().toString());
        IngresarConCredencial(credential);
    }

    private void startPhoneVerification() {
        // TODO usando la implementacion para la verificacion de numeros de Firebase
        // TODO el siguiente metodo solicita a firebase que verifique el numero de telefono del user
        // TODO para mayor informacion sonbre la documentacion del phone-auth
        // TODO https://firebase.google.com/docs/auth/android/phone-auth
        // TODO el metodo recibe los siguientes parametros verifyPhoneNumber(numeroDeTelefono, Tiempo duracion
        // TODO , unidad de Tiempo, Activity, Callbk para poder manejar la respuesta recibida)
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                edt_phone.getText().toString(), 60, TimeUnit.SECONDS, this, mcllbck);

    }

    private void IngresarConCredencial(PhoneAuthCredential phoneAuthCredential) {
        // TODO metodo Firebase para saber si se completo el ingreso con credenciales
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    userIsLoggedIn();
                }
            }
        });
    }

    private void userIsLoggedIn() {
        // TODO metodo Firebase por el cual podemos identificar el usuario que esta en el login
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        // TODO si el usuario de firebase existe y es diferente de null entonces ir a la siguiente activity
        if (firebaseUser != null) {
            startActivity(new Intent(getApplicationContext(), MainPageActivity.class));
            finish();
            return;
        }
    }
}
