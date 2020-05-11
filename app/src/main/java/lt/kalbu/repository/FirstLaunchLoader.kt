package lt.kalbu.repository

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import lt.kalbu.R
import lt.kalbu.singleUnits.SingleCard
import lt.kalbu.singleUnits.SingleCategory

class FirstLaunchLoader(val context: Context) {
    private val preloadTutorial = "Apmokymai"
    private val preloadFood = "Maistas"
    private val preloadHome = "Namai"

    fun getCat() : List<SingleCategory>{
        //list of categories to add
        return mutableListOf(
            SingleCategory(
                preloadTutorial,
                getDrawableUriString(R.drawable.tutorial_category)
            ),
            SingleCategory(
                preloadFood,
                getDrawableUriString(R.drawable.preload_food)
            ),
            SingleCategory(
                preloadHome,
                getDrawableUriString(R.drawable.preload_home)
            )
        )
    }

    fun getCards() : List<SingleCard>{
        //list of cards to add
        return mutableListOf(
            SingleCard(
                image = getDrawableUriString(R.drawable.tutorial_enlarge),
                title = "Kortelės didinimas",
                category = preloadTutorial
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.tutorial_settings),
                title = "Aktyvuoti visą funkcionalumą",
                category = preloadTutorial
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.tutorial_create_card),
                title = "Kortelių ar Kategorijų kūrimas",
                category = preloadTutorial
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.tutorial_create_stories),
                title = "Dabar - Po to istorijų kūrimas\n(Spauskite tuščią kortelę)",
                category = preloadTutorial
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.tutorial_delete),
                title = "Kortelių ar Kategorijų naikinimas\n" +
                        "(Spauskite X norint ištrinti)",
                category = preloadTutorial
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_food_apple),
                title = "Obuolys",
                category = preloadFood
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_food_banana),
                title = "Bananas",
                category = preloadFood
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_food_sandwich),
                title = "Sumuštinis",
                category = preloadFood
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_food_soup),
                title = "Sriuba",
                category = preloadFood
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_home_bathtub),
                title = "Vonia",
                category = preloadHome
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_home_bed),
                title = "Lova",
                category = preloadHome
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_home_fridge),
                title = "Šaldytuvas",
                category = preloadHome
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_home_toilet),
                title = "Tualetas",
                category = preloadHome
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