package dev.goodwin.android.common

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import dev.goodwin.android.common.R

class BulkSelectActionMode(
        private val activity: AppCompatActivity,
        private val onCancelClick: () -> Unit,
        private val onApproveClick: () -> Unit,
        private val onSelectAllClick: () -> Unit
) {

    var isEnabled = false
        private set

    private var actionMode: ActionMode? = null

    fun start() {
        if (!isEnabled) {
            isEnabled = true
            actionMode = activity.startSupportActionMode(object : ActionMode.Callback {
                override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                    return when (item.itemId) {
                        R.id.bulk_approve -> { onApproveClick(); true }
                        R.id.bulk_select_all -> { onSelectAllClick(); true }
                        else -> false
                    }
                }

                override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                    mode.menuInflater.inflate(R.menu.contextual_menu_bar_bulk_select, menu)
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                    return false
                }

                override fun onDestroyActionMode(mode: ActionMode) {
                    onCancelClick()
                    actionMode = null
                    isEnabled = false
                }
            })
        }
    }

    fun stop() {
        actionMode?.finish()
    }

    fun updateNumberOfSelectedItems(count: Int) {
        actionMode?.title = activity.getString(R.string.n_selected, count.toString())
    }

}
