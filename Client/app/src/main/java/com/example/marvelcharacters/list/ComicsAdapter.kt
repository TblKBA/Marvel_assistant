package com.example.marvelcharacters.list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.marvelcharacters.R

class ComicsAdapter(private val context: Context, private val dataSource: ArrayList<ComicsItem>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.view_itemc, parent, false)

        val comicsImageView = rowView.findViewById(R.id.comicsImage) as ImageView
        val comicsNameView = rowView.findViewById(R.id.comicsName) as TextView

        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        Glide.with(context)
            .load(getItem(position).comicsImage)
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            .placeholder(R.drawable.standard_large)
            .into(comicsImageView)

        comicsNameView.text = getItem(position).comicsTitle

        return rowView
    }

    override fun getItem(position: Int): ComicsItem {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}