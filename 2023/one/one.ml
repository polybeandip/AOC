let explode s = List.init (String.length s) (fun i -> String.get s i |> String.make 1) 

let rec calibration str fst lst = 
  match str with
  | [] -> 
    begin 
      match fst, lst with
      | Some f, Some l -> int_of_string (f ^ l)
      | Some f, None -> int_of_string (f ^ f) 
      | _ -> failwith "bad input"
    end
  | h :: t -> 
    if Str.string_match (Str.regexp "[0-9]") h 0 then
      let fst, lst = 
        begin
          match fst with 
            | Some _ -> fst, Some h 
            | None -> Some h, lst 
        end
      in calibration t fst lst
    else
      calibration t fst lst

let _ = 
  In_channel.input_lines (open_in "one.txt") 
    |> List.map explode 
    |> List.fold_left (fun acc x -> calibration x None None + acc) 0 
    |> string_of_int
    |> print_endline