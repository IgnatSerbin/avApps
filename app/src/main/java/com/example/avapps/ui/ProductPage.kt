package com.example.avapps.ui

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.avapps.R
import com.example.avapps.dataclass.SaleProduct
import com.example.avapps.helper.Noder
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException

class ProductPage : AppCompatActivity() {

    private val jsonSaleProduct = """
        {
          "saleListProduct": [
            {
              "productId": 101,
              "title": "Нектар Добрый, мультифрукт, 2 л",
              "price": "109",
              "img": "imgDobrSok2000.png",
              "country": "Россия",
              "quantity": 10
            },
            {
              "productId": 102,
              "title": "Ликер ЯГЕРМАЙСТЕР десерт.35% 0,2л",
              "price": "469",
              "img": "imgJager0200.png",
              "country": "Германия",
              "quantity": 3
            },
            {
              "productId": 103,
              "title": "Бананы Просто Азбука",
              "price": "98",
              "img": "imgAVBanana1000.png",
              "country": "Россия",
              "quantity": 20
            },
            {
              "productId": 104,
              "title": "Авокадо Хаас Премиум",
              "price": "548",
              "img": "imgAVHaas360.png",
              "country": "Аргентина",
              "quantity": 6
            },
            {
              "productId": 105,
              "title": "Сыр мягкий с голубой плесенью BLUE 54% Schonfeld",
              "price": "178",
              "img": "imgAVBlue100.png",
              "country": "Аргентина",
              "quantity": 44
            },
            {
              "productId": 106,
              "title": "Бекон МД Бородина Английский с/к",
              "price": "255",
              "img": "imgAVBeacon1.png",
              "country": "Россия",
              "quantity": 11
            },
            {
              "productId": 107,
              "title": "Телятина отварная в бульоне Считаем калории Уже Готово",
              "price": "598",
              "img": "imgAVMeet1.png",
              "country": "Россия",
              "quantity": 52
            },
            {
              "productId": 108,
              "title": "Вода минеральная природная негазированная Evian",
              "price": "193",
              "img": "imgAVEvian1000.png",
              "country": "Франция",
              "quantity": 4
            },
            {
              "productId": 109,
              "title": "Нектар Добрый, мультифрукт, 2 л",
              "price": "109",
              "img": "imgDobrSok2000.png",
              "country": "Россия",
              "quantity": 10
            },
            {
              "productId": 110,
              "title": "Ликер ЯГЕРМАЙСТЕР десерт.35% 0,2л",
              "price": "469",
              "img": "imgJager0200.png",
              "country": "Германия",
              "quantity": 3
            },
            {
              "productId": 111,
              "title": "Бананы Просто Азбука",
              "price": "98",
              "img": "imgAVBanana1000.png",
              "country": "Россия",
              "quantity": 20
            },
            {
              "productId": 112,
              "title": "Авокадо Хаас Премиум",
              "price": "548",
              "img": "imgAVHaas360.png",
              "country": "Аргентина",
              "quantity": 6
            },
            {
              "productId": 113,
              "title": "Сыр мягкий с голубой плесенью BLUE 54% Schonfeld",
              "price": "178",
              "img": "imgAVBlue100.png",
              "country": "Аргентина",
              "quantity": 44
            },
            {
              "productId": 114,
              "title": "Бекон МД Бородина Английский с/к",
              "price": "255",
              "img": "imgAVBeacon1.png",
              "country": "Россия",
              "quantity": 11
            },
            {
              "productId": 115,
              "title": "Телятина отварная в бульоне Считаем калории Уже Готово",
              "price": "598",
              "img": "imgAVMeet1.png",
              "country": "Россия",
              "quantity": 52
            },
            {
              "productId": 116,
              "title": "Вода минеральная природная негазированная Evian",
              "price": "193",
              "img": "imgAVEvian1000.png",
              "country": "Франция",
              "quantity": 4
            },
            {
              "productId": 117,
              "title": "Телятина отварная в бульоне Считаем калории Уже Готово",
              "price": "598",
              "img": "imgAVMeet1.png",
              "country": "Россия",
              "quantity": 52
            },
            {
              "productId": 118,
              "title": "Вода минеральная природная негазированная Evian",
              "price": "193",
              "img": "imgAVEvian1000.png",
              "country": "Франция",
              "quantity": 4
            },
            {
              "productId": 119,
              "title": "Нектар Добрый, мультифрукт, 2 л",
              "price": "109",
              "img": "imgDobrSok2000.png",
              "country": "Россия",
              "quantity": 10
            },
            {
              "productId": 120,
              "title": "Ликер ЯГЕРМАЙСТЕР десерт.35% 0,2л",
              "price": "469",
              "img": "imgJager0200.png",
              "country": "Германия",
              "quantity": 3
            },
            {
              "productId": 121,
              "title": "Бананы Просто Азбука",
              "price": "98",
              "img": "imgAVBanana1000.png",
              "country": "Россия",
              "quantity": 20
            },
            {
              "productId": 122,
              "title": "Авокадо Хаас Премиум",
              "price": "548",
              "img": "imgAVHaas360.png",
              "country": "Аргентина",
              "quantity": 6
            },
            {
              "productId": 123,
              "title": "Сыр мягкий с голубой плесенью BLUE 54% Schonfeld",
              "price": "178",
              "img": "imgAVBlue100.png",
              "country": "Аргентина",
              "quantity": 44
            },
            {
              "productId": 124,
              "title": "Бекон МД Бородина Английский с/к",
              "price": "255",
              "img": "imgAVBeacon1.png",
              "country": "Россия",
              "quantity": 11
            },
            {
              "productId": 125,
              "title": "Телятина отварная в бульоне Считаем калории Уже Готово",
              "price": "598",
              "img": "imgAVMeet1.png",
              "country": "Россия",
              "quantity": 52
            },
            {
              "productId": 126,
              "title": "Вода минеральная природная негазированная Evian",
              "price": "193",
              "img": "imgAVEvian1000.png",
              "country": "Франция",
              "quantity": 4
            }            
          ]
        }
    """.trimIndent()

    private val objectMapper = ObjectMapper()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)
        val arguments = intent.getSerializableExtra("productId")
        val saleProducts: Array<SaleProduct>
        var count = 0

        val actionBar = supportActionBar
        val isActionBarShowing = actionBar?.isShowing
        if (isActionBarShowing!!) {
            actionBar.show()
        }
        actionBar.title = "О продукте"
        actionBar.setHomeButtonEnabled(true)
        actionBar.setDefaultDisplayHomeAsUpEnabled(true)


        val ivProductView = findViewById<ImageView>(R.id.productView)
        val tvTitle = findViewById<TextView>(R.id.txtTitle)
        val tvPrice = findViewById<TextView>(R.id.txtProductPagePrice)

        try {
            val json = objectMapper.readTree(jsonSaleProduct)
            val saleProduct = json.get("saleListProduct")
            saleProducts = Noder.fromNode(saleProduct)

            while (count < saleProducts.size){
                if (arguments == saleProducts[count].productId){
                    ivProductView.setImageURI(Uri.parse(getExternalFilesDir(saleProducts[count].img).toString()))
                    tvTitle.text = saleProducts[count].title
                    tvPrice.text = saleProducts[count].price + " руб."
//                    actionBar.subtitle = saleProducts[count].title
                    break
                }
                count++
            }
        } catch (e: IOException){
            Log.d("ERROR SALE", e.printStackTrace().toString())
        }
      //  Toast.makeText(this, saleProducts.toString(), Toast.LENGTH_SHORT).show()
    }
}