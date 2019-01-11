package educacion.trax.quicktrade2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuPrincipal extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference bbdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        final Button añadir =findViewById(R.id.añadir);
        añadir.setOnClickListener(this);
        final Button modificar=findViewById(R.id.mod);
        modificar.setOnClickListener(this);
        final Button eliminar=findViewById(R.id.eliminar);
        eliminar.setOnClickListener(this);
        final Button buscar=findViewById(R.id.buscar);
        buscar.setOnClickListener(this);
        final Button cuenta=findViewById(R.id.cuenta);
        cuenta.setOnClickListener(this);
        bbdd= FirebaseDatabase.getInstance().getReference("Productos");
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.añadir){
            Intent i = new Intent(getApplicationContext(),AnyadirProducto.class);
            startActivityForResult(i,1);
        }
        if (v.getId()==R.id.mod){
            Intent i =new Intent(getApplicationContext(),ModProducto.class);
            startActivity(i);
        }
        if (v.getId()==R.id.eliminar){
            Intent i =new Intent(getApplicationContext(),EliminarProducto.class);
            startActivity(i);
        }
        if (v.getId()==R.id.buscar){
            Intent i=new Intent(getApplicationContext(),BuscarProducto.class);
            startActivity(i);
        }
        if (v.getId()==R.id.cuenta){
            Intent i =new Intent(getApplicationContext(),ModCuenta.class);
            startActivity(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==RESULT_OK){
                Bundle b=data.getExtras();
                final Producto p=b.getParcelable("producto");
                String key=bbdd.push().getKey();
                bbdd.child(key).setValue(p);

            }
        }
    }
}
