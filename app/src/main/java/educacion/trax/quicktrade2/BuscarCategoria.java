package educacion.trax.quicktrade2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class BuscarCategoria extends AppCompatActivity {
    private DatabaseReference bbdd;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_categoria);
        final Spinner sp=findViewById(R.id.spinner);
        txt=findViewById(R.id.text);
        final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.categorias,R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    txt.setText("");
                buscar(sp.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bbdd= FirebaseDatabase.getInstance().getReference("Productos");
    }
    private void buscar(String sp){
        Query q=bbdd.orderByChild("categoria").equalTo(sp);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
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
