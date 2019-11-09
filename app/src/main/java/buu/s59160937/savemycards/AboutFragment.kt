package buu.s59160937.savemycards

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import buu.s59160937.savemycards.databinding.FragmentAboutBinding
import buu.s59160937.savemycards.databinding.FragmentListCardBinding

class About : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAboutBinding>(
            inflater, R.layout.fragment_about, container, false
        )

        binding.shareButton.setOnClickListener{v ->
            shareSuccess()
        }

        return binding.root
    }

    private fun getShareIntent() : Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, getString(R.string.app_name))
        return shareIntent
    }
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

}
