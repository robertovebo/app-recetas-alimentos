package com.example.appalimentos.data

import android.util.Log
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import java.sql.Connection
import java.sql.DriverManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Driver
import java.util.Properties

class MySqlConnection{
    private val url = "jdbc:mariadb://10.0.2.2:3307/bdd_nutrirecetas?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC" //URL PARA LA BASE DE DATOS
    private val user = "ignacio" //USUARIO QUE TIENE ACCESO A LA BASE DE DATOS
    private val password = "" //CONTRASEÑA DEL USUARIO DE LA BASE DE DATOS
    private val TAG = "NutriDebug" //TAG PARA HACER LOGS

    //FUNCION QUE CONECTA Y SE COMUNICA CON LA BASE DE DATOS
    private fun connect(): Connection? {

        //SI EL TRY CATCH FUNCIONA REGRESA LA CONEXION SINO REGRESA NULL
        return try{
            Class.forName("org.mariadb.jdbc.Driver") //DRIVER QUE SE USO PARA LA CONEXION

            DriverManager.getConnection(url, user, password) //TANTO LA URL COMO EL USUARIO Y LA CONTRASEÑA DEBERAN CAMBIARSE AL
                                                             //QUE TENGAS EN TU BASE DE DATOS(ANTES DE DESPLEGARLA EN INTERNET)
        } catch (e : Exception){
            e.printStackTrace()
            Log.d(TAG, "Se rechazo la conexion")
            null //RETORNO DE LA FUNCION
        }
    }

    //FUNCION DE REGISTRO QUE SE EJECUTA INTERNAMENTE
    suspend fun register(email:String, passwordRaw : String, name: String, lastName: String) : Boolean = withContext(
        Dispatchers.IO)
    {
        val conn = connect() ?: return@withContext false //VARIABLE QUE GUARDA LA CONEXION A LA BASE DE DATOS
        val query = "INSERT INTO _user(user_name, user_lastname, passwrd, email) VALUES (?,?,?,?)" //QUERY PARA LA BASE DE DATOS

        //TRY CATCH PARA LA LLAMADA A LA BASE DE DATOS
        try {
            val stm = conn.prepareStatement(query)
            stm.setString(1, name)
            stm.setString(2, lastName)
            stm.setString(3, passwordRaw)
            stm.setString(4, email)
            val rowInserted = stm.executeUpdate()
            conn.close()
            rowInserted > 0
        }catch (e: Exception){
            e.printStackTrace()
            false
        }
    }

    //FUNCION DE LOGIN QUE SE EJECUTA INTERNAMENTE
    suspend fun login(email:String , passwordRaw: String) : Boolean = withContext(Dispatchers.IO){
        val conn = connect() ?: return@withContext false //VARIABLE QUE GUARDA LA CONEXION A LA BASE DE DATOS
        val query = "SELECT * FROM _user WHERE email = ? AND passwrd = ?" //QUERY PARA LA BASE DE DATOS

        //TRY CATCH PARA LA LLAMADA A LA BASE DE DATOS
        try {
            val stm = conn.prepareStatement(query)
            stm.setString(1, email)
            stm.setString(2, passwordRaw)
            val resultSet = stm.executeQuery()
            val userExists = resultSet.next()
            conn.close()
            userExists
        }catch (e : Exception){
            e.printStackTrace()
            false
        }
    }


}

