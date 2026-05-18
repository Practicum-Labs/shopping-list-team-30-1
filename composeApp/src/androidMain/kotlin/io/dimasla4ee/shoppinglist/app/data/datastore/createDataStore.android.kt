package io.dimasla4ee.shoppinglist.app.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import io.dimasla4ee.shoppinglist.core.data.datastore.DATA_STORE_FILE_NAME
import io.dimasla4ee.shoppinglist.core.data.datastore.createDataStore

fun createDataStore(context: Context): DataStore<Preferences> {
    return createDataStore {
        context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
}
