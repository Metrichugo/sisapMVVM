package com.telcel.gsa.sisap.ui.classification

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.telcel.gsa.sisap.R
import com.telcel.gsa.sisap.databinding.ActivityClassificationBinding
import com.telcel.gsa.sisap.ui.network.Boss
import java.lang.StringBuilder

class ClassificationActivity : AppCompatActivity() {

    lateinit var idFolio : String
    lateinit var idEmployee : String
    lateinit var classificationViewModel: ClassificationViewModel
    private var checkedSystem = 1
    private var checkedComplexity = 1
    private var checkedType = 1
    private var checkedPriority = 1
    private var checkedBoss = 1
    private var checkedModule = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        bundle?.let {
            idFolio = bundle.getString(getString(R.string.id_folio_extra),"")
            idEmployee = bundle.getString(getString(R.string.id_employee_extra),"")
        }
        val binding = DataBindingUtil.setContentView<ActivityClassificationBinding>(this, R.layout.activity_classification)
        binding.lifecycleOwner = this
        val classificationViewModelFactory = ClassificationViewModelFactory(idEmployee,idFolio)
        classificationViewModel = ViewModelProvider(this,classificationViewModelFactory).get(ClassificationViewModel::class.java)
        binding.viewmodel = classificationViewModel

        binding.systemClassificationTitle.setOnClickListener {
            classificationViewModel.catalogs.observe(this,{ catalog ->
                MaterialAlertDialogBuilder(this)
                    .setTitle(getString(R.string.system_title))
                    .setPositiveButton(getString(R.string.accept_button)) { _,_ ->
                        classificationViewModel.setSystemSelected(catalog.systemCatalog[checkedSystem])
                        binding.systemClassification.text = catalog.systemCatalog[checkedSystem].name
                    }
                    .setSingleChoiceItems(catalog.systemCatalog.map{it.name}.toTypedArray(),checkedSystem){ _, which->
                        checkedSystem = which
                    }
                    .show()
            })
        }

        binding.complexityClassificationTitle.setOnClickListener {
            classificationViewModel.catalogs.observe(this,{ catalog ->
                MaterialAlertDialogBuilder(this)
                    .setTitle(getString(R.string.module_title))
                    .setPositiveButton(getString(R.string.accept_button)) {_,_ ->
                        classificationViewModel.setComplexitySelected(catalog.complexityCatalog[checkedComplexity])
                        binding.complexityClassification.text = catalog.complexityCatalog[checkedComplexity].name
                    }
                    .setSingleChoiceItems(catalog.complexityCatalog.map {it.name}.toTypedArray(),checkedComplexity){_,which->
                        checkedComplexity = which
                    }.show()
            })
        }

        binding.typeClassificationTitle.setOnClickListener {
            classificationViewModel.catalogs.observe(this,{catalog ->
                MaterialAlertDialogBuilder(this)
                    .setTitle(getString(R.string.type_title))
                    .setPositiveButton(getString(R.string.accept_button)) {_,_ ->
                        classificationViewModel.setTypeSelected(catalog.typeCatalog[checkedType])
                        binding.typeClassification.text = catalog.typeCatalog[checkedType].name
                    }
                    .setSingleChoiceItems(catalog.typeCatalog.map {it.name}.toTypedArray(),checkedType){_,which->
                        checkedType = which
                    }.show()
            })
        }

        binding.priorityClassificationTitle.setOnClickListener {
            classificationViewModel.catalogs.observe(this,{catalog ->
                MaterialAlertDialogBuilder(this)
                    .setTitle(getString(R.string.priority_title))
                    .setPositiveButton(getString(R.string.accept_button)) {_,_ ->
                        classificationViewModel.setPrioritySelected(catalog.priorityCatalog[checkedPriority])
                        binding.priorityClassification.text = catalog.priorityCatalog[checkedPriority].name
                    }
                    .setSingleChoiceItems(catalog.priorityCatalog.map {it.name}.toTypedArray(),checkedPriority){_,which->
                        checkedPriority = which
                    }.show()
            })
        }

        binding.bossClassificationTitle.setOnClickListener {
            classificationViewModel.departmentBosses.observe(this, { bosses ->
                MaterialAlertDialogBuilder(this)
                    .setTitle(getString(R.string.boss_title))
                    .setPositiveButton(getString(R.string.accept_button)) {_,_ ->
                        classificationViewModel.setBossSelected(bosses.bossesList[checkedBoss])
                        binding.bossClassification.text = parseCheckedBoss(bosses.bossesList[checkedBoss])
                    }
                    .setSingleChoiceItems(bosses.bossesList.map { parseCheckedBoss(it)}.toTypedArray(),checkedBoss){_,which->
                        checkedBoss = which
                    }.show()
            })
        }

        binding.moduleClassificationTitle.setOnClickListener {
            classificationViewModel.getModules()
            classificationViewModel.moduleSelected.observe(this,{ isSelected ->
                if(isSelected==false){
                    classificationViewModel.doneModuleSelected()
                    MaterialAlertDialogBuilder(this)
                        .setTitle(getString(R.string.module_title))
                        .setPositiveButton(getString(R.string.accept_button)) {_,_ ->
                            classificationViewModel.system.value?.let {
                                classificationViewModel.setModuleSelected(classificationViewModel.modules.value?.modules!![checkedModule])
                                binding.moduleClassification.text = classificationViewModel.modules.value?.modules!![checkedModule].name
                            }
                        }
                        .setSingleChoiceItems(classificationViewModel.modules.value?.modules!!.map {it.name}.toTypedArray(),checkedModule){_,which->
                            checkedModule = which
                        }.show()
                }
            })
        }

        binding.dateClassificationTitle.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccione una fecha")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            datePicker.show(supportFragmentManager,"DatePicker")

            datePicker.addOnPositiveButtonClickListener {
                classificationViewModel.setDatePicked(it)
                binding.dateClassification.text = classificationViewModel.date.value
            }
        }

        classificationViewModel.classificationAction.observe(this,{
            setResult(RESULT_OK)
            finish()
        })
    }

    fun parseCheckedBoss(boss: Boss) : String{
        return boss.name.plus(" ").plus(boss.lastName).plus(" ").plus(boss.secondLastName)
    }
}