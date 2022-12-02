package uteq.solutions.abriractividad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView txtNombre = (TextView)findViewById(R.id.txtNombre2);
        TextView txtApellido = (TextView)findViewById(R.id.txtApellido2);
//Recuperamos la información pasada en el intent
Bundle bundle = this.getIntent().getExtras();

//Construimos el mensaje a mostrar
txtNombre.setText("Hola!, Bienvenido \n " + bundle.getString("NOMBRE"));
        txtApellido.setText("Hola!, Bienvenido \n " + bundle.getString("APELLIDO"));
    }
}