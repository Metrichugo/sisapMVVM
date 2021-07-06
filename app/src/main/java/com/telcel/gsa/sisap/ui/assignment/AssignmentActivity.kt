package com.telcel.gsa.sisap.ui.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.databinding.ActivityAssignmentBinding
import com.telcel.gsa.sisap.ui.folio.FoliosAdapter
import com.telcel.gsa.sisap.ui.network.TeamMember

class AssignmentActivity : AppCompatActivity() {

    lateinit var idFolio : String
    lateinit var idEmployee : String
    lateinit var assignmentAdapter : AssignmentAdapter
    lateinit var assignmentViewModel: AssignmentViewModel
    private var checkedProjectManager = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        bundle?.let {
            idFolio = bundle.getString(getString(R.string.id_folio_extra),"")
            idEmployee = bundle.getString(getString(R.string.id_employee_extra),"")
        }
        val binding = DataBindingUtil.setContentView<ActivityAssignmentBinding>(this, R.layout.activity_assignment)
        binding.lifecycleOwner = this
        val assignmentViewModelFactory = AssignmentViewModelFactory(idEmployee,idFolio)
        assignmentViewModel = ViewModelProvider(this,assignmentViewModelFactory).get(
            AssignmentViewModel::class.java)
        binding.viewmodel = assignmentViewModel

        assignmentAdapter = AssignmentAdapter(/*AssignmentAdapter.MemberTeamListener {
                idEmployee -> assignmentViewModel.setSelectedResource(idEmployee)
        }*/)
        binding.teamMembersList.adapter = assignmentAdapter

        binding.projectManagerTitle.setOnClickListener {
            assignmentViewModel.teamMemberList.observe(this,{ members ->
                MaterialAlertDialogBuilder(this)
                    .setTitle(getString(R.string.type_title))
                    .setPositiveButton(getString(R.string.accept_button)) {_,_ ->
                        assignmentViewModel.setProjectManagerSelected(members.TeamMemberList[checkedProjectManager])
                        binding.projectManager.text = parseMemberTeamName(members.TeamMemberList[checkedProjectManager])
                    }
                    .setSingleChoiceItems(members.TeamMemberList.map { parseMemberTeamName(it)}.toTypedArray(),checkedProjectManager){_,which->
                        checkedProjectManager = which
                    }.show()
            })
        }
    }

    fun parseMemberTeamName(teamMember: TeamMember) : String{
        return teamMember.name.plus(" ").plus(teamMember.lastName).plus(" ").plus(teamMember.secondLastName)
    }
}