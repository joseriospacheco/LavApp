package com.lavapp.app

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {


    private lateinit var btnRegistrar: Button
    private lateinit var spnTipoVehiculo: Spinner
    private lateinit var etPlaca: EditText
    private lateinit var etNombreUsuario: EditText
    private lateinit var etValorServicio: EditText

    private var mfirestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mfirestore = FirebaseFirestore.getInstance();

        btnRegistrar = findViewById(R.id.btn_registrar)
        spnTipoVehiculo = findViewById(R.id.spn_tipo_vehiculo)
        etPlaca = findViewById(R.id.et_placa_vehiculo)
        etNombreUsuario = findViewById(R.id.et_nombre_cliente)
        etValorServicio = findViewById(R.id.et_valor_servicio)
        cargarListadoTipoVehiculos()



        btnRegistrar.setOnClickListener {

            registrar()
        }


    }


    private fun registrar() {

        val datos = Intent(this, RegistroActivity::class.java)



        spnTipoVehiculo.selectedItem.toString()
        datos.putExtra("tipoVehiculo", spnTipoVehiculo.selectedItem.toString())
        datos.putExtra("placaVehiculo", etPlaca.text.toString())
        datos.putExtra("nombreUsuario", etNombreUsuario.text.toString())
        datos.putExtra("valorServicio", etValorServicio.text.toString().toDouble())

        startActivity(datos)

        // Obtén una instancia de FirebaseFirestore
        val db = FirebaseFirestore.getInstance()

        val serviciosCollection = db.collection("servicios")

        // Generar un nuevo documento con un ID único
        val nuevoDocumentoRef = serviciosCollection.document()

        // Obtener el ID único generado
        val nuevoDocumentoId = nuevoDocumentoRef.id

// Define un mapa con los datos que deseas almacenar en el documento
        val data = hashMapOf(
            "placaVehiculo" to etPlaca.text.toString(),
            "nombreCliente" to  etNombreUsuario.text.toString(),
            "tipoVehiculo" to  spnTipoVehiculo.selectedItem.toString(),
            "valor" to  etValorServicio.text.toString().toDouble()
            // Agrega más campos según tu esquema de datos
        )


        // Agregar el documento con el ID único generado y los datos del servicio
        nuevoDocumentoRef.set(data)
            .addOnSuccessListener {
                // Documento agregado exitosamente
                println("Documento agregado con ID: $nuevoDocumentoId")
            }
            .addOnFailureListener { e ->
                // Error al agregar el documento
                println("Error al agregar el documento: $e")
            }

    }

    private fun cargarListadoTipoVehiculos() {

        ArrayAdapter.createFromResource(
            this,
            R.array.tipos_vehiculos,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnTipoVehiculo.adapter = adapter
        }
    }

}