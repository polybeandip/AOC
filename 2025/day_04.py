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


def part1(data, matrix=None, rep=False):
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


def part2(data):
    matrix = [list(d) for d in data.split()]

    total = 0
    while True:
        count = part1(data, matrix, True)
        if count == 0:
            break
        total += count

    return total
