#include <stdio.h>

// Function to calculate total number of vehicles
int calculateTotal(int roads[])
{
    int total = 0;

    for (int i = 0; i < 4; i++)
    {
        total += roads[i]; 
    }

    return total;
}

// Function to find the busiest road
int findBusiestRoad(int roads[])
{
    int maxIndex = 0;

    for (int i = 1; i < 4; i++)
    {
        if (roads[i] > roads[maxIndex])
        {
            maxIndex = i;
        }
    }

    return maxIndex;
}

// Function to check traffic congestion
void checkCongestion(int roads[])
{
    for (int i = 0; i < 4; i++)
    {
        if (roads[i] > 50)
        {
            printf("Congestion Alert: Road %d has heavy traffic!\n", i + 1);
        }
    }
}

int main()
{
    int roads[4];
    int total, busiest;

    // Input vehicle count for each road
    for (int i = 0; i < 4; i++)
    {
        printf("Enter vehicles on Road %d: ", i + 1);
        scanf("%d", &roads[i]);
    }

    // Call functions
    total = calculateTotal(roads);
    busiest = findBusiestRoad(roads);

    // Display report
    printf("\n--- Traffic Analysis Report ---\n");
    printf("Total Vehicles: %d\n", total);
    printf("Busiest Road: Road %d\n", busiest + 1);
    printf("Recommended Green Signal: Road %d\n", busiest + 1);

    // Check congestion
    checkCongestion(roads);

    return 0;
}
