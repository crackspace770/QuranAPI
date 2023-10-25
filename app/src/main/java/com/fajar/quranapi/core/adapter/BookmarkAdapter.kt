package com.fajar.quranapi.core.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.R
import com.fajar.quranapi.core.data.local.entity.AyahItem
import com.fajar.quranapi.core.data.local.entity.VerseEntity
import com.fajar.quranapi.core.data.remote.response.AyahsItem
import com.fajar.quranapi.core.ui.ViewModelFactories
import com.fajar.quranapi.databinding.VerseItemBinding
import com.fajar.quranapi.ui.quran.bookmark.BookmarkViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.VerseViewHolder>() {

    private var verseItems: List<VerseEntity> = emptyList()
    private var isPlaying: Boolean = false
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var detailViewModel: BookmarkViewModel

    @SuppressLint("NotifyDataSetChanged")
    fun setVerseItems(items: List<VerseEntity>) {
        verseItems = items
        notifyDataSetChanged()
    }

    private fun obtainViewModel(activity: AppCompatActivity): BookmarkViewModel {
        val factory = ViewModelFactories.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[BookmarkViewModel::class.java]
    }

    inner class VerseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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


                itemView.setOnClickListener {
                    showVerseAlertDialog(verseItem)
                }

            }

        }

        // Function to show the alert dialog
        private fun showVerseAlertDialog(verseItem: VerseEntity) {
            val builder = AlertDialog.Builder(itemView.context)
            builder.setTitle("Tandai")
            builder.setMessage("Tandai sebagai terakhir baca?")

            // Add "Yes" button action
            builder.setPositiveButton("Yes") { _, _ ->
                // Handle "Yes" button click here
                val removedItem = verseItem
                // Remove the item from your list
                verseItems = verseItems.filter { it != removedItem }
                // Notify the adapter about the change
                notifyDataSetChanged()
                // Remove the item from the database
                detailViewModel.deleteBookmark(removedItem.number)

            }

            // Add "No" button action
            builder.setNegativeButton("No") { _, _ ->
                // Handle "No" button click here or simply dismiss the dialog
            }

            val dialog = builder.create()
            dialog.show()
        }

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



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.verse_item, parent, false)
        return VerseViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerseViewHolder, position: Int) {
        val verseItem = verseItems[position]
        holder.bind(verseItem)
    }

    override fun getItemCount(): Int {
        return verseItems.size
    }


}