package com.example.accountbookv1

class UserID {
    var id : Int = 0

    constructor(id: Int){
        this.id = id
    }

    constructor(){
    }

    override fun toString(): String {
        return "$id"
    }
}

class UserMA {
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
        return name
    }
}

class UserMDT {
    var id : Int = 0
    var trnsID : Int = 0
    var type : String = ""
    var date : String = ""
    var note : String = ""
    var amount : Int = 0

    constructor(id: Int, trnsID: Int, type: String, date: String, note: String, amount : Int){
        this.id = id
        this.trnsID = trnsID
        this.type = type
        this.date = date
        this.note = note
        this.amount = amount
    }

    constructor(){
    }
}

class UserMACom {
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

class UserMRA {
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
        return name
    }
}