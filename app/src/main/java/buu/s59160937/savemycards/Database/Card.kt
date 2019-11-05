package buu.s59160937.savemycards.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "card_table")
data class Card (
    @PrimaryKey(autoGenerate = true)
    var cardId: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String = "No card name",

    @ColumnInfo(name = "number")
    var number: String = "9999-9999-9999-9999",

    @ColumnInfo(name = "expire")
    var expire: String = "99/99",

    @ColumnInfo(name = "CVV")
    var CVV:  String = "999"
)