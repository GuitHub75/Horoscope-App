package com.cocodev2500.horoscopoapp.ui.palmistry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import com.cocodev2500.horoscopoapp.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PalmistryFragment : Fragment() {

    companion object {
        private const val CAMERA_PERMISSION_CODE = android.Manifest.permission.CAMERA
    }

    private var _binding : FragmentPalmistryBinding? = null
    private val binding get() = _binding!!

    private val requestCameraPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
               //startCamera()
            } else {
                Toast.makeText(requireContext(), "Acepta los permisos para una experiencia magica", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPalmistryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (checkCameraPermission()) {
            //tiene los permisos
        } else {
            requestCameraPermissionLauncher.launch(CAMERA_PERMISSION_CODE)
        }
    }

    private fun checkCameraPermission() : Boolean {
       return PermissionChecker.checkSelfPermission(
            requireContext(),
           CAMERA_PERMISSION_CODE
        ) == PermissionChecker.PERMISSION_GRANTED
    }

}