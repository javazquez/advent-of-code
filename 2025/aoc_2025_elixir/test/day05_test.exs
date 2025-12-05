defmodule Day05Test do
  use ExUnit.Case
  import Day05

  defp read_input(fname) do
    {:ok, contents} = File.read("input/day05#{fname}.txt")

    [ranges, ids] =
      contents |> String.split("\n\n") |> Enum.map(fn line -> String.split(line, "\n") end)

    r =
      ranges
      |> Enum.map(fn s ->
        String.split(s, "-")
        |> Enum.map(fn s2 -> String.to_integer(s2) end)
      end)

    [r, ids |> Enum.map(&String.to_integer/1)]
  end

  test "example" do
    assert 3 =
             read_input("_example")
             |> part1()
  end

  test "part1" do
    assert 643 =
             read_input("")
             |> part1()
  end

  test "part2 example" do
    assert 14 =
             read_input("_example")
             |> part2()
  end

  test "part2 " do
    assert 342_018_167_474_526 =
             read_input("")
             |> part2()
  end
end
