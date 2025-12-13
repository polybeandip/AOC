#!/usr/bin/env python
import argparse
import importlib
from aocd import get_data, submit

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('day', type=int)
    parser.add_argument('-s', '--submit', type=int, help="day to submit")
    parser.add_argument('--sample', action='store_true', help="use sample data")
    args = parser.parse_args()

    if args.submit is not None and args.submit not in [1, 2]:
        parser.error(f"Invalid part {args.submit}! -s/--submit must be 1 or 2")

    try:
        if args.day < 10:
            mod = f"day_0{args.day}"
        else:
            mod = f"day_{args.day}"
        day = importlib.import_module(mod)
    except ModuleNotFoundError:
        parser.error(f"Day {args.day} unimplemented")

    if args.sample:
        with open('sample.txt', 'r') as sample:
            data = sample.read()
    else:
        data = get_data(day=args.day, year=2025)

    p1, p2 = day.part1(data), day.part2(data)
    print("Not Done" if p1 is None else p1)
    print("Not Done" if p2 is None else p2)

    if args.submit == 1:
        submit(p1)
    if args.submit == 2:
        submit(p2)
