package com.example.t2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.CheckBox


class TransporteActivity : ComponentActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.problema4)

        val spinnerDestino: Spinner = findViewById(R.id.spinnerDestino)
        val spinnerModalidad: Spinner = findViewById(R.id.spinnerModalidad)
        val checkBoxPersonaNatural: CheckBox = findViewById(R.id.checkBoxPersonaNatural)
        val buttonCalcular: Button = findViewById(R.id.buttonCalcular)
        val textViewCostoSoles: TextView = findViewById(R.id.textViewCostoSoles)
        val textViewCostoDolares: TextView = findViewById(R.id.textViewCostoDolares)

        val destinos = arrayOf("Ica (A)", "Arequipa (B)", "Chimbote (C)", "Trujillo (D)")
        val tarifas = arrayOf(100, 450, 300, 350)

        val modalidades = arrayOf("Agencia a Agencia (0%)", "Agencia a Domicilio (25%)", "Domicilio a Agencia (35%)", "Domicilio a Domicilio (45%)")
        val incrementos = arrayOf(0.0, 0.25, 0.35, 0.45)

        spinnerDestino.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, destinos)
        spinnerModalidad.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, modalidades)

        buttonCalcular.setOnClickListener {
            val destinoIndex = spinnerDestino.selectedItemPosition
            val modalidadIndex = spinnerModalidad.selectedItemPosition
            val esPersonaNatural = checkBoxPersonaNatural.isChecked

            val tarifaBase = tarifas[destinoIndex]
            val incremento = incrementos[modalidadIndex]
            var costoFinal = tarifaBase + (tarifaBase * incremento)

            if (esPersonaNatural) {
                costoFinal += costoFinal * 0.05
            }

            val costoSoles = costoFinal * 3.7

            textViewCostoSoles.text = "Costo en Dólares: $%.2f".format(costoFinal)
            textViewCostoDolares.text = "Costo en Soles: S/%.2f".format(costoSoles)
        }
    }
}