package io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter

import me.gosimple.nbvcxz.Nbvcxz

actual class PasswordEstimator actual constructor() {
    private val nbvcxz = Nbvcxz()

    actual fun estimate(password: String): Int {
        return nbvcxz.estimate(password).basicScore
    }
}