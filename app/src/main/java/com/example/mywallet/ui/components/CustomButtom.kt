package com.example.mywallet.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.example.mywallet.R

class CustomButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.buttonStyle
): AppCompatButton(context, attrs, defStyleAttr) {

    init {
        setupDefaultStyle()
        applyAttributes(context,attrs)
    }

    private fun setupDefaultStyle() {
        this.textSize = 16f
        this.setBackgroundColor(context.getColor(android.R.color.black))
        this.setTextColor(context.getColor(android.R.color.white))
        this.text = "Botón"
    }

    private fun applyAttributes(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomButton)
            val textFromAttrs = typedArray.getString(R.styleable.CustomButton_customText)
            val backgroundColor = typedArray.getColor(
                R.styleable.CustomButton_customBackgroundColor,
                context.getColor(android.R.color.black)
            )

            textFromAttrs?.let { this.text = it }
            this.setBackgroundColor(backgroundColor)
            typedArray.recycle()
        }
    }

    fun setCustomStyle(text: String, textSize: Float, backgroundColor: Int, textColor: Int) {
        this.text = text
        this.textSize = textSize
        this.setBackgroundColor(backgroundColor)
        this.setTextColor(textColor)
    }
}