package me.mxcsyounes.templateapp.networking.utilities


import android.net.Uri
import me.mxcsyounes.templateapp.networking.models.RequestPackage
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

/**
 * @definition this class have the methods of
 * accessing the end point and getting data from the
 * internet of course , it base on the addMethod of call
 * that are defined in the request package.
 */
class HttpUtilities {

    companion object {
        fun createGetUrl(params: MutableMap<String, String>, endPoint: String): String {
            val uriBuilder = Uri.parse(endPoint).buildUpon()

            for (key in params.keys) {
                uriBuilder.appendQueryParameter(key, params[key])
            }
            return uriBuilder.build().toString()
        }

        fun getData(requestPackage: RequestPackage): String? {
            var address = requestPackage.mEndPoint

            //if the addMethod is get so we create a url with hte appropriate params.
            if (requestPackage.mMethod == RequestPackage.GET) {
                address = createGetUrl(requestPackage.mParams,
                        requestPackage.mEndPoint)
            }

            //creating the client that will make the call.
            // OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            //clientBuilder.connectTimeout(15000, TimeUnit.MILLISECONDS);

            val client = OkHttpClient()

            //make a point to the end point.
            val requestBuilder = Request.Builder().url(address)


            //if the addMethod is post we need to add the params in the multipart builder.
            if (requestPackage.mMethod == RequestPackage.POST) {
                val builder = MultipartBody.Builder()
                        .setType(MultipartBody.FORM)

                for (key in requestPackage.mParams.keys) {
                    builder.addFormDataPart(key, requestPackage.mParams[key]!!)
                }

                val requestBody = builder.build()
                requestBuilder.method(RequestPackage.POST, requestBody)
            }

            //creating the request based on what has been passed.
            val request = requestBuilder.build()

            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                //this will get true only if the response code was 200 from the http.
                return response.body()?.string()
            } else {
                //the response code was not 200.
                throw  IOException("Response code is: " + response.code())
            }
        }
    }


}
