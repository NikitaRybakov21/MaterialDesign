package com.example.materialdesign.fragment.animation

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Property
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.materialdesign.R
import com.example.materialdesign.databinding.AnimationFragmentBinding

class AnimationsFragment : Fragment() {
    private var _binding: AnimationFragmentBinding? = null
    private val binding: AnimationFragmentBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = AnimationFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animation()
    }

    @SuppressLint("Recycle")
    private fun animation() = with(binding){
        customButton.setOnClickListener {

            customButton.setAnimation(true)
            val delay = 5000L

            val animator = ObjectAnimator.ofFloat(binding.frameContainer,View.ALPHA,0f,100f)
            animator.duration   = 8000
            animator.startDelay = 4700
            animator.start()

            val constraint = ConstraintSet()
            constraint.clone(requireContext(), R.layout.animation_fragment)
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