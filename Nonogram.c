#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void printList(int *list, int n) {
	printf("\n");
	for (int i = 0; i < n; i++) {
		printf("%d", list[i]);
	}
	return;
}

void printPuzzle(int *puzzle, int n) {
	for (int i = 0; i < n*n; i++) {
		if (i%n == 0) {
			printf("\n");
		}
		if (puzzle[i]) {
			printf("x");
		} else {
			printf(" ");
		}
		printf(" ");
	}
	printf("\n");
	return;
}

void shiftFrom(int *list, int n, int from) {
	for (int i = n - 1; i > from; i--) {
		list[i] = list[i - 1];
	}
	list[from] = 0;
	return;
}

void shiftBack(int *list, int n, int from) {
    for (int i = from; i < n; i++) {
        list[i - 1] = list[i];
    }
    list[n - 1] = 0;
    return;
}

int nextBlock(int *list, int n, int i) {
	i += 2;
	while (i < n && list[i] != 1) {
		i++;
	}
	return i;
}

int findMargin(int *row, int n) {
	int margin = 0;
	while (row[n - margin - 1] == 0) {
		margin++;
	}
	return margin;
}

int *getCol(int *puzzle, int n, int coln) {
	int *col = calloc(n, sizeof(int));
	for (int i = 0; i < n; i++) {
		col[i] = puzzle[i*n + coln];
	}
	return col;
}

int equal(int *generated, int *check, int n) {
    int i = 0;
    int j = 0;
    while (i < n) {
        while (i < n && generated[i] == 0) {
            i++;
        }
        while (i < n && generated[i] != 0) {
            if (check[j] == 0) {
                return 0;
            }
            i++;
            j++;
        }
        if (j < n && check[j] != 0) {
            return 0;
        }
        j++;
    }
    return 1;
}

int minUntil(int *col, int n, int until) {
	int sum = 0;
	int i = n - 1;
	while (col[i] == 0 && until >= 0) {
		until--;
		i--;
	}
	i = 0;
	while (until >= 0) {
		sum += (col[i] > 0);
		i++;
		until--;
	}
	return sum;
}

int maxUntil(int *col, int n, int until) {
	int sum = 0;
	int i = 0;
	while (until >= 0) {
		sum += (col[i] > 0);
		i++;
		until--;
	}
	return sum;
}

void combinations(int *puzzle, int *cols, int n, int i, int margin, int row) {
	if (margin < 1 || i >= n - 1 || (i == 0 && puzzle[row*n] == 0)) {
		if (row == n - 1) {
			int *col;
			for (int j = 0; j < n; j++) {
				col = getCol(puzzle, n, j);
				if (!equal(col, cols + j*n, n)) {
					/*
					printPuzzle(puzzle, n);
					printf("col:\n");
					printList(col, n);
					printf("it went wrong at col %d", j);
					*/
					free(col);
					return;
				}
				free(col);
			}
			printPuzzle(puzzle, n);
		} else {
			int sum;
			for (int j = 0; j < n; j++) {
				sum = 0;
				for (int k = 0; k <= row; k++) {
					sum += (puzzle[k*n + j] > 0);
				}
				if (sum > maxUntil(cols + j*n, n, row) || sum < minUntil(cols + j*n, n, row)) {
					/*
					printf("--------\n");
					printPuzzle(puzzle, n);
					printf("\nwrong at col %d, max was %d, min was %d, sum was %d,  col was:", j, maxUntil(cols+j*n, n, row), minUntil(cols+j*n, n, row), sum);
					printList(cols + j*n, n);
					printf("\n");
					*/
					return;
				}
			}
			combinations(puzzle, cols, n, 0, findMargin(puzzle + (row + 1)*n, n), row + 1);
		}
		return;
	}	
	for (int j = 0; j <= margin; j++) {
		for (int k = 0; k < j; k++) {
			shiftFrom(puzzle + row*n, n, i);
			i++;
		}
		combinations(puzzle, cols, n, nextBlock(puzzle + row*n, n, i), margin - j, row);
		for (int k = 0; k < j; k++) {
			shiftBack(puzzle + row*n, n, i);
			i--;
		}
	}
	return;
}

void readRow(int *row,int n) {
	int block;
	scanf("%d", &block);
	char c = getchar();
	if (block == 0) {
		return;
	}
	int i = 1;
	row[0] = 1;
	for (; i < block; i++) {
		row[i] = 2;
	}
	row[i] = 0;
	i++;
	while (c == ',') {
		scanf("%d", &block);
		row[i] = 1;
		i++;
		for (; block > 1; block--) {
			row[i] = 2;
			i++;
		}
		row[i] = 0;
		i++;
		c = getchar();
	}
	return;
}

int *readPuzzle(int n) {
	int *puzzle = calloc(n*n, sizeof(int));
	printf("Type in the values of each row seperated by a comma.");
	printf("Start at the top.\n");
	for (int i = 0; i < n; i++) {
		printf("Row %d:", i + 1);
		readRow(puzzle + i*n, n);
	}
	return puzzle;
}

int *readCols(int n) {
	int *cols = calloc(n*n, sizeof(int));
	printf("Type in the values of each column seperated by a comma.");
	printf("Start at the left.\n");
	for (int i = 0; i < n; i++) {
		printf("Column %d:", i + 1);
		readRow(cols + i*n, n);
	}
	return cols;
}

int * transpose(int *matrix, int n) {
	/*Transposes a square matrix of size n*n*/
	int *transposed = calloc(n*n, sizeof(int));
	for (int i = 0; i < n*n; i++) {
		transposed[i] = matrix[(i%n)*n+i/n];
	}
	return transposed;
}
	

int main(int argc, char *argv[]) {
	int n;
	printf("Number of rows/columns:");
	scanf("%d", &n);
	getchar();
	int *puzzle, *cols;
	puzzle = readPuzzle(n);
	cols = readCols(n);
	int margin = findMargin(puzzle, n);
	combinations(puzzle, cols, n, 0, margin, 0);
	free(puzzle);
	return 0;
}
