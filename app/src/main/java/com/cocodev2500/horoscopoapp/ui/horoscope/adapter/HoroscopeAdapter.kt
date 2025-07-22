package com.cocodev2500.horoscopoapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cocodev2500.horoscopoapp.R
import com.cocodev2500.horoscopoapp.domain.model.HoroscopeInfo

class HoroscopeAdapter (
    private var _horoscopeList: List<HoroscopeInfo> = emptyList(),
    private val onItemSelected: (HoroscopeInfo) -> Unit = {}
): RecyclerView.Adapter<HoroscopeViewHolder>() {

    fun updateHoroscopeList(horoscopeList: List<HoroscopeInfo>) {
        _horoscopeList = horoscopeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        return HoroscopeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return _horoscopeList.size
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(_horoscopeList[position],onItemSelected)
    }

}