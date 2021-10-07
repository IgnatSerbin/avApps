package com.example.avapps

import com.example.avapps.dataclass.SaleProduct
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataApi {
    @GET("/imgSaleProducts/{imgUrl}")
    fun getSaleImage(@Path("imgUrl") imgUrl: String
    ): Call<ResponseBody>
    @GET("/imgBanner/{imgUrl}")
    fun getBannerImage(@Path("imgUrl") imgUrl: String
    ): Call<ResponseBody>
    @GET("/jsonSaleProduct/{jsonSale}")
    fun getJsonSaleProduct(@Path("jsonSale") jsonSale: String
    ): Call<ResponseBody>
}