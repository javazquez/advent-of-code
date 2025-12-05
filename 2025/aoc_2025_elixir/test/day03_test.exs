defmodule Day03Test do
  use ExUnit.Case
  import Day03

  defp read_input() do
    {:ok, contents} = File.read("input/day03.txt")

    contents
    |> String.split("\n")
  end

  test "example" do
    bat_pack = ["987654321111111", "811111111111119", "234234234234278", "818181911112111"]

    assert 357 = part1(bat_pack)
    assert 3_121_910_778_619 = part2(bat_pack)
  end

  test "part1" do
    assert 17100 = read_input() |> part1()
  end

  test "part2" do
    assert 170_418_192_256_861 = read_input() |> part2()
  end
end
