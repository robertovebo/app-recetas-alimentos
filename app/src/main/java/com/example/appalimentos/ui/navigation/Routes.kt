package com.example.appalimentos.ui.navigation

object Routes {


    const val SPLASH = "splash"

    const val WELCOME = "welcome"
    const val REGISTER = "register"

    const val LOGIN = "login"

    const val VERIFICATION = "verification/{email}"
    const val FOODS = "foods/{email}"
    const val RECIPES = "recipes/{email}"
    const val PROFILE = "profile/{email}"


    fun createVerificationRoute(email:String) = "verification/$email"
    fun createFoodsRoute(email:String) = "foods/$email"
    fun createRecipesRoute(email:String) = "recipes/$email"
    fun createProfileRoute(email: String) = "profile/$email" //AHORA EL PERFIL PEDIRA CORREO EN MAIN ACTIVITY

}