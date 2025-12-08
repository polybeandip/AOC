import sys
from aocd import get_data, submit

data = get_data(day=3, year=2025)


def find_max_jolt(banks, num_digits=2):
    def find_max_rem(digits):
        max = None
        idx = None
        for i in range(num_digits):
            others = [b for j, b in enumerate(digits) if j != i]
            can = int("".join(others))

            if max is None or max < can:
                max = can
                idx = i

        return idx

    max = banks[-num_digits:]
    top = max[0][0]

    for b in reversed(banks[:-num_digits]):
        if b >= top:
            r = find_max_rem(max)
            del max[r]
            max.insert(0, b)
            top = b

    return int("".join([p for p in max]))


def sum_max_jolts(bats, num_digits):
    sum = 0
    for b in bats:
        banks = list(b)
        mj = find_max_jolt(banks, num_digits)
        sum += mj

    return sum


def part1():
    return sum_max_jolts(data.split(), 2)


def part2():
    return sum_max_jolts(data.split(), 12)


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
