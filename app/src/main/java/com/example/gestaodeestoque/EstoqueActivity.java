package com.example.gestaodeestoque;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EstoqueActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estoque);

        tableLayout = findViewById(R.id.tableLayout);
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tableLayout.removeAllViews(); // Remove linhas anteriores
        adicionarCabecalhoTabela();  // Adiciona o cabeçalho
        exibirEstoque();             // Exibe os dados na tabela
    }

    private void adicionarCabecalhoTabela() {
        TableRow cabecalho = new TableRow(this);

        TextView nomeHeader = criarTextoTabela("Nome");
        cabecalho.addView(nomeHeader);

        TextView categoriaHeader = criarTextoTabela("Categoria");
        cabecalho.addView(categoriaHeader);

        TextView corModeloHeader = criarTextoTabela("Cor/Modelo");
        cabecalho.addView(corModeloHeader);

        TextView quantidadeHeader = criarTextoTabela("Quantidade");
        cabecalho.addView(quantidadeHeader);

        tableLayout.addView(cabecalho);
    }

    private void exibirEstoque() {
        ArrayList<Produto> produtos = databaseHelper.getProdutosConsolidados();

        for (Produto produto : produtos) {
            TableRow row = new TableRow(this);

            TextView nome = criarTextoTabela(produto.getNome());
            row.addView(nome);

            TextView categoria = criarTextoTabela(produto.getCategoria());
            row.addView(categoria);

            TextView corModelo = criarTextoTabela(produto.getCorModelo());
            row.addView(corModelo);

            TextView quantidade = criarTextoTabela(String.valueOf(produto.getQuantidade()));
            row.addView(quantidade);

            tableLayout.addView(row);
        }
    }

    private TextView criarTextoTabela(String texto) {
        TextView textView = new TextView(this);
        textView.setText(texto);
        textView.setPadding(8, 8, 8, 8);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        textView.setBackgroundResource(android.R.color.system_background_light);
        return textView;
    }
}
