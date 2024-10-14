package com.example.practica10_listviewlamda;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_producto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView nombre_prod = findViewById(R.id.nombre_producto);
        TextView precio_prod = findViewById(R.id.precio_producto);
        TextView descripcion_prod = findViewById(R.id.detalles_producto);

        if(getIntent().getExtras() != null){
            Producto producto = (Producto) getIntent().getSerializableExtra("producto");
            nombre_prod.setText(producto.getNombre());
            precio_prod.setText(""+producto.getPrecio());
            descripcion_prod.setText(producto.getDescription());
        }
    }
}
