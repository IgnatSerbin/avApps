package com.example.avapps

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.avapps.databinding.ItemProductBinding
import com.example.avapps.dataclass.SaleProduct
import com.example.avapps.dataclass.ProductCart

class ProductAdapter(var context: Context): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    private val productList = ArrayList<SaleProduct>()
    private val cart = ArrayList<ProductCart>()
    private var product: ProductCart? = null
    class ProductHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ItemProductBinding.bind(item)
        fun bind(product: SaleProduct){
            binding.imageView.setImageURI(Uri.parse(product.img))
            binding.productName.text = product.title
            binding.productPrice.text = product.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        var count = 0
        holder.bind(productList[position])
        holder.binding.countUp.setOnClickListener {
            if (count >= productList[position].quantity){
                toast(context, "Доступно максимальное количество ${productList[position].quantity}")
            } else {
                count++
            }
            holder.binding.txtPrice.text = count.toString()
        }
        holder.binding.countDown.setOnClickListener {
            if (count <= 0){
                count = 0
            } else {count--}
            holder.binding.txtPrice.text = count.toString()
        }
        holder.binding.btnSale.setOnClickListener {
            if (count > 0) {
                product = ProductCart(productList[position], count)
                cart.add(product!!)
                toast(context, "Товар ${productList[position].title} добавлен в корзину")
                Log.d("CART PRODUCT", cart.toString())
            }
        }
        holder.binding.imageView.setOnClickListener {
            val productIntent = Intent(context, ProductPage::class.java)
            productIntent.putExtra("productId", productList[position].productId)
            context.startActivity(productIntent)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun addSaleProduct(product: SaleProduct){
        productList.add(product)
        notifyDataSetChanged()
    }

    private fun toast(context: Context, msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}