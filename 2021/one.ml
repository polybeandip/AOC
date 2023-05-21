let parse ch = 
  let rec parse_aux out =
    match In_channel.input_line ch with 
    | Some x -> parse_aux (out @ [x])
    | None -> out
  in
  parse_aux []

let input = parse (open_in "one.txt") |> List.map int_of_string

let part1 =
  let rec helper lst sum =
    match lst with
    | a :: b :: t -> if a < b then helper (b :: t) (sum + 1) else helper (b :: t) sum
    | _ -> sum
  in
  helper input 0

let part2 =
  let rec helper lst sum =
    match lst with
    | a :: b  :: c  :: d :: t -> if a < d then helper (b :: c :: d :: t) (sum + 1) else helper (b :: c :: d :: t) sum
    | _ -> sum
  in
  helper input 0

let () =
  print_endline (part1 |> string_of_int);
  print_endline (part2 |> string_of_int)
