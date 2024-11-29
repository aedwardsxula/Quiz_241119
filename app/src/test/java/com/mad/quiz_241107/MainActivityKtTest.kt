package com.mad.quiz_241107

import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class MainActivityKtTest {

    @Test
    fun generateRandoms() {
        val nums = generateRandoms(5)
        Assert.assertEquals("The list should have 5 elements.",5, nums.size)
        Assert.assertEquals("The list shouldn't have duplicates.", 5, nums.toSet().size)
    }

    @Test
    fun maxConsecutivePassingScores() {
        var scores = (80..100).toMutableList()
        Assert.assertEquals("Entire len if all strictly increasing values", 21, scores.size)

        scores[0] = scores[1]
        Assert.assertEquals("First is duplicate in otherwise strictly increasing", 20, com.mad.quiz_241107.maxConsecutivePassingScores(scores))

        scores[scores.size - 2] = scores[scores.size - 1]
        Assert.assertEquals("First and last are duplicates in otherwise strictly increasing", 19, com.mad.quiz_241107.maxConsecutivePassingScores(scores))
    }
}