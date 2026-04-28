package io.dimasla4ee.shoppinglist

// TestDetekt.kt
// Данный файл предназначен для тестирования работы detekt.
// Он содержит намеренные нарушения правил: длинный класс, длинный метод с длинным списком параметров,
// а также стандартные Compose-функции с превью.

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// ================================
// 1. Класс с телом 350+ строк
// ================================
class LargeClass {

    // Свойства для увеличения строк
    var prop1: Int = 0
    var prop2: String = ""
    var prop3: Boolean = false
    var prop4: Double = 0.0
    var prop5: Float = 0f
    var prop6: Long = 0L
    var prop7: Char = 'a'
    var prop8: Byte = 0
    var prop9: Short = 0
    var prop10: Any? = null

    fun fun1() {
        println("fun1")
    }

    fun fun2() {
        println("fun2")
    }

    fun fun3() {
        println("fun3")
    }

    fun fun4() {
        println("fun4")
    }

    fun fun5() {
        println("fun5")
    }

    fun fun6() {
        println("fun6")
    }

    fun fun7() {
        println("fun7")
    }

    fun fun8() {
        println("fun8")
    }

    fun fun9() {
        println("fun9")
    }

    fun fun10() {
        println("fun10")
    }

    // Блок инициализации
    init {
        prop1 = 42
        prop2 = "Hello"
        prop3 = true
        prop4 = 3.14
        prop5 = 2.71f
        prop6 = 100000L
        prop7 = 'Z'
        prop8 = 127
        prop9 = 32767
        prop10 = listOf(1, 2, 3)
    }

    // Внутренний класс
    inner class InnerClassA {
        fun innerFun() {
            println("InnerA")
        }
    }

    inner class InnerClassB {
        fun innerFun() {
            println("InnerB")
        }
    }

    inner class InnerClassC {
        fun innerFun() {
            println("InnerC")
        }
    }

    // Вложенный класс
    class NestedClass {
        fun nestedFun() {
            println("Nested")
        }
    }

    // Объект-компаньон с методами
    companion object {
        const val CONSTANT = "CONST"
        fun helper1() = 1
        fun helper2() = 2
        fun helper3() = 3
        fun helper4() = 4
        fun helper5() = 5
        fun helper6() = 6
        fun helper7() = 7
        fun helper8() = 8
        fun helper9() = 9
        fun helper10() = 10
    }

    // Дополнительные методы для набора строк (до 350+)
    fun methodA() {
        println("A")
    }

    fun methodB() {
        println("B")
    }

    fun methodC() {
        println("C")
    }

    fun methodD() {
        println("D")
    }

    fun methodE() {
        println("E")
    }

    fun methodF() {
        println("F")
    }

    fun methodG() {
        println("G")
    }

    fun methodH() {
        println("H")
    }

    fun methodI() {
        println("I")
    }

    fun methodJ() {
        println("J")
    }

    fun methodK() {
        println("K")
    }

    fun methodL() {
        println("L")
    }

    fun methodM() {
        println("M")
    }

    fun methodN() {
        println("N")
    }

    fun methodO() {
        println("O")
    }

    fun methodP() {
        println("P")
    }

    fun methodQ() {
        println("Q")
    }

    fun methodR() {
        println("R")
    }

    fun methodS() {
        println("S")
    }

    fun methodT() {
        println("T")
    }

    fun methodU() {
        println("U")
    }

    fun methodV() {
        println("V")
    }

    fun methodW() {
        println("W")
    }

    fun methodX() {
        println("X")
    }

    fun methodY() {
        println("Y")
    }

    fun methodZ() {
        println("Z")
    }

    // Ещё пачка методов
    fun extra1() {
        println(1)
    }

    fun extra2() {
        println(2)
    }

    fun extra3() {
        println(3)
    }

    fun extra4() {
        println(4)
    }

    fun extra5() {
        println(5)
    }

    fun extra6() {
        println(6)
    }

    fun extra7() {
        println(7)
    }

    fun extra8() {
        println(8)
    }

    fun extra9() {
        println(9)
    }

    fun extra10() {
        println(10)
    }

    fun extra11() {
        println(11)
    }

    fun extra12() {
        println(12)
    }

    fun extra13() {
        println(13)
    }

    fun extra14() {
        println(14)
    }

    fun extra15() {
        println(15)
    }

    fun extra16() {
        println(16)
    }

    fun extra17() {
        println(17)
    }

    fun extra18() {
        println(18)
    }

    fun extra19() {
        println(19)
    }

    fun extra20() {
        println(20)
    }

    fun extra21() {
        println(21)
    }

    fun extra22() {
        println(22)
    }

    fun extra23() {
        println(23)
    }

    fun extra24() {
        println(24)
    }

    fun extra25() {
        println(25)
    }

    fun extra26() {
        println(26)
    }

    fun extra27() {
        println(27)
    }

    fun extra28() {
        println(28)
    }

    fun extra29() {
        println(29)
    }

    fun extra30() {
        println(30)
    }
}

// ================================
// 2. Некомпозабл функция с >6 параметров и телом >50 строк
// ================================
fun longFunctionWithManyParams(
    p1: Int,
    p2: String,
    p3: Boolean,
    p4: Double,
    p5: Float,
    p6: Long,
    p7: Char,
    p8: Byte,
    p9: Short
): String {
    // Тело длиннее 50 строк (считаем строки кода)
    var result = ""
    result += "p1=$p1, "
    result += "p2=$p2, "
    result += "p3=$p3, "
    result += "p4=$p4, "
    result += "p5=$p5, "
    result += "p6=$p6, "
    result += "p7=$p7, "
    result += "p8=$p8, "
    result += "p9=$p9"

    // Циклы и условия для увеличения количества строк
    for (i in 1..10) {
        result += " $i"
    }
    when (p3) {
        true -> result += " true branch"
        false -> result += " false branch"
    }
    val list = listOf(1, 2, 3, 4, 5)
    for (item in list) {
        result += " item=$item"
    }
    repeat(5) { idx ->
        result += " idx=$idx"
    }
    if (p1 > 0) {
        result += " positive"
    } else if (p1 == 0) {
        result += " zero"
    } else {
        result += " negative"
    }
    try {
        val parsed = p2.toIntOrNull()
        result += " parsed=$parsed"
    } catch (e: Exception) {
        result += " error"
    }
    // Добавим ещё блоки строк
    val a = p4 + p5
    val b = p6 - p1
    val c = p8 * p9
    result += " a=$a, b=$b, c=$c"
    for (j in 100 downTo 90) {
        result += " j=$j"
    }
    when (p7) {
        'A' -> result += " A"
        'B' -> result += " B"
        else -> result += " other"
    }
    listOf(10, 20, 30).forEach { num ->
        result += " num=$num"
    }
    var counter = 0
    while (counter < 3) {
        result += " counter=$counter"
        counter++
    }
    // Добавим финальную обработку
    result = result.trim()
    return result.ifEmpty { "Empty result" }
}

// ================================
// 3. Композабл функция и превью
// ================================
@Composable
fun MyComposableFunction(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun PreviewMyComposableFunction() {
    MyComposableFunction(text = "Hello, Detekt!")
}