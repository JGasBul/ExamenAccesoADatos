package educacion.trax.quicktrade2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BuscarUser extends AppCompatActivity {
    private DatabaseReference bbdd,bbdd2;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_user);
        final EditText nameET=findViewById(R.id.maincra);
        final TextView txt=findViewById(R.id.txt);

        Button bt=findViewById(R.id.button2);

        bbdd= FirebaseDatabase.getInstance().getReference("Usuarios");
        bbdd2= FirebaseDatabase.getInstance().getReference("Productos");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= String.valueOf(nameET.getText());
                Query q=bbdd.orderByChild("id").equalTo(name);
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            String  key= ds.getKey();
                            Query q2=bbdd2.orderByChild("user").equalTo(key);
                            q2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                                        Producto p=ds.getValue(Producto.class);
                                        String name=p.getNombre()+" "+p.getDesc()+" "+p.getPrecio()+" euro";
                                        txt.setText(txt.getText()+"\n"+name);
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
        });


    }
}
