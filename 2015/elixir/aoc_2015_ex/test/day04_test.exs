defmodule Day04Test do
  use ExUnit.Case
  import Day04

  defp read_input do
    {:ok, contents} = File.read("input/day04.txt")
    contents |> String.trim()
  end

  test "part 1 solution" do
    assert 254_575 = part1_answer(read_input())
  end

  test "part 2 solution" do
    assert 1_038_736 = part2_answer(read_input())
  end
end
