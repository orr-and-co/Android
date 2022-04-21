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
                "android.resource://" + requireContext().packageName + "/" + R.raw.vonecamhs,
                "CAMHS Lockdown Report",
                "CAMHS have published a report showing the effects of lockdown on young people."
            )
        )
        (videoList as ArrayList<Video>).add(
            Video(
                "android.resource://" + requireContext().packageName + "/" + R.raw.vtwocyclerecycle,
                "Cycle & Recycle",
                "Primary schools in Cardiff are ending their trials of the Cycle & Recycle program, teachers say the program was a great success."
            )
        )
        (videoList as ArrayList<Video>).add(
            Video(
                "android.resource://" + requireContext().packageName + "/" + R.raw.vthreetrams,
                "Installation of Tram Line around the edge of Cardiff",
                "The council’s plan is to introduce a new tramline that would circumnavigate the city."
            )
        )
        (videoList as ArrayList<Video>).add(
            Video(
                "android.resource://" + requireContext().packageName + "/" + R.raw.vfourpoliceofficers,
                "Additional funding to South Wales Police",
                "South Wales Police Force is receiving an additional £350,000, this move will allow them to hire 10 additional officers to patrol Cardiff’s streets."
            )
        )
        (videoList as ArrayList<Video>).add(
            Video(
                "android.resource://" + requireContext().packageName + "/" + R.raw.vfivestdavids,
                "Shutting down St David’s Centre for Earth Day",
                "The council is proposing to shut down St David’s Centre for a day as a symbolic gesture to tackle growing consumerism."
            )
        )
        (videoList as ArrayList<Video>).add(
            Video(
                "android.resource://" + requireContext().packageName + "/" + R.raw.vsixeducation,
                "Education board 10 year plan",
                "The Education Development Board is working on the next phase of its 10 year plan and wants young people’s opinions."
            )
        )
        (videoList as ArrayList<Video>).add(
            Video(
                "android.resource://" + requireContext().packageName + "/" + R.raw.vsevenrugby,
                "Rugby Festival for World Cup Victory",
                "Following their victory in the Rugby World Cup, the Welsh Rugby Union will be hosting a weeklong celebration at their headquarters on Maven Road."
            )
        )
        (videoList as ArrayList<Video>).add(
            Video(
                "android.resource://" + requireContext().packageName + "/" + R.raw.veighthomeless,
                "Ending the Homeless Hotel Scheme",
                "Following the reopening of most major services in Cardiff, the council has decided to end the Homeless Hotel Scheme, in which the hotels were paid a discounted rate to house homeless people."
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