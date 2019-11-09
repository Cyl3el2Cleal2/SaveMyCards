package buu.s59160937.savemycards


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import buu.s59160937.savemycards.databinding.FragmentLoginBinding
import java.util.concurrent.Executors


class LoginFragment : Fragment() {



    var retry : Int = 0
    lateinit var binding : FragmentLoginBinding
    // TODO: Rename and change types of parameters
    override fun onCreate(savedInstanceState: Bundle?) {

        (activity as AppCompatActivity)?.supportActionBar?.hide()


        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        (activity as AppCompatActivity)?.supportActionBar?.hide()
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login, container, false
        )

        val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences("Unknow", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        val key = sharedPreferences?.getString("P@ssw0rd", "default")
        if(key.equals("default")){
            binding.playButton.setText("Sign In")
            Toast.makeText(this.context, "Create Password", Toast.LENGTH_SHORT).show()

        }else{
            binding.playButton.setText("Finger Print")
        }

        binding.playButton.setOnClickListener { view: View ->
//                    //Hide keyboard
                    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
            if(binding.editText2.text.isNotEmpty()){
                if(key.equals("default")){
                    editor?.putString("P@ssw0rd", binding.editText2.text.toString())
                    editor?.apply()
                    view.findNavController().navigate(R.id.action_loginFragment_to_listCardFragment)
                }else{
                    if(key.equals(binding.editText2.text.toString())){
                        view.findNavController().navigate(R.id.action_loginFragment_to_listCardFragment)
                    }
                }

            }else{
                if(key.equals("default")){
                    Toast.makeText(this.context, "Enter Password", Toast.LENGTH_SHORT).show()
                }else{
                    getFinger()
                }

            }

        }
        binding.editText2.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if(!key.equals("default")){
                    if(binding.editText2.text.toString().length > 0){
                        binding.playButton.text = "Login"
                    }else{
                        binding.playButton.text = "Finger Print"
                    }
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        Log.i("LoginFragment", "Enter Login")



        return binding.root
    }

    fun test(){
        Toast.makeText(this.context, "Not Implemented", Toast.LENGTH_SHORT).show()
    }

    fun getFinger(){


        val activity: FragmentActivity? = this.activity

        val executor = Executors.newSingleThreadExecutor()
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Set the title to display.")
            .setSubtitle("Set the subtitle to display.")
            .setDescription("Set the description to display")
            .setNegativeButtonText("Negative Button")
            .build()
        val biometricPrompt = activity?.let {
            BiometricPrompt(it, executor, object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
//                    super.onAuthenticationError(errorCode, errString)
                    if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                        // user clicked negative button
                    } else {
                        test()
                    }
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    view?.findNavController()?.navigate(R.id.action_loginFragment_to_listCardFragment)
                }

                override fun onAuthenticationFailed() {
//                    super.onAuthenticationFailed()
                    retry = retry.plus(1)
                    Toast.makeText(context, "Fail Authentication", Toast.LENGTH_SHORT).show()
                }
            })
        }
        if(retry == 0){
            biometricPrompt?.authenticate(promptInfo)
        }else{
            Toast.makeText(context, "Enter Password", Toast.LENGTH_SHORT).show()
            binding.playButton.text = "Login"
        }


    }


    }

