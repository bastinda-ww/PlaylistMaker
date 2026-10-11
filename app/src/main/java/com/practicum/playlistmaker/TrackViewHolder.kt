package com.practicum.playlistmaker

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class TrackViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

    private val radiusArtCorner: Float = 2f
    private val trackTitle: TextView = itemView.findViewById(R.id.trackTitle)
    private val trackSubtitle: TextView = itemView.findViewById(R.id.artistName)
    private val albumArt: ImageView = itemView.findViewById(R.id.albumArt)

    fun bind(track: Track) {
        trackTitle.text = track.trackName
        trackSubtitle.text = itemView.context.getString(R.string.track_subtitle,track.artistName, track.trackTime)
        Glide.with(itemView)
            .load(track.artworkUrl100)
            .placeholder(R.drawable.placeholder)
            .centerInside()
            .transform(RoundedCorners(dpToPx(radiusArtCorner, itemView.context)))
            .into(albumArt)
    }

    private fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics).toInt()
    }
}