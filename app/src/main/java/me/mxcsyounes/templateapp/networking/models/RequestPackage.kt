package me.mxcsyounes.templateapp.networking.models;


/**
 * this class define how a request can be, it has of course the endpoint ,
 * the params and the addMethod.
 */

class RequestPackage() {

    companion object {
        //public constant we need in the process of making http calls
        const val GET = "GET"
        const val POST = "POST"
    }


     var mEndPoint: String = ""
     var mMethod: String = ""
     var mParams: MutableMap<String, String>

    init {
        mParams = mutableMapOf()
    }

    constructor(endPoint: String, method: String, params: MutableMap<String, String>) : this() {
        this.mMethod = method
        this.mEndPoint = endPoint
        this.mParams = params
    }

    //this addMethod only add key value pair to the map.
    fun addParams(key: String, value: String) {
        mParams[key] = value
    }

}
