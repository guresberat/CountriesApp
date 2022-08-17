package com.guresberat.countriesapp.view.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.guresberat.countriesapp.data.model.Country
import com.guresberat.countriesapp.databinding.CardViewDesignBinding


class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemClickListener: ((item: Country) -> Unit)? = null

    var items = listOf<Country>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val itemBinding: CardViewDesignBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(country: Country) {
            /*
            Glide.with(itemBinding.root.context)
                .load(country.url)
                .into(object : CustomTarget<Drawable>(50, 50) {
                    override fun onLoadCleared(drawable: Drawable?) {
                        itemBinding.textView.setCompoundDrawablesWithIntrinsicBounds(
                            drawable,
                            null,
                            null,
                            null
                        )
                    }

                    override fun onResourceReady(
                        res: Drawable,
                        transition: com.bumptech.glide.request.transition.Transition<in Drawable>?
                    ) {
                        itemBinding.textView.setCompoundDrawablesWithIntrinsicBounds(
                            res,
                            null,
                            null,
                            null
                        )
                    }

                })*/
            Glide.with(itemBinding.root.context).load(country.url).into(itemBinding.imageview)
            itemBinding.textView.text = country.name
            itemBinding.root.setOnClickListener {
                itemClickListener?.invoke(country)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = CardViewDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}