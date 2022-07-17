package com.example.accountbookv1

class UserSpinner {
    var id : Int = 0
    var name : String = ""
    var type : String = ""

    constructor(id: Int, name: String, type: String){
        this.id = id
        this.name = name
        this.type = type
    }

    constructor(){
    }

    override fun toString(): String {
        return "$name-($type)"
    }
}