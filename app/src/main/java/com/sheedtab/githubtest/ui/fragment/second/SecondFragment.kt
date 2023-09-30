package com.sheedtab.githubtest.ui.fragment.second

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.sheedtab.githubtest.R
import com.sheedtab.githubtest.databinding.BtSheetTestBinding
import com.sheedtab.githubtest.databinding.FragmentSecondBinding
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvShowBottomSheet.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            val dialogBinding =
                BtSheetTestBinding.inflate(layoutInflater, binding.root as ViewGroup, false)

            bottomSheetDialog.setContentView(dialogBinding.root)
            bottomSheetDialog.show()

            dialogBinding.btSheetSubmit.setOnClickListener {
                val birthdayText = dialogBinding.btSheetEditText.text.toString()
                val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                val birthday = Calendar.getInstance()
                birthday.time = dateFormat.parse(birthdayText)!!
                val year = birthday.get(Calendar.YEAR)
                val month = birthday.get(Calendar.MONTH)
                val day = birthday.get(Calendar.DAY_OF_MONTH)
                val birthdayDate = PersianDate().initJalaliDate(year, month+1, day)
                Snackbar.make(
                    binding.root,
                    "تعداد روزهای سن شما: ${birthdayDate.dayUntilToday}",
                    Snackbar.LENGTH_SHORT
                ).apply {
                    show()
                    setAction("باشه") {
                        this.dismiss()
                    }
                }
                bottomSheetDialog.dismiss()
            }
        }
    }

}