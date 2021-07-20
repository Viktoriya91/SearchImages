package com.example.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.search.data.ImagesResult
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class AdapterView (private val viewPager: ViewPager2, contextMain: Context, listImages : ArrayList<ImagesResult>):
ListAdapter<ImagesResult, AdapterView.Holder>(DiffCallback){
    var context = contextMain
    val list = listImages
    class Holder(itemView: View, contextView: Context, listAll: ArrayList<ImagesResult>):RecyclerView.ViewHolder(itemView){
    private val imageItem: ImageView = itemView.findViewById(R.id.imageFull)
    private val btnWebOpen: FloatingActionButton = itemView.findViewById(R.id.btnWebOpen)
    var context = contextView
        val listItems = listAll
    fun setData(item: ImagesResult) {
        Picasso.get()
            .load(item.original)
            .error(R.mipmap.ic_error)
            .into(imageItem)
        btnWebOpen.setOnClickListener {
            val i = Intent(context, WebSiteActivity::class.java).apply {
                putExtra(ConstantsForCode.LINK_KEY, item.link)
                putExtra(ConstantsForCode.POSITION_KEY, item.position.toString())
                putExtra(ConstantsForCode.LIST_KEY, listItems)
            }
            context.startActivity(i)
        }
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterView.Holder {
        return AdapterView.Holder(LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_image_adapter, parent, false),context,list)
    }

    override fun onBindViewHolder(holder: AdapterView.Holder, p: Int) {
        val args : ImagesResult = getItem(p)
        holder.setData(args)
    }
    companion object DiffCallback : DiffUtil.ItemCallback<ImagesResult>(){
        override fun areItemsTheSame(oldItem: ImagesResult, newItem: ImagesResult): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ImagesResult, newItem: ImagesResult): Boolean {
            return oldItem.position == newItem.position
        }
    }

    }
