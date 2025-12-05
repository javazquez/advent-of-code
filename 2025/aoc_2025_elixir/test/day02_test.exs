defmodule Day02Test do
  use ExUnit.Case
  import Day02

  defp read_input() do
    {:ok, contents} = File.read("input/day02.txt")

    contents
    |> String.split(",")
    |> Enum.map(fn line -> String.split(line, "-") end)
    |> Enum.map(fn [s, e] ->
      String.to_integer(s)..String.to_integer(e)
      |> Enum.to_list()
    end)
  end

  test "example" do
    tin =
      "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
      |> String.split(",")
      |> Enum.map(fn line -> String.split(line, "-") end)
      |> Enum.map(fn [s, e] ->
        String.to_integer(s)..String.to_integer(e)
        |> Enum.to_list()
      end)

    assert 1_227_775_554 = tin |> part1()
    assert 4_174_379_265 = tin |> part2()
  end

  test "part1" do
    assert 13_108_371_860 = read_input() |> part1()
  end

  test "part2" do
    assert 22_471_660_255 = read_input() |> part2()
  end
end
