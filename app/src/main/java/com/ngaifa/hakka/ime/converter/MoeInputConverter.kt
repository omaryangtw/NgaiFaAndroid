package com.ngaifa.hakka.ime.converter

import android.text.TextUtils
import android.util.Log
import com.ngaifa.hakka.BuildConfig
import com.ngaifa.hakka.parser.parser.Moe
import java.util.regex.Pattern
import com.ngaifa.hakka.parser.parser.Pfs

object MoeInputConverter {
    private val TAG = MoeInputConverter::class.java.simpleName

    private val sMoeWordExtractPattern = Pattern.compile("(?:(b|p|m|f|v|d|t|n|l|z|j|c|q|s|x|g|k|ng|h)?([aueoi+]+(n|ng|m)(?:(ng|m|n|)|([bdg]))?([1-9])?|(b|p|m|f|v|d|t|n|l|z|j|c|q|s|x|g|k|ng|h)-?-?))", Pattern.CASE_INSENSITIVE)

    fun convertMoeNumberRawInputToPfsWords(input: String?): String? {

        if (input == null) {
            return null
        }

        val matcher = sMoeWordExtractPattern.matcher(input)
        val groupCount = matcher.groupCount()
        if (groupCount == 0) {
            Log.w(TAG, "groupCount=0, return. input = $input")
            return input
        }

        val stringBuilder = StringBuilder()
        var isMatcherFound = false
        while (matcher.find()) {
            val foundHakkaWord = matcher.group()

            val moeNumber = convertMoeRawInputToMoeNumber(foundHakkaWord)
            val moe = convertMoeNumberToMoe(moeNumber)
            if (BuildConfig.DEBUG_LOG) {
                Log.d(TAG, "foundHakkaWord=$foundHakkaWord, Pfs=$moe")
            }

            stringBuilder.append(moe)
            stringBuilder.append(" ")

            isMatcherFound = true
        }
        if (isMatcherFound) {
            stringBuilder.deleteCharAt(stringBuilder.length - 1)
        }

        return stringBuilder.toString()
    }

    private fun convertMoeRawInputToMoeNumber(foundHakkaWord: String): String {
        var moeNumber = foundHakkaWord
/*
        var pfsNumber = foundHakkaWord.replace("OO", "O͘")
                .replace("Oo", "O͘")
                .replace("oo", "o͘")

        if (pfsNumber.indexOf("nn") > 0) {
            pfsNumber = pfsNumber.replace("nn", "ⁿ")
        } else if (pfsNumber.indexOf("NN") > 0) {
            pfsNumber = pfsNumber.replace("NN", "ⁿ")
        }
*/
        return moeNumber
    }

    private fun convertMoeNumberToMoe(moeNumber: String): String {
        val fixedMoeNumber = ConverterUtils.fixLomajiNumber(moeNumber)

        if (fixedMoeNumber.length <= 1) {
            return fixedMoeNumber
        }

        if (BuildConfig.DEBUG_LOG) {
            Log.d(TAG, "fixedpfsNumber=$fixedMoeNumber")
        }

        var number = ""

        val lastCharString = fixedMoeNumber.substring(fixedMoeNumber.length - 1)
        if (BuildConfig.DEBUG_LOG) {
            Log.d(TAG, "lastCharString=$lastCharString")
        }
        if (TextUtils.isDigitsOnly(lastCharString)) {
            number = lastCharString
        }

        if (TextUtils.isEmpty(number)) {
            return moeNumber
        }

        val moeWithoutNumber = fixedMoeNumber.substring(0, fixedMoeNumber.length - 1)

        val str1 = moeWithoutNumber
        val str2 = Moe.sMoeNumberToMoeUnicodeHashMap[number]
        return str1+str2
/*
        val PfsSianntiauPosition: PfsSianntiauPosition? = getPfsSianntiauPosition(PfsWithoutNumber)
        if (PfsSianntiauPosition == null) {
            return PfsWithoutNumber
        } else {
            val str1 = PfsWithoutNumber.substring(0, PfsSianntiauPosition.pos)

            val str2PfsBosianntiau = PfsWithoutNumber.substring(PfsSianntiauPosition.pos, PfsSianntiauPosition.pos + PfsSianntiauPosition.length)
            val str2pfsNumber = str2PfsBosianntiau + number
            val str2 = Pfs.sPfsNumberToPfsUnicodeHashMap[str2pfsNumber]
            if (str2.isNullOrEmpty()) {
                println("Pfs.spfsNumberToPfsUnicodeHashMap not found: $moeNumber, $str2pfsNumber")
                throw PfsUnicodeNotFoundException("Pfs.spfsNumberToPfsUnicodeHashMap[$str2pfsNumber] not found")
            }

            val str3 = PfsWithoutNumber.substring(PfsSianntiauPosition.pos + PfsSianntiauPosition.length, PfsWithoutNumber.length)

            val Pfs = str1 + str2 + str3

            if (BuildConfig.DEBUG_LOG) {
                Log.d(TAG, "PfsWithoutNumber=$PfsWithoutNumber, number=$number, tonePosition=${PfsSianntiauPosition.pos}, Pfs=$Pfs")
            }

            return Pfs
*/
//        }

//        val Pfs = generatePfsInput(PfsWithoutNumber, number, PfsSianntiauPosition)
    }

