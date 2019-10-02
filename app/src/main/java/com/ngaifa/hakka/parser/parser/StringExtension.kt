package com.ngaifa.hakka.parser.parser

fun String.isNumeric(): Boolean = this.matches("-?\\d+(\\.\\d+)?".toRegex())