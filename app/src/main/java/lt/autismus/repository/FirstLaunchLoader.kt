package lt.autismus.repository

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import lt.autismus.R
import lt.autismus.singleUnits.SingleCard
import lt.autismus.singleUnits.SingleCategory


class FirstLaunchLoader(val context: Context) {
    fun getCat() : List<SingleCategory>{
        //list of categories to add
        return mutableListOf(
            SingleCategory(
                "Maistas",
                getDrawableUriString(R.drawable.preload_food)
            ),
            SingleCategory(
                "Namai",
                getDrawableUriString(R.drawable.preload_home)
            )
        )
    }

    fun getCards() : List<SingleCard>{
        //list of cards to add
        return mutableListOf(
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_food_apple),
                title = "Obuolys",
                category = "Maistas"
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_food_banana),
                title = "Bananas",
                category = "Maistas"
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_food_sandwich),
                title = "Sumuštinis",
                category = "Maistas"
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_food_soup),
                title = "Sriuba",
                category = "Maistas"
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_home_bathtub),
                title = "Vonia",
                category = "Namai"
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_home_bed),
                title = "Lova",
                category = "Namai"
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_home_fridge),
                title = "Šaldytuvas",
                category = "Namai"
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_home_toilet),
                title = "Tualetas",
                category = "Namai"
            )
        )
    }

    private fun getDrawableUriString(resId: Int): String{
        val res = context.resources
        val resUri: Uri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE +
                    "://" + res.getResourcePackageName(resId)
                    + '/' + res.getResourceTypeName(resId)
                    + '/' + res.getResourceEntryName(resId)
        )
        return  resUri.toString()
    }
}