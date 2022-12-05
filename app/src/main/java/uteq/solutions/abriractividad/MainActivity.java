
package uteq.solutions.abriractividad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void Datos(View view){
        EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
        EditText txtApelllido = (EditText) findViewById(R.id.txtApellido);

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

    //Creamos la información a pasar entre actividades - Pares Key-Value
    Bundle b = new Bundle();



    
    b.putString("NOMBRE", txtNombre.getText().toString());
        b.putString("APELLIDO", txtApelllido.getText().toString());
    //Añadimos la información al intent
     intent.putExtras(b);
    // Iniciamos la nueva actividad
     startActivity(intent);


    }
}