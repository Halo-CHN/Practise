package com.halochn.hencoderpractise.md

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.halochn.hencoderpractise.ArcCorners
import com.halochn.hencoderpractise.R
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {


    private var mSuspensionBarHeight: Int = 0
    private var mCurrentItemPosition: Int = 0

    private var mCurrentView: View? = null

//    private lateinit var mSuspensionBar: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        initView()
        updateSuspensionBar()
    }

    private fun initView() {
        with(recyclerView) {
            val mLayoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            layoutManager = mLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    mCurrentView = mLayoutManager.findViewByPosition(mCurrentItemPosition + 1)
                    updateSuspensionBarY(mCurrentView)
                    if (mCurrentItemPosition != mLayoutManager.findFirstVisibleItemPosition()) {
                        mCurrentItemPosition = mLayoutManager.findFirstVisibleItemPosition()
                        updateSuspensionBarY(mLayoutManager.findViewByPosition(mCurrentItemPosition + 1))
                        updateSuspensionBar()
                    }
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    mSuspensionBarHeight = this@RecyclerViewActivity.mSuspensionBar.height
                }

                private fun updateSuspensionBarY(view: View?) {
                    view?.apply {
                        when {
                            top <= mSuspensionBarHeight -> this@RecyclerViewActivity.mSuspensionBar.y =
                                (-(mSuspensionBarHeight - top)).toFloat()
                            else -> this@RecyclerViewActivity.mSuspensionBar.y = 0F
                        }
                    }
                }
            })

            adapter = NetAdapter()
            setHasFixedSize(true)
        }
    }

    private fun updateSuspensionBar() {
        mSuspensionLabelTv.text = getLabel(mCurrentItemPosition)
    }

    companion object {
        private val pics = listOf(
            "https://p2.music.126.net/fsXjbojiD5IisEoK__NfHg==/109951163462214000.jpg?param=640y640",
            "https://p1.music.126.net/K5tDYtuuk8wXpaIoHJ-qkg==/109951163690801396.jpg?param=640y640",
            "https://p1.music.126.net/-PiFClzMw1DDgr9UYzieSQ==/109951164484312818.jpg?param=640y640",
            "https://p1.music.126.net/kA2a3-Hn6uKL535ICuJw5g==/109951163080143501.jpg?param=640y640"
        )

        private val labels = listOf(
            "爱江山更爱美人",
            "摄影师",
            "钻石星辰",
            "写给自己的"
        )

        fun getPic(position: Int): String {
            return pics[position % 4]
        }

        fun getLabel(position: Int): String {
            return labels[position % 4]
        }
    }


    class NetAdapter : RecyclerView.Adapter<NetAdapter.Companion.NetViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_net, parent, false)
            return NetViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: NetViewHolder, position: Int) {
            Glide.with(holder.itemView.context)
                .load(getPic(position))
                .transform(
                    CenterCrop(),
                    ArcCorners(
                        TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            30F,
                            holder.itemView.resources.displayMetrics
                        )
                    )
                )
                .into(holder.mPic)
            holder.mLabel.text = getLabel(position)
        }

        companion object {
            class NetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val mLabel: TextView = itemView.findViewById(R.id.mNetLabelTv)
                val mPic: ImageView = itemView.findViewById(R.id.mNetPicIv)
            }
        }
    }
}