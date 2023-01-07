package com.example.gestassiste


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gestassiste.fragmentos.Assistencia
import com.example.gestassiste.fragmentos.Cliente
import com.example.gestassiste.fragmentos.Equipamento

class MyViewPagerAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> Cliente()
            1 -> Equipamento()
            2 -> Assistencia()
            else -> Cliente()
        }
    }
}