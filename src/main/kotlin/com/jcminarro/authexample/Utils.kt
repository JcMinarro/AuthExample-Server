package com.jcminarro.authexample

import java.security.MessageDigest
import java.util.*

fun generateRandomString(size: Int) : String {
    val characters = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val random = Random()
    return (1..size).map { characters[random.nextInt(characters.length)] }.joinToString(separator = "")
}

fun sha256(text: String) = String(encodeHex(sha256Bytes(text.toByteArray())))

fun sha256Bytes(data: ByteArray) = MessageDigest.getInstance("SHA-256").digest(data)

fun encodeHex(data: ByteArray): CharArray {
    val toDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
    val l = data.size
    val out = CharArray(l shl 1)
    var i = 0
    var j = 0
    while (i < l) {
        out[j++] = toDigits[(240 and data[i].toInt()).ushr(4)]
        out[j++] = toDigits[15 and data[i].toInt()]
        i++
    }
    return out
}