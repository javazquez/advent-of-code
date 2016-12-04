# Juan Vazquez
# http://javazquez.com
# http://adventofcode.com/2016/day/2
defmodule Day02Test do
  use ExUnit.Case
  doctest Day02

  test "one line of commmands " do
    pad = [ [0,0,0,0,0],
            [0,1,2,3,0],
            [0,4,5,6,0],
            [0,7,8,9,0],
            [0,0,0,0,0]
            ]
    input = String.graphemes "ULL"
    {button, _coord} = Day02.push_buttons(input, pad,[2,2])            
    assert button == 1            
  end

   

  test "first example input" do
    pad =[[0,0,0,0,0],
          [0,1,2,3,0],
          [0,4,5,6,0],
          [0,7,8,9,0],
          [0,0,0,0,0]
          ]           
    [h|t] = "ULL\r\n
      RRDDD\r\n
      LURDL\r\n
      UUUUD"
      |> String.split(~r{\r\n})
      |> Enum.map(&String.trim/1) 
      |> Enum.map(&String.graphemes/1)
      primer = Day02.push_buttons(h, pad, [2,2])

    b_lst =  Enum.reduce(t,[primer],fn command_str,  [{_pos,coords} | _rst] = acc ->
        {key_pos,crds} =  Day02.push_buttons(command_str, pad, coords)
        [{key_pos, crds}] ++ acc
      end)
    |> Enum.reverse
    |> Enum.map_join(fn {key, _coords} -> key end)
    
    assert  b_lst == "1985"
  end

   test "solution 1 with file input" do
    pad =[[0,0,0,0,0],
          [0,1,2,3,0],
          [0,4,5,6,0],
          [0,7,8,9,0],
          [0,0,0,0,0]
          ] 

    {:ok, file_text}  = File.read "test/support/day_02_input.txt"
    [h| t] = file_text 
      |> String.split(~r{\n}) 
      |> Enum.map(&String.trim/1)
      |> Enum.map(&String.graphemes/1)
    primer = Day02.push_buttons(h, pad, [2,2])

    b_lst =  Enum.reduce(t,[primer], fn command_str,  [{_pos,coords} | _rst] = acc ->
      {key_pos,crds} =  Day02.push_buttons(command_str, pad, coords)
      [{key_pos, crds}] ++ acc
      end)
    |> Enum.reverse
    |> Enum.map_join(fn {key, _coords} -> key end)
     
    assert  b_lst == "76792" 
   end 


   test "new pad for solution 2 with input from example" do
    [h|t] = ["ULL","RRDDD","LURDL","UUUUD"] |> Enum.map(&String.graphemes/1)
    pad = [[0,  0,  0,  0,   0,  0, 0],
            [0,  0,  0,  1,   0,  0, 0],
            [0,  0,  2,  3,   4,  0, 0],
            [0,  5,  6,  7,   8,  9, 0],
            [0,  0, "A","B", "C", 0, 0],
            [0,  0,  0, "D",  0,  0, 0],
            [0,  0,  0,  0,   0,  0, 0]]
    primer = Day02.push_buttons(h, pad, [1,3])
    ans = Enum.reduce(t,[primer], fn command_str,  [{_pos,coords} | _rst] = acc ->
            {key_pos,crds} =  Day02.push_buttons(command_str, pad, coords)
            [{key_pos, crds}] ++ acc
        end)
      |> Enum.reverse
      |> Enum.map_join(fn {key, _coords} -> key end)  
      
    assert ans == "5DB3"
   end
   
   test "solution 2 from file text" do
    {:ok, file_text}  = File.read "test/support/day_02_input.txt"
    [h|t] = file_text 
    |> String.split(~r{\n}) 
    |> Enum.map(&String.trim/1) 
    |> Enum.map(&String.graphemes/1)
   pad = [[0,  0,  0,  0,   0,  0, 0],
          [0,  0,  0,  1,   0,  0, 0],
          [0,  0,  2,  3,   4,  0, 0],
          [0,  5,  6,  7,   8,  9, 0],
          [0,  0, "A","B", "C", 0, 0],
          [0,  0,  0, "D",  0,  0, 0],
          [0,  0,  0,  0,   0,  0, 0]]
   primer = Day02.push_buttons(h, pad, [1,3])
   ans = Enum.reduce(t,[primer], fn command_str,  [{_pos,coords} | _rst] = acc ->
        {key_pos,crds} =  Day02.push_buttons(command_str, pad, coords)
        [{key_pos, crds}] ++ acc
      end)
    |> Enum.reverse
    |> Enum.map_join(fn {key, _coords} -> key end)  

   assert ans == "A7AC3"
   end
  end