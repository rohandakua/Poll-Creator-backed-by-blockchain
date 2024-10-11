package com.example.pollcreator.additionalFunctions
import android.util.Log
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Keys
import org.web3j.crypto.Sign
import java.math.BigInteger
import java.text.SimpleDateFormat
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
    fun getDateFromTimestamp(timestamp: Long): String {
        val date = Date(timestamp )
        val format = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault()) // Adjust format as needed
        return format.format(date)
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

    fun convertDateTimeToUnix(dateString: String, timeString: String): Long {
        // Combine date and time format
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        // Combine the date and time strings
        val dateTimeString = "$dateString $timeString"
        // Parse the combined string into a Date object
        val date = format.parse(dateTimeString)
        // Return the Unix timestamp (milliseconds since epoch), or 0L if parsing fails
        return date?.time ?: 0L
    }

    fun convertToUnixTimestamp(date: Date): Long {
        // Return the Unix timestamp (seconds since 1970-01-01 00:00:00 UTC)
        return date.time
    }

    fun convertToUnixTimestampIST(date: Date): Long {
        // Return the Unix timestamp (seconds since 1970-01-01 00:00:00 UTC)
        var date1 = convertIstToGmt(date)
        return date1.time
    }
    fun convertDateToUnix(dateString: String): Long {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = format.parse(dateString)
        return date?.time ?: 0L // Returns 0L if parsing fails
    }

    fun getPollId(doubleValue: Double, longValue: Long): Double {
        Log.d("doubleValue",doubleValue.toString()+" "+longValue)
        val longAsDouble = longValue.toString().toDouble() / Math.pow(10.0, longValue.toString().length.toDouble())
        return doubleValue + longAsDouble
    }


    fun main() {
        println(getPollId(1230.0, 123456789))
    }

    fun convertToDate(dateString: String, timeString: String): Date? {
        val combinedString = "$dateString $timeString"
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()) // Adjusted to "dd/MM/yyyy"
        return try {
            dateFormat.parse(combinedString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }




}