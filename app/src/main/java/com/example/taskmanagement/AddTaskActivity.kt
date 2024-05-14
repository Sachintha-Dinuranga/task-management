//package com.example.taskmanagement
package com.example.taskmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.example.taskmanagement.db.DBOpenHelper

class AddTaskActivity : AppCompatActivity() {


    private lateinit var etTitle: TextInputLayout
    private lateinit var etDescription: TextInputLayout
    private lateinit var fabSend: FloatingActionButton
    private val dbOpenHelper = DBOpenHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        etTitle = findViewById(R.id.et_title)
        etDescription = findViewById(R.id.et_description)
        fabSend = findViewById(R.id.fab_send)

        fabSend.setOnClickListener {
            fabSendData()
        }

        val fabBack = findViewById<FloatingActionButton>(R.id.fab_back)
        fabBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // These flags will ensure that MainActivity is started anew and all other activities are cleared from the stack
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

    private fun fabSendData() {

        if (etTitle.editText?.text.toString().isEmpty()) {
            etTitle.error = "Please enter your Title"
            etTitle.requestFocus()
            return
        }

        if (etDescription.editText?.text.toString().isEmpty()) {
            etDescription.error = "Please enter your Description"
            etDescription.requestFocus()
            return
        }

        if (notEmpty()) {
            dbOpenHelper.addNote(
                etTitle.editText?.text.toString(),
                etDescription.editText?.text.toString()
            )
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
            val intentToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentToMainActivity)
            finish()
        }

    }


    private fun notEmpty(): Boolean {
        return (etTitle.editText?.text.toString().isNotEmpty()
                && etDescription.editText?.text.toString().isNotEmpty())
    }

//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return true
//    }

}