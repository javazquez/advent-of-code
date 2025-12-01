defmodule Day08Test do
  use ExUnit.Case
  import Day08

  defp read_input do
    {:ok, contents} = File.read("input/day08.txt")

    contents
    |> String.split("\n", trim: true)
  end

  test "examples" do
    0 = String.length("")
    3 = String.length("abc")
    7 = String.length("aaa\"aaa")
    1 = String.length("\x27")
  end

  test "part 1" do
    1371 =
      read_input()
      |> solve_part1()
  end

  test "part 2" do
    2117 =
      read_input()
      |> solve_part2()
  end
end
