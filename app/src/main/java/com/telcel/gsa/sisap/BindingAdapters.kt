package com.telcel.gsa.sisap

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textview.MaterialTextView
import com.telcel.gsa.sisap.ui.assignment.AssignmentAdapter
import com.telcel.gsa.sisap.ui.classification.ClassificationViewModel
import com.telcel.gsa.sisap.ui.committee.CommitteeAdapter
import com.telcel.gsa.sisap.ui.description.DescriptionViewModel
import com.telcel.gsa.sisap.ui.documents.FolioDocumentsAdapter
import com.telcel.gsa.sisap.ui.incidents.IncidentsAdapter
import com.telcel.gsa.sisap.ui.folio.FoliosAdapter
import com.telcel.gsa.sisap.ui.historical.HistoricalAdapter
import com.telcel.gsa.sisap.ui.network.*
import com.telcel.gsa.sisap.ui.workteam.WorkTeamAdapter

@BindingAdapter("incidentsListData")
fun bindRecyclerView(recyclerView: RecyclerView, data : List<Incidence>?){
    data?.let {
        val adapter = recyclerView.adapter as IncidentsAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("foliosListData")
fun bindRecyclerViewFolios(recyclerView: RecyclerView, data : List<Folio>?){
    data?.let {
        val adapter = recyclerView.adapter as FoliosAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("workTeamListData")
fun bindRecyclerViewWorkTeam(recyclerView: RecyclerView, data : List<Employee>?){
    data?.let {
        val adapter = recyclerView.adapter as WorkTeamAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("documentsFolioListData")
fun bindRecyclerViewDocumentsFolio(recyclerView: RecyclerView,data: List<Document>?){
    data?.let{
        val adapter = recyclerView.adapter as FolioDocumentsAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("committeeMembersListData")
fun bindRecyclerViewCommitteeMembers(recyclerView: RecyclerView,data: List<CommitteeMember>?){
    data?.let {
        val adapter = recyclerView.adapter as CommitteeAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("historicalListData")
fun bindRecyclerViewHistorical(recyclerView: RecyclerView,data: List<ActionHistorical>?){
    data?.let {
        val adapter = recyclerView.adapter as HistoricalAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("teamMembersListData")
fun bindRecyclerViewTeamMembers(recyclerView: RecyclerView,data: List<TeamMember>?){
    data?.let{
        val adapter = recyclerView.adapter as AssignmentAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("fullName")
fun MaterialTextView.bindTextViewFullName(employee: Employee){
    text = this.context.getString(R.string.full_name_formated,employee.employeeName,employee.employeeLastName,employee.employeeSecondLastName)
}

@BindingAdapter("complexityString")
fun MaterialTextView.bindTextViewComplexity(folioDetail: FolioDetail){
    text = parseComplexity(folioDetail)
}

@BindingAdapter("priorityString")
fun MaterialTextView.bindTextViewPriority(folioDetail: FolioDetail){
    text = parsePriority(folioDetail)
}

@BindingAdapter("uploadedDate")
fun MaterialTextView.bindTextViewUploadedDate(uploadDate : String){
    text = this.context.getString(R.string.uploaded_date,uploadDate)
}

@BindingAdapter("currentStatus")
fun MaterialTextView.bindTextViewCurrentStatus(status : String?){
    status?.let {
        text = this.context.getString(R.string.status_title_formated,status)
    }
}

@BindingAdapter("hasPendingAction")
fun MaterialButton.bindHasPendingAction( flowControl: FlowControl?){
    flowControl?.let {
        if(flowControl.idFlowControl != 0){
            visibility = View.VISIBLE
            when(flowControl.description){
                "CTRL_CLASIF_FOLIO" -> text = this.context.getString(R.string.classification_tag)
                "CTRL_ASIGNAR_FOLIO" -> text = this.context.getString(R.string.assignment_tag)
                "CTRL_AUTORIZAR_FOLIO" -> text = this.context.getString(R.string.authorization_tag)
                "CTRL_CATEGORIZACION" -> text = this.context.getString(R.string.categorization_tag)
                "CTRL_ENCUESTA_FOLIO" -> text = this.context.getString(R.string.survey_tag)
            }
        }
    }
}

@BindingAdapter("showDateInput")
fun View.bindDateInput(priority: MPriority?){
    priority?.let {
        visibility = when(priority.idPriority){
            5-> View.VISIBLE
            else -> View.GONE
        }
    }

    if(priority == null){
        visibility = View.GONE
    }
}

@BindingAdapter("sendClassification")
fun MaterialButton.bindSendClassification(enabled : Boolean){
    isEnabled = enabled
}

@BindingAdapter("memberOption")
fun MaterialCheckBox.bindMemberOption(teamMember: TeamMember?){
    teamMember?.let {
        text = teamMemberFullName(teamMember)
    }
}

@BindingAdapter("dataLoading")
fun bindStatus(loadingAnimation : LottieAnimationView , status : LoadingStatus){
    when(status){
        LoadingStatus.LOADING ->{
            loadingAnimation.visibility = View.VISIBLE
            loadingAnimation.setAnimation("loading.json")
        }
        LoadingStatus.ERROR ->{
            loadingAnimation.visibility = View.VISIBLE
            loadingAnimation.setAnimation("error.json")
        }
        LoadingStatus.DONE->{
            loadingAnimation.visibility = View.GONE
        }
    }
}