package me.dio.bankline.ui.welcome

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import me.dio.bankline.R
import me.dio.bankline.databinding.ActivityWelcomeBinding
import me.dio.bankline.domain.Correntista
import me.dio.bankline.ui.statement.BankStatementActivity

class WelcomeActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName

    private val binding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btContinue.setOnClickListener {

            if (TextUtils.isEmpty(binding.etAccountHolderId.text.toString())) {
                Log.e(TAG, getString(R.string.error_account_holder_required))
                binding.etAccountHolderId.setError(getString(R.string.error_account_holder_required))
            } else {
                try {
                    val accountHolderId = binding.etAccountHolderId.text.toString().toInt()
                    val accountHolder = Correntista(accountHolderId)
                    val intent = Intent(this, BankStatementActivity::class.java).apply {
                        putExtra(BankStatementActivity.EXTRA_ACCOUNT_HOLDER, accountHolder)
                    }
                    startActivity(intent)
                } catch (e: Exception) {
                    Log.e(TAG, e.message, e)
                }
            }
        }
    }
}