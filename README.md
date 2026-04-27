# О проекте

Это
проект [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
под платформы Android, Desktop (JVM).

Директории в проекте:

- [/composeApp](./composeApp/src) для кода, который будет общим для всех приложений Compose
  Multiplatform.
  Эта директория содержит несколько поддиректорий:
    - [commonMain](./composeApp/src/commonMain/kotlin) для кода, который общий для всех платформ.
    - Остальные директории хранят код, который компилируется только под определённые платформы.
      Например, если есть необходимость использовать Retrofit для платформы Android, то такой
      код должен храниться внутри директории [androidMain](./composeApp/src/androidMain/kotlin).
      Таким же образом, если есть необходимость написать код, специфичный для десктоп, необходимо
      использовать директорию [jvmMain](./composeApp/src/jvmMain/kotlin).

## Сборка и запуск Android приложения

Чтобы собрать и запустить дебаг-версию Android приложения, можно использовать конфигурацию запуска в
IDE или сделать это через терминал:

- macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

## Сборка и запуск Desktop (JVM) приложения

Чтобы собрать и запустить дебаг-версию десктоп приложения, можно использовать конфигурацию запуска в
IDE или сделать это через терминал:

- macOS/Linux
  ```shell
  ./gradlew :composeApp:run
  ```
- Windows
  ```shell
  .\gradlew.bat :composeApp:run
  ```

## Выбранный стек технологий

| Компонент     | Библиотека                                                                          | Аргументация                                                                                                                                                                                                                   |
|---------------|-------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| UI            | [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform)         | - Инструментарий для разработки UI на едином языке программирования Kotlin.<br>- Дешевизна разработки под разные платформы (Android, iOS, Desktop, Web) засчёт переиспользования кода.<br>- Декларативный подход к разработке. |
| DB            | [Room](https://developer.android.com/kotlin/multiplatform/room)                     | - Использование Kotlin аннотаций для генерации кода.<br>- Проверка SQL-запросов на этапе компиляции.<br>- Рекомендуемый подход от Google.                                                                                      |
| DI            | [Koin](https://github.com/InsertKoinIO/koin)                                        | - Мультиплатформенная библиотека.<br>- Выявление ошибок на этапе компиляции.<br>- Простой DSL.                                                                                                                                 |
| Network       | [Ktorfit](https://github.com/Foso/Ktorfit)                                          | - Основана на мультиплатформенной библиотеке [Ktor](https://github.com/ktorio/ktor).<br>- Использование Kotlin аннотаций для генерации кода как в [Retrofit](https://github.com/square/retrofit).                              |
| Navigation    | [Navigation 3](https://kotlinlang.org/docs/multiplatform/compose-navigation-3.html) | - Мультиплатформенная библиотека.<br>- Навигация основана на управлении стеком экранов.<br>- Не полагается на использование строковых констант.                                                                                |
| Serialization | [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization)            | - Нативное решение от JetBrains.<br>- Наличие пользовательских плагинов для поддержки большего количества форматов.<br>- Использование Kotlin аннотаций для генерации кода.                                                    |

## Выбранная архитектура

| Выбранная архитектура    |
|--------------------------|
| Clean Architecture + MVI |
