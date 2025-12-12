import sys
import math
from aocd import get_data, submit

data = get_data(day=6, year=2025)


def do_fold(fold, op):
    match op:
        case '*':
            return math.prod(fold)
        case '+':
            return sum(fold)

    raise RuntimeError(f"Invalid op={op}")


def part1():
    lines = data.split('\n')

    nums = [[] for _ in range(len(lines[0]))]
    for line in lines[:-1]:
        for i, num in enumerate(line.split()):
            nums[i].append(int(num))

    total = 0
    for i, c in enumerate(lines[-1].split()):
        total += do_fold(nums[i], c)

    return total


def part2():
    lines = data.split('\n')
    nums, ops = lines[:-1], lines[-1].split()

    N = len(nums)
    total = 0

    indices = [0 for _ in range(N)]
    fold    = []
    opdex   = 0
    while True:
        if opdex >= len(ops):
            break

        op = ops[opdex]

        curr = ""
        for i in range(N):
            c = nums[i][indices[i]] if indices[i] < len(nums[i]) else ' '
            if c != ' ':
                curr += c
            indices[i] += 1

        if curr == "":
            total += do_fold(fold, op)
            opdex += 1
            fold   = []
        else:
            fold.append(int(curr))

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
