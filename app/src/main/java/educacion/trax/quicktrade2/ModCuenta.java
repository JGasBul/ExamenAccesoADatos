package educacion.trax.quicktrade2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ModCuenta extends AppCompatActivity {
    private DatabaseReference bbdd;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_cuenta);
        final EditText idET = findViewById(R.id.id);
        final EditText nomET = findViewById(R.id.nom);
        final EditText apeET = findViewById(R.id.ap);
        final EditText direccionET = findViewById(R.id.direccion);

        bbdd= FirebaseDatabase.getInstance().getReference("Usuarios");


        final Button cancelar = findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                finish();
            }
        });

        final Button aceptar=findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id=String.valueOf(idET.getText());
                final String nom= String.valueOf(nomET.getText());
                final String ap= String.valueOf(apeET.getText());
                final String direccion=String.valueOf(direccionET.getText());
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String key="";
                if (user != null) {
                    key = user.getUid();
                }
                    Query q=bbdd.orderByKey().equalTo(key);
                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                    String key=datasnapshot.getKey();
                                    if (!id.equals("")){
                                        bbdd.child(key).child("id").setValue(id);
                                    }
                                    if (!nom.equals("")){
                                        bbdd.child(key).child("nom").setValue(nom);
                                    }
                                    if (!ap.equals("")){
                                        bbdd.child(key).child("ap").setValue(ap);
                                    }
                                    if (!direccion.equals("")){
                                        bbdd.child(key).child("direccion").setValue(direccion);
                                    }
                                }
                                Toast.makeText(getApplicationContext(), "Completado con exito",Toast.LENGTH_SHORT).show();
                                finish();


                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
        });

    }
}