    fun getPfsSianntiauPosition(PfsBoSianntiau: String): PfsSianntiauPosition? {
        val str = PfsBoSianntiau.toLowerCase()
        val vowelList = listOf("a", "i", "u", "ṳ", "e", "o")
        val semivowelList = listOf("m", "ng", "n")
        val choankhiunnVowelList = listOf("ir", "er")

        val lastIndexOfAnyVowel = str.lastIndexOfAny(vowelList)

        if (lastIndexOfAnyVowel == -1) {
            // Found no vowel

            val lastIndexOfAnySemiVowel = str.lastIndexOfAny(semivowelList)
            if (lastIndexOfAnySemiVowel == -1) {
                // Found no vowel, nor semivowel. Abort tone marking.
                return null
            } else {
                // Found no vowel, but found semivowel. Tone marks at semivowel.
                if (str.contains("ng")) {
                    return PfsSianntiauPosition(lastIndexOfAnySemiVowel, 2)
                } else {
                    return PfsSianntiauPosition(lastIndexOfAnySemiVowel, 1)
                }
            }
        } else {
            // Found a vowel

            // handle ir/er
            if (str.contains("(ir|er)".toRegex())) {
//                println("ir/er PfsBoSianntiau: $PfsBoSianntiau")

                val lastIndexOfAnyChoankhiunnVowel = str.lastIndexOfAny(choankhiunnVowelList)
                return PfsSianntiauPosition(lastIndexOfAnyChoankhiunnVowel, 1)
            }

            val vowelCount: Int = getVowelCount(str, lastIndexOfAnyVowel)

            if (vowelCount == 1) {
                return PfsSianntiauPosition(lastIndexOfAnyVowel, 1)
            } else {
                // Found HokBoim

                val last2ndJiboPosition = findJiboPositionFromLastCharExludingPhinnim(str, 2)
                val last2ndJiboString = str.substring(last2ndJiboPosition, last2ndJiboPosition + 1)

                val isPfsJipsiann = isPfsJipsiann(str)
                if (isPfsJipsiann) {
                    // Found HokBoim Jipsiann

                    // Handle special cases:
                    if (str.toLowerCase().contains("iuh")) {
                        // "iuh" found
                        val findJiboPositionFromLastCharExludingPhinnim = findJiboPositionFromLastCharExludingPhinnim(str, 2)
                        return PfsSianntiauPosition(findJiboPositionFromLastCharExludingPhinnim, 1)
                    } else {
                        if (last2ndJiboString.toLowerCase().matches("[iu]".toRegex())) {
                            val findJiboPositionFromLastCharExludingPhinnim = findJiboPositionFromLastCharExludingPhinnim(str, 3)
                            return PfsSianntiauPosition(findJiboPositionFromLastCharExludingPhinnim, 1)
                        } else {
                            return PfsSianntiauPosition(last2ndJiboPosition, 1)
                        }
                    }
                } else {
                    // Found HokBoim Not Jipsiann

                    // Handle special cases:
                    if (last2ndJiboString.toLowerCase() == "i") {
                        // Tone marks at the last jibo. (excluding phinnim)
                        val findJiboPositionFromLastCharExludingPhinnim = findJiboPositionFromLastCharExludingPhinnim(str, 1)
                        return PfsSianntiauPosition(findJiboPositionFromLastCharExludingPhinnim, 1)
                    }

                    // Tone marks at the last 2nd jibo. (excluding phinnim)
                    val findJiboPositionFromLastCharExludingPhinnim = findJiboPositionFromLastCharExludingPhinnim(str, 2)
                    return PfsSianntiauPosition(findJiboPositionFromLastCharExludingPhinnim, 1)
                }
            }
        }
    }

