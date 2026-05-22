########################################
# Kotlin Serialization
########################################

-keep class kotlinx.serialization.** { *; }

-keepclassmembers class ** {
    @kotlinx.serialization.Serializable *;
}

-keepclassmembers class * {
    *** Companion;
}

-keepclassmembers class **$$serializer {
    *;
}

########################################
# Ktorfit
########################################

-keep class de.jensklingenberg.ktorfit.** { *; }

########################################
# Ktor
########################################

-keep class io.ktor.** { *; }

########################################
# Koin
########################################

-keep class org.koin.** { *; }

########################################
# Room
########################################

-keep class androidx.room.** { *; }

########################################
# Compose
########################################

-keep class androidx.compose.** { *; }

########################################
# Coroutines
########################################

-keep class kotlinx.coroutines.** { *; }

########################################
# Enums
########################################

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}