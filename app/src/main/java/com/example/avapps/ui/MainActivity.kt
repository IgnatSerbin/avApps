package com.example.avapps.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.avapps.R
import com.example.avapps.adapter.ProductAdapter
import com.example.avapps.databinding.ActivityMainBinding
import com.example.avapps.dataclass.ImageBanner
import com.example.avapps.dataclass.SaleProduct
import com.example.avapps.helper.Noder
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val jsonSaleProduct = """
        {
          "imgBanners": [
            {
            "bannerId": 301,
            "bannerImage": "imgBanner1.png"
            },
            {
            "bannerId": 302,
            "bannerImage": "imgBanner2.png"
            },
            {
            "bannerId": 303,
            "bannerImage": "imgBanner3.png"
            },
            {
            "bannerId": 304,
            "bannerImage": "imgBanner4.png"
            }
          ],
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

    lateinit var binding: ActivityMainBinding
    private val adapter = ProductAdapter(this)

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter.setHasStableIds(true)

        var saleProducts: Array<SaleProduct>
        var imageBanners: Array<ImageBanner>
        var count = 0
        val msgErrorText = findViewById<TextView>(R.id.msgErrorText)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val saleProduct = findViewById<TextView>(R.id.saleProductId)
        progressBar.visibility = View.VISIBLE

//        GlobalScope.launch(Dispatchers.Default) {
        GlobalScope.launch(Dispatchers.Default){
            try {
                val json = objectMapper.readTree(jsonSaleProduct)
                val jsonSaleProduct = json.get("saleListProduct")
                saleProducts = Noder.fromNode(jsonSaleProduct)
                val jsonBanner = json.get("imgBanners")
                imageBanners = Noder.fromNode(jsonBanner)
                count = 0


                CoroutineScope(Dispatchers.Main).launch {
//                runOnUiThread {
                    binding.apply {
                        imgBanner.setImageURI(Uri.parse(getExternalFilesDir(imageBanners[0].bannerImage).toString()))
                        rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
                        rcView.adapter = adapter
                        while (count < jsonSaleProduct.size()) {
                            val products = SaleProduct(
                                saleProducts[count].productId,
                                saleProducts[count].title,
                                saleProducts[count].price + " руб.",
                                getExternalFilesDir(saleProducts[count].img).toString(),
                                saleProducts[count].country,
                                saleProducts[count].quantity
                            )
                            adapter.addSaleProduct(products)
                            count++
                        }
                    }
                    progressBar.visibility = View.INVISIBLE
                    saleProduct.visibility = View.VISIBLE
                }
            } catch (e: IOException) {
                progressBar.visibility = View.INVISIBLE
                Log.d("DOWNLOAD IMAGE", e.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    msgErrorText.visibility = View.VISIBLE
                    msgErrorText.text =
                        e.message + "\n" + "Проверьте соединение с интернетом и перезапустите приложение"
                }
            }
        }
    }

    private fun downloadImg(imgUrl: String, imgString: ByteArray) {
        if (imgUrl.isEmpty()) {
            Log.d("DownloadImage", "Изображения нет")
        } else {
            val imgName = imgUrl.substringAfterLast('/')
            val imgPath = imgUrl.dropLast(imgName.length)
            val folder: File
            try {
                val storageDirectory = this.getExternalFilesDir(imgPath).toString()
                folder = File(storageDirectory)
                if (!folder.exists()) {
                    folder.mkdir()
                }
                if (imgUrl.isEmpty()) {
                    Log.d("DownloadImage", "Изображения нет")
                } else {
                    val file = File(folder, imgName)
                    FileUtils.writeByteArrayToFile(file, imgString)
                    Log.d("DownloadImage", "Выполнено $storageDirectory")
                }
            } catch (e: IOException) {
                Log.d("DownloadImage", e.toString())
            }
        }
    }
}