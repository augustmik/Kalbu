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

            //CommonTasks
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_common_sleep),
                title = "Miegoti",
                category = preloadCommonTasks
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_common_read),
                title = "Skaityti",
                category = preloadCommonTasks
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_common_write),
                title = "Rašyti",
                category = preloadCommonTasks
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_common_walk),
                title = "Eiti",
                category = preloadCommonTasks
            ),

            //preloadMusic
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_music_guitar),
                title = "Gitara",
                category = preloadMusic
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_music_violin),
                title = "Smuikas",
                category = preloadMusic
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_music_piano),
                title = "Pianinas",
                category = preloadMusic
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_music_flute),
                title = "Dudelė",
                category = preloadMusic
            ),

            //preloadGamesAndToys
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_toy_teddy),
                title = "Meškiukas",
                category = preloadGamesAndToys
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_toy_robot),
                title = "Robotas",
                category = preloadGamesAndToys
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_toy_train),
                title = "Traukinys",
                category = preloadGamesAndToys
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_toy_controller),
                title = "Pultelis",
                category = preloadGamesAndToys
            ),

            //preloadSport
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_sport_basketball),
                title = "Krepšinis",
                category = preloadSport
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_sport_football),
                title = "Futbolas",
                category = preloadSport
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_sport_bike),
                title = "Dviratis",
                category = preloadSport
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_sport_runner),
                title = "Bėgimas",
                category = preloadSport
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_sport_scooter),
                title = "Paspirtukas",
                category = preloadSport
            ),

            //preloadToolsAndKitchenware
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_tools_bowl),
                title = "Dubuo",
                category = preloadToolsAndKitchenware
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_tools_fork),
                title = "Šakutė",
                category = preloadToolsAndKitchenware
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_tools_knife),
                title = "Peilis",
                category = preloadToolsAndKitchenware
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_tools_plate),
                title = "Lėkštė",
                category = preloadToolsAndKitchenware
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_tools_spoon),
                title = "Šaukštas",
                category = preloadToolsAndKitchenware
            ),

            //preloadAnimals
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_animal_dog),
                title = "Šuo",
                category = preloadAnimals
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_animal_cat),
                title = "Katė",
                category = preloadAnimals
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_animal_bee),
                title = "Bitė",
                category = preloadAnimals
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_animal_cow),
                title = "Karvė",
                category = preloadAnimals
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_animal_turtle),
                title = "Vėžlys",
                category = preloadAnimals
            ),

            //preloadClothing
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_clothing_polo),
                title = "Marškinėliai",
                category = preloadClothing
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_clothing_hawaiian),
                title = "Marškiniai",
                category = preloadClothing
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_clothing_jeans),
                title = "Džinsai",
                category = preloadClothing
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_clothing_sweater),
                title = "Megztinis",
                category = preloadClothing
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_clothing_sweatpants),
                title = "Treninginės kelnės",
                category = preloadClothing
            ),

            //preloadEmotions
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_emotions_happy),
                title = "Laimingas",
                category = preloadEmotions
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_emotions_sad),
                title = "Liūdnas",
                category = preloadEmotions
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_emotions_angry),
                title = "Piktas",
                category = preloadEmotions
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_emotions_neutral),
                title = "Neutralus",
                category = preloadEmotions
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_emotions_cool),
                title = "Krūtas",
                category = preloadEmotions
            ),

            //preloadFood
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

            //preloadHome
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
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_home_table),
                title = "Stalas",
                category = preloadHome
            ),

            //preloadPeople
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_people_parents),
                title = "Tėvai",
                category = preloadPeople
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_people_grandma),
                title = "Močiutė",
                category = preloadPeople
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_people_grandfather),
                title = "Senelis",
                category = preloadPeople
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_people_uncle),
                title = "Dėdė",
                category = preloadPeople
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_people_kids),
                title = "Vaikai",
                category = preloadPeople
            ),

            //preloadPlaces
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_places_school),
                title = "Mokykla",
                category = preloadPlaces
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_places_hospital),
                title = "Ligoninė",
                category = preloadPlaces
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_places_field),
                title = "Laukas",
                category = preloadPlaces
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_places_park),
                title = "Parkas",
                category = preloadPlaces
            ),

            //preloadTransport
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_transport_car),
                title = "Automobilis",
                category = preloadTransport
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_transport_bus),
                title = "Autobusas",
                category = preloadTransport
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_transport_trolleybus),
                title = "Troleibusas",
                category = preloadTransport
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_transport_cruise),
                title = "Laivas",
                category = preloadTransport
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_transport_plane),
                title = "Lėktuvas",
                category = preloadTransport
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_transport_train),
                title = "Traukinys",
                category = preloadTransport
            ),

            //preloadColors
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_color_blue),
                title = "Mėlyna",
                category = preloadColors
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_color_green),
                title = "Žalia",
                category = preloadColors
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_color_orange),
                title = "Oranžinė",
                category = preloadColors
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_color_red),
                title = "Raudona",
                category = preloadColors
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_color_yellow),
                title = "Geltona",
                category = preloadColors
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_color_black),
                title = "Juoda",
                category = preloadColors
            ),

            //preloadShapes
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_shapes_circle),
                title = "Apskritimas",
                category = preloadShapes
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_shapes_square),
                title = "Kvadratas",
                category = preloadShapes
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_shapes_triangle),
                title = "Trikampis",
                category = preloadShapes
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_shapes_star),
                title = "Žvaigždė",
                category = preloadShapes
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_shapes_hexagon),
                title = "Šešiakampis",
                category = preloadShapes
            ),

            //preloadNumbers
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_numbers_1),
                title = "Vienas",
                category = preloadNumbers
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_numbers_2),
                title = "Du",
                category = preloadNumbers
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_numbers_3),
                title = "Trys",
                category = preloadNumbers
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_numbers_4),
                title = "Keturi",
                category = preloadNumbers
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_numbers_5),
                title = "Penki",
                category = preloadNumbers
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_numbers_6),
                title = "Šeši",
                category = preloadNumbers
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_numbers_7),
                title = "Septyni",
                category = preloadNumbers
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_numbers_8),
                title = "Aštuoni",
                category = preloadNumbers
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_numbers_9),
                title = "Devyni",
                category = preloadNumbers
            ),
            SingleCard(
                image = getDrawableUriString(R.drawable.preload_numbers_10),
                title = "Dešimt",
                category = preloadNumbers
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