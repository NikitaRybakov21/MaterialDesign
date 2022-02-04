package com.example.materialdesign.fragment.animation

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BulletSpan
import android.text.style.QuoteSpan
import android.util.Property
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentAnimationBinding
import com.example.materialdesign.main.MainActivity
import com.example.materialdesign.main.SavedOptions

class AnimationsFragment : Fragment() {
    private var _binding: FragmentAnimationBinding? = null
    private val binding: FragmentAnimationBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAnimationBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadBg()

        //binding.textHeader.typeface = Typeface.createFromAsset(requireContext().assets,"font/rany.otf")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.textHeader.typeface = resources.getFont(R.font.current)
        }

        val span = SpannableStringBuilder("Welcome to App NASA")
        span.setSpan(QuoteSpan(R.color.colorAccent,10,10),0 , 19,Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            span.setSpan(BulletSpan(20,resources.getColor(R.color.colorAccent),20),0,19,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
        animation()
    }

    private fun loadBg(){
        val options = SavedOptions.getOptions(requireActivity() as MainActivity)
        val op = options.load("Theme")

        if(op){
            binding.bgStart.setImageResource(R.drawable.bg_start_dark)
        }else{
            binding.bgStart.setImageResource(R.drawable.bg_start)
        }
    }

    @SuppressLint("Recycle")
    private fun animation() = with(binding){
        customButton.setOnClickListener {
            binding.click.text = ""

            customButton.setAnimation(true)
            val delay = 5000L

            val animator = ObjectAnimator.ofFloat(binding.frameContainer,View.ALPHA,0f,100f)
            animator.duration   = 8000
            animator.startDelay = 4700
            animator.start()

            val constraint = ConstraintSet()
            constraint.clone(requireContext(), R.layout.fragment_animation)
            constraint.connect(R.id.customButton,ConstraintSet.BOTTOM, R.id.containerForButton, ConstraintSet.BOTTOM)
            constraint.connect(R.id.customButton,ConstraintSet.TOP, R.id.containerForButton, ConstraintSet.TOP)
            constraint.connect(R.id.view,ConstraintSet.TOP, R.id.containerForButton, ConstraintSet.TOP)

            animationStart(constraint,2000,3000)

            objectAnimator(2000,delay - 200,-200f,View.X,cloud)
            objectAnimator(2100,delay, 700f,View.X,cloud2)

            objectAnimator(2100,delay, 1000f,View.TRANSLATION_Y,moon)
            objectAnimator(2100,delay, 1000f,View.TRANSLATION_Y,view2)

            val animatorText = ObjectAnimator.ofFloat(textHeader,View.ALPHA,0f,100f)
            animatorText.duration   = 8000
            animatorText.startDelay = delay + 500
            animatorText.start()
        }
    }

    private fun objectAnimator(duration: Long, startDelay: Long, value: Float, view: Property<View, Float>,viewItem: View){
        val animator = ObjectAnimator.ofFloat(viewItem,view,value)
        animator.duration     = duration
        animator.startDelay   = startDelay
        animator.interpolator = AnticipateOvershootInterpolator(1f)
        animator.start()
    }

    private fun animationStart(constraint: ConstraintSet,timeAnimation : Long,startDelay : Long) {
        val transition = ChangeBounds()
        transition.duration     = timeAnimation
        transition.interpolator = AnticipateOvershootInterpolator(1f)
        transition.startDelay   = startDelay

        TransitionManager.beginDelayedTransition(binding.constraintContainer, transition)
        constraint.applyTo(binding.constraintContainer)
    }

    companion object {
        fun newInstance() = AnimationsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}