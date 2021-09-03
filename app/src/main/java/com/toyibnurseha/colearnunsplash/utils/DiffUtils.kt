package com.toyibnurseha.colearnunsplash.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.toyibnurseha.colearnunsplash.domain.model.UnsplashModel

class DiffUtils(
    private val oldList: List<UnsplashModel>,
    private val newList: List<UnsplashModel>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (
            altDescription,
            color,
            createdAt,
            _,
            height,
            id,
            likes,
            _,
            updatedAt,
            width,
        ) = oldList[oldPosition]
        val (
            altDescription1,
            color1,
            createdAt1,
            _,
            height1,
            id1,
            likes1,
            _,
            updatedAt1,
            width1,
        ) = newList[newPosition]

        return altDescription == altDescription1
                && color == color1
                && createdAt == createdAt1
                && height == height1
                && id == id1
                && likes == likes1
                && updatedAt == updatedAt1
                && width == width1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}