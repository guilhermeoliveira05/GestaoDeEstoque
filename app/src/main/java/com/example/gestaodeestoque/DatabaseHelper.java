package com.example.gestaodeestoque;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "estoque.db";
    private static final int DATABASE_VERSION = 1;

    // Tabela de Produtos
    private static final String TABLE_PRODUTOS = "produtos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_CATEGORIA = "categoria";
    private static final String COLUMN_COR_MODELO = "cor_modelo";
    private static final String COLUMN_QUANTIDADE = "quantidade";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criando a tabela de produtos
        String createTable = "CREATE TABLE " + TABLE_PRODUTOS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_CATEGORIA + " TEXT, " +
                COLUMN_COR_MODELO + " TEXT, " +
                COLUMN_QUANTIDADE + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se a versão do banco for atualizada, a tabela será recriada
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTOS);
        onCreate(db);
    }

    // Função para inserir um produto
    public boolean insertProduto(String nome, String categoria, String corModelo, int quantidade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOME, nome);
        contentValues.put(COLUMN_CATEGORIA, categoria);
        contentValues.put(COLUMN_COR_MODELO, corModelo);
        contentValues.put(COLUMN_QUANTIDADE, quantidade);

        long result = db.insert(TABLE_PRODUTOS, null, contentValues);
        db.close();

        return result != -1; // Se o resultado for -1, ocorreu um erro
    }


    // Função para recuperar produtos consolidados (somando a quantidade)
    public ArrayList<Produto> getProdutosConsolidados() {
        ArrayList<Produto> produtos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // A consulta com alias para a soma da quantidade
        String query = "SELECT " + COLUMN_NOME + ", " + COLUMN_CATEGORIA + ", " + COLUMN_COR_MODELO + ", SUM(" + COLUMN_QUANTIDADE + ") AS quantidade " +
                "FROM " + TABLE_PRODUTOS + " GROUP BY " + COLUMN_NOME + ", " + COLUMN_CATEGORIA + ", " + COLUMN_COR_MODELO;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Recuperando os dados do cursor com os nomes das colunas
                int nomeIndex = cursor.getColumnIndex(COLUMN_NOME);
                int categoriaIndex = cursor.getColumnIndex(COLUMN_CATEGORIA);
                int corModeloIndex = cursor.getColumnIndex(COLUMN_COR_MODELO);
                int quantidadeIndex = cursor.getColumnIndex("quantidade");

                if (nomeIndex != -1 && categoriaIndex != -1 && corModeloIndex != -1 && quantidadeIndex != -1) {
                    String nome = cursor.getString(nomeIndex);
                    String categoria = cursor.getString(categoriaIndex);
                    String corModelo = cursor.getString(corModeloIndex);
                    int quantidade = cursor.getInt(quantidadeIndex);

                    Produto produto = new Produto(nome, categoria, corModelo, quantidade);
                    produtos.add(produto);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return produtos;
    }

}