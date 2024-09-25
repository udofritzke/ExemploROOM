package com.example.exemploroomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM Usuario")
    List<Usuario> todos();

    @Query("SELECT * FROM Usuario WHERE uid IN (:idUsuarios)")
    List<Usuario> pegaTodosPeloId(int[] idUsuarios);

    @Query("SELECT * FROM Usuario WHERE nome LIKE :primeiro AND " +
            "sobre_nome LIKE :ultimo LIMIT 1")
    Usuario procuraPeloNomeCompleto(String primeiro, String ultimo);

    @Query("SELECT * FROM Usuario WHERE nome LIKE :primeiro LIMIT 1")
    Usuario procuraPeloNome(String primeiro);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insereTodos(Usuario... usuarios);

    @Delete
    void delete(Usuario usuario);

    @Update
    void atualizaUsuario(Usuario...usuarios);
}
