package com.thenewsapp.activities.sharedpreferences

import android.content.Context

class LoginManager (var context: Context) {
    fun saveLoginDetails(email: String?, password: String?) {
        val sharedPreferences =
            context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Email", email)
        editor.putString("Password", password)
        editor.apply()
    }

   fun getEmail(): String? {
            val sharedPreferences =
                context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE)
            return sharedPreferences.getString("Email", "")
        }

    fun isUserLoggedOut(): Boolean {
        val sharedPreferences =
            context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE)
        val isEmailEmpty = sharedPreferences.getString("Email", "")!!.isEmpty()
        val isPasswordEmpty = sharedPreferences.getString("Password", "")!!.isEmpty()
        return isEmailEmpty || isPasswordEmpty
    }
}