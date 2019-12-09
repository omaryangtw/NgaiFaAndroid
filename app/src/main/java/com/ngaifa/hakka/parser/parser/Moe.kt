package com.ngaifa.hakka.parser.parser

object Moe {

    // <poj_unicode, poj_number>
    val sMoeUnicodeToMoeNumberHashMap: HashMap<String, String> = HashMap()

    // <poj_number, poj_unicode>
    val sMoeNumberToMoeUnicodeHashMap: HashMap<String, String> = HashMap()

    init {
        sMoeUnicodeToMoeNumberHashMap["^"] = "1"
        sMoeUnicodeToMoeNumberHashMap["ˋ"] = "2"
        sMoeUnicodeToMoeNumberHashMap["ˊ"] = "3"
        sMoeUnicodeToMoeNumberHashMap["ˋ"] = "5"
        //sMoeUnicodeToMoeNumberHashMap[""] = "6"


        for ((key, value) in sMoeUnicodeToMoeNumberHashMap) {
            sMoeNumberToMoeUnicodeHashMap[value] = key
        }
    }
}
