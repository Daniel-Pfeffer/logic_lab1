import kotlin.math.sqrt

fun main() {
    val start = 0
    val end = 4
    var x = numberConstraint(start, end) + "&" + rowConstraint(start, end) + "&" + columnConstraint(
        start,
        end
    ) + "&" + duplicateConstraint(start, end) + "&" + subgridConstraint(start, end)
    print(x)
}

fun numberConstraint(start: Int, end: Int): String {
    var o = ""
    for (r in start until end) {
        for (c in start until end) {
            var inner = ""
            for (n in start until end) {
                inner += c(r, c, n + 1) + "|"
            }
            inner = inner.substring(0, inner.length - 1)
            o += "($inner)"
            o += "&"
        }
        o = o.substring(0, o.length - 1)
        o += "&"
    }
    return "(${o.substring(0, o.length - 1)})"
}

fun rowConstraint(start: Int, end: Int): String {
    var o = ""
    for (r in start until end) {
        for (n in start until end) {
            var inner = ""
            for (c in start until end) {
                inner += c(r, c, n + 1) + "|"
            }
            inner = inner.substring(0, inner.length - 1)
            o += "($inner)"
            o += "&"
        }
        o = o.substring(0, o.length - 1)
        o += "&"
    }
    return "(${o.substring(0, o.length - 1)})"
}

fun columnConstraint(start: Int, end: Int): String {
    var o = ""
    for (c in start until end) {
        for (n in start until end) {
            var inner = ""
            for (r in start until end) {
                inner += c(r, c, n + 1) + "|"
            }
            inner = inner.substring(0, inner.length - 1)
            o += "($inner)"
            o += "&"
        }
        o = o.substring(0, o.length - 1)
        o += "&"
    }
    return "(${o.substring(0, o.length - 1)})"
}

fun duplicateConstraint(start: Int, end: Int): String {
    var o = ""
    for (r in start until end) {
        for (c in start until end) {
            var inner = ""
            for (n in start until end) {
                var inner1 = ""
                for (n1 in n + 1 until end) {
                    inner1 += "(!${c(r, c, n + 1)}|!${c(r, c, n1 + 1)})&"
                }
                if (inner1.length > 0) {
                    inner1 = inner1.substring(0, inner1.length - 1)
                    inner += "($inner1)"
                    inner += "&"
                }

            }
            inner = inner.substring(0, inner.length - 1)
            o += "($inner)"
            o += "&"
        }
        o = o.substring(0, o.length - 1)
        o += "&"
    }
    return "(${o.substring(0, o.length - 1)})"
}

fun subgridConstraint(start: Int, end: Int): String {
    var o = ""
    val subgridEnd = sqrt(end.toDouble()).toInt()
    for (n in start until end) {
        for (outerCnt in 0..subgridEnd step subgridEnd) {
            for (cnt in 0..subgridEnd step subgridEnd) {
                var innerN = ""
                for (r in start + outerCnt until subgridEnd + outerCnt) {
                    var inner = ""
                    for (c in start + cnt until subgridEnd + cnt) {
                        inner += c(r, c, n + 1) + "|"
                    }
                    inner = inner.substring(0, inner.length - 1)
                    innerN += inner
                    innerN += "|"
                }
                innerN = innerN.substring(0, innerN.length - 1)
                o += "($innerN)"
                o += "&"
            }
        }
    }
    return "(${o.substring(0, o.length - 1)})"
}

fun c(c: Int, r: Int, n: Int): String {
    return "${'a' + c}${'a' + r}$n"
}