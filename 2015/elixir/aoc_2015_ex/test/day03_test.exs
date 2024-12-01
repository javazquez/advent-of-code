defmodule Day03Test do
  use ExUnit.Case
  import Day03
  doctest Day03

  defp read_input do
    {:ok, contents} = File.read("input/day03.txt")
    contents |> String.trim() |> String.to_charlist()
  end

  test "part 1 solution" do
    assert 2565 = part1_answer(read_input())
  end

  test "part 2 solution" do
    assert 2639 = part2_answer(read_input())
  end
end
