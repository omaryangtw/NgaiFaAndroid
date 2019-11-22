package com.ngaifa.hakka.parser.parser

object Pfs {

    // <poj_unicode, poj_number>
    val sPfsUnicodeToPfsNumberHashMap: HashMap<String, String> = HashMap()

    // <poj_number, poj_unicode>
    val sPfsNumberToPfsUnicodeHashMap: HashMap<String, String> = HashMap()

    init {
        // A
        sPfsUnicodeToPfsNumberHashMap["Â"] = "A1"
        sPfsUnicodeToPfsNumberHashMap["À"] = "A2"
        sPfsUnicodeToPfsNumberHashMap["Á"] = "A3"
        sPfsUnicodeToPfsNumberHashMap["A"] = "A5"
        sPfsUnicodeToPfsNumberHashMap["A̍"] = "A6"


        // a
        sPfsUnicodeToPfsNumberHashMap["â"] = "a1"
        sPfsUnicodeToPfsNumberHashMap["à"] = "a2"
        sPfsUnicodeToPfsNumberHashMap["á"] = "a3"
        sPfsUnicodeToPfsNumberHashMap["a"] = "a5"
        sPfsUnicodeToPfsNumberHashMap["a̍"] = "a6"


        // I
        sPfsUnicodeToPfsNumberHashMap["Î"] = "I1"
        sPfsUnicodeToPfsNumberHashMap["Ì"] = "I2"
        sPfsUnicodeToPfsNumberHashMap["Í"] = "I3"
        sPfsUnicodeToPfsNumberHashMap["I"] = "I5"
        sPfsUnicodeToPfsNumberHashMap["I̍"] = "I6"


        // i
        sPfsUnicodeToPfsNumberHashMap["î"] = "i1"
        sPfsUnicodeToPfsNumberHashMap["ì"] = "i2"
        sPfsUnicodeToPfsNumberHashMap["í"] = "i3"
        sPfsUnicodeToPfsNumberHashMap["i"] = "i5"
        sPfsUnicodeToPfsNumberHashMap["i̍"] = "i6"


        // U
        sPfsUnicodeToPfsNumberHashMap["Û"] = "U1"
        sPfsUnicodeToPfsNumberHashMap["Ù"] = "U2"
        sPfsUnicodeToPfsNumberHashMap["Ú"] = "U3"
        sPfsUnicodeToPfsNumberHashMap["U"] = "U5"
        sPfsUnicodeToPfsNumberHashMap["U̍"] = "U6"


        // u
        sPfsUnicodeToPfsNumberHashMap["û"] = "u1"
        sPfsUnicodeToPfsNumberHashMap["ù"] = "u2"
        sPfsUnicodeToPfsNumberHashMap["ú"] = "u3"
        sPfsUnicodeToPfsNumberHashMap["u"] = "u5"
        sPfsUnicodeToPfsNumberHashMap["u̍"] = "u6"

        //Ṳ todo:
        sPfsUnicodeToPfsNumberHashMap["û"] = "Ṳ1"
        sPfsUnicodeToPfsNumberHashMap["ù"] = "Ṳ2"
        sPfsUnicodeToPfsNumberHashMap["Ṳ́"] = "Ṳ3"
        sPfsUnicodeToPfsNumberHashMap["Ṳ"] = "Ṳ5"
        //sPfsUnicodeToPfsNumberHashMap["u̍"] = "Ṳ6"

        // ṳ
        sPfsUnicodeToPfsNumberHashMap["ṳ̂"] = "ṳ1"
        sPfsUnicodeToPfsNumberHashMap["ṳ̀"] = "ṳ2"
        sPfsUnicodeToPfsNumberHashMap["ṳ́́"] = "ṳ3"
        sPfsUnicodeToPfsNumberHashMap["ṳ"] = "ṳ5"
        sPfsUnicodeToPfsNumberHashMap["ṳ̍"] = "ṳ6"

        // E
        sPfsUnicodeToPfsNumberHashMap["Ê"] = "E1"
        sPfsUnicodeToPfsNumberHashMap["È"] = "E2"
        sPfsUnicodeToPfsNumberHashMap["É"] = "E3"
        sPfsUnicodeToPfsNumberHashMap["E"] = "E5"
        sPfsUnicodeToPfsNumberHashMap[""] = "E6"


        // e
        sPfsUnicodeToPfsNumberHashMap["ê"] = "e1"
        sPfsUnicodeToPfsNumberHashMap["è"] = "e2"
        sPfsUnicodeToPfsNumberHashMap["é"] = "e3"
        sPfsUnicodeToPfsNumberHashMap["e"] = "e5"
        sPfsUnicodeToPfsNumberHashMap["e̍"] = "e6"


        // O
        sPfsUnicodeToPfsNumberHashMap["Ô"] = "O1"
        sPfsUnicodeToPfsNumberHashMap["Ò"] = "O2"
        sPfsUnicodeToPfsNumberHashMap["Ó"] = "O3"
        sPfsUnicodeToPfsNumberHashMap["O"] = "O5"
        sPfsUnicodeToPfsNumberHashMap["Á"] = "O6"


        // o
        sPfsUnicodeToPfsNumberHashMap["ô"] = "o1"
        sPfsUnicodeToPfsNumberHashMap["ò"] = "o2"
        sPfsUnicodeToPfsNumberHashMap["ó"] = "o3"
        sPfsUnicodeToPfsNumberHashMap["o"] = "o5"
        sPfsUnicodeToPfsNumberHashMap["o̍"] = "o6"

        // M
        sPfsUnicodeToPfsNumberHashMap["M̂"] = "M1"
        sPfsUnicodeToPfsNumberHashMap["M̀"] = "M2"
        sPfsUnicodeToPfsNumberHashMap["Ḿ"] = "M3"
        sPfsUnicodeToPfsNumberHashMap["M"] = "M5"
        sPfsUnicodeToPfsNumberHashMap["Á"] = "M6"

        // m
        sPfsUnicodeToPfsNumberHashMap["m̂"] = "m1"
        sPfsUnicodeToPfsNumberHashMap["m̀"] = "m2"
        sPfsUnicodeToPfsNumberHashMap["ḿ"] = "m3"
        sPfsUnicodeToPfsNumberHashMap["m"] = "m5"
        sPfsUnicodeToPfsNumberHashMap["m̍"] = "m6"

        // N
        sPfsUnicodeToPfsNumberHashMap["N̂"] = "N1"
        sPfsUnicodeToPfsNumberHashMap["Ǹ"] = "N2"
        sPfsUnicodeToPfsNumberHashMap["Ń"] = "N3"
        sPfsUnicodeToPfsNumberHashMap["N"] = "N5"
        sPfsUnicodeToPfsNumberHashMap["Á"] = "N6"

        // n
        sPfsUnicodeToPfsNumberHashMap["n̂"] = "n1"
        sPfsUnicodeToPfsNumberHashMap["ǹ"] = "n2"
        sPfsUnicodeToPfsNumberHashMap["ń"] = "n3"
        sPfsUnicodeToPfsNumberHashMap["n"] = "n5"
        sPfsUnicodeToPfsNumberHashMap["n̍"] = "n6"

        // Ng
        sPfsUnicodeToPfsNumberHashMap["N̂g"] = "Ng1"
        sPfsUnicodeToPfsNumberHashMap["Ǹg"] = "Ng2"
        sPfsUnicodeToPfsNumberHashMap["Ńg"] = "Ng3"
        sPfsUnicodeToPfsNumberHashMap["Ng"] = "Ng5"
        sPfsUnicodeToPfsNumberHashMap["Á"] = "Ng6"

        // NG
        sPfsUnicodeToPfsNumberHashMap["N̂G"] = "NG1"
        sPfsUnicodeToPfsNumberHashMap["ǸG"] = "NG2"
        sPfsUnicodeToPfsNumberHashMap["ŃG"] = "NG3"
        sPfsUnicodeToPfsNumberHashMap["NG"] = "NG5"
        sPfsUnicodeToPfsNumberHashMap["Á"] = "NG6"

        // ng
        sPfsUnicodeToPfsNumberHashMap["n̂g"] = "ng1"
        sPfsUnicodeToPfsNumberHashMap["ǹg"] = "ng2"
        sPfsUnicodeToPfsNumberHashMap["ńg"] = "ng3"
        sPfsUnicodeToPfsNumberHashMap["ng"] = "ng5"
        sPfsUnicodeToPfsNumberHashMap["n̍g"] = "ng6"

        for ((key, value) in sPfsUnicodeToPfsNumberHashMap) {
            sPfsNumberToPfsUnicodeHashMap[value] = key
        }
    }
}
