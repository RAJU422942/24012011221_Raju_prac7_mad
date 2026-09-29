package com.example.a24012011221_raju_prac7_mad

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "persons_db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(PersonDbTableData.CREATE_TABLE)
        insertDefaultData(db)
    }

    fun populateDefaultDataIfEmpty() {
        if (personsCount == 0) {
            val db = writableDatabase
            insertDefaultData(db)
            db.close()
        }
    }

    private fun insertDefaultData(db: SQLiteDatabase) {
        val defaultPersons = listOf(
            Person("1", "Yesenia Yang", "yesenia_yang@gnu.ac.in", "+917983385148", "16 Sackett Street, Courtland, Michigan", 20.22222, 22.017888),
            Person("2", "Penelope Cooper", "penelope_cooper@gnu.ac.in", "+916836390000", "35 Aitken Place, Orovada, Maryland", 16.193406, 72.259952),
            Person("3", "Roberts Gill", "roberts_gill@gnu.ac.in", "+919241276734", "39 Tompkins Place, Bartonsville, Oklahoma", 20.654321, 90.654321),
            Person("4", "Deloris Lawson", "deloris_lawson@gnu.ac.in", "+918379880401", "72 Troutman Street, Mooresburg, South Dakota", 18.987654, 71.987654),
            Person("5", "Conley Hickman", "conley_hickman@gnu.ac.in", "+918258363534", "38 Bath Avenue, Coalmont, Florida", 22.345678, 75.345678),
            Person("6", "Horne Koch", "horne_koch@gnu.ac.in", "+918797996598", "16 Sackett Street, Courtland, Michigan", 40.123456, 71.123456)
        )
        for (person in defaultPersons) {
            val values = getValues(person)
            db.insertWithOnConflict(
                PersonDbTableData.TABLE_NAME,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + PersonDbTableData.TABLE_NAME)
        onCreate(db)
    }

    fun insertPerson(person: Person): Long {
        val db = writableDatabase
        val values = getValues(person)
        val id = db.insertWithOnConflict(
            PersonDbTableData.TABLE_NAME,
            null,
            values,
            SQLiteDatabase.CONFLICT_REPLACE
        )
        db.close()
        return id
    }

    fun insertPerson(name: String, emailId: String, phoneNo: String, address: String): Long {
        val person = Person(
            id = System.currentTimeMillis().toString(),
            name = name,
            emailId = emailId,
            phoneNo = phoneNo,
            address = address,
            latitude = 0.0,
            longitude = 0.0
        )
        return insertPerson(person)
    }

    private fun getValues(person: Person): ContentValues {
        return ContentValues().apply {
            put(PersonDbTableData.COLUMN_ID, person.id)
            put(PersonDbTableData.COLUMN_PERSON_NAME, person.name)
            put(PersonDbTableData.COLUMN_PERSON_EMAIL_ID, person.emailId)
            put(PersonDbTableData.COLUMN_PERSON_PHONE_NO, person.phoneNo)
            put(PersonDbTableData.COLUMN_PERSON_ADDRESS, person.address)
            put(PersonDbTableData.COLUMN_PERSON_GPS_LAT, person.latitude)
            put(PersonDbTableData.COLUMN_PERSON_GPS_LONG, person.longitude)
        }
    }

    fun getPerson(id: String): Person? {
        val db = readableDatabase
        val cursor = db.query(
            PersonDbTableData.TABLE_NAME,
            null,
            "${PersonDbTableData.COLUMN_ID}=?",
            arrayOf(id),
            null, null, null
        )
        var person: Person? = null
        if (cursor.moveToFirst()) {
            person = getPerson(cursor)
        }
        cursor.close()
        return person
    }

    private fun getPerson(cursor: Cursor): Person {
        return Person(
            id = cursor.getString(cursor.getColumnIndexOrThrow(PersonDbTableData.COLUMN_ID)),
            name = cursor.getString(cursor.getColumnIndexOrThrow(PersonDbTableData.COLUMN_PERSON_NAME)),
            emailId = cursor.getString(cursor.getColumnIndexOrThrow(PersonDbTableData.COLUMN_PERSON_EMAIL_ID)),
            phoneNo = cursor.getString(cursor.getColumnIndexOrThrow(PersonDbTableData.COLUMN_PERSON_PHONE_NO)),
            address = cursor.getString(cursor.getColumnIndexOrThrow(PersonDbTableData.COLUMN_PERSON_ADDRESS)),
            latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(PersonDbTableData.COLUMN_PERSON_GPS_LAT)),
            longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(PersonDbTableData.COLUMN_PERSON_GPS_LONG))
        )
    }

    val allPersons: ArrayList<Person>
        get() {
            val personList = ArrayList<Person>()
            val selectQuery = "SELECT * FROM ${PersonDbTableData.TABLE_NAME}"
            val db = readableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    personList.add(getPerson(cursor))
                } while (cursor.moveToNext())
            }
            cursor.close()
            return personList
        }

    fun getAllPersons(): List<Person> {
        return allPersons
    }

    val personsCount: Int
        get() {
            val countQuery = "SELECT * FROM ${PersonDbTableData.TABLE_NAME}"
            val db = readableDatabase
            val cursor = db.rawQuery(countQuery, null)
            val count = cursor.count
            cursor.close()
            return count
        }

    fun updatePerson(person: Person): Int {
        val db = writableDatabase
        val values = getValues(person)
        val rows = db.update(
            PersonDbTableData.TABLE_NAME,
            values,
            "${PersonDbTableData.COLUMN_ID}=?",
            arrayOf(person.id)
        )
        db.close()
        return rows
    }

    fun deletePerson(person: Person): Int {
        val db = writableDatabase
        val rows = db.delete(
            PersonDbTableData.TABLE_NAME,
            "${PersonDbTableData.COLUMN_ID}=?",
            arrayOf(person.id)
        )
        db.close()
        return rows
    }
}
