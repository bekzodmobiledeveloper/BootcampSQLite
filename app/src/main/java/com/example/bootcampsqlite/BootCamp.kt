package com.example.bootcampsqlite

import java.io.Serializable

class BootCamp:Serializable {
    var id: Int? = null
    var name: String? = null
    var text: String? = null
    var bolim: String? = null

    constructor(id: Int?, name: String?, text: String?, bolim: String?) {
        this.id = id
        this.name = name
        this.text = text
        this.bolim = bolim
    }

    constructor(name: String?, text: String?, bolim: String?) {
        this.name = name
        this.text = text
        this.bolim = bolim
    }

}