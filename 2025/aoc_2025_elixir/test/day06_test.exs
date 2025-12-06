defmodule Day06Test do
  use ExUnit.Case
  import Day06

  defp read_input(fname) do
    {:ok, contents} = File.read("input/day06#{fname}.txt")
    contents
  end

  test "example" do
    assert 4_277_556 =
             read_input("_example")
             |> format_prob1()
             |> part1()
  end

  test "part1" do
    assert 5_346_286_649_122 =
             read_input("")
             |> format_prob1()
             |> part1()
  end

  test "example 2" do
    assert 3_263_827 = read_input("_example") |> format_prob2() |> part2()
  end

  test "part 2" do
    assert 10_389_131_401_929 = read_input("") |> format_prob2() |> part2()
  end
end
