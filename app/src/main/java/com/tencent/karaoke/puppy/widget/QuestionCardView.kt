package com.tencent.karaoke.puppy.widget

/**
 * Created by passyruan on 2024/4/29
 */
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.tencent.karaoke.puppy.R

/**
 * question card
 */
class QuestionCardView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private var mItem1: TextView
    private var mItem2: TextView
    private var mItem3: TextView
    private var mList: List<String>? = null
    private var mAns: String? = null
    private var mIndex = -1
    private var mCallBack: ((correct: Boolean) -> Unit)? = null

    init {
        val root = LayoutInflater.from(context)
            .inflate(R.layout.view_question_card, null)
        addView(root)
        mItem1 = root.findViewById(R.id.text_ans1)
        mItem2 = root.findViewById(R.id.text_ans2)
        mItem3 = root.findViewById(R.id.text_ans3)
        initLis()
    }

    private fun initLis() {
        mItem1.setOnClickListener { onSelect(0, mItem1) }
        mItem2.setOnClickListener { onSelect(1, mItem2) }
        mItem3.setOnClickListener { onSelect(2, mItem3) }
    }

    fun setData(ques: List<String>, ans: String) {
        reset()
        mList = ques
        mAns = ans
        mIndex = ques.indexOf(ans)
        if (ques.isNotEmpty()) {
            mItem1.text = ques[0]
        }
        if (ques.size > 1) {
            mItem2.text = ques[1]
        }
        if (ques.size > 2) {
            mItem3.text = ques[2]
        }
    }

    fun setResultLis(callback: (correct: Boolean) -> Unit) {
        mCallBack = callback
    }

    private fun reset() {
        mItem1.setBackgroundResource(R.color.question_color)
        mItem2.setBackgroundResource(R.color.question_color)
        mItem3.setBackgroundResource(R.color.question_color)
    }

    private fun onSelect(index: Int, view: TextView) {
        reset()
        if (index == mIndex) {
            view.setBackgroundResource(R.color.question_correct)
            mCallBack?.invoke(true)
        } else {
            view.setBackgroundResource(R.color.question_error)
            mCallBack?.invoke(false)
        }
    }
}