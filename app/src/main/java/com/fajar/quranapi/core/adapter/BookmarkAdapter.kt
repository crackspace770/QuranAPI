package com.fajar.quranapi.core.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.R
import com.fajar.quranapi.core.data.local.entity.VerseEntity
import com.fajar.quranapi.databinding.VerseItemBinding
import com.fajar.quranapi.ui.main.MainActivity
import com.fajar.quranapi.ui.quran.bookmark.BookmarkFragmentDirections
import com.fajar.quranapi.ui.quran.bookmark.BookmarkViewModel
import java.io.IOException

class BookmarkAdapter(private val context: Context): RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    private var verseItems: List<VerseEntity> = emptyList()
    private var isPlaying: Boolean = false
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var viewModel: BookmarkViewModel

    @SuppressLint("NotifyDataSetChanged")
    fun setVerseItems(items: List<VerseEntity>) {
        verseItems = items
        notifyDataSetChanged()
    }

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = VerseItemBinding.bind(itemView)

        fun bind(verseItem: VerseEntity) {
            with(binding) {
                tvNumber.text = verseItem.number
                tvVerse.text = verseItem.arab
                tvTerjemahan.text = verseItem.translation

                btnPlayAudio.setOnClickListener {
                    val audioUrl = verseItem.audio
                    playAudio(audioUrl)
                }
            }

            itemView.setOnClickListener {
                navigateToSurah(verseItem, itemView)
            }

        }
    }

    private fun navigateToSurah(verseItem: VerseEntity, view: View) {
        val surahNum = verseItem.number

        // TODO: Check if surahNum is valid and perform navigation
        // For now, you can print it for debugging purposes
        println("Clicked on Surah: $surahNum")

        // TODO: Perform navigation using Safe Args
        // Replace 'yourActionId' with the actual action ID in your nav_graph.xml
        val action = BookmarkFragmentDirections.actionBookmarkFragmentToSurahDetailFragment(surahNum)
        view.findNavController().navigate(action)
    }

    private fun playAudio(audioUrl: String) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
        } else {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.stop()
            }
            mediaPlayer?.reset()
        }

        try {
            mediaPlayer?.setDataSource(audioUrl)
            mediaPlayer?.prepare()
            mediaPlayer?.start()
        } catch (e: IOException) {
            e.printStackTrace()
            // Handle any exceptions or errors that may occur during playback
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.verse_item, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val verseItem = verseItems[position]
        holder.bind(verseItem)
    }

    override fun getItemCount(): Int {
        return verseItems.size
    }


}