package com.t4zb.hospital_appointment_system.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.t4zb.hospital_appointment_system.R
import com.t4zb.hospital_appointment_system.databinding.FragmentRegisterBinding
import com.t4zb.hospital_appointment_system.ui.viewmodel.RegisterViewModel
import com.t4zb.hospital_appointment_system.ui.viewmodel.UserModelState
import com.t4zb.hospital_appointment_system.util.Constants
import com.t4zb.hospital_appointment_system.util.showToast


class RegisterFragment : Fragment() {

    private lateinit var mbinding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var mContext: FragmentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireActivity()
        UserModelState.USERTYPE.value = Constants.USERTYPEPATIENT

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mbinding = FragmentRegisterBinding.bind(view)
        initializeSpinner()
        switchControl()

        mbinding.loginBtn.setOnClickListener {
            var directions = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(directions)
        }

        mbinding.registerBtn.setOnClickListener {

            viewModel.mcontext = mContext
            viewModel.name = mbinding.loginNameTextField.text
            viewModel.surName = mbinding.loginSurNameTextField.text
            viewModel.email = mbinding.loginEmailTextField.text
            viewModel.password = mbinding.loginPasswordTextField.text

            viewModel.onRegisterClict()
            UserModelState.ISFAIL.observe(viewLifecycleOwner, { isfail ->
                if (isfail == true) {
                    showToast(mContext, "bir hata oluştu")
                    UserModelState.ISFAIL.value = false
                }

            })
            UserModelState.ISUSERCONNECTED.observe(viewLifecycleOwner, {
                if (it == true) {
                    if(UserModelState.USERTYPE.value == Constants.USERTYPEPATIENT){
                        var directions = RegisterFragmentDirections.actionRegisterFragmentToHomePagePationFragment()
                        findNavController().navigate(directions)
                    }else{
                        // pass
                        var directions =
                            RegisterFragmentDirections.actionRegisterFragmentToHomePageDoctorFragment()
                        findNavController().navigateUp()
                        findNavController().navigate(directions)
                    }

                }
            })
        }


    }

    fun switchControl() {
        mbinding.userTypeSwitch.setOnCheckedChangeListener { compoundButton, onswitch ->
            if (onswitch) {
                UserModelState.USERTYPE.value = Constants.USERTYPEDOCTOR
                mbinding.userTypeTextView.text = "Doctor Register"
                mbinding.doctorLayout.visibility = View.VISIBLE
            } else {
                UserModelState.USERTYPE.value = Constants.USERTYPEPATIENT
                mbinding.userTypeTextView.text = "Patient Register"
                mbinding.doctorLayout.visibility = View.INVISIBLE
            }
        }
    }

    fun initializeSpinner() {
        val options = arrayOf(
            Constants.DOCTORTYPE_radyoloji,
            Constants.DOCTORTYPE_kardiyoloji,
            Constants.DOCTORTYPE_noroloji
        )
        var adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, options)

        mbinding.spinnerDoctorType.adapter = adapter


        mbinding.spinnerDoctorType.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

                UserModelState.DOCTORTYPE.value = options[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    companion object {

    }
}