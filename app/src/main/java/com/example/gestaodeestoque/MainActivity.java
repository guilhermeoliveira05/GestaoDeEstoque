package com.example.gestaodeestoque;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button buttonOlharEstoque, buttonAdicionarProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOlharEstoque = findViewById(R.id.buttonOlharEstoque);
        buttonAdicionarProduto = findViewById(R.id.buttonAdicionarProduto);

        buttonOlharEstoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para a tela de estoque
                Intent intent = new Intent(MainActivity.this, EstoqueActivity.class);
                startActivity(intent);
            }
        });

        buttonAdicionarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para a tela de adicionar produto
                Intent intent = new Intent(MainActivity.this, AdicionarProduto.class);
                startActivity(intent);
            }
        });
    }
}

