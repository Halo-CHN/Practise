package com.hehebaba.practise.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_viewpager2_demo.*

class ViewPager2DemoActivity : AppCompatActivity() {

    private lateinit var viewPage2DemoAdapter: ViewPage2DemoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager2_demo)

        initView()
        initData()
    }

    private fun initView() {

        viewPage2DemoAdapter = ViewPage2DemoAdapter(this)
        pager.adapter = viewPage2DemoAdapter

        TabLayoutMediator(tab_layout, pager) { tab, position ->
            tab.text = (position + 1).toString()
        }.attach()
    }

    private fun initData() {

    }
}

private const val ARG_OBJECT = "object"

class ViewPage2DemoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_viewpager2_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val textView = view.findViewById<TextView>(R.id.tv_in_demo_fragment)
            textView.text = getInt(ARG_OBJECT).toString()
        }
    }
}

class ViewPage2DemoAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 10
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPage2DemoFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}
