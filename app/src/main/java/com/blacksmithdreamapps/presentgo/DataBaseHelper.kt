package com.blacksmithdreamapps.presentgo

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 014 14.03.2018.
 */
class DataBaseHelper

(private val myContext: Context){

    fun  handleDb() : SQLiteDatabase
    {
        var myDatabase = myContext.openOrCreateDatabase("MyGifts", MODE_PRIVATE, null)
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS `en` (\n" +
                "\t`id`\tINTEGER NOT NULL DEFAULT 1 PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                "\t`Family`\tINTEGER,\n" +
                "\t`Friends`\tINTEGER,\n" +
                "\t`Colleagues`\tINTEGER,\n" +
                "\t`Beloved`\tINTEGER,\n" +
                "\t`Superiors`\tINTEGER,\n" +
                "\t`Person_i_hate`\tINTEGER,\n" +
                "\t`Male`\tINTEGER,\n" +
                "\t`Female`\tINTEGER,\n" +
                "\t`Child`\tINTEGER,\n" +
                "\t`Teenager`\tINTEGER,\n" +
                "\t`Adult`\tINTEGER,\n" +
                "\t`Elderly`\tINTEGER,\n" +
                "\t`Budget_minimal`\tINTEGER,\n" +
                "\t`Budget_low`\tINTEGER,\n" +
                "\t`Budget_middle`\tINTEGER,\n" +
                "\t`Budget_middle_plus`\tINTEGER,\n" +
                "\t`Budget_high`\tINTEGER,\n" +
                "\t`Birthday`\tINTEGER,\n" +
                "\t`Anniversary`\tINTEGER,\n" +
                "\t`Holiday`\tINTEGER,\n" +
                "\t`Graduation`\tINTEGER,\n" +
                "\t`Wedding`\tINTEGER,\n" +
                "\t`Just_because`\tINTEGER,\n" +
                "\t`Stranger`\tINTEGER,\n" +
                "\t`One_time_seen`\tINTEGER,\n" +
                "\t`Acquaintance`\tINTEGER,\n" +
                "\t`Comrade`\tINTEGER,\n" +
                "\t`Close`\tINTEGER,\n" +
                "\t`Name`\tTEXT,\n" +
                "\t`Description`\tTEXT,\n" +
                "\t`Image`\tTEXT\n" +
                ")");
        myDatabase.execSQL("INSERT or REPLACE INTO `en` (id,Family,Friends,Colleagues,Beloved,Superiors,Person_i_hate,Male,Female,Child,Teenager,Adult,Elderly,Budget_minimal,Budget_low,Budget_middle,Budget_middle_plus,Budget_high,Birthday,Anniversary,Holiday,Graduation,Wedding,Just_because,Stranger,One_time_seen,Acquaintance,Comrade,Close,Name,Description,Image) VALUES (0,'true','true','false','false','false','true','true','true','false','false','true','true','true','true','true','false','false','false','false','true','false','true','true','true','true','true','true','true','humidifier','smells good, easy to use, cheap enough','https://i5.walmartimages.com/asr/77e71f98-a377-4a1b-890d-f99b2318ea29_1.8d1ea0c74df02f4a9d1d608f54976dc7.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF'),\n" +
                " (1,'true','true','true','true','false','false','true','true','false','true','true','false','false','true','true','true','false','true','false','true','false','true','true','false','true','true','true','true','headphones','easy to buy, people needs them, plays music :)','https://static.eldorado.ru/photos/71/712/096/18/new_71209618_l_1475743981.jpeg'),\n" +
                " (2,'true','true','true','true','false','true','true','true','false','true','true','true','false','false','true','true','true','true','true','true','false','true','true','true','true','true','true','true','perfume\n" +
                "','smells good, huge diversity, easy to buy','https://images.ua.prom.st/2831522_w0_h0_1153.jpg'),\n" +
                " (3,'true','true','true','true','false','false','true','true','false','true','true','true','true','true','true','true','true','true','true','true','false','true','true','false','false','true','true','true','cozy scarf','sweet, warm, beautiful','https://i1.rozetka.ua/goods/2418130/28196537_images_2418130185.jpg'),\n" +
                " (4,'true','true','false','true','false','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','souvenir','beautiful, linked with memories, may be anything','https://www.tourprom.ru/site_media/images/souvenir/big-ben-suvenir-iz-londona_1.jpg'),\n" +
                " (5,'true','true','true','true','true','false','true','true','false','true','true','true','false','true','true','true','true','true','true','true','false','true','false','false','false','true','true','true','trip together','laugh, adventures, memories','http://www.phototopic.ru/uploads/posts/2013-08/1376423049_foto-puteshestvie-nogami-vpered_2.jpg'),\n" +
                " (6,'true','true','true','true','false','false','true','true','true','true','true','true','false','true','true','true','true','true','true','true','true','false','false','false','false','false','true','true','mobile phone','extra needed, useful, you can make selfies:)','http://vashgolos.net/photo/techno/70288_0.jpg'),\n" +
                " (7,'true','true','true','true','false','false','true','true','true','true','true','true','false','true','true','true','true','true','true','true','true','false','false','false','false','false','true','true','tablet','like a mobile, but bigger:), but bigger:(','https://skobka.com.ua/upload/medialibrary/5ed/5ed52db91e1bb65bf9e99f3af5d61a0d.jpg'),\n" +
                " (8,'true','true','true','true','false','true','true','true','false','false','true','true','false','true','true','true','false','true','true','true','false','true','true','false','true','true','true','true','bathrobe','warm, sweet, great gift for any human!','https://media.togas.com/media/catalog/product/cache/8/image/1600x/040ec09b1e35df139433887a97daa66f/e/m/emborio_main_1.jpg'),\n" +
                " (10,'true','true','false','true','true','false','true','true','false','false','true','true','false','false','true','true','true','false','true','true','true','true','false','false','false','false','true','true','permit for a sanatorium','best for families, in summer, has program','http://www.ayda.ru/images/pics/8/h-25061-_1.jpg'),\n" +
                " (11,'false','true','false','true','false','false','true','true','false','true','true','false','false','true','true','true','true','true','true','true','true','true','true','false','false','true','true','true','photocam','high-quality photos, you look like pro, many-many-many photos','https://i2.wp.com/funfoto.me/wp-content/uploads/2017/11/1641431791-1.jpg?fit=800%2C800'),\n" +
                " (12,'true','true','true','false','true','true','true','true','true','true','true','true','true','true','true','true','false','true','false','true','true','true','false','true','true','true','true','false','table lamp','work in evening, your eyes are safe, very useful','http://lampa.dn.ua/img2/nastolnaja-lampa/nastolnaja-lampa-vt-069-black.jpg'),\n" +
                " (13,'true','true','true','true','false','false','true','true','true','true','true','false','true','true','true','false','false','true','true','true','false','false','true','true','true','true','true','true','ride on skates','skate on ice, best with friends, you can play hockey','http://www.novosibirskgid.ru/image/1364301867_886.jpg'),\n" +
                " (14,'false','true','false','true','false','false','true','true','false','true','false','false','true','true','true','true','true','true','false','true','false','false','true','true','true','true','true','true','skateboard','you can skateboard, even with friends, perform tricks','http://www.championnet.ru/spree/products/46778/original/03%D0%A1%D0%BA%D0%B5%D0%B9%D1%82%D0%B1%D0%BE%D1%80%D0%B4_Larsen_City-2__%D1%8031%D1%858.jpg?1455878689'),\n" +
                " (15,'true','true','true','false','false','false','true','true','true','true','true','false','false','true','true','true','false','true','false','true','false','false','false','false','false','true','true','true','designer flash drive','looks rather pretty, cheap, can be handmade','http://www.usb-fleshki.com.ua/cont/img/cat/big/dizaynerskaya-fleshka-domik.jpg?1483778060'),\n" +
                " (16,'true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','false','true','true','true','true','true','true','organizer','write what you think, manage time, little psyhologist sometimes','http://didaktor.ru/wp-content/uploads/2012/08/notopersonalorganizer-300x225.jpg'),\n" +
                " (17,'true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','false','false','true','false','true','true','false','false','true','true','true','true','true','decoration','looks pretty, creates own style, field for experiments','https://i.pinimg.com/originals/e7/0a/ca/e70aca2a95a3258d3ab7f35e3d6cf227.jpg'),\n" +
                " (18,'true','true','true','true','false','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','false','true','true','true','true','true','true','true','magic ball','it is magic, it is a ball, it is a magic ball!','https://www.lewisblack.com/sites/default/files/magic-ball-i-dont-give-a-shit-800_0.jpg'),\n" +
                " (19,'false','true','true','true','false','true','true','true','true','true','true','false','true','true','true','false','false','true','false','true','false','false','true','true','true','true','true','true','cozy slippers','you legs feels cozy:), it is very comfortable, suites everyone','http://www.barmaley-sakh.ru/file/1773.jpg'),\n" +
                " (20,'true','true','true','true','false','true','true','true','true','true','true','true','true','true','true','false','false','true','true','true','false','true','true','true','true','true','true','true','cup','drink tea, drink coffee, drink anything you want from','https://prazdnik-shop.com.ua/20486-large_default/chashka-batareyka.jpg'),\n" +
                " (21,'true','true','true','true','true','true','true','true','false','true','true','true','true','true','true','false','false','true','true','true','true','false','true','true','true','true','true','true','twister game','play with friends, it is fun, laugh at every step','http://lotosplazaptz.ru/images/cms/data/fotoalbomy/prazdnik_zazhzheniya_novogodnih_ognej_yolochka_gori2/img_1750.jpg'),\n" +
                " (22,'true','true','false','true','false','true','true','true','false','true','true','false','false','true','true','true','false','true','false','true','false','false','true','true','true','true','true','true','monopoly game','play with friends, it is fun, build your business or empire','https://s3-eu-west-1.amazonaws.com/mosigra.product.main/525/945/IMG_8689_800x500.jpg'),\n" +
                " (23,'true','true','false','true','false','true','true','true','false','true','true','false','false','true','true','true','false','true','false','true','false','false','true','true','true','true','true','true','plad','soft, warm, sweet\n" +
                "','https://ucarecdn.com/52d5dcc3-df47-4212-8988-a783fb8952f1/'),\n" +
                " (24,'true','true','true','true','false','false','true','true','false','false','true','true','true','true','false','false','false','true','true','true','false','true','false','false','false','true','true','true','a set of salt cellars or pepper','put here spices, good gift for family, can be designed in original way','https://ae01.alicdn.com/kf/HTB1L0NSHpXXXXbjaXXXq6xXFXXX3/-.jpg_640x640.jpg'),\n" +
                " (25,'true','true','true','false','true','false','true','true','false','false','true','true','false','false','true','true','true','false','false','true','false','true','false','false','false','false','false','true','appliances','good gift for family, helps by house, makes dirty job','https://pisces.bbystatic.com/image2/BestBuy_US/store/ee/2016/app/pr/SOL-1819-majors-labor-day-sale/SOL-1819-samsung-stainless-showcase-slideins.jpg;maxHeight=309;maxWidth=457'),\n" +
                " (26,'true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','false','true','true','true','true','true','false','true','true','true','true','true','original alarm clock','it can be original form, original sound, makes your wake up in original way','https://img01-olxua.akamaized.net/img-olxua/582313232_1_644x461_podarok-aktsiyasovremennyy-i-originalnyy-budilnik-ct390-thomson-kremenchug.jpg'),\n" +
                " (27,'true','false','false','true','false','false','false','true','false','false','true','true','false','false','true','true','true','true','true','true','false','true','true','false','false','false','false','true','romantic trip','live, love, laugh','https://media.jaa.su/wp-content/uploads/2012/06/46.jpg'),\n" +
                " (28,'true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','bouquet of flowers','easy to find, smells good, can be a sign of smth bigger','https://flor2u.ru/images/uploads/conversion/bb3/bb38ecceec8d0b21ff510f4a9b16b70d/flor.detail.gallery.jpg'),\n" +
                " (29,'true','true','true','true','false','false','true','true','true','true','true','true','true','true','true','true','true','true','true','true','true','false','true','true','true','true','true','true','a kilo of candies','they are sweet, tasty, never is enough of them','https://optomamarf.ru/files/8b9/8b91783007b542dccbce2de211e5f7bd.jpg'),\n" +
                " (30,'true','true','true','true','false','true','true','true','true','true','true','false','true','true','true','true','true','true','true','true','true','false','true','true','true','true','true','true','tickets for the concert','jump, sing, relax','https://d3c1jucybpy4ua.cloudfront.net/data/49674/big_picture/concert-tickets.jpg?1476965087');")
        return myDatabase

    }


}
