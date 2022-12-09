package com.example.gestassiste


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gestassiste.fragmentos.Clientes
import com.example.gestassiste.fragmentos.Equipamento
import com.example.gestassiste.fragmentos.Assistencia

class MyViewPagerAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> Clientes()
            1 -> Equipamento()
            2 -> Assistencia()
            else -> Clientes()
        }
    }
}