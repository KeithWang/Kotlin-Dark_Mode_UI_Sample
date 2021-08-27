package vic.sample.darkmodeuisample.adapter

import androidx.annotation.Keep
import java.io.Serializable


@Keep
data class ListItem(
    val coinValue: Int = 0,
    val progressPercent: Int = 0,
    val rewords: Int = 0,
    val sell: Int = 0,
    var isBadgeShow: Boolean = false
) : Serializable
