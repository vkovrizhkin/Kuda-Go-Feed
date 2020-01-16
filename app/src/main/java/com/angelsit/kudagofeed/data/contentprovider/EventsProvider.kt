package com.angelsit.kudagofeed.data.contentprovider

import android.content.*
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri


class EventsProvider : ContentProvider() {

    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        val rowId = db!!.insert(TABLE_NAME, "", values)

        if (rowId > 0) {
            val _uri = ContentUris.withAppendedId(CONTENT_URI, rowId)
            context?.contentResolver?.notifyChange(_uri, null)
            return _uri
        } else throw SQLiteException("Failed to a record into $uri")
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
        db = dbHelper.writableDatabase
        return (db != null)
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        var count = 0
        when (uriMatcher.match(uri)) {
            uriCode ->
                count = db!!.update(TABLE_NAME, values, selection, selectionArgs)
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var count = 0
        when (uriMatcher.match(uri)) {
            uriCode -> count = db!!.delete(TABLE_NAME, selection, selectionArgs)
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun getType(uri: Uri): String? {
        when (uriMatcher.match(uri)) {
            uriCode -> return "vnd.android.cursor.dir/events"
            else -> throw IllegalArgumentException("Unsupported URI $uri")
        }
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
        private const val PROVIDER_NAME =
            "com.angelsit.kudagofeed.data.contentprovider.EventsProvider"
        private const val URL = "content://$PROVIDER_NAME/events"
        val CONTENT_URI = Uri.parse(URL)

        val id = "id"
        val title = "title"
        val description = "description"
        val avatar = "avatar"
        val uriCode = 1

        @JvmStatic
        private var db: SQLiteDatabase? = null

        const val DATABASE_NAME = "EventsDB"
        const val TABLE_NAME = "Events"
        const val DATABASE_VERSION = 1
        const val CREATE_DB_TABLE = (" CREATE TABLE " + TABLE_NAME
                + " (id INTEGER PRIMARY KEY, "
                + " title TEXT," +
                "description TEXT," +
                "avatar TEXT);")

        private val values: HashMap<String, String>? = null
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(PROVIDER_NAME, "events", uriCode)
            addURI(PROVIDER_NAME, "events/*", uriCode)
        }
    }

}