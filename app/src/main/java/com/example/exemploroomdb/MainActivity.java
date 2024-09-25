package com.example.exemploroomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.Button;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    static BancoDadosApp db = null;
    ImageButton mBotaoSalva;
    Button mBotaoBuscaUsuario;
    EditText mTextoNome;
    EditText mTextoSobreNome;
    EditText mTextoNomeParaBusca;
    TextView mTextoResultadoBusca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (db == null) {
            db = Room.databaseBuilder(getApplicationContext(),
                    BancoDadosApp.class, "bd-usuarios").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        //fallbackToDestructiveMigration(): força mudança do esquema do banco
        }

        mBotaoSalva = (ImageButton) findViewById(R.id.imageButton);
        // utilização de classe anônima interna
        mBotaoSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "salva dados") ;
                mTextoNome = (EditText) findViewById(R.id.texto_nome);
                mTextoSobreNome = (EditText) findViewById(R.id.texto_sobrenome);
                UsuarioDao usuarioDao = db.userDao();
                Usuario usuario = new Usuario();
                usuario.nome = (String) mTextoNome.getText().toString();
                //usuario.uid = new UUID();
                usuario.sobreNome = (String) mTextoSobreNome.getText().toString();
                usuarioDao.insereTodos(usuario);
            }
        });
        mBotaoBuscaUsuario = (Button) findViewById(R.id.botao_busca_usuario);
        mBotaoBuscaUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "salva dados") ;
                mTextoNomeParaBusca = (EditText) findViewById(R.id.texto_nome_consulta);
                UsuarioDao usuarioDao = db.userDao();
                Usuario usuario;
                usuario = usuarioDao.procuraPeloNome(mTextoNomeParaBusca.getText().toString());
                mTextoResultadoBusca = (TextView) findViewById(R.id.texto_mostra_usuario);

                if (usuario != null) {
                    mTextoResultadoBusca.setText(usuario.sobreNome);
                    Log.d(TAG, "Sobre nome do usuário: " + usuario.sobreNome);
                }
                else {
                    mTextoResultadoBusca.setText("Não encontrou usuário: "+mTextoNomeParaBusca.getText().toString());
                    Log.d(TAG, "Não encontrou nome de usuário" + mTextoNomeParaBusca.getText().toString());
                }
            }
        });
/*
        UsuarioDao usuarioDao = db.userDao();

        Usuario usuario = new Usuario();
        usuario.nome = "Joaquim";
        usuario.uid = 3;
        usuario.sobreNome = "Pereira";

        if (usuarioDao != null) {
            usuarioDao.insereTodos(usuario);
            Log.i(TAG, "Cadastrou usuário");
        } else {
            Log.i(TAG, "Não cadastrou usuário");
        }

        List<Usuario> usuarios ; //= usuarioDao.getAll();
        //for (Usuario u : usuarios) {
        //    Log.i(TAG, u.nome);
        //}

        int ids[] = new int[3];
        ids[0]=0;
        ids[1]=1;
        ids[2]=4;
        usuarios = usuarioDao.pegaTodosPeloId(ids);
        for (Usuario u : usuarios) {
            Log.i(TAG, u.nome);
        }
        */

    }
}