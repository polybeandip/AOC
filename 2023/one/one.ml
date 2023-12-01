let explode s = 
  List.init (String.length s) (fun i -> String.get s i |> String.make 1) 

let digits = 
  [
    ("one", 1); 
    ("two", 2); 
    ("three", 3); 
    ("four", 4); 
    ("five", 5); 
    ("six", 6); 
    ("seven", 7); 
    ("eight", 8); 
    ("nine", 9)
  ]

let rec grab acc n lst =
  match n, lst with
    | 0, _ -> acc
    | _, h :: t -> grab (acc ^ h) (n - 1) t
    | _ -> ""

let get_num lst = match lst with 
  | h :: _ when Str.string_match (Str.regexp "[0-9]") h 0 -> Some (int_of_string h)
  | _ -> 
    let three, four, five = grab "" 3 lst, grab "" 4 lst, grab "" 5 lst in
    let f x = List.assoc_opt x digits in
    begin
      match f three, f four, f five with
        | Some x, None, None 
        | None, Some x, None 
        | None, None, Some x -> Some x
        | None, None, None -> None
        | _ -> failwith "bad input"
    end

let rec calibration str fst lst = 
  match str with
  | [] -> 
    begin 
      match fst, lst with
        | Some f, Some l -> f*10 + l
        | Some f, None -> f*10 + f
        | _ -> failwith "bad input"
    end
  | _ :: t -> 
    begin
      match get_num str with
        | Some x ->
          let fst, lst = 
            begin
              match fst with 
                | Some _ -> fst, Some x
                | None -> Some x, lst 
            end
          in calibration t fst lst
        | None -> calibration t fst lst
    end 

let _ = 
  In_channel.input_lines (open_in "one.txt") 
    |> List.map explode 
    |> List.fold_left (fun acc x -> calibration x None None + acc) 0 
    |> string_of_int
    |> print_endline