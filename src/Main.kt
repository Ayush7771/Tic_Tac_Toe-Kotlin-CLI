import java.util.InputMismatchException
import java.util.Scanner

var gameState = true

fun main() {
    val grid = MutableList(3) {
        MutableList(3) { " " }
    }

    var inputCount = 0

    printGrids(grid)

    while (inputCount < 9 && gameState) {
        try {
            val scanner = Scanner(System.`in`)
            val x = scanner.nextInt()
            val y = scanner.nextInt()
            if (grid[x - 1][y - 1] == "X" || grid[x - 1][y - 1] == "O") {
                println("This cell is occupied! Choose another one!")
            } else {
                if (inputCount % 2 == 0) {
                    grid[x - 1][y - 1] = "X"

                    inputCount++
                } else {
                    grid[x - 1][y - 1] = "O"
                    inputCount++
                }

                printGrids(grid)
                checkResult(grid)
                if(!gameState) break
            }
        } catch (e: InputMismatchException) {
            println("You should enter numbers!")
        } catch (e: IndexOutOfBoundsException) {
            println("Coordinates should be from 1 to 3!")
        }
    }

    if (gameState && inputCount == 9) println("Draw")


}

fun printGrids(grid: MutableList<MutableList<String>>) {
    println("---------")
    for (i in grid.indices) {
        print("| ")
        for (j in grid[i].indices) {
            print("${grid[i][j]} ")
        }
        println("|")
    }
    println("---------")

}


fun checkResult(grid: MutableList<MutableList<String>>) {
    when {
        grid[0][0] != " " && grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2] -> {
            println("${grid[0][0]} wins")
            gameState = false
        }         //Top Horizontal Line Check
        grid[1][0] != " " && grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2] -> {
            println("${grid[1][0]} wins")
            gameState = false
        }         //Middle Horizontal Line Check
        grid[2][0] != " " && grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2] -> {
            println("${grid[2][0]} wins")
            gameState = false
        }         //Bottom Horizontal Line Check

        grid[0][0] != " " && grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0] -> {
            println("${grid[0][0]} wins")
            gameState = false
        }         //First Vertical Line Check
        grid[0][1] != " " && grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1] -> {
            println("${grid[0][1]} wins")
            gameState = false
        }         //Middle Vertical Line Check
        grid[0][2] != " " && grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2] -> {
            println("${grid[0][2]} wins")
            gameState = false
        }         //Last vertical Line Check

        grid[0][0] != " " && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] -> {
            println("${grid[0][0]} wins")
            gameState = false
        }         //Top Left to Bottom Right Diagonal Line Check
        grid[2][0] != " " && grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2] -> {
            println("${grid[2][0]} wins")
            gameState = false
        }         //Bottom Left to Top Right Diagonal Line Check

    }

}