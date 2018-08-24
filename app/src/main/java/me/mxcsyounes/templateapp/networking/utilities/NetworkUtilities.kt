/*------------------------------------------------------------------------------
 - Copyright (c) 2018. This code was created by Younes Charfaoui in the process of Graduation Project for the year of  2018 , which is about creating a platform  for students and professors to help them in the communication and the get known of the university information and so on.
 -----------------------------------------------------------------------------*/

package me.mxcsyounes.templateapp.networking.utilities;

import android.content.Context
import android.net.ConnectivityManager

/**
 * @definition this class have common method of the network state
 * and some kind of that.
 */
class NetworkUtilities {

    companion object {
        fun isConnected(context: Context): Boolean {
            val manager: ConnectivityManager? =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            try {
                if (manager != null) {
                    val info = manager.activeNetworkInfo
                    return info != null && info.isConnected
                }
                return false
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }
        }
    }
}
