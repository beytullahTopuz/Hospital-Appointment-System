package com.t4zb.hospital_appointment_system.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.t4zb.hospital_appointment_system.R
import com.t4zb.hospital_appointment_system.databinding.FragmentLoginBinding
import com.t4zb.hospital_appointment_system.ui.viewmodel.LoginViewModel
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState
import com.t4zb.hospital_appointment_system.util.Constants
import com.t4zb.hospital_appointment_system.util.showToast


class LoginFragment : Fragment() {

    private lateinit var mbinding: FragmentLoginBinding
    private lateinit var mContext: FragmentActivity
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UserModelState.USERTYPE.value = Constants.USERTYPEPATIENT
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireActivity()
        mbinding = FragmentLoginBinding.bind(view)
        switchControl()

        mbinding.registerBtn.setOnClickListener {
            var direction = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()

            findNavController().navigate(direction)
        }

        mbinding.loginBtn.setOnClickListener {
            viewModel.mcontext = mContext
            viewModel.email = mbinding.loginEmailTextField.text
            viewModel.password = mbinding.loginPasswordTextField.text

            viewModel.onLoginClicted()

            UserModelState.ISFAIL.observe(viewLifecycleOwner, { isfail ->
                if (isfail == true) {
                    showToast(mContext, "bir hata oluştu")
                    UserModelState.ISFAIL.value = false
                }
            })
            UserModelState.ISUSERCONNECTED.observe(viewLifecycleOwner,{
                if (it == true){
                    if(UserModelState.USERTYPE.value == Constants.USERTYPEPATIENT){
                        var directions = LoginFragmentDirections.actionLoginFragmentToHomePagePationFragment()
                        findNavController().navigateUp()
                        findNavController().navigate(directions)
                    }else{

                            // doktorun home page sayfasina gidecek
                        var directions =
                            LoginFragmentDirections.actionLoginFragmentToHomePageDoctorFragment()
                        findNavController().navigate(directions)
                    }
                }
            })
        }
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    fun switchControl() {
        mbinding.userTypeSwitchLogin.setOnCheckedChangeListener { compoundButton, onswitch ->
            if (onswitch) {
                UserModelState.USERTYPE.value = Constants.USERTYPEDOCTOR
                mbinding.userTypeTextViewLogin.text = "Doctor Login"
            } else {
                UserModelState.USERTYPE.value = Constants.USERTYPEPATIENT
                mbinding.userTypeTextViewLogin.text = "Patient Login"
            }
        }
    }

    companion object {

    }
}