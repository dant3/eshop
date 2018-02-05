package com.github.dant3.eshop.api.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.dant3.eshop.api.Page
import com.github.dant3.eshop.api.ShoppingApi
import com.github.dant3.eshop.model.ItemSummary
import java.util.*

class ShoppingApiStub(val objectMapper: ObjectMapper): ShoppingApi {
    private val random = Random()

    suspend override fun findItems(searchQuery: String, page: Int): Page<ItemSummary> {
        val apiResponseDelay = 100 + random.nextInt(2500)

        Thread.sleep(apiResponseDelay.toLong())

        val apiResponse = objectMapper.readValue(response, ItemsResponse::class.java)
        return Page(page, apiResponse.itemCount / apiResponse.limit, apiResponse.items)
    }

    private val response = """
        {
    "href": "https://api.ebay.com/buy/browse/v1/item_summary/search?q=drone&limit=12&offset=0",
    "total": 36,
    "next": "https://api.ebay.com/buy/browse/v1/item_summary/search?q=drone&limit=12&offset=12",
    "limit": 12,
    "offset": 0,
    "itemSummaries": [
        {
            "itemId": "v1|291524585112|0",
            "title": "Syma X5SW-V3 Wifi FPV RC Drone Quadcopter 2.4Ghz 6-Axis Gyro with Headless Mode",
            "image": {
                "imageUrl": "https://i.ebayimg.com/thumbs/images/g/JGkAAOSw-81ZmoWa/s-l225.jpg"
            },
            "price": {
                "value": "54.99",
                "currency": "USD"
            },
            "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|291524585112|0",
            "seller": {
                "username": "egrandmart",
                "feedbackPercentage": "99.4",
                "feedbackScore": 135589
            },
            "marketingPrice": {
                "originalPrice": {
                    "value": "78.98",
                    "currency": "USD"
                },
                "discountPercentage": "30",
                "discountAmount": {
                    "value": "23.99",
                    "currency": "USD"
                }
            },
            "condition": "New",
            "conditionId": "1000",
            "thumbnailImages": [
                {
                    "imageUrl": "https://i.ebayimg.com/00/s/MTYwMFgxNjAw/z/JGkAAOSw-81ZmoWa/${'$'}_0.JPG?set_id=880000500F"
                }
            ],
            "shippingOptions": [
                {
                    "shippingCostType": "FIXED",
                    "shippingCost": {
                        "value": "0.00",
                        "currency": "USD"
                    }
                }
            ],
            "buyingOptions": [
                "FIXED_PRICE"
            ],
            "currentBidPrice": {
                "value": "54.99",
                "currency": "USD"
            },
            "epid": "1242098853",
            "itemWebUrl": "http://www.ebay.com/itm/Syma-X5SW-V3-Wifi-FPV-RC-Drone-Quadcopter-2-4Ghz-6-Axis-Gyro-with-Headless-Mode-/291524585112?hash=item43e0382698:g:JGkAAOSw-81ZmoWa",
            "itemLocation": {
                "postalCode": "90061",
                "country": "US"
            },
            "categories": [
                {
                    "categoryId": "182186"
                },
                {
                    "categoryId": "179697"
                },
                {
                    "categoryId": "220"
                },
                {
                    "categoryId": "625"
                },
                {
                    "categoryId": "2562"
                },
                {
                    "categoryId": "182181"
                }
            ],
            "additionalImages": [
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/291524585112_2_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/291524585112_3_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/291524585112_4_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/291524585112_5_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/291524585112_6_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/291524585112_7_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/291524585112_8_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/291524585112_9_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/291524585112_10_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/291524585112_11_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/291524585112_12_0_1/225x225.jpg"
                }
            ],
            "adultOnly": false
        },
        {
            "itemId": "v1|222347987223|0",
            "title": "DJI Phantom 4 PRO+ PLUS Drone 4k w/ Gimbal Camera 1080p 20MP + 5.5\" Display NEW",
            "image": {
                "imageUrl": "https://i.ebayimg.com/thumbs/images/g/3fIAAOSwTzlZgj~R/s-l225.jpg"
            },
            "price": {
                "value": "1799.00",
                "currency": "USD"
            },
            "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|222347987223|0",
            "seller": {
                "username": "consumerelectronicscostsavers",
                "feedbackPercentage": "99.1",
                "feedbackScore": 23165
            },
            "marketingPrice": {
                "originalPrice": {
                    "value": "2299.99",
                    "currency": "USD"
                },
                "discountPercentage": "22",
                "discountAmount": {
                    "value": "500.99",
                    "currency": "USD"
                }
            },
            "condition": "New",
            "conditionId": "1000",
            "thumbnailImages": [
                {
                    "imageUrl": "https://i.ebayimg.com/00/s/ODAwWDgwMA==/z/3fIAAOSwTzlZgj~R/${'$'}_0.JPG?set_id=880000500F"
                }
            ],
            "shippingOptions": [
                {
                    "shippingCostType": "FIXED",
                    "shippingCost": {
                        "value": "0.00",
                        "currency": "USD"
                    }
                }
            ],
            "buyingOptions": [
                "FIXED_PRICE"
            ],
            "currentBidPrice": {
                "value": "1799.00",
                "currency": "USD"
            },
            "epid": "2159540393",
            "itemWebUrl": "http://www.ebay.com/itm/DJI-Phantom-4-PRO-PLUS-Drone-4k-w-Gimbal-Camera-1080p-20MP-5-5-Display-NEW-/222347987223?hash=item33c4f90917:g:3fIAAOSwTzlZgj~R",
            "itemLocation": {
                "postalCode": "19120",
                "country": "US"
            },
            "categories": [
                {
                    "categoryId": "179697"
                },
                {
                    "categoryId": "625"
                }
            ],
            "additionalImages": [
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_2_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_3_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_4_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_5_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_6_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_7_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_8_0_1/225x225.jpg"
                }
            ],
            "adultOnly": false
        },
        {
            "itemId": "v1|232126092274|0",
            "title": "DJI Mavic Pro Folding Drone - 4K Stabilized Camera, Active Track, Avoidance, GPS",
            "image": {
                "imageUrl": "https://i.ebayimg.com/thumbs/images/g/17EAAOSwiiRZgi9G/s-l225.jpg"
            },
            "price": {
                "value": "999.00",
                "currency": "USD"
            },
            "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|232126092274|0",
            "seller": {
                "username": "consumerelectronicscostsavers",
                "feedbackPercentage": "99.1",
                "feedbackScore": 23166
            },
            "marketingPrice": {
                "originalPrice": {
                    "value": "1299.99",
                    "currency": "USD"
                },
                "discountPercentage": "23",
                "discountAmount": {
                    "value": "300.99",
                    "currency": "USD"
                }
            },
            "condition": "New",
            "conditionId": "1000",
            "thumbnailImages": [
                {
                    "imageUrl": "https://i.ebayimg.com/00/s/NDM3WDgwMA==/z/17EAAOSwiiRZgi9G/${'$'}_0.PNG?set_id=880000500F"
                }
            ],
            "shippingOptions": [
                {
                    "shippingCostType": "FIXED",
                    "shippingCost": {
                        "value": "0.00",
                        "currency": "USD"
                    }
                }
            ],
            "buyingOptions": [
                "FIXED_PRICE"
            ],
            "currentBidPrice": {
                "value": "999.00",
                "currency": "USD"
            },
            "epid": "512988795",
            "itemWebUrl": "http://www.ebay.com/itm/DJI-Mavic-Pro-Folding-Drone-4K-Stabilized-Camera-Active-Track-Avoidance-GPS-/232126092274?hash=item360bcb13f2:g:17EAAOSwiiRZgi9G",
            "itemLocation": {
                "postalCode": "19120",
                "country": "US"
            },
            "categories": [
                {
                    "categoryId": "179697"
                },
                {
                    "categoryId": "625"
                }
            ],
            "additionalImages": [
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/232126092274_2_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/232126092274_3_0_1/225x225.jpg"
                }
            ],
            "adultOnly": false
        },
            {
            "itemId": "v1|332061783310|0",
            "title": "FIFA WORLD CUP FULL SIZE SOCCER BALL INTERNATIONAL COUNTRY FLAGS OFFICIAL SIZE 5",
            "image": {
                "imageUrl": "https://i.ebayimg.com/thumbs/images/g/0iIAAOSwEzxYUFbp/s-l225.jpg"
            },
            "price": {
                "value": "8.95",
                "currency": "USD"
            },
            "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|332061783310|0",
            "seller": {
                "username": "unlimitedwares",
                "feedbackPercentage": "99.0",
                "feedbackScore": 378908
            },
            "condition": "New",
            "conditionId": "1000",
            "thumbnailImages": [
                {
                    "imageUrl": "https://i.ebayimg.com/00/s/NjAxWDYwMQ==/z/0iIAAOSwEzxYUFbp/${'$'}_0.JPG?set_id=880000500F"
                }
            ],
            "shippingOptions": [
                {
                    "shippingCostType": "FIXED",
                    "shippingCost": {
                        "value": "0.00",
                        "currency": "USD"
                    }
                }
            ],
            "buyingOptions": [
                "FIXED_PRICE"
            ],
            "currentBidPrice": {
                "value": "8.95",
                "currency": "USD"
            },
            "epid": "1134280568",
            "itemWebUrl": "http://www.ebay.com/itm/FIFA-WORLD-CUP-FULL-SIZE-SOCCER-BALL-INTERNATIONAL-COUNTRY-FLAGS-OFFICIAL-SIZE-5-/332061783310?hash=item4d506cb50e:g:0iIAAOSwEzxYUFbp",
            "itemLocation": {
                "postalCode": "60053",
                "country": "US"
            },
            "categories": [
                {
                    "categoryId": "20863"
                },
                {
                    "categoryId": "888"
                },
                {
                    "categoryId": "20862"
                },
                {
                    "categoryId": "159049"
                }
            ],
            "additionalImages": [
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/332061783310_2_0_1/225x225.jpg"
                }
            ],
            "adultOnly": false
        },
        {
            "itemId": "v1|263165763908|0",
            "title": "adidas Performance Confederations Cup Glider Soccer Ball Size 1",
            "image": {
                "imageUrl": "https://i.ebayimg.com/thumbs/images/g/z~EAAOSwACZZsz4a/s-l225.jpg"
            },
            "price": {
                "value": "12.00",
                "currency": "USD"
            },
            "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|263165763908|0",
            "seller": {
                "username": "orderagain",
                "feedbackPercentage": "99.6",
                "feedbackScore": 25208
            },
            "condition": "New",
            "conditionId": "1000",
            "thumbnailImages": [
                {
                    "imageUrl": "https://i.ebayimg.com/00/s/MTYwMFgxNjAw/z/z~EAAOSwACZZsz4a/${'$'}_0.JPG?set_id=8800005007"
                }
            ],
            "shippingOptions": [
                {
                    "shippingCostType": "FIXED",
                    "shippingCost": {
                        "value": "0.00",
                        "currency": "USD"
                    }
                }
            ],
            "buyingOptions": [
                "FIXED_PRICE"
            ],
            "currentBidPrice": {
                "value": "12.00",
                "currency": "USD"
            },
            "epid": "2146258740",
            "itemWebUrl": "http://www.ebay.com/itm/adidas-Performance-Confederations-Cup-Glider-Soccer-Ball-Size-1-/263165763908?hash=item3d45e6e144:g:z~EAAOSwACZZsz4a",
            "itemLocation": {
                "country": "US"
            },
            "categories": [
                {
                    "categoryId": "20863"
                },
                {
                    "categoryId": "888"
                },
                {
                    "categoryId": "20862"
                },
                {
                    "categoryId": "159049"
                }
            ],
            "additionalImages": [
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/263165763908_2_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/263165763908_3_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/263165763908_4_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/263165763908_5_0_1/225x225.jpg"
                }
            ],
            "adultOnly": false
        },
        {
            "itemId": "v1|282637435420|0",
            "title": "Adidas Champions League Official Soccer Ball Capitano Finale Cardiff 2017 Size 5",
            "image": {
                "imageUrl": "https://i.ebayimg.com/thumbs/images/g/0rUAAOSwqgRZrKHX/s-l225.jpg"
            },
            "price": {
                "value": "24.99",
                "currency": "USD"
            },
            "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|282637435420|0",
            "seller": {
                "username": "rab50000",
                "feedbackPercentage": "100.0",
                "feedbackScore": 1272
            },
            "condition": "New",
            "conditionId": "1000",
            "thumbnailImages": [
                {
                    "imageUrl": "https://i.ebayimg.com/00/s/NzY4WDc2OA==/z/0rUAAOSwqgRZrKHX/${'$'}_0.JPG?set_id=8800005007"
                }
            ],
            "shippingOptions": [
                {
                    "shippingCostType": "FIXED",
                    "shippingCost": {
                        "value": "0.00",
                        "currency": "USD"
                    }
                }
            ],
            "buyingOptions": [
                "FIXED_PRICE"
            ],
            "currentBidPrice": {
                "value": "24.99",
                "currency": "USD"
            },
            "epid": "2256353041",
            "itemWebUrl": "http://www.ebay.com/itm/Adidas-Champions-League-Official-Soccer-Ball-Capitano-Finale-Cardiff-2017-Size-5-/282637435420?hash=item41ce81021c:g:0rUAAOSwqgRZrKHX",
            "itemLocation": {
                "postalCode": "90005",
                "country": "US"
            },
            "categories": [
                {
                    "categoryId": "20863"
                },
                {
                    "categoryId": "888"
                },
                {
                    "categoryId": "20862"
                },
                {
                    "categoryId": "159049"
                }
            ],
            "additionalImages": [
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/282637435420_2_0_1/225x225.jpg"
                }
            ],
            "adultOnly": false
        },${ "" /*danger*/ }
            {
            "itemId": "v1|232328214987|531582721516",
            "title": "BRAND NEW Apple iPhone 6 Plus 5.5\" Display 16GB GSM UNLOCKED Smartphone",
            "itemGroupHref": "https://api.ebay.com/buy/browse/v1/item/get_items_by_item_group?item_group_id=232328214987",
            "image": {
                "imageUrl": "https://i.ebayimg.com/thumbs/images/m/mQX6bLHIttCN7i0hrrPH28g/s-l225.jpg"
            },
            "price": {
                "value": "329.99",
                "currency": "USD"
            },
            "itemGroupType": "SELLER_DEFINED_VARIATIONS",
            "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|232328214987|531582721516",
            "seller": {
                "username": "buyspry",
                "feedbackPercentage": "98.5",
                "feedbackScore": 141113
            },
            "condition": "New",
            "conditionId": "1000",
            "shippingOptions": [
                {
                    "shippingCostType": "FIXED",
                    "shippingCost": {
                        "value": "0.00",
                        "currency": "USD"
                    }
                }
            ],
            "buyingOptions": [
                "FIXED_PRICE"
            ],
            "currentBidPrice": {
                "value": "329.99",
                "currency": "USD"
            },
            "epid": "203709242",
            "itemWebUrl": "http://www.ebay.com/itm/BRAND-NEW-Apple-iPhone-6-Plus-5-5-Display-16GB-GSM-UNLOCKED-Smartphone-/232328214987?var=&hash=item3617d739cb:m:mQX6bLHIttCN7i0hrrPH28g",
            "itemLocation": {
                "postalCode": "20879",
                "country": "US"
            },
            "categories": [
                {
                    "categoryId": "9355"
                },
                {
                    "categoryId": "15032"
                }
            ],
            "adultOnly": false
        },
        {
            "itemId": "v1|222629437239|521475083860",
            "title": " BLU Studio G2 Android Cell Phone Unlocked   Dual SIM GSM Smartphone ",
            "itemGroupHref": "https://api.ebay.com/buy/browse/v1/item/get_items_by_item_group?item_group_id=222629437239",
            "image": {
                "imageUrl": "https://i.ebayimg.com/thumbs/images/m/mlb238vmvXcUuYz1PXwQ4gg/s-l225.jpg"
            },
            "price": {
                "value": "64.97",
                "currency": "USD"
            },
            "itemGroupType": "SELLER_DEFINED_VARIATIONS",
            "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|222629437239|521475083860",
            "seller": {
                "username": "tojo0308",
                "feedbackPercentage": "99.2",
                "feedbackScore": 1970
            },
            "condition": "New",
            "conditionId": "1000",
            "shippingOptions": [
                {
                    "shippingCostType": "FIXED",
                    "shippingCost": {
                        "value": "0.00",
                        "currency": "USD"
                    }
                }
            ],
            "buyingOptions": [
                "FIXED_PRICE"
            ],
            "currentBidPrice": {
                "value": "64.97",
                "currency": "USD"
            },
            "epid": "2024368820",
            "itemWebUrl": "http://www.ebay.com/itm/BLU-Studio-G2-Android-Cell-Phone-Unlocked-Dual-SIM-GSM-Smartphone-/222629437239?var=&hash=item33d5bf9f37:m:mlb238vmvXcUuYz1PXwQ4gg",
            "itemLocation": {
                "postalCode": "33173",
                "country": "US"
            },
            "categories": [
                {
                    "categoryId": "9355"
                },
                {
                    "categoryId": "15032"
                }
            ],
            "additionalImages": [
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222629437239_2_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222629437239_3_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222629437239_4_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222629437239_5_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222629437239_6_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222629437239_7_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222629437239_8_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222629437239_9_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222629437239_10_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222629437239_11_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222629437239_12_0_1/225x225.jpg"
                }
            ],
            "adultOnly": false
        },
        {
            "itemId": "v1|332242861213|541254833108",
            "title": "Brand New Apple iPhone 6s Plus 5.5\" Retina 16GB 4G LTE GSM UNLOCKED Smartphone",
            "itemGroupHref": "https://api.ebay.com/buy/browse/v1/item/get_items_by_item_group?item_group_id=332242861213",
            "image": {
                "imageUrl": "https://i.ebayimg.com/thumbs/images/m/mi9RA8MhBfqyGt2k7VRjkcQ/s-l225.jpg"
            },
            "price": {
                "value": "399.99",
                "currency": "USD"
            },
            "itemGroupType": "SELLER_DEFINED_VARIATIONS",
            "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|332242861213|541254833108",
            "seller": {
                "username": "buyspry",
                "feedbackPercentage": "98.5",
                "feedbackScore": 141106
            },
            "marketingPrice": {
                "originalPrice": {
                    "value": "409.99",
                    "currency": "USD"
                },
                "discountPercentage": "2",
                "discountAmount": {
                    "value": "10.00",
                    "currency": "USD"
                }
            },
            "condition": "New",
            "conditionId": "1000",
            "shippingOptions": [
                {
                    "shippingCostType": "FIXED",
                    "shippingCost": {
                        "value": "0.00",
                        "currency": "USD"
                    }
                }
            ],
            "buyingOptions": [
                "FIXED_PRICE"
            ],
            "currentBidPrice": {
                "value": "399.99",
                "currency": "USD"
            },
            "epid": "216202309",
            "itemWebUrl": "http://www.ebay.com/itm/Brand-New-Apple-iPhone-6s-Plus-5-5-Retina-16GB-4G-LTE-GSM-UNLOCKED-Smartphone-/332242861213?var=&hash=item4d5b37bc9d:m:mi9RA8MhBfqyGt2k7VRjkcQ",
            "itemLocation": {
                "postalCode": "20879",
                "country": "US"
            },
            "categories": [
                {
                    "categoryId": "9355"
                },
                {
                    "categoryId": "15032"
                }
            ],
            "adultOnly": false
        }, ${ "" /*danger*/ }
            {
            "itemId": "v1|222347987223|0",
            "title": "DJI Phantom 4 PRO+ PLUS Drone 4k w/ Gimbal Camera 1080p 20MP + 5.5\" Display NEW",
            "image": {
                "imageUrl": "https://i.ebayimg.com/thumbs/images/g/3fIAAOSwTzlZgj~R/s-l225.jpg"
            },
            "price": {
                "value": "1799.00",
                "currency": "USD"
            },
            "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|222347987223|0",
            "seller": {
                "username": "consumerelectronicscostsavers",
                "feedbackPercentage": "99.1",
                "feedbackScore": 23165
            },
            "marketingPrice": {
                "originalPrice": {
                    "value": "2299.99",
                    "currency": "USD"
                },
                "discountPercentage": "22",
                "discountAmount": {
                    "value": "500.99",
                    "currency": "USD"
                }
            },
            "condition": "New",
            "conditionId": "1000",
            "thumbnailImages": [
                {
                    "imageUrl": "https://i.ebayimg.com/00/s/ODAwWDgwMA==/z/3fIAAOSwTzlZgj~R/${'$'}_0.JPG?set_id=880000500F"
                }
            ],
            "shippingOptions": [
                {
                    "shippingCostType": "FIXED",
                    "shippingCost": {
                        "value": "0.00",
                        "currency": "USD"
                    }
                }
            ],
            "buyingOptions": [
                "FIXED_PRICE"
            ],
            "currentBidPrice": {
                "value": "1799.00",
                "currency": "USD"
            },
            "epid": "2159540393",
            "itemWebUrl": "http://www.ebay.com/itm/DJI-Phantom-4-PRO-PLUS-Drone-4k-w-Gimbal-Camera-1080p-20MP-5-5-Display-NEW-/222347987223?hash=item33c4f90917:g:3fIAAOSwTzlZgj~R",
            "itemLocation": {
                "postalCode": "19120",
                "country": "US"
            },
            "categories": [
                {
                    "categoryId": "179697"
                },
                {
                    "categoryId": "625"
                }
            ],
            "additionalImages": [
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_2_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_3_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_4_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_5_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_6_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_7_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/222347987223_8_0_1/225x225.jpg"
                }
            ],
            "adultOnly": false
        },
        {
            "itemId": "v1|232126092274|0",
            "title": "DJI Mavic Pro Folding Drone - 4K Stabilized Camera, Active Track, Avoidance, GPS",
            "image": {
                "imageUrl": "https://i.ebayimg.com/thumbs/images/g/17EAAOSwiiRZgi9G/s-l225.jpg"
            },
            "price": {
                "value": "999.00",
                "currency": "USD"
            },
            "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|232126092274|0",
            "seller": {
                "username": "consumerelectronicscostsavers",
                "feedbackPercentage": "99.1",
                "feedbackScore": 23166
            },
            "marketingPrice": {
                "originalPrice": {
                    "value": "1299.99",
                    "currency": "USD"
                },
                "discountPercentage": "23",
                "discountAmount": {
                    "value": "300.99",
                    "currency": "USD"
                }
            },
            "condition": "New",
            "conditionId": "1000",
            "thumbnailImages": [
                {
                    "imageUrl": "https://i.ebayimg.com/00/s/NDM3WDgwMA==/z/17EAAOSwiiRZgi9G/${'$'}_0.PNG?set_id=880000500F"
                }
            ],
            "shippingOptions": [
                {
                    "shippingCostType": "FIXED",
                    "shippingCost": {
                        "value": "0.00",
                        "currency": "USD"
                    }
                }
            ],
            "buyingOptions": [
                "FIXED_PRICE"
            ],
            "currentBidPrice": {
                "value": "999.00",
                "currency": "USD"
            },
            "epid": "512988795",
            "itemWebUrl": "http://www.ebay.com/itm/DJI-Mavic-Pro-Folding-Drone-4K-Stabilized-Camera-Active-Track-Avoidance-GPS-/232126092274?hash=item360bcb13f2:g:17EAAOSwiiRZgi9G",
            "itemLocation": {
                "postalCode": "19120",
                "country": "US"
            },
            "categories": [
                {
                    "categoryId": "179697"
                },
                {
                    "categoryId": "625"
                }
            ],
            "additionalImages": [
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/232126092274_2_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/232126092274_3_0_1/225x225.jpg"
                }
            ],
            "adultOnly": false
        },
        {
            "itemId": "v1|132341438034|0",
            "title": "3DR - Solo Drone REV B GPS - Brand new - Quadcopter SA11A for GoPro Camera ",
            "image": {
                "imageUrl": "https://i.ebayimg.com/thumbs/images/g/EuYAAOSwkglZycsg/s-l225.jpg"
            },
            "price": {
                "value": "209.99",
                "currency": "USD"
            },
            "itemHref": "https://api.ebay.com/buy/browse/v1/item/v1|132341438034|0",
            "seller": {
                "username": "speedstuffsales",
                "feedbackPercentage": "99.3",
                "feedbackScore": 1283
            },
            "condition": "New",
            "conditionId": "1000",
            "thumbnailImages": [
                {
                    "imageUrl": "https://i.ebayimg.com/00/s/MTIwMFgxMjAw/z/EuYAAOSwkglZycsg/${'$'}_0.JPG?set_id=8800005007"
                }
            ],
            "buyingOptions": [
                "FIXED_PRICE"
            ],
            "currentBidPrice": {
                "value": "209.99",
                "currency": "USD"
            },
            "epid": "809540199",
            "itemWebUrl": "http://www.ebay.com/itm/3DR-Solo-Drone-REV-B-GPS-Brand-new-Quadcopter-SA11A-for-GoPro-Camera-/132341438034?hash=item1ed02a1652:g:EuYAAOSwkglZycsg",
            "itemLocation": {
                "postalCode": "92584",
                "country": "US"
            },
            "categories": [
                {
                    "categoryId": "179697"
                },
                {
                    "categoryId": "625"
                }
            ],
            "additionalImages": [
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/132341438034_2_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/132341438034_3_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/132341438034_4_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/132341438034_5_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/132341438034_6_0_1/225x225.jpg"
                },
                {
                    "imageUrl": "https://galleryplus.ebayimg.com/ws/web/132341438034_7_0_1/225x225.jpg"
                }
            ],
            "adultOnly": false
        }
    ]
}"""
}