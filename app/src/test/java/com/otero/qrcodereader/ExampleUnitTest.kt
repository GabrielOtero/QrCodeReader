package com.otero.qrcodereader

import com.otero.qrcodereader.extensions.toTimePtBr
import com.otero.qrcodereader.model.QrCodeInfoModel
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        val qrCodeModels = listOf(
            QrCodeInfoModel(value = "asd1;123312;12313", timeStamp = System.currentTimeMillis()),
            QrCodeInfoModel(
                value = "blablbal;123213;213123",
                timeStamp = System.currentTimeMillis()
            ),
            QrCodeInfoModel(
                value = "qweretyreu;2111111;1111",
                timeStamp = System.currentTimeMillis()
            ),
            QrCodeInfoModel(value = "9897998;hasjhda;12344", timeStamp = System.currentTimeMillis())
        )
        val content =
            qrCodeModels.joinToString("\n") { it.timeStamp.toTimePtBr() + ";" + it.value }

        print(content)
    }
}