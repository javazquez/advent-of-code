defmodule Day02Test do
  use ExUnit.Case
  import Day02
  doctest Day02

  defp read_input do
    {:ok, contents} = File.read("input/day02.txt")

    contents
    |> String.split("\n")
    |> Enum.map(fn row ->
      String.split(row, "x")
      |> Enum.map(&Integer.parse/1)
    end)
  end

  test "part 1 solution" do
    assert 1_586_300 = part1_answer(read_input())
  end

  test "part 2 solution" do
    assert 3_737_498 = part2_answer(read_input())
  end
end
