package com.ngaifa.hakka.parser

import com.ngaifa.hakka.R

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ngaifa.hakka.parser.parser.input.KipParseIntentService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        KipParseIntentService.startParsing(this)
        finish()
    }
}
