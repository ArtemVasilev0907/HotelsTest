package com.skydivers.hotelstest.booking.model

private const val EMPTY = ""

private val X = arrayOf(
    EMPTY, "Первый ", "Второй ", "Третий ", "Четвертый ", "Пятый ", "Шестой ",
    "Седьмой ", "Восьмой ", "Девятый ", "Десятый ", "Одиннадцатый ", "Двенадцатый ",
    "Тринадцатый ", "Четырнадцатый ", "Пятнатцатый ", "Шестнадцатый ",
    "Семнадцатый ", "Восемнадцатый ", "Девятнадцатый "
)

private val Y = arrayOf(
    EMPTY, EMPTY, "Двадцать ", "Тридцать ", "Сорок ", "Пятьтесят ",
    "Шестьдесят ", "Семдесят ", "Восемдесят ", "Девяносто "
)


private fun convertToDigit(n: Long, suffix: String): String? {

    if (n == 0L) {
        return EMPTY
    }


    return if (n > 19) {
        Y[(n / 10).toInt()] + X[(n % 10).toInt()] + suffix
    } else {
        X[n.toInt()] + suffix
    }
}

internal fun convertToText(n: Long): String {

    val res = StringBuilder()

    // добавляем цифры на разряде десять миллионов и сто миллионов
    res.append(convertToDigit(n / 1000000000 % 100, "Billion, "))

    // добавляем цифры на разряде десять миллионов и сто миллионов
    res.append(convertToDigit(n / 10000000 % 100, "Crore, "))

    // добавляем цифры на разряде сто тысяч и один миллион
    res.append(convertToDigit(n / 100000 % 100, "Lakh, "))

    // добавляем цифры на разряде тысяч и десятков тысяч
    res.append(convertToDigit(n / 1000 % 100, "Thousand, "))

    // добавляем цифру на сотый разряд
    res.append(convertToDigit(n / 100 % 10, "Hundred "))
    if (n > 100 && n % 100 != 0L) {
        res.append("and ")
    }

    // добавляем цифры в разряде единиц и десятков
    res.append(convertToDigit(n % 100, ""))
    val result = res.toString().trim { it <= ' ' }
        .replace(", and", " and")
        .replace("^(.*),$".toRegex(), "$1")
    return result
}
