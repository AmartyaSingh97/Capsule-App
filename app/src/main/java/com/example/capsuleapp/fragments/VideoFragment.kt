package com.example.capsuleapp.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.capsuleapp.R


class VideoFragment : Fragment() {

    private lateinit var videoView: VideoView
    private var stopPosition: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_video, container, false)

        videoView = view.findViewById(R.id.videoView)

//        videoView.setOnPreparedListener(OnPreparedListener { mp ->
//            mp.setOnVideoSizeChangedListener { mp, width, height -> /*
//                         * add media controller
//                         */
//                val mc = MediaController(this.context)
//                videoView.setMediaController(mc)
//                /*
//                         * and set its position on screen
//                         */mc.setAnchorView(videoView)
//            }
//        })

        val videoPath =   "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"

        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        val mediaController = android.widget.MediaController(this.context)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.requestFocus()
        mediaController.setMediaPlayer(videoView)
        videoView.start()

        return view
    }
    override fun onPause() {
        super.onPause()
        stopPosition = videoView.currentPosition
        videoView.pause()
    }

    override fun onResume() {
        super.onResume()
        videoView.seekTo(stopPosition)
        videoView.start()
    }


}