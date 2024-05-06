package com.tencent.karaoke.puppy.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tencent.karaoke.puppy.R
import com.tencent.karaoke.puppy.databinding.ActivityPuppyBinding
import com.tencent.karaoke.puppy.viewModel.PuppyViewModel

class MainActivity : AppCompatActivity() {

    private var mViewModel: PuppyViewModel? = null
    private var mBinding: ActivityPuppyBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initData()
        initObserver()
    }

    private fun initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_puppy)
        setContentView(mBinding?.root)
        mViewModel = ViewModelProvider(this)[PuppyViewModel::class.java]
        mBinding?.lifecycleOwner = this

    }

    private fun initListener() {
        mBinding?.cardPuppy?.setResultLis { correct ->
            Toast.makeText(
                this,
                if (correct) "answer correctly！" else "wrong answer",
                Toast.LENGTH_SHORT
            ).show()
        }
        mBinding?.textNext?.setOnClickListener {
            mViewModel?.createNewPuppyQuestion()
        }
    }

    private fun initData() {
        mViewModel?.getPuppyList()
    }

    private fun initObserver() {
        mViewModel?.puppyQue?.observe(this) {
            mBinding?.cardPuppy?.setData(it.selections, it.answer)
            mBinding?.imgPuppy?.let { img ->
                Glide.with(this)
                    .load(it.url)
                .placeholder(R.mipmap.icon_loading)
//                .error(R.drawable.error)
                    .into(img)
            }
        }
    }
}