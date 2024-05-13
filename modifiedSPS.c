#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    int playerChoice, computerChoice;
    int playerScore = 0, computerScore = 0;
    char choiceNames[3][10] = {"Rock", "Paper", "Scissors"};

    srand(time(NULL));

    printf("Let's play Rock, Paper, Scissors!\n");

    while(1) {
        printf("\nPlease choose (0 for Rock, 1 for Paper, 2 for Scissors, -1 to quit): ");
        scanf("%d", &playerChoice);

        if(playerChoice == -1)
            break;

        if(playerChoice < 0 || playerChoice > 2) {
            printf("Invalid choice. Please choose again.\n");
            continue;
        }

        computerChoice = rand() % 3;

        printf("You chose %s.\n", choiceNames[playerChoice]);
        printf("Computer chose %s.\n", choiceNames[computerChoice]);

        
        if(playerChoice == computerChoice) {
            printf("It's a draw!\n");
        } else if((playerChoice == 0 && computerChoice == 2) || 
                  (playerChoice == 1 && computerChoice == 0) ||
                  (playerChoice == 2 && computerChoice == 1)) {
            printf("You win!\n");
            playerScore++;
        } else {
            printf("Computer wins!\n");
            computerScore++;
        }

        printf("Score: You %d - %d Computer\n", playerScore, computerScore);
    }

    printf("Thanks for playing!\n");

    return 0;
}



