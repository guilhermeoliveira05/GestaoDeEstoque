package com.example.gestaodeestoque;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProdutoAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> produtos;

    public ProdutoAdapter(Context context, ArrayList<String> produtos) {
        super(context, 0, produtos);
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // Infla o layout personalizado para cada item
            convertView = LayoutInflater.from(context).inflate(R.layout.item_produto, parent, false);
        }

        // Obtém o item atual (produto) da lista
        String produto = getItem(position);

        // Conecta os TextViews do layout com os dados do produto
        TextView textViewNomeProduto = convertView.findViewById(R.id.textViewNomeProduto);
        TextView textViewCategoria = convertView.findViewById(R.id.textViewCategoria);
        TextView textViewCorModelo = convertView.findViewById(R.id.textViewCorModelo);
        TextView textViewQuantidade = convertView.findViewById(R.id.textViewQuantidade);

        // Preenche os TextViews com os dados
        String[] dadosProduto = produto.split(", ");  // Separando os dados, caso esteja no formato "Nome: ..., Categoria: ..., ..."

        textViewNomeProduto.setText(dadosProduto[0]);
        textViewCategoria.setText(dadosProduto[1]);
        textViewCorModelo.setText(dadosProduto[2]);
        textViewQuantidade.setText(dadosProduto[3]);

        return convertView;
    }
}
