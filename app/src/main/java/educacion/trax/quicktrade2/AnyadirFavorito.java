package educacion.trax.quicktrade2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AnyadirFavorito extends AppCompatActivity implements View.OnClickListener{
private Producto p;
private Favorito fav;
private DatabaseReference bbdd,bbdd2;
private EditText nombre;
private TextView result;
private Button cancelar;
private Button buscar;
private Button aceptar;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_anyadir_favorito);
        nombre=findViewById(R.id.name);
        result=findViewById(R.id.result);
        cancelar=findViewById(R.id.Cancelar);
        cancelar.setOnClickListener(this);
        buscar=findViewById(R.id.Buscar);
        buscar.setOnClickListener(this);
        aceptar=findViewById(R.id.Añadir);
        aceptar.setOnClickListener(this);
        bbdd= FirebaseDatabase.getInstance().getReference("Productos");
        mAuth = FirebaseAuth.getInstance();



    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.Cancelar){
            finish();
        }
        if (v.getId()==R.id.Buscar){
            String producto= String.valueOf(nombre.getText());
            fav=new Favorito(producto);
            Query q=bbdd.orderByChild("nombre").equalTo(producto);
            q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        result.setText("");
                        Producto p=ds.getValue(Producto.class);
                        String name=p.getNombre()+" "+p.getDesc()+" "+p.getPrecio()+" euro";
                        result.setText(name);
                        if (result.getText().equals("")){
                            Toast.makeText(getApplicationContext(),"No se ha encontrado nada",Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if (v.getId()==R.id.Añadir){
            String producto= String.valueOf(nombre.getText());
            fav=new Favorito(producto);
            if (!result.getText().equals("")) {
                Query q = bbdd.orderByChild("nombre").equalTo(producto);
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            final String key = ds.getKey();
                            String keyuser = user.getUid();
                            bbdd2 = FirebaseDatabase.getInstance().getReference("Usuarios");
                           Query q2=bbdd2.orderByKey().equalTo(keyuser);
                           q2.addListenerForSingleValueEvent(new ValueEventListener() {
                               @Override
                               public void onDataChange(DataSnapshot dataSnapshot) {
                                   for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                       bbdd2.child(ds.getKey()).child("Favoritos").child(key).setValue(fav);
                                       Toast.makeText(getApplicationContext(),"Añadido correctamente",Toast.LENGTH_LONG).show();
                                   }
                               }

                               @Override
                               public void onCancelled(DatabaseError databaseError) {

                               }
                           });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"No se ha encontrado nada",Toast.LENGTH_LONG).show();
            }
        }
    }
}
