package com.example.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.search.data.ImagesResult
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {
    var list = ArrayList<ImagesResult>()
    var positionImage = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        list = intent.getSerializableExtra(ConstantsForCode.LIST_KEY) as ArrayList<ImagesResult>
        positionImage = intent.getStringExtra(ConstantsForCode.POSITION_KEY).toString()
        init()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    fun init(){
        val adapter = AdapterView(viewPager,this, list)
        adapter.submitList(list)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(positionImage.toInt() - 1,false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            val i = Intent(this,FirstActivity::class.java)
            i.putExtra(ConstantsForCode.LIST_KEY,list)
            i.putExtra(ConstantsForCode.STRING_KEY,"true")
            startActivity(i)
        }
        return true
    }

}