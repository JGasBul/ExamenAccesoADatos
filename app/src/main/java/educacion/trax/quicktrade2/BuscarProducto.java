package educacion.trax.quicktrade2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuscarProducto extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto);
        Button user=findViewById(R.id.user);
        user.setOnClickListener(this);
        Button categoria=findViewById(R.id.categoria);
        categoria.setOnClickListener(this);
        Button fav=findViewById(R.id.añadir_fav);
        fav.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.user){
            Intent i =new Intent(getApplicationContext(),BuscarUser.class);
            startActivity(i);
        }
        if (v.getId()==R.id.categoria){
            Intent i =new Intent(getApplicationContext(),BuscarCategoria.class);
            startActivity(i);
        }
        if (v.getId()==R.id.añadir_fav){
            Intent i =new Intent(getApplicationContext(),AnyadirFavorito.class);
            startActivity(i);
        }
        if (v.getId()==R.id.b_fav){
            Intent i =new Intent(getApplicationContext(),BuscarFav.class);
            startActivity(i);
        }
    }
}
