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
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AuthenticationActivity : BaseActivity(), AuthenticationActivityListener {
    private lateinit var binding: ActivityAuthenticationBinding
    private val authenticationActivityPresenter: AuthenticationActivityPresenter by inject<AuthenticationActivityPresenterImpl> { parametersOf(this) }
    private val authenticationRepository by inject<AuthenticationRepository>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authenticationActivityPresenter.getRequestTokenFromApi()
        binding.exitButton.setOnClickListener {
            val name = binding.userName.text.toString()
            val password = binding.userPassword.text.toString()
            authenticationActivityPresenter.createSessionWithLoginFromApi(
                UsersDataJSON(name, password, authenticationRepository.getRequestToken()!!)
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

