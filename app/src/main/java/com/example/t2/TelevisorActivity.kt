package com.example.t2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class TelevisorActivity : ComponentActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.problema2)

        val etPrecioDolares: EditText = findViewById(R.id.etPrecioDolares)
        val spinnerMarca: Spinner = findViewById(R.id.spinnerMarca)
        val spinnerTamano: Spinner = findViewById(R.id.spinnerTamano)
        val btnCalcular: Button = findViewById(R.id.btnCalcular)
        val tvResultado: TextView = findViewById(R.id.tvResultado)

        val marcas = arrayOf("SAMSUNG", "PANASONIC", "LG", "SONY")
        val descuentos19 = arrayOf(12, 14, 12, 13)
        val descuentos21 = arrayOf(13, 12, 14, 15)

        val tamanos = arrayOf("19\"", "21\"")

        spinnerMarca.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, marcas)
        spinnerTamano.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tamanos)

        btnCalcular.setOnClickListener {
            val precioDolares = etPrecioDolares.text.toString().toDouble()
            val marcaSeleccionada = spinnerMarca.selectedItemPosition
            val tamanoSeleccionado = spinnerTamano.selectedItemPosition

            val descuento = if (tamanoSeleccionado == 0) descuentos19[marcaSeleccionada] else descuentos21[marcaSeleccionada]
            val precioConDescuentoDolares = precioDolares * (1 - descuento / 100.0)
            val precioConDescuentoSoles = precioConDescuentoDolares * 3.8 // Supongamos que el tipo de cambio es 3.8
            val precioFinalSoles = precioConDescuentoSoles * 1.18 // Aplicar IGV del 18%

            tvResultado.text = """
                Precio original en dólares: $%.2f
                Precio con descuento en dólares: $%.2f
                Precio con descuento en soles: S/. %.2f
                Precio final con IGV en soles: S/. %.2f
            """.trimIndent().format(precioDolares, precioConDescuentoDolares, precioConDescuentoSoles, precioFinalSoles)
        }
    }
}

