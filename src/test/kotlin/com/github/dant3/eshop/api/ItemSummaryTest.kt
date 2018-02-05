package com.github.dant3.eshop.api

import com.github.dant3.eshop.EShopComponent
import com.github.dant3.eshop.model.Image
import com.github.dant3.eshop.model.ItemId
import com.github.dant3.eshop.model.ItemSummary
import com.github.dant3.eshop.model.Price
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.net.URL

class ItemSummaryTest {
    private val sampleJson = """
        |{
        |    "itemId": "v1|222347987223|0",
        |    "title": "DJI Phantom 4 PRO+ PLUS Drone 4k w/ Gimbal Camera 1080p 20MP + 5.5\" Display NEW",
        |    "image": {
        |        "imageUrl": "https://i.ebayimg.com/thumbs/images/g/3fIAAOSwTzlZgj~R/s-l225.jpg"
        |    },
        |    "price": {
        |        "value": "1799.00",
        |        "currency": "USD"
        |    },
        |    "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|222347987223|0",
        |    "seller": {
        |        "username": "consumerelectronicscostsavers",
        |        "feedbackPercentage": "99.1",
        |        "feedbackScore": 23165
        |    },
        |    "marketingPrice": {
        |        "originalPrice": {
        |            "value": "2299.99",
        |            "currency": "USD"
        |        },
        |        "discountPercentage": "22",
        |        "discountAmount": {
        |            "value": "500.99",
        |            "currency": "USD"
        |        }
        |    },
        |    "condition": "New",
        |    "conditionId": "1000",
        |    "thumbnailImages": [
        |        {
        |            "imageUrl": "https://i.ebayimg.com/00/s/ODAwWDgwMA==/z/3fIAAOSwTzlZgj~R/${'$'}_0.JPG?set_id=880000500F"
        |        }
        |    ],
        |    "shippingOptions": [
        |        {
        |            "shippingCostType": "FIXED",
        |            "shippingCost": {
        |                "value": "0.00",
        |                "currency": "USD"
        |            }
        |        }
        |    ],
        |    "buyingOptions": [
        |        "FIXED_PRICE"
        |    ],
        |    "currentBidPrice": {
        |        "value": "1799.00",
        |        "currency": "USD"
        |    },
        |    "epid": "2159540393",
        |    "itemWebUrl": "http://www.ebay.com/itm/DJI-Phantom-4-PRO-PLUS-Drone-4k-w-Gimbal-Camera-1080p-20MP-5-5-Display-NEW-/222347987223?hash=item33c4f90917:g:3fIAAOSwTzlZgj~R",
        |    "itemLocation": {
        |        "postalCode": "19120",
        |        "country": "US"
        |    },
        |    "categories": [
        |        {
        |            "categoryId": "179697"
        |        },
        |        {
        |            "categoryId": "625"
        |        }
        |    ],
        |    "additionalImages": [
        |        {
        |            "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_2_0_1/225x225.jpg"
        |        },
        |        {
        |            "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_3_0_1/225x225.jpg"
        |        },
        |        {
        |            "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_4_0_1/225x225.jpg"
        |        },
        |        {
        |            "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_5_0_1/225x225.jpg"
        |        },
        |        {
        |            "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_6_0_1/225x225.jpg"
        |        },
        |        {
        |            "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_7_0_1/225x225.jpg"
        |        },
        |        {
        |            "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_8_0_1/225x225.jpg"
        |        }
        |    ],
        |    "adultOnly": false
        |}""".trimMargin()


    @Test fun it_can_be_parsed_from_sample_json() {
        val objectMapper = EShopComponent.objectMapper
        val result = objectMapper.readValue(sampleJson, ItemSummary::class.java)

        with (result) {
            assertEquals(ItemId("v1|222347987223|0"), id)
            assertEquals("DJI Phantom 4 PRO+ PLUS Drone 4k w/ Gimbal Camera 1080p 20MP + 5.5\" Display NEW", title)
            assertEquals(Image(URL("https://i.ebayimg.com/thumbs/images/g/3fIAAOSwTzlZgj~R/s-l225.jpg")), image)
            assertEquals(Price(BigDecimal("1799.00"), "USD"), price)
        }
    }
}