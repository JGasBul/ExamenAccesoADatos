package educacion.trax.quicktrade2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        final EditText idET = findViewById(R.id.id);
        final EditText nomET = findViewById(R.id.nom);
        final EditText apeET = findViewById(R.id.ap);
        final EditText passET = findViewById(R.id.pass);
        final EditText emailET = findViewById(R.id.email);
        final EditText direccionET = findViewById(R.id.direccion);

        final Button cancelar = findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                setResult(RESULT_CANCELED,i);
                finish();
            }
        });
        final Button aceptar = findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(idET.getText());
                String nom= String.valueOf(nomET.getText());
                String ape= String.valueOf(apeET.getText());
                String email= String.valueOf(emailET.getText());
                String pass= String.valueOf(passET.getText());
                String direccion = String.valueOf(direccionET.getText());
                if (id.equals("") | nom.equals("") | ape.equals("") | email.equals("") | pass.equals("") | direccion.equals("")){
                    Toast.makeText(v.getContext(), "Introduzca todos los campos necesarios",Toast.LENGTH_SHORT).show();
                }else{
                    Persona p=new Persona(id,nom,ape,email,pass,direccion);
                    Intent i=new Intent();
                    Bundle b=new Bundle();
                    b.putParcelable("persona",p);
                    i.putExtras(b);
                    setResult(RESULT_OK,i);
                    finish();
                }
            }
        });
    }
}
