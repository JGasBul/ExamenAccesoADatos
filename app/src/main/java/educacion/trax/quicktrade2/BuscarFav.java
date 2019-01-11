package educacion.trax.quicktrade2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BuscarFav extends AppCompatActivity {
private TextView result;
private Button atras;
private DatabaseReference bbdd,bbdd2;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_fav);
        result=findViewById(R.id.result);
        atras=findViewById(R.id.atras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bbdd= FirebaseDatabase.getInstance().getReference("Usuarios");
        bbdd2= FirebaseDatabase.getInstance().getReference("Productos");
        mAuth = FirebaseAuth.getInstance();

    }
    private void mostrar(){
        FirebaseUser user = mAuth.getCurrentUser();
        Query q=bbdd.orderByKey().equalTo(user.getUid());
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                   bbdd= bbdd.child(ds.getKey()).child("Favoritos");
                   Query q2=bbdd.orderByKey();
                   q2.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           for (DataSnapshot ds : dataSnapshot.getChildren()) {
                               Favorito fav= ds.getValue(Favorito.class);
                               Query q3=bbdd.orderByChild("categoria").equalTo(fav.getNom());
                               q3.addListenerForSingleValueEvent(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(DataSnapshot dataSnapshot) {
                                       for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                           result.setText("");
                                           Producto p=ds.getValue(Producto.class);
                                           String name=p.getNombre()+" "+p.getDesc()+" "+p.getPrecio()+" euro";
                                           result.setText(result.getText()+"\n"+name);
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
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
