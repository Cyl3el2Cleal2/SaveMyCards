package buu.s59160937.savemycards.Database
/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CardDatabaseDao {

    @Insert
    fun insert(card: Card)

    @Update
    fun update(card: Card)

    @Query("SELECT * from card_table WHERE cardId = :key")
    fun get(key: Long): Card?

    @Query("DELETE FROM card_table")
    fun clear()

    @Query("SELECT * FROM card_table ORDER BY cardId DESC LIMIT 1")
    fun getTocard(): Card?

    @Query("SELECT * FROM card_table ORDER BY cardId DESC")
    fun getAllcards(): LiveData<List<Card>>
}