package kk.techbytecare.dream11.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

import kk.techbytecare.dream11.MainActivity;
import kk.techbytecare.dream11.R;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;

    EditText edtPassword,edtPhone,edtReferCode,edtEmail;

    TextView txtLogIn;

    FirebaseAuth mAuth;
    DatabaseReference users;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("REGISTER & PLAY");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        btnRegister = findViewById(R.id.btnRegister);

        edtPassword = findViewById(R.id.edtPassword);
        edtPhone = findViewById(R.id.edtPhone);
        edtReferCode = findViewById(R.id.edtReferCode);
        edtEmail = findViewById(R.id.edtEmail);

        txtLogIn = findViewById(R.id.txtLogIn);

        txtLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String referCode = edtReferCode.getText().toString();
                String phone = edtPhone.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if (!TextUtils.isEmpty(referCode) || !TextUtils.isEmpty(phone) || !TextUtils.isEmpty(email) ||
                        !TextUtils.isEmpty(password))    {

                    dialog = new ProgressDialog(RegisterActivity.this);
                    dialog.setTitle("User Registration");
                    dialog.setMessage("Please wait while we register you...");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                    registerUser(referCode,phone,email,password);
                }
            }
        });
    }

    private void registerUser(final String referCOde, final String phone, final String email, final String password) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())    {

                    dialog.dismiss();

                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = currentUser.getUid();

                    users = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                    String deviceToken = FirebaseInstanceId.getInstance().getToken();

                    HashMap<String,Object> userMap = new HashMap<>();
                    userMap.put("referCode",referCOde);
                    userMap.put("phone",phone);
                    userMap.put("email",email);
                    userMap.put("password",password);
                    userMap.put("uid",uid);
                    userMap.put("isStaff",false);
                    userMap.put("deviceToken",deviceToken);

                    users.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())    {

                                dialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Good to go...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
                else    {
                    dialog.hide();
                    Toast.makeText(RegisterActivity.this, "Can't Register you!! Try again in a little while.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
