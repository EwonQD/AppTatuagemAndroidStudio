package com.seuprojeto.agendamento

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class PerfilActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val btnIrParaAgendamento = findViewById<Button>(R.id.btnIrAgendamento)
        btnIrParaAgendamento.setOnClickListener {
            val intent = Intent(this, AgendamentoActivity::class.java)
            startActivity(intent)
        }
        val btnIrParaPerfil = findViewById<Button>(R.id.btnPerfil)
        btnIrParaPerfil.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }
        val btnIrParaHome = findViewById<Button>(R.id.btnHome)
        btnIrParaHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnIrParaChat = findViewById<LinearLayout>(R.id.btnChat)
        btnIrParaChat.setOnClickListener {
            val url = "https://theuselessweb.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        val btnIrParaAgendar = findViewById<LinearLayout>(R.id.btnAgendar)
        btnIrParaAgendar.setOnClickListener {
            val intent = Intent(this, AgendamentoActivity::class.java)
            startActivity(intent)
        }
    }
}