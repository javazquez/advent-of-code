defmodule Day04Test do
  use ExUnit.Case
  import Day04

  defp read_input(fname) do
    {:ok, contents} = File.read("input/day04#{fname}.txt")

    contents
    |> String.split("\n")
  end

  test "example" do
    assert 13 = read_input("_example") |> part1()
  end

  test "part1" do
    assert 1587 = read_input("") |> part1()
  end

  @tag timeout: :infinity
  test "part2" do
    assert 43 = read_input("_example") |> part2()
    assert 8946 = read_input("") |> part2()
  end
end
