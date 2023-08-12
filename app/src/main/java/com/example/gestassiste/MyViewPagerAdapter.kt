package com.example.gestassiste


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gestassiste.fragmentos.Frag_Assistencia
import com.example.gestassiste.fragmentos.Frag_Cliente
import com.example.gestassiste.fragmentos.Frag_Equipamento

class MyViewPagerAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 1
    }
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            //0 -> Frag_Cliente()
            //1 -> Frag_Equipamento()
            0 -> Frag_Assistencia()
            else -> Frag_Assistencia()
        }
    }
}