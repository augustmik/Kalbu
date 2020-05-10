package lt.kalbu.util

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

class DataBindingAdapter {

//    @BindingAdapter("android:src")
//    fun setImageUri(view: ImageView, imageUri: String?) {
//        if (imageUri == null) {
//            view.setImageURI(null)
//        } else {
//            view.setImageURI(Uri.parse(imageUri))
//        }
//    }
//
//    @BindingAdapter("android:src")
//    fun setImageUri(view: ImageView, imageUri: Uri?) {
//        view.setImageURI(imageUri)
//    }
//
//    @BindingAdapter("android:src")
//    fun setImageDrawable(view: ImageView, drawable: Drawable?) {
//        view.setImageDrawable(drawable)
//    }
    @BindingAdapter("android:src")
    fun setImageDrawable(view: ImageView, bitmap: Bitmap?) {
        view.setImageBitmap(bitmap)
    }
}
//@BindingAdapter("app:srcCompat")
//fun setImageResource(imageView: ImageView, resource: Int) {
//    imageView.setImageResource(resource)
//}