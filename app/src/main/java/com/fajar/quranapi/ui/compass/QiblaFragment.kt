package com.fajar.quranapi.ui.compass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fajar.quranapi.R
import com.fajar.quranapi.databinding.FragmentQiblaBinding


class QiblaFragment: Fragment(R.layout.fragment_qibla) {

    private var _binding: FragmentQiblaBinding? = null
    private val binding get() = _binding!!

    private var currentCompassDegree = 0f
    private var currentNeedleDegree = 0f

    val activity = requireActivity() as AppCompatActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQiblaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CompassQibla.Builder(activity).onPermissionGranted { permission ->
            Toast.makeText(activity, "onPermissionGranted $permission", Toast.LENGTH_SHORT).show()
        }.onPermissionDenied {
            Toast.makeText(activity, "onPermissionDenied", Toast.LENGTH_SHORT).show()
        }.onGetLocationAddress { address ->
            binding.tvLocation.text = buildString {
                append(address.locality)
                append(", ")
                append(address.subAdminArea)
            }
        }.onDirectionChangeListener { qiblaDirection ->
            binding.tvDirection.text = if (qiblaDirection.isFacingQibla) "You're Facing Qibla"
            else "${qiblaDirection.needleAngle.toInt()}°"

            val rotateCompass = RotateAnimation(
                currentCompassDegree, qiblaDirection.compassAngle, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            ).apply {
                duration = 1000
            }
            currentCompassDegree = qiblaDirection.compassAngle

            binding.ivCompass.startAnimation(rotateCompass)

            val rotateNeedle = RotateAnimation(
                currentNeedleDegree, qiblaDirection.needleAngle, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            ).apply {
                duration = 1000
            }
            currentNeedleDegree = qiblaDirection.needleAngle

            binding.ivNeedle.startAnimation(rotateNeedle)
        }.build()

    }

}