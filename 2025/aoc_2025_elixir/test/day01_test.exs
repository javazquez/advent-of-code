defmodule Day01Test do
  use ExUnit.Case
  import Day01

  defp read_input(fname) do
    {:ok, contents} = File.read("input/day01#{fname}.txt")

    contents
    |> String.split("\n")
    |> parse_input()
  end

  test "example" do
    assert 3 = part1(read_input("_example"), 50)
  end

  test "example 2" do
    assert {82, 1} = rotate({:left, 68}, 50)
    assert {52, 0} = rotate({:left, 30}, 82)
    assert {0, 0} = rotate({:right, 48}, 52)
    assert {95, 0} = rotate({:left, 5}, 0)
    assert {55, 1} = rotate({:right, 60}, 95)
    assert {0, 0} = rotate({:left, 55}, 55)
    assert {99, 0} = rotate({:left, 1}, 0)
    assert {0, 0} = rotate({:left, 99}, 99)
    assert {14, 0} = rotate({:right, 14}, 0)
    assert {32, 1} = rotate({:left, 82}, 14)

    assert 6 = part2(read_input("_example"), 50)
  end

  test "Part 1" do
    assert 1105 = part1(read_input(""), 50)
  end

  test "Part 2" do
    assert 6599 = part2(read_input(""), 50)
  end
end
