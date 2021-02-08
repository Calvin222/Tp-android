package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.widget.Button
import com.google.zxing.integration.android.IntentIntegrator
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_page2.*

class Page2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)

        //Boutton de redirection vers la page pour lister des produits
        val button_liste = findViewById<Button>(R.id.button_liste)

        button_liste.setOnClickListener {
            val intent = Intent(this, ListeObjetActivity::class.java)

            startActivity(intent)
        }

        //Boutton de redirection vers la page du lecteur de musique
        val btn_music = findViewById<Button>(R.id.btn_music)

        btn_music.setOnClickListener {
            val intent = Intent(this, MusicActivity::class.java)

            startActivity(intent)
        }

        //Boutton pour le scan de QrCode
        btn_scan.setOnClickListener {
            val scanner = IntentIntegrator(this)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()

    }
}
    //Fonction pour le scan de QRcode
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}

