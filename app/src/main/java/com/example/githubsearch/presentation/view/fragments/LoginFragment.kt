package com.example.githubsearch.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.githubsearch.R
import com.example.githubsearch.databinding.FragmentLoginBinding
import com.example.githubsearch.presentation.viewModel.LoginViewModel
import com.example.githubsearch.utils.getClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment(R.layout.fragment_login) {
    private val auth by lazy { Firebase.auth }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val loginViewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkAuthState()

        binding.googleHolder.setOnClickListener {
            signInWithGoogle()
        }
        binding.skipAuthHolder.setOnClickListener {
            findNavController().navigate(R.id.action_login_fragment_to_viewPagerFragment)
        }
    }

    private val setupAuth =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            loginViewModel.setupSignIn(result)
            checkAuthState()
        }

    private fun signInWithGoogle() {
        val signInClient = getClient(requireContext())
        setupAuth.launch(signInClient.signInIntent)
    }

    private fun checkAuthState() {
        if (auth.currentUser != null) {
            findNavController().navigate(R.id.action_login_fragment_to_viewPagerFragment)
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}