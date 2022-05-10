package com.example.khadyayatra.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.khadyayatra.DatabaseHelper
import com.example.khadyayatra.R
import com.example.khadyayatra.model.RegUser

class UserLogin : AppCompatActivity() {

    lateinit var edtmobno: EditText
    lateinit var edtpassword: EditText
    lateinit var btnlogin: Button
    lateinit var txtforgotpass: TextView
    lateinit var txtregister: TextView
    lateinit var sharedprefernces: SharedPreferences
    val context = this
    val db: DatabaseHelper = DatabaseHelper(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        //title = "Login"
        edtmobno = findViewById(R.id.edtmobno)
        edtpassword = findViewById(R.id.edtpassword)
        btnlogin = findViewById(R.id.btnlogin)
        txtforgotpass = findViewById(R.id.txtforgetpassword)
        txtregister = findViewById(R.id.txtRegister)
        //val validmobno = "0123456789"
        //val validpass = "shree"


        //sharedprefernces = getSharedPreferences("UserLogin", Context.MODE_PRIVATE)


        sharedprefernces =
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)


        btnlogin.setOnClickListener {
            val mobno = edtmobno.text.toString()
            val password = edtpassword.text.toString()
            val args = listOf(mobno, password).toTypedArray()
            val opendb = db.readableDatabase

                val query = "SELECT * FROM TABLE_NAME WHERE COL_REGNAME = ? AND COL_PASSWORD = ?"
                val result = opendb.rawQuery(query,args)
                if(result.moveToNext()){
                    Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                    val i = Intent(this@UserLogin, MainActivity::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(context, "Invalid Credential", Toast.LENGTH_SHORT).show()
                }
            }


        txtregister.setOnClickListener{
            val i =
                Intent(
                    this@UserLogin, Register::class.java
                ) //connecting to the two activities
            startActivity(i)
            finish()
            }
    }



    override fun onPause() {
        super.onPause()
        finish()
    }

    /*fun savePreference(title: String) {
        sharedprefernces.edit().putBoolean("isLoggedIn", true).apply()
        //sharedprefernces.edit().putString("Title", title).apply()
    }*/
}