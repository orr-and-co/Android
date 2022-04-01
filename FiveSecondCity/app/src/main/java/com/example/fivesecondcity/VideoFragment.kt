package com.example.fivesecondcity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import java.util.ArrayList

class VideoFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var videoList: MutableList<Video>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoList = ArrayList()
        viewPager = view.findViewById(R.id.viewPager2)

        (videoList as ArrayList<Video>).add(
            Video(
                "android.resource://" + requireContext().packageName + "/" + R.raw.vone,
                "New Title 1",
                "A description"
            )
        )
        (videoList as ArrayList<Video>).add(
            Video(
                "android.resource://" + requireContext().packageName + "/" + R.raw.vtwo,
                "New Title 2",
                "A description"
            )
        )
        val adapter = VideoAdapter(videoList as ArrayList<Video>)
        viewPager.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }
}