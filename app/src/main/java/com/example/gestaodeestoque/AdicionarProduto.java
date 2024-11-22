package com.example.gestaodeestoque;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdicionarProduto extends AppCompatActivity {

    // Definindo os componentes da tela
    EditText editTextNome, editTextCategoria, editTextCorModelo, editTextQuantidade;
    Button buttonAdicionarProduto;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_produto);

        // Inicializando os componentes
        editTextNome = findViewById(R.id.editTextNome);
        editTextCategoria = findViewById(R.id.editTextCategoria);
        editTextCorModelo = findViewById(R.id.editTextCorModelo);
        editTextQuantidade = findViewById(R.id.editTextQuantidade);
        buttonAdicionarProduto = findViewById(R.id.buttonAdicionarProduto);

        // Inicializando o banco de dados
        databaseHelper = new DatabaseHelper(this);

        // Configurando o clique do botão de adicionar produto
        buttonAdicionarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Capturando os valores dos campos
                String nome = editTextNome.getText().toString();
                String categoria = editTextCategoria.getText().toString();
                String corModelo = editTextCorModelo.getText().toString();
                String quantidadeStr = editTextQuantidade.getText().toString();

                // Verificando se os campos estão preenchidos
                if (nome.isEmpty() || categoria.isEmpty() || corModelo.isEmpty() || quantidadeStr.isEmpty()) {
                    Toast.makeText(AdicionarProduto.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convertendo a quantidade para inteiro
                int quantidade = Integer.parseInt(quantidadeStr);

                // Inserindo o produto no banco de dados
                boolean sucesso = databaseHelper.insertProduto(nome, categoria, corModelo, quantidade);

                // Verificando se o produto foi inserido com sucesso
                if (sucesso) {
                    Toast.makeText(AdicionarProduto.this, "Produto adicionado com sucesso!", Toast.LENGTH_SHORT).show();

                    // Limpando os campos após adicionar o produto
                    limparCampos();
                } else {
                    Toast.makeText(AdicionarProduto.this, "Erro ao adicionar o produto!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para limpar os campos após adicionar o produto
    private void limparCampos() {
        editTextNome.setText("");
        editTextCategoria.setText("");
        editTextCorModelo.setText("");
        editTextQuantidade.setText("");
    }
}
