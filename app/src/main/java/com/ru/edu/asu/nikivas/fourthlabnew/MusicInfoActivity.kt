package com.ru.edu.asu.nikivas.fourthlabnew

import android.os.Bundle
import android.app.Activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.*
import com.mtechviral.mplaylib.MusicFinder
import com.ru.edu.asu.nikivas.fourthlabnew.R
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*

import kotlinx.android.synthetic.main.activity_music_info.*

class MusicInfoActivity : Activity() {

    private var mediaPlayer: MediaPlayer? = null
    private var listOfSongs: List<MusicFinder.Song>? = null
    private var isPlay: Boolean = false
    private var currentSong: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_info)

        this.authorButton.onClick { authorButtonClick() }
        this.randomButton.onClick { randomButtonClick() }
        init();

        this.listSongsListView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            if(!isPlay || (position != currentSong)){
                mediaPlayer?.reset()
                mediaPlayer = MediaPlayer.create(ctx, listOfSongs!![position].uri)
                mediaPlayer?.start()
                isPlay = true
                currentSong = position
            }else{
                mediaPlayer?.pause()
                isPlay = false
            }
        }
    }

    fun init() {

        val songFinder = MusicFinder(contentResolver)
        songFinder.prepare()
        var songs = songFinder.allSongs
        var listSongsNames = arrayListOf<String>()

        this.listOfSongs = songs
        songs.forEach{i-> listSongsNames.add(i.title)}

        var ad = ArrayAdapter<String>(this, R.layout.list_white_text,R.id.list_content, listSongsNames);

        listSongsListView.adapter = ad;


    }

    fun randomButtonClick(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun authorButtonClick(){
        val intent = Intent(this, AuthorInfo::class.java)
        startActivity(intent)
    }

}
