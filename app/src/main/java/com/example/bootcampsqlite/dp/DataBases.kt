package com.example.bootcampsqlite.dp

import com.example.bootcampsqlite.BootCamp

interface DataBases {
    fun addInformation(bootCamp: BootCamp)
    fun getAllInformation(part: String): ArrayList<BootCamp>
    fun deleteInformation(bootCamp: BootCamp)
    fun updateInformatsion(bootCamp: BootCamp): Int
}