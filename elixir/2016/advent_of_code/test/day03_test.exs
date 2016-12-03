# Juan Vazquez
# http://javazquez.com
# http://adventofcode.com/2016/day/3

defmodule Day03Test do
  use ExUnit.Case
  doctest Day03
  test "first example input" do
      assert  Day03.is_valid_triangle?(5, 10, 25) == false
  end

  test "read input file for solution 1" do
      {:ok, file_text}  = File.read "test/support/day_03_input.txt"
      assert String.split(file_text, "\n") 
          |> Enum.map(&String.trim/1) 
          |>  Enum.map(fn line -> String.split(line, ~r{\s+}) end )  
          |> Enum.map(fn row -> 
              [{a,_}, {b, _}, {c, _}] = Enum.map(row, &Integer.parse/1)
              {a, b, c, Day03.is_valid_triangle?(a,b,c)}  
          end)
          |> Enum.filter(fn {_a, _b, _c, bool} -> bool end) 
          |> Enum.count == 983
      end

  test "read input file for solution 2" do
      {:ok, file_text}  = File.read "test/support/day_03_input.txt"
      assert String.split(file_text, "\n")
      |> Enum.map(&String.trim/1) 
      |> Enum.map(fn line -> String.split(line, ~r{\s+}) end )
      |> Enum.map(fn row -> [{a,_}, {b, _}, {c, _}] = Enum.map(row, &Integer.parse/1) # convert to string and return list
              [a, b, c] 
          end)
      |> Enum.chunk(3) # partition into groups of 3 
      |> Enum.flat_map(&List.zip/1) # create lists from the columns via zip! 
      |> Enum.filter( fn {a,b,c} -> Day03.is_valid_triangle?(a,b,c) end ) 
      |> Enum.count == 1836
  end
end