defmodule Day01Test do
  use ExUnit.Case
  import Day01
  doctest Day01

  defp read_input do
    {:ok, contents} = File.read("input/day01.txt")
    contents |> String.trim() |> String.to_charlist()
  end

  test "part 1 solution" do
    assert 280 = part1_answer(read_input())
  end

  test "part 2 solution" do
    assert 1797 = part2_answer(read_input())
  end
end
