package educacion.trax.quicktrade2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ModProducto extends AppCompatActivity {
    private DatabaseReference bbdd;
    private String categoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_producto);
        final EditText nomET=findViewById(R.id.nom);
        final EditText descET=findViewById(R.id.desc);
        final EditText precioET=findViewById(R.id.precio);
        final RadioButton tecRB=findViewById(R.id.radioButton);
        final RadioButton cocheRB=findViewById(R.id.radioButton2);
        final RadioButton hogarRB=findViewById(R.id.radioButton3);
        bbdd= FirebaseDatabase.getInstance().getReference("Productos");


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
                String nom= String.valueOf(nomET.getText());
                final String desc= String.valueOf(descET.getText());
                final String precio=String.valueOf(precioET.getText());
                categoria="";
                if (tecRB.isChecked()){
                    categoria= String.valueOf(tecRB.getText());
                }
                if (cocheRB.isChecked()){
                    categoria= String.valueOf(cocheRB.getText());
                }
                if (hogarRB.isChecked()){
                    categoria= String.valueOf(hogarRB.getText());
                }
                if (nom.equals("")){
                    Toast.makeText(v.getContext(), "Introduzca todos los campos necesarios(Nombre a modificar)",Toast.LENGTH_SHORT).show();
                }else {
                    Query q=bbdd.orderByChild("nombre").equalTo(nom);
                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue()==null){
                                Toast.makeText(getApplicationContext(), "Error, compruebe que ha escrito correctamente el titulo",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                    String key=datasnapshot.getKey();
                                    if (!desc.equals("")){
                                        bbdd.child(key).child("desc").setValue(desc);
                                    }
                                    if (!precio.equals("")){
                                        bbdd.child(key).child("precio").setValue(precio);
                                    }
                                    if (!categoria.equals("")){
                                        bbdd.child(key).child("categoria").setValue(categoria);
                                    }
                                }
                                Toast.makeText(getApplicationContext(), "Completado con exito",Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

    }
}
