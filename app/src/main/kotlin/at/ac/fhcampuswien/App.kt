/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package at.ac.fhcampuswien

class App {
    // Game logic for a number guessing game
    fun playNumberGame(digitsToGuess: Int = 4) {
        var input = 0
        val number = generateRandomNonRepeatingNumber(digitsToGuess)
        //println(number)

        while (true){
            input = readln().toInt()
            val res = checkUserInputAgainstGeneratedNumber(input, number)
            println(res.toString())
            if (res.m == digitsToGuess && res.n == digitsToGuess){
                println("Correct guess!")
                return
            }
        }
    }

    /**
     * Generates a non-repeating number of a specified length between 1-9.
     *
     * Note: The function is designed to generate a number where each digit is unique and does not repeat.
     * It is important to ensure that the length parameter does not exceed the maximum possible length
     * for non-repeating digits (which is 9 excluding 0 for base-10 numbers).
     *
     * @param length The length of the non-repeating number to be generated.
     *               This dictates how many digits the generated number will have.
     * @return An integer of generated non-repeating number.
     *         The generated number will have a number of digits equal to the specified length and will
     *         contain unique, non-repeating digits.
     * @throws IllegalArgumentException if the length is more than 9 or less than 1.
     */
    val generateRandomNonRepeatingNumber: (Int) -> Int = { length ->
        if (length > 9 || length < 1) {
            throw IllegalArgumentException()
        }

        val digits = mutableListOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
        var number : String = ""
        while (number.length < length) {
            val idx = (0 until digits.size).random()
            number += digits.removeAt(idx)
        }
        number.toInt()
    }

    /**
     * Compares the user's input integer against a generated number for a guessing game.
     * This function evaluates how many digits the user guessed correctly and how many of those
     * are in the correct position. The game generates number with non-repeating digits.
     *
     * Note: The input and the generated number must both be numbers.
     * If the inputs do not meet these criteria, an IllegalArgumentException is thrown.
     *
     * @param input The user's input integer. It should be a number with non-repeating digits.
     * @param generatedNumber The generated number with non-repeating digits to compare against.
     * @return [CompareResult] with two properties:
     *         1. `n`: The number of digits guessed correctly (regardless of their position).
     *         2. `m`: The number of digits guessed correctly and in the correct position.
     *         The result is formatted as "Output: m:n", where "m" and "n" represent the above values, respectively.
     * @throws IllegalArgumentException if the inputs do not have the same number of digits.
     */
    val checkUserInputAgainstGeneratedNumber: (Int, Int) -> CompareResult = { input, generatedNumber ->
        val i = input.toString()
        val g = generatedNumber.toString()

        if (i.length != g.length){
            throw IllegalArgumentException()
        }

        var n = 0
        var m = 0


        for (idx in g.indices){
            if (g.contains(i[idx])){
                n++
                if(idx > 0){
                    if(i.slice(0 until idx).contains(i[idx])){
                        n--
                    }
                }
            }

            if (g[idx] == i[idx]){
                m++
            }
        }


        CompareResult(n, m)   // return value is a placeholder
    }
}

fun main() {
    println("Hello World!")
    // TODO: call the App.playNumberGame function with and without default arguments
    val app = App()
    println(app.playNumberGame())
}
