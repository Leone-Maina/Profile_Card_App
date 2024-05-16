package com.example.profilecard

data class UserProfile(val id: Int, val name: String, val status: Boolean, val drawableId: Int)

val userProfileList = arrayListOf(
    UserProfile(
        id = 0,
        name = "Michaela Runnings",
        status = true,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 1,
        name = "John Pestridge",
        status = false,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 2,
        name = "Manilla Andrews",
        status = true,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 3,
        name = "Dan Spicer",
        status = false,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 4,
        name = "Keanu Dester",
        status = false,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 5,
        name = "Anichu Patel",
        status = false,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 6,
        name = "Kienla Onso",
        status = true,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 7,
        name = "Andra Matthews",
        status = false,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 8,
        name = "Georgia S.",
        status = false,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 9,
        name = "Matt Dengo",
        status = false,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 10,
        name = "Marsha T.",
        status = true,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 11,
        name = "Invshu Patel",
        status = true,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 12,
        name = "Braylen Nathan",
        status = true,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 13,
        name = "Jorge Andre",
        status = false,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 14,
        name = "Jamison Dana",
        status = false,
        R.drawable.profile_circle_icon
    ),

    UserProfile(
        id = 15,
        name = "Laura S.",
        status = true,
        R.drawable.profile_circle_icon
    ),
)