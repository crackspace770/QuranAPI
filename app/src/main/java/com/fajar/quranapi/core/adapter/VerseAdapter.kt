package com.fajar.quranapi.core.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.R
import com.fajar.quranapi.core.data.remote.response.AyahsItem
import com.fajar.quranapi.databinding.VerseItemBinding
import com.fajar.quranapi.ui.quran.detail.DetailViewModel
import java.io.IOException

class VerseAdapter(private val viewModel: DetailViewModel) : RecyclerView.Adapter<VerseAdapter.VerseViewHolder>() {

    private var verseItems: List<AyahsItem> = emptyList()
    private var mediaPlayer: MediaPlayer? = null
    private var highlightedPosition: Int = -1

    @SuppressLint("NotifyDataSetChanged")
    fun setHighlightedPosition(position: Int) {
        highlightedPosition = position
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setVerseItems(items: List<AyahsItem>) {
        verseItems = items
        notifyDataSetChanged()
    }



    inner class VerseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = VerseItemBinding.bind(itemView)

        fun bind(verseItem: AyahsItem,  position: Int, context: Context) {
            with(binding) {
                tvNumber.text = verseItem.number.inSurah.toString()
                tvVerse.text = verseItem.arab
                tvTerjemahan.text = verseItem.translation

                btnPlayAudio.setOnClickListener {
                    val audioUrl = verseItem.audio.alafasy
                    playAudio(audioUrl)

                }
                if (position == highlightedPosition) {
                    // Highlight the verse (apply your highlight logic here)
                    itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.blue_bg))
                } else {
                    // Reset the verse highlight (remove background color or reset text color)
                    itemView.setBackgroundColor(Color.TRANSPARENT)
                }

                itemView.setOnClickListener {
                    showVerseAlertDialog(context, verseItem)
                }

            }

        }

        // Function to show the alert dialog
        private fun showVerseAlertDialog(context: Context, verseItem: AyahsItem) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Tandai")
            builder.setMessage("Tandai sebagai terakhir baca?")

            // Add "Yes" button action
            builder.setPositiveButton("Yes") { _, _ ->
                // Handle "Yes" button click here
                viewModel.bookmarkVerse(verseItem)
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
        holder.bind(verseItem, position, holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return verseItems.size
    }
}