package com.example.exemploroomdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Usuario {
    @PrimaryKey (autoGenerate = true) @NonNull
    //public int uid;
    public int uid;

    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "sobre_nome")
    public String sobreNome;

    @Ignore
    public String apelido;
}