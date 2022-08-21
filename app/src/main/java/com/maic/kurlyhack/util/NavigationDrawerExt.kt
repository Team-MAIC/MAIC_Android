package com.maic.kurlyhack.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.maic.kurlyhack.R

fun Context.showDrawer(dl: DrawerLayout, nv: NavigationView) {
    dl.openDrawer(GravityCompat.END)

    val close = nv.getHeaderView(0).findViewById<ImageView>(R.id.iv_menu_close)
    close.setOnClickListener {
        dl.closeDrawers()
    }

    nv.setNavigationItemSelectedListener { menuItem ->
        Log.d("###", "1번")
        menuItem.isChecked = true
        dl.closeDrawers()
        val id = menuItem.itemId
        val title = menuItem.title.toString()
        when (id) {
            R.id.work -> Toast.makeText(this, "$title 페이지가 준비 중입니다.", Toast.LENGTH_SHORT).show()
            R.id.announcement -> Toast.makeText(this, "$title 페이지가 준비 중입니다.", Toast.LENGTH_SHORT).show()
            R.id.personal -> Toast.makeText(this, "$title 페이지가 준비 중입니다.", Toast.LENGTH_SHORT).show()
            R.id.pay -> Toast.makeText(this, "$title 페이지가 준비 중입니다.", Toast.LENGTH_SHORT).show()
            R.id.logout -> Toast.makeText(this, "$title 페이지가 준비 중입니다.", Toast.LENGTH_SHORT).show()
            R.id.withdrawal -> Toast.makeText(this, "$title 페이지가 준비 중입니다.", Toast.LENGTH_SHORT).show()
        }
        true
    }
}


