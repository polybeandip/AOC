from aocd import get_data

data = get_data(day=1, year=2025)


def part1():
    count = 0
    nob = 50
    cmds = data.split()

    for cmd in cmds:
        if nob == 0:
            count += 1
        if 'L' in cmd:
            nob = (nob - int(cmd[1:])) % 100
        else:
            nob = (nob + int(cmd[1:])) % 100

    return count


def part2():
    count = 0
    nob = 50
    cmds = data.split()

    for i, cmd in enumerate(cmds):
        r = int(cmd[1:])
        if 'L' in cmd:
            x = nob - int(cmd[1:])
        else:
            x = nob + int(cmd[1:])

        if nob == 0:
            t = r // 100
            count += t
        elif x >= 100:
            t = x // 100
            count += t
        elif x <= 0:
            t = ((-x) // 100) + 1
            count += t

        nob = x % 100

    return count


p1 = part1()
p2 = part2()

print(p1)
print(p2)
