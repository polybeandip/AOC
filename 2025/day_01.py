def part1(data):
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


def part2(data):
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
