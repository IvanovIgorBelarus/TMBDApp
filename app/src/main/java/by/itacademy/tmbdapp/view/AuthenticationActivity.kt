package by.itacademy.tmbdapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository
import by.itacademy.tmbdapp.api.data.UsersDataJSON
import by.itacademy.tmbdapp.databinding.ActivityAuthenticationBinding
import by.itacademy.tmbdapp.presentation.AuthenticationActivityListener
import by.itacademy.tmbdapp.presentation.AuthenticationActivityPresenter
import by.itacademy.tmbdapp.presentation.AuthenticationActivityPresenterImpl

class AuthenticationActivity : BaseActivity(), AuthenticationActivityListener {
    private lateinit var binding: ActivityAuthenticationBinding
    private val authenticationActivityPresenter: AuthenticationActivityPresenter by lazy {
        AuthenticationActivityPresenterImpl(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authenticationActivityPresenter.getRequestTokenFromApi()
        binding.exitButton.setOnClickListener {
            val name = binding.userName.text.toString()
            val password = binding.userPassword.text.toString()
            authenticationActivityPresenter.createSessionWithLoginFromApi(
                UsersDataJSON(name, password, AuthenticationRepository.getRequestToken()!!)
            )
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun createSessionWithLogin(isCreate: Boolean?) {
        if (isCreate!!) {
            Toast.makeText(this, "Login in", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
        }
    }

}

