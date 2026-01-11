package com.example.utsanmp.view

import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

// BindingAdapter helpers to bind RadioGroup <-> String value using RadioButton tags
@BindingAdapter("checkedValue")
fun bindCheckedValue(rg: RadioGroup, value: String?) {
    if (value == null) {
        rg.clearCheck()
        return
    }
    for (i in 0 until rg.childCount) {
        val child = rg.getChildAt(i)
        if (child is RadioButton) {
            val tag = child.tag
            if (tag is String && tag == value) {
                if (!child.isChecked) child.isChecked = true
                return
            }
        }
    }
    // if no matching tag, clear selection
    rg.clearCheck()
}

@InverseBindingAdapter(attribute = "checkedValue", event = "checkedValueAttrChanged")
fun captureCheckedValue(rg: RadioGroup): String? {
    val checkedId = rg.checkedRadioButtonId
    if (checkedId == -1) return ""
    val rb = rg.findViewById<RadioButton>(checkedId)
    val tag = rb?.tag
    return if (tag is String) tag else ""
}

@BindingAdapter("checkedValueAttrChanged")
fun setCheckedValueListener(rg: RadioGroup, listener: InverseBindingListener?) {
    if (listener == null) {
        rg.setOnCheckedChangeListener(null)
    } else {
        rg.setOnCheckedChangeListener { _, _ ->
            listener.onChange()
        }
    }
}

