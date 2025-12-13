def get_intervals(inter_str):
    intervals = []
    for inter in inter_str.split():
        interval = [int(i) for i in inter.split('-')]
        intervals.append(interval)

    return intervals


def part1(data):
    [inter_str, ing_str] = data.split('\n\n')
    intervals = get_intervals(inter_str)

    count = 0
    for ing in ing_str.split():
        ingredient = int(ing)
        for [l, r] in intervals:
            if l <= ingredient and ingredient <= r:
                count += 1
                break

    return count


def part2(data):
    inter_str = data.split('\n\n')[0]
    intervals = get_intervals(inter_str)

    intervals.sort(key=lambda p: p[0])

    prev_right = None

    count = 0
    for [l, r] in intervals:
        if prev_right is None or prev_right <= l:
            dc = r - l + 1
            count += dc
            prev_right = r + 1
        elif l < prev_right and prev_right <= r:
            dc = r - prev_right + 1
            count += dc
            prev_right = r + 1

    return count
