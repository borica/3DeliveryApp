package com.br.opet.app3delivery.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.opet.app3delivery.R;
import com.br.opet.app3delivery.activity.defaultActivity.NoBarActitity;
import com.br.opet.app3delivery.model.UserModel;

public class SignUpActivity extends NoBarActitity implements View.OnClickListener {

    EditText name;
    EditText email;
    EditText password;
    Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        signupBtn = findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //OnClick CADASTRAR button
        if(valid()) {

            UserModel newUser = new UserModel();

            newUser.setmContext(this);
            newUser.setName(name.getText().toString());
            newUser.setEmail(email.getText().toString());
            newUser.setPassword(password.getText().toString());

            newUser.save();
        }
    }

    private boolean valid() {
        boolean isValid = true;

        if(name.getText().toString().isEmpty()) {
            name.setError("Nome Inválido");
            isValid = false;
        }
        if(email.getText().toString().isEmpty()) {
            email.setError("Email Inválido");
            isValid = false;
        }
        if(password.getText().toString().isEmpty()) {
            password.setError("Senha Inválida");
            isValid = false;
        }
        return isValid;
    }
}