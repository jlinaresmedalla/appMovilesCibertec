package com.example.t2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : ComponentActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.problema1)

        var etNumPrendas: EditText = findViewById(R.id.etNumPrendas)
        var spinnerTipoPrenda: Spinner = findViewById(R.id.spinnerTipoPrenda)
        var spinnerCategoria: Spinner = findViewById(R.id.spinnerCategoria)
        var btnCalcular: Button = findViewById(R.id.btnCalcular)
        var tvResultado: TextView = findViewById(R.id.tvResultado)

        var tiposDePrenda = arrayOf("POLO", "CAMISA", "PANTALÓN")
        var tarifas = arrayOf(0.5, 1.0, 1.5)

        var categorias = arrayOf("A", "B", "C", "D")
        var bonificaciones = arrayOf(250.0, 150.0, 100.0, 50.0)

        spinnerTipoPrenda.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tiposDePrenda)
        spinnerCategoria.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categorias)

        btnCalcular.setOnClickListener {
            var numPrendas = etNumPrendas.text.toString().toInt()
            var tipoPrendaSeleccionada = spinnerTipoPrenda.selectedItemPosition
            var categoriaSeleccionada = spinnerCategoria.selectedItemPosition

            var tarifa = tarifas[tipoPrendaSeleccionada]
            var bonificacion = if (numPrendas > 700) bonificaciones[categoriaSeleccionada] else 0.0

            var ingresoBruto = numPrendas * tarifa + bonificacion
            var descuentoImpuestos = ingresoBruto * 0.09
            var descuentoSeguro = ingresoBruto * 0.02
            var descuentoSolidaridad = ingresoBruto * 0.01
            var ingresoNeto = ingresoBruto - (descuentoImpuestos + descuentoSeguro + descuentoSolidaridad)

            tvResultado.text = """
                Ingreso Bruto: S/. %.2f
                Descuento por Impuestos: S/. %.2f
                Descuento por Seguro: S/. %.2f
                Descuento por Solidaridad: S/. %.2f
                Ingreso Neto: S/. %.2f
            """.trimIndent().format(ingresoBruto, descuentoImpuestos, descuentoSeguro, descuentoSolidaridad, ingresoNeto)
        }
    }
}

