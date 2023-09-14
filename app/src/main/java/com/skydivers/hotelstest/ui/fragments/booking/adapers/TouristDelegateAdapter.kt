package com.skydivers.hotelstest.ui.fragments.booking.adapers


import android.view.View
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.databinding.TouristItemBinding
import com.skydivers.hotelstest.models.booking.TouristUIModel
import com.skydivers.hotelstest.ui.action.BookingUserAction
import com.skydivers.hotelstest.ui.design.delegateAdaper.ViewBindingDelegateAdapter
import com.skydivers.hotelstest.ui.fragments.booking.adapers.bindingExt.afterTextChanged
import com.skydivers.hotelstest.ui.fragments.booking.adapers.bindingExt.checkRequires
import com.skydivers.hotelstest.ui.fragments.booking.adapers.bindingExt.checkThis
import com.skydivers.hotelstest.ui.fragments.booking.adapers.bindingExt.afterDateChanged


class TouristDelegateAdapter(private var onUserAction: ((BookingUserAction) -> Unit)? = null) :
    ViewBindingDelegateAdapter<TouristUIModel, TouristItemBinding>(TouristItemBinding::inflate) {


    var bindingItems: TouristItemBinding? = null


    override fun TouristItemBinding.onBind(item: TouristUIModel) {

        bindingItems = this

        title.text = item.title + " турист"
        image.setImageResource(R.drawable.arrow_right_blue)
        checkCollapsedItem(this, item.isCollapsed)
        image.setOnClickListener {
            item.isCollapsed = !item.isCollapsed
            checkCollapsedItem(this, item.isCollapsed)
        }


        firstNameEditText.run {
            setText(item.firstName)
            afterTextChanged {
                item.firstName = it
                item.saveIfFilled()
                checkThis(this)
            }

        }
        lastNameEditText.run {
            setText(item.lastName)
            afterTextChanged {
                item.lastName = it
                item.saveIfFilled()
                checkThis(this)
            }

        }


        birthDayEditText.run {
            setText(item.birthDay)
            afterDateChanged {
                item.birthDay = it
                item.saveIfFilled()
                checkThis(this)
            }

        }
        citizenshipEditText.run {
            setText(item.citizenship)
            afterTextChanged {
                item.citizenship = it
                item.saveIfFilled()
                checkThis(this)
            }
        }
        passportEditText.run {
            setText(item.passportNumber)
            afterTextChanged {
                item.passportNumber = it
                item.saveIfFilled()
                checkThis(this)

            }
        }
        passportEndDateEditText.run {
            setText(item.passportEndDate)
            afterDateChanged {
                item.passportEndDate = it
                item.saveIfFilled()
                checkThis(this)
            }
        }


    }


    override fun checkRequires(): Boolean {

        return if (bindingItems != null)
            bindingItems!!.checkRequires()
        else false
    }

    private fun checkCollapsedItem(itemBinding: TouristItemBinding, collapsed: Boolean) {
        if (collapsed) {
            itemBinding.image.rotation = -90f
            itemBinding.allFieldsLayout.visibility = View.VISIBLE

        } else {
            itemBinding.image.rotation = 90f
            itemBinding.allFieldsLayout.visibility = View.GONE
        }
    }

    private fun TouristUIModel.saveIfFilled() {

        if (this.isFilled()) {
            onUserAction?.invoke(BookingUserAction.SaveTourist(this))
        }
    }

    override fun isForViewType(item: Any) = item is TouristUIModel

    override fun TouristUIModel.getItemId(): Any = id

}