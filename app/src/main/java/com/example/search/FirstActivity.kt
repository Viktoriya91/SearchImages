package com.example.search

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.search.data.ImagesResult
import com.example.searchimages.AdapterImage
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstActivity : AppCompatActivity() {
    val adapter = AdapterImage(ArrayList(),this)
    var listAll = ArrayList<ImagesResult>()
    var page = 0
    var query = ""
    var connect = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        init()
        getIntentView()
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun connectInternet():Boolean{
        val connectManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(connectManager != null){
            val isConnect = connectManager.getNetworkCapabilities(connectManager.activeNetwork)
            if(isConnect != null){
                return true
            }
        }
        return false
    }

    fun init(){
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = adapter
        txtNotConnect.visibility = View.GONE
        btnAddImages.visibility = View.GONE
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_app,menu)
        val searchItem = menu.findItem(R.id.search_item)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onQueryTextSubmit(querySearch: String?): Boolean {
                connect = connectInternet()
                if(connect == true){
                    txtNotConnect.visibility = View.GONE
                if(querySearch != null){
                    page = 0
                    if(listAll.size>0) listAll.clear()
                    getImagesAll(querySearch,page)
                    query = querySearch
                    searchView.clearFocus()
                }}else {
                    txtNotConnect.visibility = View.VISIBLE
                    btnAddImages.visibility = View.GONE
                    listAll.clear()
                    adapter.updateAdapter(listAll)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return true
    }

    private fun getImagesAll(querySearch: String, pageSearch: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val api = InterfaceApi()
            val response = api.searchImages(querySearch, ConstantsForCode.TBM, pageSearch)
            withContext(Dispatchers.Main) {
                val listImagesResult = response.imagesResults
                if (listImagesResult.size > 0) {
                    listAll = (listAll + listImagesResult) as ArrayList<ImagesResult>
                     adapter.updateAdapter(listAll)
                    btnAddImages.visibility = View.VISIBLE
                }
            }
        }
    }

    fun OnClickAddImages(view: View){
        page++
        getImagesAll(query,page)
    }

    fun getIntentView(){
        val i = intent
        if(i != null){
            if(i.getStringExtra(ConstantsForCode.STRING_KEY) == "true"){
                listAll.clear()
                listAll = i.getSerializableExtra(ConstantsForCode.LIST_KEY) as ArrayList<ImagesResult>
                adapter.updateAdapter(listAll)
                btnAddImages.visibility = View.VISIBLE
            }
        }
    }
}
