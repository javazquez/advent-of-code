# Juan Vazquez
# http://javazquez.com
# http://adventofcode.com/2016/day/1
defmodule Day01Test do
  use ExUnit.Case
  doctest Day01

  test "first example" do
    {blocks , _} = Day01.distance(["R2","R3"]) 
    assert blocks == 5
  end    

  test "second example" do
    {blocks , _} = Day01.distance(["R2", "R2", "R2"])
      assert  blocks == 2
  end

  test "third example" do
    {blocks, _ } =  Day01.distance(["R5", "L5", "R5", "R3"])
      assert blocks == 12
  end

  test "read the input file for Part 1" do
    {:ok, file_text}  = File.read "test/support/day_01_input.txt"
    input_list = file_text 
      |> String.split(",") 
      |> Enum.map(&String.trim/1)
      {blocks, _ } = Day01.distance(input_list ) 
    assert blocks == 246
  end

  test "example input for part 2" do
    {_blocks, all_coords} = Day01.distance(["R8", "R4", "R4", "R8"])
      sorted_coords = Enum.reverse(all_coords)
        |> Enum.map(fn {direction, x, y} -> {x, y}  end)
       uniq= Enum.uniq(sorted_coords)
       {x_coor, y_coor} = (sorted_coords -- uniq) |> List.first
       {blocks, _} = Day01.distance([], [{:dontcare, x_coor, y_coor }])
       assert blocks == 4
  end

  test "read the input file for Part 2" do
    {:ok, file_text}  = File.read "test/support/day_01_input.txt"
    input_list = file_text 
      |> String.split(",") 
      |> Enum.map(&String.trim/1)
    {blocks, all_coords } = Day01.distance(input_list)
    sorted_coords = Enum.reverse all_coords
        |> Enum.map(fn {direction, x, y} -> {x, y}  end)
    uniq= Enum.uniq(sorted_coords)
    {x_coor, y_coor} = (sorted_coords -- uniq) |> List.first
    {blocks, _} = Day01.distance([], [{:dontcare, x_coor, y_coor }])
       
    assert blocks == 124
  end
end

