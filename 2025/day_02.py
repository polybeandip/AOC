from math import floor, ceil
from aocd import get_data

data = get_data(day=2, year=2025)


def invalid_range_sum(l, r, ls, rs, d):
    assert (ls == rs)
    assert (ls % d == 0)
    n = ls / d

    f = (1 - (10**d)**n) / (1 - 10**d)

    lb = ceil(l / f)
    rb = floor(r / f)

    x = (rb - lb + 1) * (rb + lb) / 2
    y = x * f

    return y


def part1():
    ranges = data.split(",")
    sum = 0
    for range in ranges:
        [l, r] = range.split("-")
        ls = len(l)
        rs = len(r)
        if ls % 2 == 1 and rs % 2 == 1:
            continue
        l = int(l)
        r = int(r)
        if ls % 2 == 1:
            l = int('1' + ('0' * ls))
            ls += 1
        if rs % 2 == 1:
            r = int('9' * (rs - 1))
            rs -= 1

        sum += invalid_range_sum(l, r, ls, rs, ls / 2)

    return int(sum)


def part2():
    ranges = data.split(",")
    sum = 0
    for idrange in ranges:
        [l, r] = idrange.split("-")
        ls, rs = len(l), len(r)
        l, r = int(l), int(r)

        if ls == rs:
            bounds = [(l, r, ls, rs)]
        else:
            shift_l = int('1' + ('0' * ls))
            shift_r = int('9' * (rs - 1))

            bounds = [
                (shift_l, r, ls + 1, rs),
                (l, shift_r, ls, rs - 1)
            ]

        for (l, r, ls, rs) in bounds:
            done = []
            for d in range((ls // 2), 0, -1):
                if (ls % d != 0):
                    continue

                for o in done:
                    if o % d == 0:
                        break
                else:
                    sum += invalid_range_sum(l, r, ls, rs, d)

                done.append(d)

            if (ls == 10 or ls == 6):
                sum -= invalid_range_sum(l, r, ls, rs, 1)

    return int(sum)


p1 = part1()
p2 = part2()
print(p1)
print(p2)
