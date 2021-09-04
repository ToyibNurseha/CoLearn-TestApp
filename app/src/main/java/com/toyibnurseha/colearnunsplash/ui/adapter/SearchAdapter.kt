package com.toyibnurseha.colearnunsplash.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.toyibnurseha.colearnunsplash.databinding.ItemPhotoBinding
import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel
import com.toyibnurseha.colearnunsplash.utils.DiffUtils

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var photos = ArrayList<UnsplashModel>()
    var onItemClick: ((UnsplashModel) -> Unit)? = null

    fun setData(newListData: List<UnsplashModel>?) {
//        if (newListData == null) return
//        val diffUtilCallback = DiffUtils(photos, newListData)
//        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        photos.clear()
        if (newListData != null) {
            photos.addAll(newListData)
        }
            notifyDataSetChanged()
//        diffResult.dispatchUpdatesTo(this)
    }

    private val differCallback = object : DiffUtil.ItemCallback<UnsplashModel>() {
        override fun areItemsTheSame(oldItem: UnsplashModel, newItem: UnsplashModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UnsplashModel, newItem: UnsplashModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
//        holder.bind(photos[position])
    }

    override fun getItemCount(): Int  = differ.currentList.size

    inner class ViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photoData: UnsplashModel) {
            with(binding) {
                itemView.setOnClickListener {
                    listener?.let {
                        it(photoData)
                    }
                }

                Glide.with(itemView.context)
                    .load(photoData.urls.small)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }
                    })
                    .into(ivImage)
            }
        }
    }

    private var listener: ((UnsplashModel?) -> Unit)? = null

    fun setOnItemClickListener(clickListener: ((UnsplashModel?) -> Unit)?) {
        listener = clickListener
    }
}