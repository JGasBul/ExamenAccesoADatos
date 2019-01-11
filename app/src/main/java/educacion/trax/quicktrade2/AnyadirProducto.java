package educacion.trax.quicktrade2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AnyadirProducto extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyadir_producto);
        final EditText nomET=findViewById(R.id.nom);
        final EditText descET=findViewById(R.id.desc);
        final EditText precioET=findViewById(R.id.precio);
        final RadioButton tecRB=findViewById(R.id.radioButton);
        final RadioButton cocheRB=findViewById(R.id.radioButton2);
        final RadioButton hogarRB=findViewById(R.id.radioButton3);
        mAuth = FirebaseAuth.getInstance();

        final Button cancelar = findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                setResult(RESULT_CANCELED,i);
                finish();
            }
        });

        final Button aceptar=findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom= String.valueOf(nomET.getText());
                String desc= String.valueOf(descET.getText());
                String precio=String.valueOf(precioET.getText());
                String categoria="";
                if (tecRB.isChecked()){
                    categoria= String.valueOf(tecRB.getText());
                }
                if (cocheRB.isChecked()){
                    categoria= String.valueOf(cocheRB.getText());
                }
                if (hogarRB.isChecked()){
                    categoria= String.valueOf(hogarRB.getText());
                }
                if (nom.equals("")|desc.equals("")|precio.equals("")|categoria.equals("")){
                    Toast.makeText(v.getContext(), "Introduzca todos los campos necesarios",Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Producto p=new Producto(nom,desc,categoria,Double.valueOf(precio),user.getUid());
                    Intent i=new Intent();
                    Bundle b=new Bundle();
                    b.putParcelable("producto",p);
                    i.putExtras(b);
                    setResult(RESULT_OK,i);
                    finish();
                }
            }
        });
    }
}
