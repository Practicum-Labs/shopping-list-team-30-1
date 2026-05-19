package io.dimasla4ee.shoppinglist.app.data.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import io.dimasla4ee.shoppinglist.core.data.datastore.DATA_STORE_FILE_NAME
import io.dimasla4ee.shoppinglist.core.data.datastore.createDataStore
import java.io.File

fun createDataStore(dataDirPath: String): DataStore<Preferences> {
    return createDataStore {
        File(dataDirPath, DATA_STORE_FILE_NAME).absolutePath
    }
}