package com.example.khadyayatra.model

class RegUser {
    var id : Int = 0
    var regname : String = ""
    var mobno : String = ""
    var email : String = ""
    var delivery_address : String = ""
    var pass : String = ""
    var conpass : String = ""

    constructor (regname : String, mobno : String, email : String, delivery_address : String, pass : String, conpass : String){
        this.regname = regname
        this.mobno = mobno
        this.email = email
        this.delivery_address = delivery_address
        this.pass = pass
        this.conpass = conpass
    }

    constructor (){
    }
}