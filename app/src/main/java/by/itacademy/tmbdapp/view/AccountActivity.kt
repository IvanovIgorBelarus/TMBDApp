package by.itacademy.tmbdapp.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository
import by.itacademy.tmbdapp.databinding.ActivityAccountBinding
import by.itacademy.tmbdapp.model.AccountModel
import by.itacademy.tmbdapp.presentation.AccountActivityListener
import by.itacademy.tmbdapp.presentation.AccountPresenter
import by.itacademy.tmbdapp.presentation.AccountPresenterImpl
import by.itacademy.tmbdapp.presentation.adapters.PosterAdapter
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AccountActivity : BaseActivity(), AccountActivityListener {
    private lateinit var binding: ActivityAccountBinding
    private val accountPresenter: AccountPresenter by inject<AccountPresenterImpl> { parametersOf(this) }
    private val posterAdapter: PosterAdapter by inject()
    private val authenticationRepository by inject<AuthenticationRepository>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (authenticationRepository.getSessionId() != null) {
            accountPresenter.getAccountDetailsApi(authenticationRepository.getSessionId(), posterAdapter)
        }
        binding.posterRecycler.apply {
            layoutManager =
                LinearLayoutManager(this@AccountActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = posterAdapter
        }
    }

    override fun getDetails(accountModel: AccountModel) {
        with(binding) {
            userName.text = accountModel.username
            Glide.with(avatar)
                .load(accountModel.avatar)
                .into(avatar)
        }
    }
}