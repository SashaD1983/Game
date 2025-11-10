package com.example.simplegame.data

import androidx.annotation.DrawableRes
import com.example.simplegame.R

enum class EmojiElement(@DrawableRes val iconRes: Int) {
    CHEEKY(R.drawable.cheeky),
    EXCITED(R.drawable.excited),
    GLASSES_MOUSTACHE(R.drawable.glasses_moustache),
    HEART(R.drawable.heart),
    SMIRK(R.drawable.smirk);

    companion object {
        val all = listOf(CHEEKY, EXCITED, GLASSES_MOUSTACHE, HEART, SMIRK)
    }
}