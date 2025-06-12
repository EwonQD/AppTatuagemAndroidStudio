package com.seuprojeto.agendamento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }
}
