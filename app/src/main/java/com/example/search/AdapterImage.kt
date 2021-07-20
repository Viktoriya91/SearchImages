package com.example.searchimages

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.search.ConstantsForCode
import com.example.search.ImageActivity
import com.example.search.R
import com.example.search.data.ImagesResult
import com.squareup.picasso.Picasso

class AdapterImage(listMain: ArrayList<ImagesResult>,contextMain: Context):
    RecyclerView.Adapter<AdapterImage.Holder>() {
    val listArray = listMain
    var context = contextMain
    class Holder(itemView: View, contextView: Context, list: ArrayList<ImagesResult>): RecyclerView.ViewHolder(itemView){
        val imageItem: ImageView = itemView.findViewById(R.id.imageItem)
        val context = contextView
        val listImages = list
        fun setData(item: ImagesResult){
            Picasso.get()
                .load(item.thumbnail)
                .error(R.mipmap.ic_error)
                .into(imageItem)

            itemView.setOnClickListener{
                val intentAdapter = Intent(context, ImageActivity::class.java).apply {
                    putExtra(ConstantsForCode.LIST_KEY,listImages)
                    putExtra(ConstantsForCode.POSITION_KEY,item.position.toString())
               }
                context.startActivity(intentAdapter)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.adapter_image,parent,false),context,listArray)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        listArray.get(position)?.let { holder.setData(it) }
    }

    fun updateAdapter (listItems:List<ImagesResult>) {
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listArray.size
    }
}