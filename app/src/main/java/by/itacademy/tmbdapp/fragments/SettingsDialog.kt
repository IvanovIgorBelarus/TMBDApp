package by.itacademy.tmbdapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import by.itacademy.tmbdapp.R

class SettingsDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.warning))
                .setMessage(getString(R.string.message))
                .setPositiveButton("OK") { dialog, id ->
                    dialog.cancel()
                    activity?.finish()
                }
            builder.create()
        }
    }
}