    private fun isPfsJipsiann(PfsBoSianntiau: String): Boolean {
        val lastCharExcludingPhinnim = PfsBoSianntiau.replace("ⁿ", "").substring(PfsBoSianntiau.length - 1)
        return lastCharExcludingPhinnim.toLowerCase().matches("[ptk]".toRegex())
    }

    private fun findJiboPositionFromLastCharExludingPhinnim(PfsBoSianntiau: String, findWhichCharFromRight: Int): Int {
        var pos: Int = PfsBoSianntiau.length - 1
        var foundJiboCount = 0

        while (pos >= 0) {
            val currentCharString = PfsBoSianntiau.substring(pos, pos + 1)
            var isFoundPfsOoPoint = false
            var isFoundPfsNg = false

            if (currentCharString == "ⁿ") {
                // skip
            } else if (currentCharString == "\u0358") {
                // found "o͘  "'s "͘  "
                isFoundPfsOoPoint = true
            } else if (currentCharString.toLowerCase() == "g" && pos - 1 >= 0) {
                val nextCharString = PfsBoSianntiau.substring(pos - 1, pos)
                if (nextCharString.toLowerCase() == "n") {
                    // found "ng"
                    isFoundPfsNg = true
                } else {
                    // found "g"
                }
                foundJiboCount++
            } else {
                foundJiboCount++
            }

            if (foundJiboCount == findWhichCharFromRight) {
                break
            }

            if (isFoundPfsNg) {
                pos -= 2
            } else if (isFoundPfsOoPoint) {
                pos--
            } else {
                pos--
            }

            if (pos < 0) {
                break
            }
        }

        return pos
    }

    private fun getVowelCount(PfsBoSianntiau: String, lastIndexOfAnyVowel: Int): Int {
        if (lastIndexOfAnyVowel == 0) {
            return 1
        }

        val isLeftCharAlsoVowel = PfsBoSianntiau.substring(lastIndexOfAnyVowel - 1, lastIndexOfAnyVowel).contains("[aiueo]".toRegex())
        if (!isLeftCharAlsoVowel) {
            return 1
        }

        return 2

//        if (lastIndexOfAnyVowel - 2 <= 0) {
//            return 2
//        }
//
//        val isLeft2CharAlsoVowel = PfsBoSianntiau.substring(lastIndexOfAnyVowel - 2, lastIndexOfAnyVowel - 1).contains("a|i|u|e|o".toRegex())
//        if (!isLeft2CharAlsoVowel) {
//            return 2
//        }
//
//        return 3
    }

//    private fun calculateTonePosition(PfsWithoutNumber: String): Int {
//        var PfsWithoutNumber = PfsWithoutNumber
//        PfsWithoutNumber = PfsWithoutNumber.toLowerCase()
//        val count = PfsWithoutNumber.length
//
//        if (BuildConfig.DEBUG_LOG) {
//            Log.d(TAG, "calculateTonePosition: PfsWithoutNumber = $PfsWithoutNumber, count = $count")
//        }
//
//        val lastIndexOfVowel = lastIndexOfRegex(PfsWithoutNumber, "a|i|u|e|o")
//        if (lastIndexOfVowel == -1) {
//            val lastIndexOfHalfVowel = lastIndexOfRegex(PfsWithoutNumber, "m|ng|n")
//            return if (lastIndexOfHalfVowel == -1) {
//                -1
//            } else {
//                lastIndexOfHalfVowel
//            }
//        } else {
//            if (count == 1) {
//                return 0
//            } else {
//                if (lastIndexOfVowel == 0) {
//                    return 0
//                } else {
//                    if (PfsWithoutNumber.contains("oai") && PfsWithoutNumber.endsWith("h")) {
//                        return PfsWithoutNumber.indexOf("o")
//                    }
//
//                    val previousChar = PfsWithoutNumber.substring(lastIndexOfVowel - 1, lastIndexOfVowel)
//                    // if vowel count >= 2
//                    if (previousChar.matches("a|i|u|e|o".toRegex())) {
//                        val lastVowelChar = PfsWithoutNumber.substring(lastIndexOfVowel, lastIndexOfVowel + 1)
//                        return if (lastVowelChar == "i" && lastIndexOfVowel == count - 2) {
//                            lastIndexOfVowel - 1
//                        } else {
//                            // if vowel is the last char
//                            if (lastIndexOfVowel == count - 1) {
//                                if (previousChar == "i") {
//                                    lastIndexOfVowel
//                                } else {
//                                    lastIndexOfVowel - 1
//                                }
//                            } else {
//                                lastIndexOfVowel
//                            }
//                        }
//                    } else {
//                        return lastIndexOfVowel
//                    }
//                }
//            }
//        }
//    }

