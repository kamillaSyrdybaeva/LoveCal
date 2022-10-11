package com.example.lovecalculate.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lovecalculate.R
import com.example.lovecalculate.databinding.FragmentOnBoardingBinding
import com.example.lovecalculate.helper.SharedPreferences
import com.example.lovecalculate.data.models.OnBoardingModel
import com.example.lovecalculate.ui.adapters.OnBoardingAdapter
import com.example.lovecalculate.ui.inter.TransitionLoveCalculate
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment : Fragment(), TransitionLoveCalculate {

    private lateinit var binding: FragmentOnBoardingBinding
    private val list = arrayListOf<OnBoardingModel>()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences.getPreferences(requireContext(), findNavController())

        list.add(
            OnBoardingModel(
                "lottieAnimation/on_boarding_1.json",
                "Have a good time",
                "You should take the time to help those\n who need you", "Father"
            )
        )
        list.add(
            OnBoardingModel(
                "lottieAnimation/on_boarding_2.json", "Cherishing love",
                " It is now no longer possible for\n you to cherish love", "Father"
            )
        )
        list.add(
            OnBoardingModel(
                "lottieAnimation/on_boarding_3.json",
                "Have a breakup?",
                "We have made the correction for you\n don't worry\n Maybe someone is waiting for you!",
                "GO!"
            )
        )
        binding.vpOnBoarding.adapter = OnBoardingAdapter(list, this)
        binding.dotsIndicator.attachTo(binding.vpOnBoarding)
    }

    override fun next() {
        sharedPreferences.putPreferences(requireContext())
        findNavController().navigate(R.id.loveCalculate)
    }

    override fun transition() {
        val adapter = binding.vpOnBoarding.adapter
        val currentPosition = binding.vpOnBoarding.currentItem
        val nextPosition = currentPosition + 1
        if (nextPosition < adapter?.itemCount!!) {
            binding.vpOnBoarding.currentItem = nextPosition
        } else
            binding.vpOnBoarding.currentItem = 0
    }
}