package com.br.opet.app3delivery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.br.opet.app3delivery.R;
import com.br.opet.app3delivery.activity.defaultActivity.NoBarActitity;
import com.br.opet.app3delivery.application.ApplicationContext;
import com.br.opet.app3delivery.model.Session;
import com.br.opet.app3delivery.service.listeners.SessionResponseListener;
import com.br.opet.app3delivery.service.SessionService;

public class LoginActivity extends NoBarActitity implements View.OnClickListener{

    private static final String TAG = LoginActivity.class.getName();

    private ApplicationContext applicationContext;

    private EditText username;
    private EditText password;
    private Button loginBtn;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        applicationContext = (ApplicationContext) this.getApplicationContext();

        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginbtn);
        signUp = findViewById(R.id.signup);

        loginBtn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //Next activity intent
        final Intent[] i = new Intent[1];

        switch (view.getId()){
            case R.id.signup:
                //Proceed to signup activity
                i[0] = new Intent(this, SignUpActivity.class);
                startActivity(i[0]);
                break;
            case R.id.loginbtn:

                if(loginValid()) {
                    //Proceed to auth the user
                    Session newSession = new Session();
                    newSession.setmContext(this);
                    newSession.setEmail(username.getText().toString());
                    newSession.setPassword(password.getText().toString());

                    //Authentication
                    new SessionService().authenticate(newSession, new SessionResponseListener() {
                        @Override
                        public void onError(String message) {
                            if(message.equals("401")) {
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onResponse(Session responseSession) {
                            //Set logged user in the global application context
                            applicationContext.setLoggedUser(responseSession);

                            i[0] = new Intent(LoginActivity.this, DashboardActivity.class);
                            startActivity(i[0]);
                            finish();
                        }
                    });

                } else {
                    Toast.makeText(this, "Favor preencher os dados corretamente!", Toast.LENGTH_LONG).show();
                }

                break;
            default: break;
        }

    }

    //Validates login form
    private Boolean loginValid() {

        boolean valid = true;

        if(username.getText().toString().isEmpty()) {
            username.setError("Usuário inválido");
            valid = false;
        }
        if(password.getText().toString().isEmpty()) {
            password.setError("Senha inválida");
            valid = false;
        }

        return valid;
    }

}