package com.seuprojeto.agendamento

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AgendamentoActivity : AppCompatActivity() {

    private lateinit var spinnerTipo: Spinner
    private lateinit var editOutroTipo: EditText
    private lateinit var editData: EditText
    private lateinit var imagePreview: ImageView

    private val tipos = listOf(
        "Old School", "Minimalismo", "Pontilismo", "Realismo",
        "Aquarela", "Blackwork", "Single line", "Lettering",
        "Oriental", "Sem contorno", "Geek", "Outro"
    )

    private val PICK_IMAGE = 100
    private var imageUri: Uri? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agendamento)

        spinnerTipo = findViewById(R.id.spinnerTipo)
        editOutroTipo = findViewById(R.id.editOutroTipo)
        editData = findViewById(R.id.editData)
        imagePreview = findViewById(R.id.imagePreview)

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

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipo.adapter = adapter

        spinnerTipo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                editOutroTipo.visibility = if (tipos[pos] == "outro") View.VISIBLE else View.GONE
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        editData.setOnClickListener {
            val c = Calendar.getInstance()
            val dpd = DatePickerDialog(this,
                { _, year, month, day ->
                    val dataFormatada = "%02d/%02d/%d".format(day, month + 1, year)
                    editData.setText(dataFormatada)
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }


        findViewById<Button>(R.id.btnUploadImagem).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE)
        }

        findViewById<Button>(R.id.btnEnviar).setOnClickListener {
            Toast.makeText(this, "Solicitação enviada (modo offline).", Toast.LENGTH_LONG).show()
            // Aqui você pode salvar os dados localmente ou usar SQLite/Room
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.data
            val inputStream = contentResolver.openInputStream(imageUri!!)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imagePreview.setImageBitmap(bitmap)
            imagePreview.visibility = View.VISIBLE
        }
    }
}
