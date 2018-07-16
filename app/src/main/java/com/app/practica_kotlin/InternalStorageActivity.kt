package com.app.practica_kotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_internal_storage.*
import java.io.FileOutputStream

class InternalStorageActivity : AppCompatActivity() {

    var file = "my_first_file"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal_storage)

        saveBtn.setOnClickListener { try {
            var fout:FileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
            fout.write(enterText.text.toString().toByteArray(Charsets.UTF_8))
            fout.close()
            Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show()
    }catch (e: Exception){
            e.printStackTrace()
        }
        }

        loadBtn.setOnClickListener {
            try {
                val fin = openFileInput(file)
                var temp = ""
                var bytes: ByteArray = fin.readBytes()

                for (byte in bytes) {
                    temp += byte.toChar()
                }
                resultArea.setText(temp)
                Toast.makeText(this, "File Read", Toast.LENGTH_LONG).show()
            }catch (e: Exception){
                e.printStackTrace()
            }

                }
            }
        }

