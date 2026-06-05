package com.example.appalimentos.data

import android.util.Log
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import jakarta.mail.Authenticator
import jakarta.mail.Message
import jakarta.mail.PasswordAuthentication
import jakarta.mail.Session
import jakarta.mail.Transport
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import java.sql.Connection
import java.sql.DriverManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mindrot.jbcrypt.BCrypt
import java.sql.Driver
import java.util.Properties
import kotlin.random.Random


class MySqlConnection{
    private val url = "jdbc:mariadb://10.0.2.2:3307/bdd_nutrirecetas?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC" //URL PARA LA BASE DE DATOS
    private val user = "ignacio" //USUARIO QUE TIENE ACCESO A LA BASE DE DATOS
    private val password = "" //CONTRASEÑA DEL USUARIO DE LA BASE DE DATOS
    private val TAG = "NutriDebug" //TAG PARA HACER LOGS

    //ULTIMOS CAMBIOS 04-06-2026
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

    //ULTIMOS CAMBIOS 05-06-2026
    //FUNCION DE REGISTRO QUE SE EJECUTA INTERNAMENTE
    suspend fun register(email:String, passwordRaw : String) : Boolean = withContext(Dispatchers.IO)
    {
        val conn = connect() ?: return@withContext false //VARIABLE QUE GUARDA LA CONEXION A LA BASE DE DATOS
        val query = "INSERT INTO _user(passwrd, email, is_verified, verification_code) VALUES (?,?.0,?)" //QUERY PARA LA BASE DE DATOS

        //TRY CATCH PARA LA LLAMADA A LA BASE DE DATOS
        try {

            val verificationCode = String.format("%06d", Random.nextInt(999999)) //SE GENERA CODIGO ALEATORIO
            val passwordHashed = BCrypt.hashpw(passwordRaw, BCrypt.gensalt()) //VARIABLE DONDE SE ENCRIPTA LA CONTRASEÑA

            val stm = conn.prepareStatement(query)
            stm.setString(1, passwordHashed)
            stm.setString(2, email)
            stm.setString(3,verificationCode)

            val rowInserted = stm.executeUpdate()
            conn.close()

            //SI SE REGISTRO EL USUARIO EN LA BASE DE DATOS
            if(rowInserted > 0){

                //LLAMADA A LA FUNCION
                SendVerificationEmail(email,verificationCode)
                return@withContext true
            }

            false
        }catch (e: Exception){
            e.printStackTrace()
            false
        }
    }

    //ULTIMOS CAMBIOS 05-06-2026
    //FUNCION QUE ENVIA CORREO DE VERIFICACION
    private fun SendVerificationEmail(destiniyEmail: String, code: String){

        //CUENTA DE GOOGLE DESDE DONDE SE ENVIARA EL CORREO AL USUARIO
        val senderEmail = "nutrirecetasauth@gmail.com" //CORREO DE LA CUENTA DE GOOGLE
        val senderPassword = "kqryxswgxydhvcyr" //CONTRASEÑA DE APLICACION DE LA CUENTA DE GOOGLE

        //CODIGO PENDIENTE POR COMENTAR
        val props = Properties().apply {
            put("mail.smtp.host", "smtp.gmail.com")
            put("mail.smtp.socketFactory.port","465")
            put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory")
            put("mail.smtp.auth","true")
            put("mail.smtp.port","465")
        }

        //CODIGO PENDIENTE POR COMENTAR
        val session = Session.getInstance(props, object : Authenticator(){
            override fun getPasswordAuthentication(): PasswordAuthentication{
                return PasswordAuthentication(senderEmail, senderPassword)
            }
        })

        try {
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(senderEmail))
                addRecipient(Message.RecipientType.TO, InternetAddress(destiniyEmail))
                subject = "Código de verificación - NutriRecetas"
                setText("!Hola¡ Tu código de verificación para activar tu cuenta es:  $code\n\nNo lo compartas con nadie")
            }
            Transport.send(message)

        }catch(e : Exception){
            e.printStackTrace()
        }

    }




    //ULTIMOS CAMBIOS 05-06-2026
    //FUNCION DE LOGIN QUE SE EJECUTA INTERNAMENTE
    suspend fun login(email:String , passwordRaw: String) : Boolean = withContext(Dispatchers.IO){
        val conn = connect() ?: return@withContext false //VARIABLE QUE GUARDA LA CONEXION A LA BASE DE DATOS
        val query = "SELECT passwrd FROM _user WHERE email = ?" //QUERY PARA LA BASE DE DATOS

        //TRY CATCH PARA LA LLAMADA A LA BASE DE DATOS
        try {
            val stm = conn.prepareStatement(query)
            stm.setString(1, email)
            val resultSet = stm.executeQuery()

            //SI HAY REGISTRO EN LA BASE DE DATOS ENTRA AL BLOQUE DE CODIGO
            if(resultSet.next()){

                val passwordHashed = resultSet.getString("passwrd") //SE OBTIENE LA CONTRASEÑA ENCRIPTADA Y SE TRANSFORMA A STRING

                conn.close() //SE CIERRA LA CONEXION
                return@withContext BCrypt.checkpw(passwordRaw, passwordHashed) //REGRESA LA COMPARACION ENTRE LA CONTRASEÑA EN LA BASE DE DATOS
                                                                                         //Y LA INGRESADA POR EL USUARIO
            }
            conn.close() //SE CIERRA LA CONEXION
            false
        }catch (e : Exception){
            e.printStackTrace()
            false
        }
    }


}

