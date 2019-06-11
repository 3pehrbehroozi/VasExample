package ir.ayantech.ayanvas.dialog

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import ir.ayantech.ayanvas.R
import ir.ayantech.ayanvas.helper.setHtmlText
import kotlinx.android.synthetic.main.dialog_yes_no.*

class YesNoDialog(context: Context, title: String, message: String) : Dialog(context) {

    init {
        initialize(context, title, message)
    }

    private fun initialize(context: Context, title: String, message: String) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_yes_no)

        titleTv.text = title
        messageTv.setHtmlText(message)
    }

    fun setPositiveAction(action: View.OnClickListener) {
        positiveTv.setOnClickListener(action)
    }

    fun setNegativeAction(action: View.OnClickListener) {
        negativeTv.setOnClickListener(action)
    }

    fun setNegativeText(text: String) {
        negativeTv.text = text
    }

    fun setPositiveText(text: String) {
        positiveTv.text = text
    }

    fun removeNegativeButton() {
        negativeTv.visibility = View.GONE
    }
}
