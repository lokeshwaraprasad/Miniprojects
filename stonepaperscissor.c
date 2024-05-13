#include<stdio.h>
#include<stdlib.h>
void guess(int op)
{
    int comp=1;
    if(op==comp)
    {
        printf("\nIT IS AN DRAW .YOU BOTH CHOSE PAPER");
    }
    else if(op==2)
    {
        printf("HOORAY!!YOU WON YOU WON");
    }
    else{
        printf("You lost..computer won");
    }
}
int main()
{
    printf("\nWELCOME TO THE STONE PAPPER SCISSOR GAME:");
    int n;
    int op;
    printf("\n Press 1.Paper\n2.scissor\n3.rock");
    printf("\nEnter the number of time you wanna play the game:");
    scanf("%d",&n);
    
    for(int i=0;i<n;i++)
    {
        printf("\nEnter the Choice:");
        scanf("%d",&op);
        guess(op);
        
    }
}