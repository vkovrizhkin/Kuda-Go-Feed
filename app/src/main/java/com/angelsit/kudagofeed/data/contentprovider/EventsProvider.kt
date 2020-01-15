package com.angelsit.kudagofeed.data.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.content.UriMatcher
import java.lang.IllegalArgumentException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class EventsProvider : ContentProvider() {

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {

        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = TABLE_NAME

        when (uriMatcher.match(uri)) {
            uriCode -> queryBuilder.setProjectionMap(values)
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        val mSortOrder = if (sortOrder.isNullOrEmpty()) id else sortOrder
        return queryBuilder.query(
            db,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            mSortOrder

        )
    }

    override fun onCreate(): Boolean {
        val dbHelper = DatabaseHelper(context!!)
        val db = dbHelper.writableDatabase
        return (db != null)
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri): String? {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private class DatabaseHelper internal constructor(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(CREATE_DB_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }

    companion object {
        const val PROVIDER_NAME = "com.angelsit.kudagofeed.data.contentprovider.EventsProvider"
        const val URL = "content://$PROVIDER_NAME/events"
        val CONTENT_URI = Uri.parse(URL)

        val id = "id"
        val name = "name"
        val uriCode = 1

        private val db: SQLiteDatabase? = null
        val DATABASE_NAME = "EventsDB"
        val TABLE_NAME = "Events"
        val DATABASE_VERSION = 1
        val CREATE_DB_TABLE = (" CREATE TABLE " + TABLE_NAME
                + " (id INTEGER, "
                + " title TEXT," +
                "description TEXT," +
                "avatar TEXT);")

        private val values: HashMap<String, String>? = null
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(PROVIDER_NAME, "users", uriCode)
            addURI(PROVIDER_NAME, "users/*", uriCode)
        }
    }

}