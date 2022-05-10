package com.example.khadyayatra.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.khadyayatra.DatabaseHelper
import com.example.khadyayatra.R
import com.example.khadyayatra.model.RegUser

class Register : AppCompatActivity() {

    lateinit var btnRegister : Button
    lateinit var edtName : EditText
    lateinit var edtMobNo : EditText
    lateinit var edtEmail : EditText
    lateinit var edtDelAdd : EditText
    lateinit var edtPass : EditText
    lateinit var edtConPass : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val context = this
        val db = DatabaseHelper(context)

        btnRegister = context.findViewById(R.id.btnRegister)
        edtName = context.findViewById(R.id.edtregname)
        edtMobNo = context.findViewById(R.id.edtregmobno)
        edtEmail = context.findViewById(R.id.edtregemailid)
        edtDelAdd = context.findViewById(R.id.edtDeliveryAddress)
        edtPass = context.findViewById(R.id.edtregpassword)
        edtConPass = context.findViewById(R.id.edtregconpassword)

        btnRegister.setOnClickListener(){

            if(edtName.text.toString().isNotEmpty()
                && edtMobNo.text.toString().isNotEmpty()
                && edtEmail.text.toString().isNotEmpty()
                && edtDelAdd.text.toString().isNotEmpty()
                && edtPass.text.toString().isNotEmpty()
                && edtConPass.text.toString().isNotEmpty()
            ){
                val reguser = RegUser(edtName.text.toString(), edtMobNo.text.toString(),
                        edtEmail.text.toString(), edtDelAdd.text.toString(),
                    edtPass.text.toString(), edtConPass.text.toString())
                db.insertData(reguser)
                edtName.text.clear()
                edtMobNo.text.clear()
                edtEmail.text.clear()
                edtDelAdd.text.clear()
                edtPass.text.clear()
                edtConPass.text.clear()
            }else{
                Toast.makeText(context, "Please, fill all the details!!!", Toast.LENGTH_SHORT).show()
            }
        }




    }
}