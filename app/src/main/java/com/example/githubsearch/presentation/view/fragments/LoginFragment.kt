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
import com.example.githubsearch.utils.AuthenticationState
import com.example.githubsearch.utils.getClient
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment(R.layout.fragment_login) {
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
        }

    private fun signInWithGoogle() {
        val signInClient = getClient(requireContext())
        setupAuth.launch(signInClient.signInIntent)
    }

    private fun checkAuthState() {
        loginViewModel.authenticationState.observe(viewLifecycleOwner) { auth ->
            when (auth) {
                AuthenticationState.AUTHENTICATED -> {
                    findNavController().navigate(R.id.action_login_fragment_to_viewPagerFragment)
                } else -> {
                    AuthenticationState.UNAUTHENTICATED
                }
            }
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}