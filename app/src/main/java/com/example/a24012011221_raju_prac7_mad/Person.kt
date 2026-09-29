package com.example.a24012011221_raju_prac7_mad

import org.json.JSONObject
import java.io.Serializable

class Person(
    var id: String,
    var name: String,
    var emailId: String,
    var phoneNo: String,
    var address: String,
    var latitude: Double,
    var longitude: Double
) : Serializable {

    companion object {

        fun fromJson(jsonObject: JSONObject): Person {

            val id = jsonObject.getString("id")
            val emailId = jsonObject.getString("email")
            val phoneNo = jsonObject.getString("phone")

            val profileObject = jsonObject.getJSONObject("profile")

            val name = profileObject.getString("name")
            val address = profileObject.getString("address")

            val locationObject = profileObject.getJSONObject("location")

            val latitude = locationObject.getDouble("lat")
            val longitude = locationObject.getDouble("long")

            return Person(
                id = id,
                name = name,
                emailId = emailId,
                phoneNo = phoneNo,
                address = address,
                latitude = latitude,
                longitude = longitude
            )
        }
    }
}