    /**
     * Version of lastIndexOf that uses regular expressions for searching.
     * By Tomer Godinger.
     *
     * @param str    String in which to search for the pattern.
     * @param toFind Pattern to locate.
     * @return The index of the requested pattern, if found; NOT_FOUND (-1) otherwise.
     */
    fun lastIndexOfRegex(str: String, toFind: String): Int {
        val pattern = Pattern.compile(toFind)
        val matcher = pattern.matcher(str)

        // Default to the NOT_FOUND constant
        var lastIndex = -1

        // Search for the given pattern
        while (matcher.find()) {
            lastIndex = matcher.start()
        }

        return lastIndex
    }

    //    private static int calculateTonePositionWithPfsToneStatistics(String PfsWithoutNumber) {
    //        resetTempArray();
    //
    //        int count = PfsWithoutNumber.length();
    //        for (int i = 0; i < count; i++) {
    //            final char c = PfsWithoutNumber.charAt(i);
    //
    //            switch (c) {
    //                case 'o':
    //                case 'O':
    //                    sLomajiNumberToWordTempArray[0] = i + 1;
    //                    break;
    //                case 'a':
    //                case 'A':
    //                    sLomajiNumberToWordTempArray[1] = i + 1;
    //                    break;
    //                case 'e':
    //                case 'E':
    //                    sLomajiNumberToWordTempArray[2] = i + 1;
    //                    break;
    //                case 'u':
    //                case 'U':
    //                    sLomajiNumberToWordTempArray[3] = i + 1;
    //                    break;
    //                case 'i':
    //                case 'I':
    //                    sLomajiNumberToWordTempArray[4] = i + 1;
    //                    break;
    //                case 'n':
    //                case 'N':
    //                    sLomajiNumberToWordTempArray[5] = i + 1;
    //                    break;
    //                case 'm':
    //                case 'M':
    //                    sLomajiNumberToWordTempArray[6] = i + 1;
    //                    break;
    //            }
    //        }
    //        int foundToneCharPosition = -1;
    //        for (final int pos : sLomajiNumberToWordTempArray) {
    //            if (pos > 0) {
    //                foundToneCharPosition = pos - 1;
    //                break;
    //            }
    //        }
    //
    //        return foundToneCharPosition;
    //    }

//    private fun generatePfsInput(PfsWithoutNumber: String, number: String, PfsSianntiauPosition: PfsSianntiauPosition): String {
//        val stringBuilder = StringBuilder()
//
//        val length = PfsWithoutNumber.length
//        for (i in 0 until length) {
//            val currentCharString = PfsWithoutNumber.substring(i, i + 1)
//
//            if (i == tonePosition) {
//                val pfsNumber = currentCharString + number
//                val Pfs = Pfs.spfsNumberToPfsUnicodeHashMap[pfsNumber]
//                if (Pfs != null) {
//                    stringBuilder.append(Pfs)
//                } else {
//                    stringBuilder.append(currentCharString)
//                }
//            } else {
//                stringBuilder.append(currentCharString)
//            }
//        }
//
//        return stringBuilder.toString()
//    }
}
