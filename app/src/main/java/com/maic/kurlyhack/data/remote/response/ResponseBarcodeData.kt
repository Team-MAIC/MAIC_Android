package com.maic.kurlyhack.data.remote.response

data class ResponseBarcodeData(
    val result: Int,
    val product: ProductData,
    val compare: CompareData
)

data class ProductData(
    val productId: Int,
    val productName: String,
    val productThumbnail: String
)

data class CompareData(
    val productId: Int,
    val productName: String,
    val productThumbnail: String
)
