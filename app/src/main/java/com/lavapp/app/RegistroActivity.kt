package com.lavapp.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


        var tvDatos: TextView = findViewById<TextView>(R.id.tv_datos)
        val tipoVehiculo = getIntent().getStringExtra("tipoVehiculo")
        val placaVehiculo = getIntent().getStringExtra("placaVehiculo")
        val nombreUsuario = getIntent().getStringExtra("nombreUsuario")
        val valorServicio = getIntent().getDoubleExtra("valorServicio",0.0)

        val datos = "Placa vehículo: ${placaVehiculo} \n Tipo vehículo: ${tipoVehiculo} \n Nombre cliente: ${nombreUsuario} \n Valor servicio: ${valorServicio}"

        tvDatos.setText(datos)
    }
}