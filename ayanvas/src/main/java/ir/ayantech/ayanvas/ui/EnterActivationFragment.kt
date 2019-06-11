package ir.ayantech.ayanvas.ui

import ir.ayantech.ayannetworking.api.AyanCallStatus
import ir.ayantech.ayanvas.R
import ir.ayantech.ayanvas.core.SubscriptionResult
import ir.ayantech.ayanvas.core.VasUser
import ir.ayantech.ayanvas.helper.loadBase64
import ir.ayantech.ayanvas.helper.setHtmlText
import ir.ayantech.ayanvas.helper.setOnTextChange
import ir.ayantech.ayanvas.model.ConfirmMciSubscriptionInput
import ir.ayantech.ayanvas.model.EndPoint
import ir.ayantech.ayanvas.ui.fragmentation.FragmentationFragment
import kotlinx.android.synthetic.main.fragment_enter_activation.*

class EnterActivationFragment : FragmentationFragment() {

    lateinit var mobileNumber: String

    override fun getLayoutId(): Int = R.layout.fragment_enter_activation

    override fun onCreate() {
        super.onCreate()
        backIv.setOnClickListener { pop() }
        with(getResponseOfGetServiceInfo()!!) {
            iconIv.loadBase64(ImageBase64)
            descriptionTv.setHtmlText(SecondPageContent)
            nextTv.text = SecondPageButton
            activationCodeEt.setOnTextChange { if (it.length == 4) hideSoftInput() }
            nextTv.setOnClickListener {
                getAyanApi().ayanCall<Void>(
                    AyanCallStatus {
                        success {
                            VasUser.saveMobile(activity!!, mobileNumber)
                            (activity as AuthenticationActivity).doCallBack(SubscriptionResult.OK)
                        }
                    },
                    EndPoint.ConfirmMciSubscription,
                    ConfirmMciSubscriptionInput(activationCodeEt.text.toString())
                )
            }
        }
    }
}