package com.example.pollcreator.additionalFunctions
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Keys
import org.web3j.crypto.Sign
import java.math.BigInteger
import java.util.*

class helperFunctions {


    fun isValidPrivateKey(privateKey: String?): Boolean {
        try {
            if (privateKey?.length != 64) {
                return false
            }
            val privateKeyBigInt = BigInteger(privateKey, 16)

            val ecKeyPair = ECKeyPair.create(privateKeyBigInt)

            return true
        } catch (e: Exception) {
            return false
        }
    }




    fun createCustomDate(
        year: Int, month: Int, day: Int,
        hour: Int, minute: Int, second: Int = 0
    ): Date {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"))

        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1) // Month is 0-based in Calendar, so subtract 1
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, second)
        calendar.set(Calendar.MILLISECOND, 0) // Set milliseconds to 0

        return calendar.time // Convert to Date object
    }

    fun convertIstToGmt(istDate: Date): Date {
        // Create a calendar instance in IST time zone
        val istCalendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"))
        istCalendar.time = istDate

        // Create another calendar instance for GMT
        val gmtCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"))

        // Set the time of the GMT calendar to the same time as the IST calendar
        gmtCalendar.set(Calendar.YEAR, istCalendar.get(Calendar.YEAR))
        gmtCalendar.set(Calendar.MONTH, istCalendar.get(Calendar.MONTH))
        gmtCalendar.set(Calendar.DAY_OF_MONTH, istCalendar.get(Calendar.DAY_OF_MONTH))
        gmtCalendar.set(Calendar.HOUR_OF_DAY, istCalendar.get(Calendar.HOUR_OF_DAY))
        gmtCalendar.set(Calendar.MINUTE, istCalendar.get(Calendar.MINUTE))
        gmtCalendar.set(Calendar.SECOND, istCalendar.get(Calendar.SECOND))
        gmtCalendar.set(Calendar.MILLISECOND, istCalendar.get(Calendar.MILLISECOND))

        // Adjust the time difference between IST and GMT (subtract the IST offset)
        val gmtOffsetMillis = istCalendar.timeZone.rawOffset
        gmtCalendar.timeInMillis = gmtCalendar.timeInMillis - gmtOffsetMillis

        // Return the GMT date
        return gmtCalendar.time
    }



    fun convertToUnixTimestamp(date: Date): Long {
        // Return the Unix timestamp (seconds since 1970-01-01 00:00:00 UTC)
        return date.time / 1000
    }

    fun convertToUnixTimestampIST(date: Date): Long {
        // Return the Unix timestamp (seconds since 1970-01-01 00:00:00 UTC)
        var date1 = convertIstToGmt(date)
        return date1.time / 1000
    }


    fun main() {
        // Example usage
        val currentDate = createCustomDate(
            year = 2024,
            month = 8,
            day = 31,
            hour = 13,
            minute = 5
        )
        val unixTimestamp = convertToUnixTimestamp(currentDate)
        println("Unix Timestamp in IST: $unixTimestamp")
    }




}