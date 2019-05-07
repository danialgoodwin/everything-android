package dev.goodwin.android.common

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.fragment.app.Fragment

const val REQUEST_CODE_GET_IMAGE = 123

/**
 *
 * More info: https://developer.android.com/guide/components/intents-common
 */
object Intents {

    /**
     *
     * ACTION_GET_CONTENT
     * More info: https://developer.android.com/guide/topics/providers/document-provider.html#client
     * More info: https://developer.android.com/guide/components/intents-common#Storage
     */
    fun getImage(fragment: Fragment) {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        fragment.startActivityForResult(intent, REQUEST_CODE_GET_IMAGE)

        // Pre-API 24 that requires permission
//        val i = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        startActivityForResult(i, REQUEST_CODE_GET_IMAGE)
    }

    fun handleGetImage(fragment: Fragment, resultCode: Int, resultData: Intent?, onHandleResult: (Uri, Bitmap) -> Unit) {
        if (resultCode == RESULT_OK && resultData != null) {
            resultData.data?.let {
                val fullPhotoUri: Uri = resultData.data
                val bitmap = MediaStore.Images.Media.getBitmap(fragment.requireActivity().contentResolver, fullPhotoUri)
                onHandleResult.invoke(fullPhotoUri, bitmap)
            }
        }
    }

}
