package buu.s59160937.savemycards


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import buu.s59160937.savemycards.databinding.FragmentViewCardBinding
import androidx.databinding.adapters.TextViewBindingAdapter.setText




class ViewCardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {

        (activity as AppCompatActivity)?.supportActionBar?.hide()


        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding  = DataBindingUtil.inflate<FragmentViewCardBinding>(
            inflater,
            R.layout.fragment_view_card,
            container,
            false
        )


        binding.apply {
            Name.text = arguments?.get("name").toString()
            cardNumber.setText(arguments?.get("number").toString())
            CVV.setText(arguments?.get("cvv").toString())
            expireDate.setText(arguments?.get("expire").toString())

            cardNumber.setOnClickListener {
                    copyToClipboard(cardNumber.text)
            }

            CVV.setOnClickListener {
                copyToClipboard(CVV.text)
            }

            expireDate.setOnClickListener {
                copyToClipboard(expireDate.text)
            }


        }


        return binding.root
    }

    fun copyToClipboard(text: CharSequence){
        Toast.makeText(context,"Copied!", Toast.LENGTH_SHORT).show()

        val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.primaryClip = clip
    }


}
