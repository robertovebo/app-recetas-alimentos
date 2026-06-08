package com.example.appalimentos.ui.navigation

object Routes {


    const val SPLASH = "splash"

    const val WELCOME = "welcome"
    const val REGISTER = "register"

    const val LOGIN = "login"

    const val VERIFICATION = "verification/{email}"

    fun createVerificationRoute(email:String) = "verification/$email"
    const val FOODS = "foods"

    const val PROFILE = "profile/{email}"
    fun createProfileRoute(email: String) = "profile/$email" //AHORA EL PERFIL PEDIRA CORREO EN MAIN ACTIVITY

    const val RECIPES = "recipes"
}