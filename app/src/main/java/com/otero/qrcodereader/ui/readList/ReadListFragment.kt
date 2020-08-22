package com.otero.qrcodereader.ui.readList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.otero.qrcodereader.R
import kotlinx.android.synthetic.main.fragment_read_list.*

class ReadListFragment : Fragment(), View.OnClickListener {

    private lateinit var readListViewModel: ReadListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        readListViewModel =
            ViewModelProviders.of(this).get(ReadListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_read_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        btn_download.setOnClickListener(this)
    }

    private fun initRecyclerView() {
        val resultAdapter = ResultAdapter()
        val verticalSpacingItemDecorator = VerticalSpacingItemDecorator(32)

        read_list.adapter = resultAdapter
        read_list.addItemDecoration(verticalSpacingItemDecorator)
        read_list.layoutManager =
            LinearLayoutManager(activity?.baseContext, LinearLayoutManager.VERTICAL, false)
    }

    override fun onClick(v: View?) {
        when(v){
            btn_download -> {
                ExportConfirmationDialog(
                    onConfirm = {
                        Log.d("ReadListFragment", "Confirm")
                        Snackbar.make(
                            view!!,
                            getString(R.string.export_list_confirmation_dialog_success_message),
                            Snackbar.LENGTH_LONG
                        ).show()
                    },
                    onCancel = { Log.d("ReadListFragment", "Cancel") }
                ).show(parentFragmentManager)
            }
        }
    }
}