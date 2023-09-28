package com.skydivers.hotelstest.booking.ui.adapers


import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.skydivers.hotelstest.booking.model.TouristUIModel
import com.skydivers.hotelstest.booking.ui.R
import com.skydivers.hotelstest.booking.ui.repositories.BookingUserAction
import com.skydivers.hotelstest.booking.ui.adapers.bindingExt.afterDateChanged
import com.skydivers.hotelstest.booking.ui.adapers.bindingExt.afterTextChanged
import com.skydivers.hotelstest.booking.ui.adapers.bindingExt.checkRequires
import com.skydivers.hotelstest.booking.ui.adapers.bindingExt.checkThis
import com.skydivers.hotelstest.booking.ui.delegateAdaper.ViewBindingDelegateAdapter
import com.skydivers.hotelstest.booking.ui.databinding.TouristItemBinding


internal class TouristDelegateAdapter(var onUserAction: ((BookingUserAction) -> Unit)? = null) :
    ViewBindingDelegateAdapter<com.skydivers.hotelstest.booking.model.TouristUIModel, TouristItemBinding>(TouristItemBinding::inflate) {


    private var bindingItems: TouristItemBinding? = null

    override fun TouristItemBinding.onBind(item: TouristUIModel) {

        bindingItems = this

        title.text = item.title + " турист"
        image.setImageResource(R.drawable.arrow_right_blue)


                checkCollapsedItem(this, item.isCollapsed)


        image.setOnClickListener {
            item.isCollapsed = !item.isCollapsed
            checkCollapsedItem(this, item.isCollapsed)
        }

        textViewOptions.setOnClickListener {
            onOptionsMenuClick(it, item)
        }

        etFirstName.run {
            setText(item.firstName)
            afterTextChanged {
                item.firstName = it
                checkThis(this)
            }

        }
        etLastName.run {
            setText(item.lastName)
            afterTextChanged {
                item.lastName = it
                checkThis(this)
            }

        }


        etBirthDay.run {
            setText(item.birthDay)
            afterDateChanged {
                item.birthDay = it
                checkThis(this)
            }

        }
        etCitizenship.run {
            setText(item.citizenship)
            afterTextChanged {
                item.citizenship = it
                checkThis(this)
            }
        }
        etPassport.run {
            setText(item.passportNumber)
            afterTextChanged {
                item.passportNumber = it
                checkThis(this)

            }
        }
        etPassportEndDate.run {
            setText(item.passportEndDate)
            afterDateChanged {
                item.passportEndDate = it
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

    private fun onOptionsMenuClick(view: View?, item: TouristUIModel) {
        val popupMenu = PopupMenu(view!!.context, view)
        // add the menu
        popupMenu.inflate(R.menu.user_menu)
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(menuItem: MenuItem?): Boolean {
                when (menuItem?.itemId) {
                    R.id.delete -> {

                        onUserAction?.invoke(BookingUserAction.DeleteTourist(item.id))
                        return true
                    }

                    R.id.save -> {
                        item.saveIfFilled()

                        return true
                    }


                }
                return false
            }
        })

        popupMenu.show()
    }

    override fun isForViewType(item: Any) = item is com.skydivers.hotelstest.booking.model.TouristUIModel

    override fun com.skydivers.hotelstest.booking.model.TouristUIModel.getItemId(): Any = id

}