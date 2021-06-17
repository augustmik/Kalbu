package lt.kalbu.repository

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import lt.kalbu.R
import lt.kalbu.singleUnits.SingleCard
import lt.kalbu.singleUnits.SingleCategory

class FirstLaunchLoader(val context: Context) {
    private val preloadTutorial = "Naudojimo paaiškinimai\n(Išmokus ištrink mane!)"

    // New ones
    private val preloadCommonTasks = "Bendrieji veiksmai"
    private val preloadMusic = "Muzika"
    private val preloadGamesAndToys = "Žaidimai ir Žaislai"
    private val preloadSport = "Sportas"
    private val preloadToolsAndKitchenware = "Įrankiai"
    private val preloadAnimals = "Gyvūnai"
    private val preloadClothing = "Apranga"
    private val preloadEmotions = "Emocijos"
    private val preloadFood = "Maistas"
    private val preloadHome = "Namai"
    private val preloadPeople = "Žmonės"
    private val preloadPlaces = "Vietos"
    private val preloadTransport = "Transportas"
    private val preloadColors = "Spalvos"
    private val preloadShapes = "Formos"
    private val preloadNumbers = "Skaičiai"

    fun getCat() : List<SingleCategory>{
        //list of categories to add
        return mutableListOf(
            SingleCategory(
                preloadTutorial,
                getDrawableUriString(R.drawable.tutorial_category)
            ),
            SingleCategory(
                preloadCommonTasks,
                getDrawableUriString(R.drawable.preload_category_commontasks)
            ),
            SingleCategory(
                preloadMusic,
                getDrawableUriString(R.drawable.preload_category_music)
            ),
            SingleCategory(
                preloadGamesAndToys,
                getDrawableUriString(R.drawable.preload_category_gamesntoys)
            ),
            SingleCategory(
                preloadSport,
                getDrawableUriString(R.drawable.preload_category_sports)
            ),
            SingleCategory(
                preloadToolsAndKitchenware,
                getDrawableUriString(R.drawable.preload_category_kitchenware)
            ),
            SingleCategory(
                preloadAnimals,
                getDrawableUriString(R.drawable.preload_category_animals)
            ),
            SingleCategory(
                preloadClothing,
                getDrawableUriString(R.drawable.preload_category_clothing)
            ),
            SingleCategory(
                preloadEmotions,
                getDrawableUriString(R.drawable.preload_category_emotions)
            ),
            SingleCategory(
                preloadFood,
                getDrawableUriString(R.drawable.preload_category_food)
            ),
            SingleCategory(
                preloadHome,
                getDrawableUriString(R.drawable.preload_category_home)
            ),
            SingleCategory(
                preloadPeople,
                getDrawableUriString(R.drawable.preload_category_people)
            ),
            SingleCategory(
                preloadPlaces,
                getDrawableUriString(R.drawable.preload_category_places)
            ),
            SingleCategory(
                preloadTransport,
                getDrawableUriString(R.drawable.preload_category_transport)
            ),
            SingleCategory(
                preloadColors,
                getDrawableUriString(R.drawable.preload_category_colors)
            ),
            SingleCategory(
                preloadShapes,
                getDrawableUriString(R.drawable.preload_category_shapes)
            ),
            SingleCategory(
                preloadNumbers,
                getDrawableUriString(R.drawable.preload_category_numbers)
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
                title = "Dabar - Po to istorijų kūrimas\n(Atidarius spausk tuščią kortelę)",
                category = preloadTutorial
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.tutorial_delete),
                title = "Kortelių ar Kategorijų naikinimas\n" +
                        "(Spausk X norint ištrinti)",
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