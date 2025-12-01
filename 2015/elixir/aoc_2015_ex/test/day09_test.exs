defmodule Day09Test do
  use ExUnit.Case
  import Day09

  defp read_input do
    {:ok, contents} = File.read("input/day09.txt")

    contents
    |> String.split("\n")
  end

  test "part 1" do
    251 = read_input() |> solve_part1()
  end

  test "part 2" do
    898 = read_input() |> solve_part2()
  end
end
