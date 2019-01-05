package com.lijing.dev.network.response

import java.io.Serializable

/**
 * @author lijing
 */
class ApiResponseModel<T> : Serializable {

    var code: Int? = null
    var msg: String? = null
    var data: T? = null

}
