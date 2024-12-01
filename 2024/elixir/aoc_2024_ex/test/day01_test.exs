defmodule Day01Test do
  use ExUnit.Case
  import Day01
  doctest Day01

  # return two lists of numbers
  defp read_input do
    {:ok, contents} = File.read("input/day01.txt")

    contents
    |> String.split("\n", trim: true)
    |> Enum.map(fn s ->
      String.split(s)
      |> Enum.map(fn s ->
        Integer.parse(s)
        |> elem(0)
      end)
      |> List.to_tuple()
    end)
    |> Enum.unzip()
  end

  test "part 1 solution" do
    {a, b} = read_input()
    assert 2_344_935 = part1_answer(a, b)
  end

  test "part 2 solution" do
    {a, b} = read_input()
    assert 27_647_262 = part2_answer(a, b)
  end
end
