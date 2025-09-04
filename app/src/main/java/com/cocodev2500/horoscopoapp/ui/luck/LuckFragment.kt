package com.cocodev2500.horoscopoapp.ui.luck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import com.cocodev2500.horoscopoapp.R
import com.cocodev2500.horoscopoapp.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import androidx.core.view.isVisible

@AndroidEntryPoint
class LuckFragment : Fragment() {
    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initListeners()
    }

    private fun initListeners() {
        binding.ivRulette.setOnClickListener {
            spinRulette()
        }
    }

    private fun spinRulette() {
        val randomAngle = Random.nextInt(360, 720) // Random angle between 360 and 720 degrees
        val degress = binding.ivRulette.rotation + randomAngle
        val animator = binding.ivRulette.animate()
            .rotation(degress)
            .setDuration(2000)
        animator.interpolator = DecelerateInterpolator()
        animator.withEndAction { //aca va el codigo que quiero que se ejecute cuando termine la animacion
            slideCard()
        }
        animator.start()
    }

    private fun slideCard() {
      val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(),  R.anim.slide_up  )
            slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                binding.reverse.isVisible = true
            }

            override fun onAnimationEnd(animation: Animation) {
                growCard()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        binding.reverse.startAnimation(slideUpAnimation)
    }

    private fun growCard(){
        val growAnimation = AnimationUtils.loadAnimation(requireContext(),  R.anim.grow )
        growAnimation.setAnimationListener(
            object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {
                    binding.reverse.isVisible = false
                    showPremonitionView()
                }

                override fun onAnimationRepeat(animation: Animation) {}
            }
        )
        binding.reverse.startAnimation(growAnimation)
    }

    private fun showPremonitionView() {
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
        disappearAnimation.duration = 500

        val apperAnimation = AlphaAnimation(0.0f, 1.0f)
        apperAnimation.duration = 1000

        disappearAnimation.setAnimationListener(
            object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {
                    binding.preView.isVisible = false
                    binding.predictionView.isVisible = true
                }

                override fun onAnimationRepeat(animation: Animation) {}
            }
        )
        binding.preView.startAnimation(disappearAnimation)
        binding.predictionView.startAnimation(apperAnimation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        _binding = FragmentLuckBinding.inflate(inflater, container, false)
        return binding.root
    }

}