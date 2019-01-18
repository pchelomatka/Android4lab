package com.ru.edu.asu.nikivas.fourthlabnew

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_author_info.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class AuthorInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author_info)
        this.listButton.onClick { onMusicInfoClick() }

    }

    fun onMusicInfoClick(){
        var intent = Intent(this,MusicInfoActivity::class.java)
        startActivity(intent)
    }

    fun onOneSongClick(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
