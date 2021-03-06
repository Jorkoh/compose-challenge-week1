/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data.utils

import com.example.androiddevchallenge.data.models.Gender
import kotlin.random.Random

object CatStuff {
    fun randomName() = names.random()
    fun randomBreed() = breeds.random()
    fun randomAge() = Random.nextInt(1, 20)
    fun randomGender() = Gender.values().random()
    fun randomLocation() = locations.random()
    fun randomStory() = stories.random()

    private val names = listOf(
        "Bella",
        "Kitty",
        "Lily",
        "Charlie",
        "Lucy",
        "Leo",
        "Milo",
        "Jack",
        "Nala",
        "Sam",
        "Simba",
        "Chloe",
        "Baby",
        "Sadie",
        "Ziggy",
        "Princess",
        "Salem",
        "Sophie",
        "Shadow",
        "Izzy",
        "Cleo",
        "Boots",
        "Loki",
        "Daisy",
        "Cooper",
        "Missy",
        "Oreo",
        "Tiger",
        "Lulu",
        "Tucker",
        "Jasmine",
        "Jackson",
        "Murphy",
        "Pepper",
        "Fiona",
        "Jax",
        "Frank",
        "Romeo",
        "Millie",
        "Abby",
        "Minnie",
        "Olivia",
        "Lola",
        "Athena",
        "Teddy",
        "Ruby",
        "Oscar",
        "Bear",
        "Moose",
        "Pumpkin",
        "Willow",
        "Mittens",
        "Coco",
        "Penny",
        "Sammy",
        "Theo",
        "Kali",
        "Bob",
        "Clyde",
        "Tigger",
        "Buddy",
        "Joey",
        "Emma",
        "Ollie",
        "Toby",
        "George",
        "Marley",
        "Bagheera",
        "Belle",
        "Binx",
        "Boo",
        "Ash",
        "Scout",
        "Gizmo",
        "Louie",
        "Ginger",
        "Midnight",
        "Mochi",
        "Blue",
        "Frankie",
        "Rosie",
        "Ella",
        "Calvin",
        "Lucky",
        "Hazel",
        "Thor",
        "Gus",
        "Maggie",
        "Piper",
        "Harley",
        "Rocky",
        "Peanut",
        "Mimi",
        "Kitten",
        "Remy",
        "Annie",
        "Sunny",
        "Layla",
        "Riley",
        "Walter"
    )

    private val breeds = listOf(
        "Abyssinian",
        "Aegean",
        "American Bobtail",
        "American Curl",
        "American Ringtail",
        "American Shorthair",
        "American Wirehair",
        "Aphrodite Giant",
        "Arabian Mau",
        "Asian",
        "Asian Semi-longhair",
        "Australian Mist",
        "Balinese",
        "Bambino",
        "Bengal",
        "Birman",
        "Bombay",
        "Brazilian Shorthair",
        "British Longhair",
        "British Shorthair",
        "Burmese",
        "Burmilla",
        "California Spangled",
        "Chantilly Tiffany",
        "Chartreux",
        "Chausie",
        "Colorpoint Shorthair",
        "Cornish Rex",
        "Cymric",
        "Cyprus",
        "Devon Rex",
        "Donskoy",
        "Dragon Li",
        "Dwelf",
        "Egyptian Mau",
        "European Shorthair",
        "Exotic Shorthair",
        "Foldex",
        "German Rex",
        "Havana Brown",
        "Highlander",
        "Himalayan",
        "Japanese Bobtail",
        "Javanese",
        "Kanaani",
        "Khao Manee",
        "Kinkalow",
        "Korat",
        "Korean Bobtail",
        "Korn Ja",
        "Kurilian Bobtail",
        "Lambkin",
        "LaPerm",
        "Lykoi",
        "Maine Coon",
        "Manx",
        "Mekong Bobtail",
        "Minskin",
        "Napoleon",
        "Munchkin",
        "Nebelung",
        "Norwegian Forest Cat",
        "Ocicat",
        "Ojos Azules",
        "Oregon Rex",
        "Oriental Bicolor",
        "Oriental Longhair",
        "Oriental Shorthair",
        "Persian",
        "Peterbald",
        "Pixie-bob",
        "Ragamuffin",
        "Ragdoll",
        "Raas",
        "Russian Blue",
        "Russian Tabby",
        "Sam Sawet",
        "Savannah",
        "Scottish Fold",
        "Selkirk Rex",
        "Serengeti",
        "Serrade Petit",
        "Siamese",
        "Siberian",
        "Neva Masquerade",
        "Singapura",
        "Snowshoe",
        "Sokoke",
        "Somali",
        "Sphynx",
        "Suphalak",
        "Thai",
        "Wichien Maat",
        "Thai Lilac",
        "Tonkinese",
        "Toyger",
        "Turkish Angora",
        "Turkish Van",
        "Turkish Vankedisi",
        "Ukrainian Levkoy",
        "Wila Krungthep",
        "York Chocolate"
    )

    private val locations = listOf(
        "Big Beaver, PA",
        "North Decatur, GA",
        "Lexington, GA",
        "Glasgow, VA",
        "Stone Ridge, NY",
        "Manorville, NY",
        "Windsor, PA",
        "Anchorage, KY",
        "North Atlanta, GA",
        "Tehachapi, CA",
        "East Palatka, FL",
        "Cleveland Heights, OH",
        "Reading, PA",
        "Reidland, KY",
        "Romney, WV",
        "Palermo, ME",
        "Arlington Heights, WA",
        "Aguanga, CA",
        "Ellerbe, NC",
        "Elwood, NE",
        "Collegeville, MN",
        "Mount Repose, OH",
        "Cleves, OH",
        "Athens, TN",
        "Sophia, WV",
        "Simsbury Center, CT",
        "South Portland, ME",
        "Hildale, UT",
        "Upper Montclair, NJ",
        "Bellaire, KS",
        "Fort Myers, FL",
        "Hastings, NE",
        "Gridley, CA",
        "Pemberwick, CT",
        "Freeland, MI",
        "Lakeview, MI",
        "Tahoka, TX",
        "Saint Johns, MI",
        "Ferguson, MO",
        "Eutopolis, IL",
        "Sodus, NY",
        "Inglis, FL",
        "Kingstree, SC",
        "Summerside, OH",
        "Merriam, KS",
        "Tecumseh, MI",
        "Woodbridge, NJ",
        "West Salem, OH",
        "Marvin, NC"
    )

    private val stories = listOf(
        "This is a placeholder for a super sad sob story. It's truly sad. As you keep reading " +
            "you feel the urge to adopt this cute kitten, it's the only way to repay the unjust " +
            "suffering it has gone through! Too bad this is just a dev challenge."
    )
}
