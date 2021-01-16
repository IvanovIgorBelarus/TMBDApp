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


class AccountActivity : BaseActivity(), AccountActivityListener {
    private lateinit var binding: ActivityAccountBinding
    private val accountPresenter: AccountPresenter by lazy { AccountPresenterImpl(this) }
    private val posterAdapter: PosterAdapter by lazy { PosterAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (AuthenticationRepository.getSessionId() != null) {
            accountPresenter.getAccountDetailsApi(AuthenticationRepository.getSessionId())
            accountPresenter.getAccountRatedMoviesList(AuthenticationRepository.getSessionId(),
                posterAdapter)
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