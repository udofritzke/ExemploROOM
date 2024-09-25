package com.example.exemploroomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Usuario.class}, version = 2)
public abstract class BancoDadosApp extends RoomDatabase {
    public abstract UsuarioDao userDao();
}