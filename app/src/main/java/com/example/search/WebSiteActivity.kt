package com.example.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.search.data.ImagesResult
import kotlinx.android.synthetic.main.activity_web_site.*

class WebSiteActivity : AppCompatActivity() {
    var position = ""
    var list = ArrayList<ImagesResult>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_site)

        val link = intent.getStringExtra(ConstantsForCode.LINK_KEY)
        position = intent.getStringExtra(ConstantsForCode.POSITION_KEY).toString()
        list = intent.getSerializableExtra(ConstantsForCode.LIST_KEY) as ArrayList<ImagesResult>
        if (link != null) {
            webView.loadUrl(link.toString())
            txtError.visibility = View.GONE
        }else{
            txtError.visibility = View.VISIBLE
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            setDataFromWeb()
        }
        return true
    }
    fun setDataFromWeb(){
        Log.i("QQ",position)
        val i = Intent(this, ImageActivity::class.java).apply {
            putExtra(ConstantsForCode.POSITION_KEY,position)
            putExtra(ConstantsForCode.LIST_KEY,list)
        }
        startActivity(i)
    }
}