package com.example.t2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class SubsidioActivity : ComponentActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.problema3)

        val etNumeroHijos: EditText = findViewById(R.id.etNumeroHijos)
        val spinnerEstadoCivil: Spinner = findViewById(R.id.spinnerEstadoCivil)
        val btnCalcular: Button = findViewById(R.id.btnCalcular)
        val tvResultado: TextView = findViewById(R.id.tvResultado)

        val estadosCiviles = arrayOf("Viuda", "Casada", "Soltera")

        spinnerEstadoCivil.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estadosCiviles)

        btnCalcular.setOnClickListener {
            val numeroHijos = etNumeroHijos.text.toString().toInt()
            val estadoCivil = spinnerEstadoCivil.selectedItem.toString()

            val subsidioBase = when {
                numeroHijos <= 3 -> 75 * numeroHijos
                numeroHijos in 4..6 -> 110 * numeroHijos
                else -> 120 * numeroHijos
            }

            val subsidioAdicional = when (estadoCivil) {
                "Viuda" -> 55
                "Casada" -> 25
                "Soltera" -> 10 * numeroHijos
                else -> 0
            }

            val subsidioTotal = subsidioBase + subsidioAdicional

            tvResultado.text = """
                Subsidio base: S/. $subsidioBase
                Subsidio adicional: S/. $subsidioAdicional
                Subsidio total: S/. $subsidioTotal
            """.trimIndent()
        }
    }
}

