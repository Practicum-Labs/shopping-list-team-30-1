package io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter

expect class PasswordEstimator() {
    fun estimate(password: String): Int
}