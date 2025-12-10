import sys
from aocd import get_data, submit

data = get_data(day=4, year=2025)


def get(matrix, i, j):
    if i < 0 or i >= len(matrix):
        return '.'
    if j < 0 or j >= len(matrix[0]):
        return '.'

    return matrix[i][j]


def check(matrix, i, j):
    if get(matrix, i, j) != '@':
        return False

    count = 0
    for dx in [-1, 0, 1]:
        for dy in [-1, 0, 1]:
            if dx == 0 and dy == 0:
                continue

            if get(matrix, i + dx, j + dy) == '@':
                count += 1

    return count < 4


def part1(matrix=None, rep=False):
    if matrix is None:
        matrix = data.split()

    count = 0
    for i in range(len(matrix)):
        for j in range(len(matrix[0])):
            if check(matrix, i, j):
                if rep:
                    matrix[i][j] = '.'
                count += 1

    return count


def part2():
    matrix = [list(d) for d in data.split()]

    total = 0
    while True:
        count = part1(matrix, True)
        if count == 0:
            break
        total += count

    return total


p1 = part1()
p2 = part2()
print("Not Done" if p1 is None else p1)
print("Not Done" if p2 is None else p2)

for i, c in enumerate(sys.argv):
    n = None if i == len(sys.argv) - 1 else sys.argv[i + 1]
    if c == "-s" and n == "1":
        submit(p1)
    if c == "-s" and n == "2":
        submit(p2)
